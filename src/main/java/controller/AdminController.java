package controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import exception.ProjectException;
import logic.Board;
import logic.Mail;
import logic.Member;
import logic.ProjectService;
import logic.TransactionHistory;

@Controller
public class AdminController {

	@Autowired
	private ProjectService service;

	// 관리자페이지 기본 요청 메서드 : 관리자 유효성 검증은 LoginAspect에서 처리할 예정
	@RequestMapping(value = "admin/adminManagement", method = RequestMethod.GET)
	public ModelAndView adminManagementPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		List<Member> hostRegList = service.hostRegList(); // host 등록 요청 목록
		List<Board> guestQuestionList = service.guestQuestionList(); // guest 문의 목록
		List<Board> hostQuestionList = service.hostQuestionList(); // host 문의 목록
		List<TransactionHistory> transHostList = service.hostTransHistoryList("first");// host 공간 별 월별 수입 목록

		mav.addObject("hRegList", hostRegList);
		mav.addObject("gList", guestQuestionList);
		mav.addObject("hList", hostQuestionList);
		mav.addObject("thList", transHostList);

		return mav;
	}

	// 관리자가 총 회원 정보를 확인할 때 사용되는 메서드 (=회원리스트 확인)
	@RequestMapping(value = "admin/adminMemberList", method = RequestMethod.GET)
	public ModelAndView adminMemberList(HttpSession session, String searchType, String searchContent, String startDate,
			String endDate, Integer limit, Integer pageNum) {

		ModelAndView mav = new ModelAndView();

		if (pageNum == null || ("" + pageNum).equals("")) {
			pageNum = 1;
			limit = 30;
		}

		int listCnt = service.getMemberCnt(searchType, searchContent, startDate, endDate, pageNum, limit);
		List<Member> memList = service.getMemberList(searchType, searchContent, startDate, endDate, pageNum, limit);

		int maxpage = (int) ((double) listCnt / limit + 0.95);
		int startpage = (((int) (pageNum / 10.0 + 0.9)) - 1) * 10 + 1;
		int endpage = startpage + 9;

		if (endpage > maxpage) {
			endpage = maxpage;
		}

		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage ", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("listCnt", listCnt);
		mav.addObject("list", memList);

		if (searchType != null && searchType.equals("")) {
			mav.addObject("searchType", searchType);
		}

		return mav;
	}

	// 관리자가 총 Host 회원 정보를 확인할 때 사용되는 메서드 (=host 리스트 확인)
	@RequestMapping(value = "admin/adminHostList", method = RequestMethod.GET)
	public ModelAndView adminHostList(HttpSession session, String searchType, String searchContent, String startDate,
			String endDate, Integer limit, Integer pageNum) {

		ModelAndView mav = new ModelAndView();

		if (pageNum == null || ("" + pageNum).equals("")) {
			pageNum = 1;
			limit = 30;
		}

		int listCnt = service.getHostCnt(searchType, searchContent, startDate, endDate, pageNum, limit);
		List<Member> memList = service.getHostList(searchType, searchContent, startDate, endDate, pageNum, limit);

		int maxpage = (int) ((double) listCnt / limit + 0.95);
		int startpage = (((int) (pageNum / 10.0 + 0.9)) - 1) * 10 + 1;
		int endpage = startpage + 9;

		if (endpage > maxpage) {
			endpage = maxpage;
		}

		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage ", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("listCnt", listCnt);
		mav.addObject("list", memList);

		if (searchType != null && searchType.equals("")) {
			mav.addObject("searchType", searchType);
		}

		return mav;
	}

	// Host계정 전환 요청 전처리 전 확인 위해 보여질 페이지
	@RequestMapping(value = "admin/adminHostRegDetail", method = RequestMethod.GET)
	public ModelAndView adminHostRegDetailView(HttpSession session, String id) {

		ModelAndView mav = new ModelAndView();
		Member hostMember = service.getMember(id);
		mav.addObject("hostMem", hostMember);

		return mav;
	}

	// Host계정 전환 요청 작업을 처리할 때 호출 되는 메서드
	@RequestMapping(value = "admin/adminHostRegister", method = RequestMethod.GET)
	public ModelAndView adminHostRegister(HttpSession session, String id) {
		ModelAndView mav = new ModelAndView();

		service.hostRegister(id); // host 계정으로 전환

		mav.setViewName("redirect:/admin/adminManagement.sms");

		return mav;
	}

	// admin문의에 대한 답변 작업을 처리할 때 호출 되는 메서드(작성 전)
	@RequestMapping(value = "admin/adminAnswerQuestion", method = RequestMethod.GET)
	public ModelAndView adminAnswerQuestion(HttpSession session, Integer bNo) {
		ModelAndView mav = new ModelAndView();

		Board board = service.getBoard(bNo); // 문의글 객체
		Board answerBoard = new Board(); // 작성할 답변글 객체

		mav.addObject("board", board);
		mav.addObject("answerBoard", answerBoard);
		return mav;
	}

	// admin문의에 대한 답변 작업을 처리할 때 호출 되는 메서드(작성완료)
	@RequestMapping(value = "admin/adminAnswerQuestion", method = RequestMethod.POST)
	public ModelAndView adminAnswerQuestionWrite(Board answerBoard, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		Board board = service.getBoard(answerBoard.getbNo());
		answerBoard.setId(board.getId());
		answerBoard.setKind(board.getKind());

		service.boardReply(answerBoard);

		mav.addObject("msg", "답변이 성공적으로 달렸습니다.");
		
		if(answerBoard.getKind() == 4) {
			mav.addObject("url", "Glist.sms?id=admin");
		}
		
		if(answerBoard.getKind() == 5) {
			mav.addObject("url", "Hlist.sms?id=admin");
		}
		
		mav.setViewName("admin/alert");

		return mav;
	}

	// 거래관리대장 페이지를 처리할 때 호출 되는 메서드 (GET)
	@RequestMapping(value = "admin/adminTransHostList", method = RequestMethod.GET)
	public ModelAndView adminTransHostList(HttpSession session) {

		ModelAndView mav = new ModelAndView();

		List<TransactionHistory> transHostList = service.hostTransHistoryList("second");// host 공간 별 월별 수입 목록

		mav.addObject("thList", transHostList);

		return mav;
	}

	// 거래관리대장 페이지를 처리할 때 호출 되는 메서드 (POST)
	@RequestMapping(value = "admin/adminTransHostList", method = RequestMethod.POST)
	public ModelAndView adminSearchTransList(HttpSession session, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		String searchType = request.getParameter("searchType");
		String searchContent = request.getParameter("searchContent");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		List<TransactionHistory> transHostList = service.searchTransHistoryList(searchType, searchContent, startDate,
				endDate); // 거래관리대장 검색용

		mav.addObject("thList", transHostList);

		return mav;
	}

	// 회원 ID 체크한 후 메일 작성 폼으로 이동
	@RequestMapping(value = "admin/adminMailForm", method = RequestMethod.POST)
	public ModelAndView adminMailForm(HttpSession session, String[] idchks) {

		ModelAndView mav = new ModelAndView("admin/adminMailWrite");

		if (idchks == null || idchks.length == 0) {
			throw new ProjectException("메일을 보낼 대상자를 선택하세요.", "adminMemberList.sms");
		}

		List<Member> list = service.selectMemberList(idchks);
		mav.addObject("memberList", list);

		return mav;
	}

	@RequestMapping(value = "admin/mailSend", method = RequestMethod.POST) // 메일 전송을 눌렀을 때 호출되는 메서드
	public ModelAndView adminMailSend(Mail mail, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/mailSuccess");

		mailSendExcutor(mail, request); // 본격 메일 전송 메서드, 개 어려움. 뭔 말인지 모르겠음.
		// 근데 해냄

		return mav;
	}

	// 정확하게는 모르겠는데, 메일 보낼 때 사용 되는 내부 클래스 :: File 업로드용
	private BodyPart bodyPart(MultipartFile mf, String path) {

		MimeBodyPart body = new MimeBodyPart();

		String orgFile = mf.getOriginalFilename();

		File f1 = new File(path + "picture/" + orgFile);

		try {
			mf.transferTo(f1); // 서버에 임시로 파일을 저장
			body.attachFile(f1); // 저장된 파일을 메일에 첨부
			body.setFileName(new String(orgFile.getBytes("UTF-8"), "8859_1")); // 첨부된 파일의 이름을 설정

		} catch (Exception e) {
			e.printStackTrace();
		}

		return body;
	}

	// 정확하게는 모르겠는데, 메일 보낼 때 사용 되는 내부 클래스(2) :: email 사용 검증 객체
	private final class MyAuthenticator extends Authenticator {

		private String id;
		private String pw;

		public MyAuthenticator(String id, String pw) {
			this.id = id;
			this.pw = pw;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(id, pw);
		}
	}

	// 본격 메일 전송 메서드의 몸통, 2개의 내부 클래스 사용 : MyAuthenticator, BodyPart
	private void mailSendExcutor(Mail mail, HttpServletRequest request) {

		String naverid = mail.getNaverid(); // 입력된 NaverId (@ 앞까지만 기재해야함.)
		String naverpass = mail.getNaverpass(); // 입력된 NaverPass

		String host = "smtp.naver.com";
		String popHost = "smtp.pop.com";
		int port = 465;

		String path = request.getServletContext().getRealPath("/");

		MyAuthenticator auth = new MyAuthenticator(naverid, naverpass); // email 사용 검증 객체

		Properties prop = System.getProperties(); // email 사용 설정 객체

		// 사용 설정
		prop.put("mail.smtp.host", host);
		prop.put("mail.pop3.host", popHost);
		prop.put("mail.smtp.port", port);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.stmp.ssl.trust", host);
		prop.put("mail.smtp.socketFactory.port", port);
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.socketFactory.fallback", "false");
		// 사용 설정

		Session mailSession = Session.getInstance(prop, auth); // Naver에게 Email사용 검증하는 작업

		mailSession.setDebug(true); // for debug

		MimeMessage msg = new MimeMessage(mailSession); // 메일 전송 객체

		try {

			msg.setFrom(new InternetAddress(naverid)); // 메일(문자열)을 주소화 시킴

			List<InternetAddress> addrs = new ArrayList<InternetAddress>();

			String[] emails = mail.getRecipient().split(","); // mail.getRecipient() : E-mail을 받는 사람들

			for (int i = 0; i < emails.length; i++) {
				try {
					addrs.add(new InternetAddress(new String(emails[i].getBytes("euc-kr"), "8859_1"))); // 8859_1 =
																										// 브라우저의 기본
																										// Encoding
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

			InternetAddress[] arr = new InternetAddress[emails.length];

			for (int i = 0; i < addrs.size(); i++) {
				arr[i] = addrs.get(i);
			}

			msg.setSentDate(new Date()); // Mail을 보낸 일시

			InternetAddress from = new InternetAddress(naverid);

			msg.setFrom(from);
			msg.setRecipients(Message.RecipientType.TO, arr); // 받는 사람들
			msg.setSubject(mail.getTitle()); // 제목

			MimeMultipart multipart = new MimeMultipart();
			MimeBodyPart message = new MimeBodyPart();

			message.setContent(mail.getContents(), mail.getMtype());

			multipart.addBodyPart(message); // 글의 내용 부분을 Part로 지정

			for (MultipartFile mf : mail.getFile1()) {
				if ((mf != null) && (!mf.isEmpty())) {
					multipart.addBodyPart(bodyPart(mf, path));
				}
			}

			msg.setContent(multipart);
			Transport.send(msg);

		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	// 관리자페이지에서 Host계정의 모든 문의 목록 보여주기
	@RequestMapping("admin/Hlist")
	public ModelAndView adminHlist(HttpSession session,Integer pageNum,String searchType, String searchContent) {
		if (pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		ModelAndView mav = new ModelAndView();

		List<Board> hostQuestionList = service.hostQuestionList1(searchType,searchContent); // host 문의 목록

		mav.addObject("hList", hostQuestionList);

		return mav;
	}

	// 관리자페이지에서 Guest계정의 모든 문의 목록 보여주기
	@RequestMapping("admin/Glist")
	public ModelAndView adminGlist(HttpSession session,Integer pageNum,String searchType, String searchContent) {
		if (pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		ModelAndView mav = new ModelAndView();
		

		List<Board> guestQuestionList = service.guestQuestionList1(searchType,searchContent); // guest 문의 목록

		mav.addObject("gList", guestQuestionList);

		return mav;
	}

	// 공지사항 리스트 조회용 메서드
	@RequestMapping("notice/list")
	public ModelAndView noticeList(Integer pageNum, String searchType, String searchContent) {
		int kind = 1;
		if (pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}

		ModelAndView mav = new ModelAndView();

		int limit = 10; // 한 페이지에 나올 게시글의 숫자
		int listcount = service.boardcount(searchType, searchContent, kind); // 표시될 총 게시글의 수
		List<Board> boardlist = service.boardList(searchType, searchContent, pageNum, limit, kind);

		int maxpage = (int) ((double) listcount / limit + 0.95);
		int startpage = ((int) ((pageNum / 10.0 + 0.9) - 1)) * 10 + 1; // 시작페이지
		int endpage = startpage + 9; // 마지막 페이지
		if (endpage > maxpage)
			endpage = maxpage;
		int boardcnt = listcount - (pageNum - 1) * limit;

		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("listcount", listcount);
		mav.addObject("boardlist", boardlist);
		mav.addObject("boardcnt", boardcnt);
		return mav;
	}

	// 공지사항 작성 = GET
	@RequestMapping(value = "notice/write", method = RequestMethod.GET)
	public ModelAndView adminNoticeWrite(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Board board = new Board();
		mav.addObject("board", board);
		return mav;
	}

	// 공지사항 작성 = POST
	@RequestMapping(value = "notice/write", method = RequestMethod.POST)
	public ModelAndView adminNoticeWrite(HttpSession session, @Valid Board board, BindingResult bindingResult,
			HttpServletRequest request, Integer pageNum) {

		ModelAndView mav = new ModelAndView();

		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			System.out.println(bindingResult.getModel());
			return mav;
		}

		try {
			service.boardWrite(board, request);
			mav.setViewName("redirect:/notice/list.sms?pageNum=" + pageNum);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("오류가 발생하였습니다.", "redirect:/notice/list.sms");
		}
		return mav;
	}

	// 공지사항 수정 = GET
	@RequestMapping(value = "notice/update", method = RequestMethod.GET)
	public ModelAndView adminNoticeUpdate(HttpSession session, Integer bNo) {
		ModelAndView mav = new ModelAndView();
		Board board = service.getBoard(bNo);
		mav.addObject("board", board);
		return mav;
	}

	// 공지사항 수정 = POST
	@RequestMapping(value = "notice/update", method = RequestMethod.POST)
	public ModelAndView adminNoticeUpdate(HttpSession session, @Valid Board board, BindingResult bindingResult,
			HttpServletRequest request, Integer pageNum) {

		ModelAndView mav = new ModelAndView();

		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}

		try {
			service.boardUpdate(board, request);
			mav.setViewName("redirect:/notice/list.sms?pageNum=" + pageNum);
		} catch (Exception e) {
			throw new ProjectException("오류가 발생하였습니다.", "list.sms");
		}

		return mav;
	}

	// 공지사항 삭제 = GET
	@RequestMapping(value = "notice/delete", method = RequestMethod.GET)
	public ModelAndView adminNoticeDelete(HttpSession session, Integer bNo) {
		ModelAndView mav = new ModelAndView();
		Board board = service.getBoard(bNo);
		mav.addObject("board", board);
		return mav;
	}

	// 공지사항 삭제 = POST
	@RequestMapping(value = "notice/delete", method = RequestMethod.POST)
	public ModelAndView adminNoticeDelete(HttpSession session, Integer bNo, Integer pageNum) {

		ModelAndView mav = new ModelAndView();

		try {
			service.boardDelete(bNo);
			mav.setViewName("redirect:/notice/list.sms?pageNum=" + pageNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	// 공지사항 게시글 세부 조회용 메서드
	@RequestMapping(value = "notice/detail", method = RequestMethod.GET)
	public ModelAndView noticeDetail(Integer pageNum, Integer bNo) {

		ModelAndView mav = new ModelAndView();
		Board board = service.getBoard(bNo);
		mav.addObject("board", board);

		return mav;
	}
}

package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exception.ProjectException;
import logic.Board;
import logic.Member;
import logic.ProjectService;
import logic.TransactionHistory;

@Controller
public class AdminController {
	
	@Autowired
	private ProjectService service;
	
	// 관리자페이지 기본 요청 메서드 : 관리자 유효성 검증은 LoginAspect에서 처리할 예정
	@RequestMapping(value="admin/adminManagement", method=RequestMethod.GET)
	public ModelAndView adminManagementPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		List<Member> hostRegList = service.hostRegList();						// host 등록 요청 목록
		List<Board> guestQuestionList = service.guestQuestionList();			// guest 문의 목록
		List<Board> hostQuestionList = service.hostQuestionList();				// host 문의 목록
		List<TransactionHistory> transHostList = service.hostTransHistoryList("first");// host 공간 별 월별 수입 목록
		
		mav.addObject("hRegList",hostRegList);
		mav.addObject("gList",guestQuestionList);
		mav.addObject("hList",hostQuestionList);
		mav.addObject("thList", transHostList);
		
		return mav;
	}
	
	// 관리자가 총 회원 정보를 확인할 때 사용되는 메서드 (=회원리스트 확인)
	@RequestMapping(value="admin/adminMemberList", method=RequestMethod.GET)
	public ModelAndView adminMemberList(HttpSession session, String searchType, 
			String searchContent, String startDate, String endDate,
			Integer limit, Integer pageNum) {
		
		ModelAndView mav = new ModelAndView();
		
		if(pageNum == null || ("" + pageNum).equals("")) {
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
		
		if(searchType != null && searchType.equals("")) {
			mav.addObject("searchType", searchType);
		}
		
		return mav;
	}
	
	// 관리자가 총 Host 회원 정보를 확인할 때 사용되는 메서드 (=host 리스트 확인)
	@RequestMapping(value="admin/adminHostList", method=RequestMethod.GET)
	public ModelAndView adminHostList(HttpSession session, String searchType, 
			String searchContent, String startDate, String endDate,
			Integer limit, Integer pageNum) {
		
		ModelAndView mav = new ModelAndView();
		
		if(pageNum == null || ("" + pageNum).equals("")) {
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
		
		if(searchType != null && searchType.equals("")) {
			mav.addObject("searchType", searchType);
		}
		
		return mav;
	}
	
	// Host계정 전환 요청 전처리 전 확인 위해 보여질 페이지
	@RequestMapping(value="admin/adminHostRegDetail", method=RequestMethod.GET)
	public ModelAndView adminHostRegDetailView(HttpSession session, String id) {
		
		ModelAndView mav = new ModelAndView();
		Member hostMember = service.getMember(id);
		mav.addObject("hostMem", hostMember);
		
		return mav;
	}
	
	// Host계정 전환 요청 작업을 처리할 때 호출 되는 메서드
	@RequestMapping(value="admin/adminHostRegister", method=RequestMethod.GET)
	public ModelAndView adminHostRegister(HttpSession session, String id) {
		ModelAndView mav = new ModelAndView();
		
		service.hostRegister(id);	// host 계정으로 전환
		
		mav.setViewName("redirect:/admin/adminManagement.sms");
		
		return mav;
	}
	
	// admin문의에 대한 답변 작업을 처리할 때 호출 되는 메서드(작성 전)
	@RequestMapping(value="admin/adminAnswerQuestion", method=RequestMethod.GET)
	public ModelAndView adminAnswerQuestion(HttpSession session, Integer bNo) {
		ModelAndView mav = new ModelAndView();
		
		Board board = service.getBoard(bNo); // 문의글 객체
		Board answerBoard = new Board();	// 작성할 답변글 객체
		
		mav.addObject("board", board);
		mav.addObject("answerBoard", answerBoard);
		return mav;
	}
	
	// admin문의에 대한 답변 작업을 처리할 때 호출 되는 메서드(작성완료)
	@RequestMapping(value="admin/adminAnswerQuestion", method=RequestMethod.POST)
	public ModelAndView adminAnswerQuestionWrite(Board answerBoard, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		Board board = service.getBoard(answerBoard.getbNo());
		answerBoard.setId(board.getId());
		answerBoard.setKind(board.getKind());
		
		service.boardReply(answerBoard);
		
		mav.setViewName("redirect:/admin/adminManagement.sms?id=admin");
		
		return mav;
	}
	
	// 거래관리대장 페이지를 처리할 때 호출 되는 메서드 (GET)
	@RequestMapping(value="admin/adminTransHostList", method=RequestMethod.GET)
	public ModelAndView adminTransHostList(HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		List<TransactionHistory> transHostList = service.hostTransHistoryList("second");// host 공간 별 월별 수입 목록
		
		mav.addObject("thList", transHostList);
		
		return mav;
	}
	
	// 거래관리대장 페이지를 처리할 때 호출 되는 메서드 (POST)
	@RequestMapping(value="admin/adminTransHostList", method=RequestMethod.POST)
	public ModelAndView adminSearchTransList(HttpSession session, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		String searchType = request.getParameter("searchType");
		String searchContent = request.getParameter("searchContent");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		List<TransactionHistory> transHostList = 
				service.searchTransHistoryList(searchType, searchContent, startDate, endDate); // 거래관리대장 검색용
		
		mav.addObject("thList", transHostList);
		
		return mav;
	}
	
	@RequestMapping(value="admin/adminMailForm", method = RequestMethod.POST)
	public ModelAndView adminMailForm(HttpSession session, String[] idchks) {
		
		ModelAndView mav = new ModelAndView("admin/adminMailWrite");
		
		if(idchks == null || idchks.length == 0) {
			throw new ProjectException("메일을 보낼 대상자를 선택하세요.", "adminMemberList.sms");
		}
		
		List<Member> list = service.selectMemberList(idchks);
		mav.addObject("memberList", list);
		
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
	public ModelAndView adminNoticeWrite(HttpSession session, @Valid Board board, BindingResult bindingResult, HttpServletRequest request, Integer pageNum) {

		ModelAndView mav = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			System.out.println(bindingResult.getModel());
			return mav;
		}

		try {
			service.boardWrite(board, request);
			mav.setViewName("redirect:/notice/list.sms?pageNum="+pageNum);
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
	
	// 공지사항 수정  = POST
	@RequestMapping(value = "notice/update", method = RequestMethod.POST)
	public ModelAndView adminNoticeUpdate(HttpSession session, @Valid Board board, BindingResult bindingResult, HttpServletRequest request, Integer pageNum) {

		ModelAndView mav = new ModelAndView();

		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		
		try {
			service.boardUpdate(board, request);
			mav.setViewName("redirect:/notice/list.sms?pageNum="+pageNum);
		} catch (Exception e) {
			throw new ProjectException("오류가 발생하였습니다.", "list.sms");
		}
		
		return mav;
	}
	
	// 공지사항 수정 = GET
	@RequestMapping(value = "notice/delete", method = RequestMethod.GET)
	public ModelAndView adminNoticeDelete(HttpSession session, Integer bNo) {
		ModelAndView mav = new ModelAndView();
		Board board = service.getBoard(bNo);
		mav.addObject("board", board);
		return mav;
	}
	
	// 공지사항 삭제 시 호출되는 메서드
	@RequestMapping(value = "notice/delete", method = RequestMethod.POST)
	public ModelAndView adminNoticeDelete(HttpSession session, Integer bNo, Integer pageNum) {

		ModelAndView mav = new ModelAndView();
		
		try {
			service.boardDelete(bNo);
			mav.setViewName("redirect:/notice/list.sms?pageNum="+pageNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		// 공지사항 게시글 세부 조회용 메서드
		@RequestMapping(value="notice/detail", method=RequestMethod.GET)
		public ModelAndView noticeDetail(Integer pageNum, Integer bNo) {
			
			ModelAndView mav = new ModelAndView();
			Board board = service.getBoard(bNo);
			mav.addObject("board", board);
			
			return mav;
		}
}

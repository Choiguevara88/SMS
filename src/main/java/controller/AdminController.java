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

@Controller
public class AdminController {
	
	@Autowired
	private ProjectService service;
	int kind = 4;
	
	// 관리자페이지 기본 요청 메서드 : 관리자 유효성 검증은 LoginAspect에서 처리할 예정
	@RequestMapping(value="admin/adminManagement", method=RequestMethod.GET)
	public ModelAndView adminManagementPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		List<Member> hostRegList = service.hostRegList();			// host 등록 요청 목록
		List<Board> guestQuestionList = service.guestQuestionList();// guest 문의 목록
		List<Board> hostQuestionList = service.hostQuestionList();	// host 문의 목록
		
		mav.addObject("hRegList",hostRegList);
		mav.addObject("gList",guestQuestionList);
		mav.addObject("hList",hostQuestionList);
		
		return mav;
	}
	
	// Host계정 전환 요청 작업을 처리할 때 호출 되는 메서드
	@RequestMapping(value="admin/adminHostRegister", method=RequestMethod.GET)
	public ModelAndView adminHostRegister(HttpSession session, String id) {
		ModelAndView mav = new ModelAndView();
		
		service.hostRegister(id);	// host 계정으로 전환
		
		mav.setViewName("adminManagement");
		
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

		
		service.boardReply(answerBoard);
		
		mav.setViewName("admin/adminManagement");
		
		return mav;
	}
	@RequestMapping("admin/list")
	public ModelAndView list (Integer pageNum, Integer kind, String id) {
		
		if(pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		
		ModelAndView mav = new ModelAndView();
		
		int limit = 5;		// 한 페이지에 나올 게시글의 숫자
		int listcount = service.boardcount(kind,id);	// 표시될 총 게시글의 수
		List<Board> boardlist = service.boardList(kind,id,pageNum, limit);
		
		int maxpage = (int)((double)listcount/limit + 0.95);
		int startpage = ((int)((pageNum/5.0 + 0.9) - 1)) * 5 + 1; // 시작페이지
		int endpage = startpage + 4;	// 마지막 페이지
		if(endpage > maxpage) endpage = maxpage;
		int boardcnt = listcount - (pageNum - 1) * limit;
		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("listcount",listcount);
		mav.addObject("boardlist",boardlist);
		mav.addObject("boardcnt",boardcnt);
		mav.addObject("kind",kind);
				
		return mav;
	}
	
	// 게시글 등록하기
	@RequestMapping(value="admin/write", method=RequestMethod.POST) // 게시글 작성 시 호출되는 메서드
	public ModelAndView write(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		String id = (String)request.getSession().getAttribute("loginUser");
		
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		
		try {
			service.boardWrite(board, request);
			mav.setViewName("redirect:/admin/list.sms");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("오류가 발생하였습니다." , "/admin/write.sms");
		}
		mav.addObject("id",id);
		mav.addObject("kind",kind);
		return mav;
	}
	
	// 게시글 수정하기
	@RequestMapping(value="admin/update", method=RequestMethod.POST) // 게시글 작성 시 호출되는 메서드
	public ModelAndView update(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
			
			ModelAndView mav = new ModelAndView();
			int sNo = board.getsNo();
			
			if(bindingResult.hasErrors()) {
				mav.getModel().putAll(bindingResult.getModel());
				return mav;
			}
			try {
				service.boardUpdate(board, request);
				mav.setViewName("redirect:/admin/list.sms");
			} catch (Exception e) {
				e.printStackTrace();
				throw new ProjectException("오류가 발생하였습니다." , "/admin/list.sms");
			}
			mav.addObject("kind",kind);
			mav.addObject("sNo",sNo);
			return mav;
		}
	
	@RequestMapping(value="admin/delete", method=RequestMethod.POST)
	public ModelAndView delete(Integer bNo, Integer pageNum,Integer sNo,Integer kind) {
		
		ModelAndView mav = new ModelAndView();
		
			service.boardDelete(bNo);
			mav.setViewName("redirect:/admin/list.sms?sNo="+sNo+"&kind="+kind);
			return mav;
	}
	
	@RequestMapping(value="admin/*", method=RequestMethod.GET) // 게시글 작성 View로 접속할 때 호출되는 메서드
	public ModelAndView detail(Integer bNo, HttpServletRequest request) {
				
		ModelAndView mav = new ModelAndView();
		
		Board board = new Board();

		if(bNo != null) {
			board = service.getBoard(bNo);
			String url = request.getServletPath();
			
			if(url.contains("/admin/detail.sms")) {
				service.updateReadCnt(bNo);
			}
		}
		mav.addObject("kind",kind);
		mav.addObject("board", board);
		return mav;
	}


}

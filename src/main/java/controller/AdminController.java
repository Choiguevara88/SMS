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
}

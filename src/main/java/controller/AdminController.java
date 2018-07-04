package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		System.out.println(answerBoard.getbNo());
		System.out.println(answerBoard.getId());
		
		service.boardReply(answerBoard);
		
		mav.setViewName("admin/adminManagement");
		
		return mav;
	}

}

package controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.Board;
import logic.Member;
import logic.ProjectService;

@Controller
public class QAController {
	@Autowired
	private ProjectService service;
	
	@ModelAttribute
	public Board getBoard() {
		return new Board();
	}
	
	
	// 1:1 문의 작성 시 호출 되는 메서드
	@RequestMapping(value="qa/questionAdmin", method=RequestMethod.GET)
	public ModelAndView writeQuestion(String id, HttpSession session) {
		System.out.println(id);
		Member writer = service.getMember(id);
		System.out.println(writer);
		ModelAndView mav = new ModelAndView();
		
		
		Board board = new Board();
		
		if(writer.getMemType() != 1) {	// 해당 아이디가 Host계정이라면?
			board.setKind(5);				// Board 객체 Kind = 5 부여
		} else {							// 해당 아이디가 Host 계정이 아니라면?
			board.setKind(4);				// Board 객체 Kind = 4 부여
		}
		
		mav.addObject("board", board);
		
		return mav;
	}
	
	
	// 게시글 수정하기
	
	
	@RequestMapping(value="qa/*", method=RequestMethod.GET) // 게시글 작성 View로 접속할 때 호출되는 메서드
	public ModelAndView detail(Integer bNo, HttpServletRequest request) {
				
		ModelAndView mav = new ModelAndView();
		
		Board board = new Board();

		if(bNo != null) {
			board = service.getBoard(bNo);
			String url = request.getServletPath();
			
			if(url.contains("/qa/detail.sms")) {
				service.updateReadCnt(bNo);
			}
		}
		mav.addObject("board", board);
		return mav;
	}

}

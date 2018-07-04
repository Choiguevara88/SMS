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
	
	@RequestMapping(value="adminManagement", method=RequestMethod.GET)
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

}

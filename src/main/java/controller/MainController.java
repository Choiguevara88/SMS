package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Member;
import logic.ProjectService;

@Controller
public class MainController {
	
	@Autowired
	private ProjectService service;
	
	@RequestMapping("main")
	public ModelAndView mainPage() {
		ModelAndView mav = new ModelAndView("main");
		mav.addObject(new Member());
		return mav;
	}
	
}

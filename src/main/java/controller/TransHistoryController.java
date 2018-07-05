package controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.ProjectService;

@Controller
public class TransHistoryController {
	
	@Autowired
	private ProjectService service;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView transactionList (HttpSession session, String id) {
		
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}

}

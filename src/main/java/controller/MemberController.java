package controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.Member;
import logic.ProjectService;


@Controller
public class MemberController {
	@Autowired
	ProjectService service;
	
	@ModelAttribute
	public Member getMember() {
		return new Member();
	}
	
	
	@RequestMapping(value="joinForm") //회원가입 폼으로 
	public String joinForm() {
		return "member/joinForm";
	}
	
	@RequestMapping("member/join") //회원가입
	public ModelAndView join(@Valid Member member, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		try {
		service.joinsms(member);
		mav.setViewName("redirect: ../login.sms");
		mav.addObject("member",member);
		} catch(DataIntegrityViolationException e) {
			bindingResult.reject("error.duplicate.user");
		}
		return mav;
	}
	@RequestMapping(value = "login", method = RequestMethod.GET) //URL로 검색해서 들어 왔을 때
	public String loginForm() {
		return "member/loginpage";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST) //id, pw입력해서 로그인 누를 때
	public ModelAndView loginForm(@Valid Member member, BindingResult bindingResult, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			mav.setViewName("member/loginpage");
			return mav;
		}
		
		Member dbmember = service.getMember(member.getId());
		
		if(dbmember == null) {
			bindingResult.reject("error.login.id");
			mav.setViewName("member/loginpage");
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		
		if(dbmember.getPw().equals(member.getPw())) {
			mav.setViewName("main");
			mav.addObject("dbmember",dbmember);
			session.setAttribute("loginMember", dbmember);
		} else {
			bindingResult.reject("error.login.password");
			mav.getModel().putAll(bindingResult.getModel());
			mav.setViewName("member/loginpage");
		}
		
		
		return mav;
	}
	
	@RequestMapping(value="logout") //세션을 끊고 리다이렉트만 해주면 되어서 String, redirect 사용
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect: main.sms";
	}
	@RequestMapping(value="personal_info")
	public ModelAndView personal_info(String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member member = (Member)session.getAttribute("loginMember");
		mav.addObject("member");
		mav.setViewName("member/personal_info");
		return mav;
	}
	@RequestMapping(value="personal_info_update")
	public ModelAndView personal_info_update(String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/update");
		return mav;
	}
	@RequestMapping(value="personal_info_new")
	public ModelAndView personal_info_new(@Valid Member member, BindingResult bindingResult, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			mav.setViewName("personal_info_update");
			return mav;
		}
		service.updateMember(member);
		mav.setViewName("redirect: personal_info.sms?id=" + member.getId());
		return mav;
	}
}

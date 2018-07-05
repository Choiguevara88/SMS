package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	
	@RequestMapping(value="joinForm") //����媛��� �쇱�쇰� 
	public String joinForm() {
		return "member/joinForm";
	}
	
	@RequestMapping("member/join") //����媛���
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
	@RequestMapping(value = "login", method = RequestMethod.GET) //URL濡� 寃����댁�� �ㅼ�� ���� ��
	public String loginForm() {
		return "member/loginpage";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST) //id, pw���ν�댁�� 濡�洹몄�� ��瑜� ��
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
	
	@RequestMapping(value="logout") //�몄���� ��怨� 由щ�ㅼ�대���몃� �댁＜硫� ���댁�� String, redirect �ъ��
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
	@RequestMapping(value="checkID") //ID중복확인. 여기서만 나오고 끝남
	public @ResponseBody String checkID(String id) {
		Member member = service.getMember(id);
		if (member == null) {
			return "0";
		}
		return "1";
	}
	@RequestMapping(value="becomeaHost")
	public ModelAndView becomeaHost(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/becomeaHost");
		return mav;
	}
	@RequestMapping(value="addhostdata")
	public ModelAndView addhostdata(Member member, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		System.out.println(member);
		service.becomeaHost(member,request);
		mav.setViewName("redirect: becomeahost_complete.sms");
		return mav;
	}
	@RequestMapping(value="becomeahost_complete")
	public ModelAndView becomeahost_complete(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/becomeahost_complete");
		return mav;
	}
	@RequestMapping(value="findmyID")
	public ModelAndView findmyID() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/findmyID");
		return mav;
	}
	@RequestMapping(value="letsfindID")
	public ModelAndView letsfindID(String name, String email) {
		Member member = service.find_member(name, email);
		System.out.println(member);
		ModelAndView mav = new ModelAndView();
		if(member == null) {
			mav.setViewName("member/findID_result");
			return mav;
		}
		else {
			String id = member.getId();
			mav.addObject("id",id);
			mav.setViewName("member/findID_result");
			return mav;
		}
	}
	@RequestMapping(value="findmypassword")
	public ModelAndView findmypassword() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/findmypassword");
		return mav;
	}
	@RequestMapping(value="letsfindmypassword")
	public ModelAndView letsfindmypassword(String name, String id, String email) {
		ModelAndView mav = new ModelAndView();
		Member member = service.find_password(id,email,name);
		if(member == null) {
			mav.setViewName("member/findpassword_result");
			return mav;
		}else {
			mav.addObject("member",member);
			mav.setViewName("member/findpassword_result");
			return mav;
		}
	}
}

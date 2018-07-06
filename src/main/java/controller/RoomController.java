package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exception.ProjectException;
import logic.Building;
import logic.Member;
import logic.ProjectService;
import logic.Room;

@Controller
public class RoomController {
	@Autowired
	private ProjectService service;

	@ModelAttribute
	public Room getRoom() {
		return new Room();
	}
	@RequestMapping("room/roomForm")
	public ModelAndView roomForm() {
		ModelAndView mav = new ModelAndView("room/roomForm");
		mav.addObject(new Room());
	return mav;
	}
	
	@RequestMapping("room/roomSuccess")
	public ModelAndView roomSuccess(@Valid Room room, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			mav.setViewName("room/roomForm");
			return mav;
		}
		try{
			service.insertRoom(room);
			mav.setViewName("redirect:NewFile.sms"); // 이게 자꾸 404가 나오는데 왜그러죠?
		}catch(Exception e) {
			e.printStackTrace();
			throw new ProjectException("throw new ProjectException(string,string주소)","roomForm.sms");
		}
	
	return mav;
}
	@RequestMapping("room/NewFile")
	public ModelAndView NewFile() {
		ModelAndView mav = new ModelAndView();
		return mav;
}
	
	@RequestMapping(value="building/roomList", method=RequestMethod.GET)
	public ModelAndView myBuildingList(HttpServletRequest request) {		
		ModelAndView mav = new ModelAndView();
		/* 추후에 리퀘스트로 로그인된 아이디 가져와야함.*/
		Integer sNo = 1;
		try {
			List<Room> myRoomList = service.getMyRoom(sNo);
			mav.addObject("myRoomList",myRoomList);
			mav.addObject("sNo", sNo);
			mav.setViewName("room/roomList");
		}catch(Exception e) {
			e.printStackTrace();
			throw new ProjectException("throw new ProjectException(string,string주소)","roomForm.sms");
		}
		
		return mav;
	}
}

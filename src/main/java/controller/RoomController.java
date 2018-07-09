package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
	public ModelAndView roomForm(HttpServletRequest request) {
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
	@RequestMapping(value="room/roomList", method=RequestMethod.GET)
	public String loginForm() {		
	return "member/loginpage";
	}
	@RequestMapping(value="room/roomList", method=RequestMethod.POST)
	public ModelAndView myBuildingList(HttpServletRequest request,Building building) {		
		ModelAndView mav = new ModelAndView();
			Integer sNo = building.getsNo();
		try {
			List<Room> myRoomList = service.getmyRoomList(sNo);
			mav.addObject("myRoomList",myRoomList);
			mav.addObject("sNo", sNo);
			mav.setViewName("room/roomList");
		}catch(Exception e) {
			e.printStackTrace();
			throw new ProjectException("throw new ProjectException(string,string주소)","roomForm.sms");
		}
		return mav;
	}
	@RequestMapping("room/roomDetail")
	public ModelAndView roomDetail(HttpServletRequest request,Room room) {
		ModelAndView mav = new ModelAndView();
		try {
		
		Room myRoom = service.getMyRoom(room.getsRNo());
		mav.addObject("myRoom", myRoom);
		mav.setViewName("room/roomDetail");
		}catch(Exception e){
			throw new ProjectException("throw new ProjectEx", "roomList.sms");
		}
	return mav;
	}
	
	@RequestMapping("room/roomUpdateForm")
	public ModelAndView roomUpdateForm(HttpServletRequest request,Room room) {
		ModelAndView mav = new ModelAndView("room/roomUpdateForm");
		Room myRoom = service.getMyRoom(room.getsRNo());
		mav.addObject("myRoom", myRoom);
	return mav;	
	}
	@RequestMapping("room/roomUpdateSuccess.sms")
	public ModelAndView roomroomUpdate(HttpServletRequest request,@Valid Room room, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			mav.setViewName("redirect:roomUpdateForm");
			return mav;
		}
		try{
			service.updateRoom(room);
			mav.setViewName("redirect:NewFile.sms"); 
		}catch(Exception e) {
			e.printStackTrace();
			throw new ProjectException("throw new ProjectException(string,string주소)","roomForm.sms");
		}
	
	return mav;
}
	@RequestMapping("room/roomDeleteForm")
	public ModelAndView roomDeleteForm(HttpServletRequest request,Room room) {
		ModelAndView mav = new ModelAndView("room/roomDeleteForm");
		Room myRoom = service.getMyRoom(room.getsRNo());
		mav.addObject("myRoom", myRoom);
	return mav;	
	}
	
	@RequestMapping("room/roomDeleteSuccess")
	public ModelAndView roomDelete(HttpSession session,Integer sRNo, String pass)throws ProjectException {
		ModelAndView mav = new ModelAndView();
		try{
			Member loginMember = (Member) session.getAttribute("loginMember");
			String loginMemberPass = loginMember.getPw();
			
				if(pass != (loginMemberPass)) {
				
					throw new ProjectException("하이요","roomDeleteForm.sms");
				}
				Room myRoom = service.getMyRoom(sRNo);
				System.out.println(myRoom.getsRName());
				service.deleteRoom(myRoom.getsRNo());
				mav.setViewName("redirect:NewFile");
				
			}catch(Exception e) {
			e.printStackTrace();
			throw new ProjectException("로그인하세요. try catch구문은 한번 밖에 못쓰나? 두 번 쓰면 안됨?","redirect:roomDeleteForm");
		}
	return mav;
}
}


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
	@RequestMapping("building/roomForm")
	public ModelAndView roomForm(HttpServletRequest request, Room room) {
		ModelAndView mav = new ModelAndView();
		System.out.println(room.getsNo());
		String sNo = Integer.toString(room.getsNo());
		Building building = service.getMyBuildingOne(sNo);
		mav.addObject("building", building);
		mav.addObject("room",room);
		mav.setViewName("room/roomForm");
	return mav;
	}
	@RequestMapping("building/roomSuccess")
	public ModelAndView roomSuccess(HttpServletRequest request,@Valid Room room, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			String sNo = Integer.toString(room.getsNo());
			Building building = service.getMyBuildingOne(sNo);
			mav.addObject("building", building);
			mav.setViewName("room/roomForm");
			return mav;
		}
		try{
			Integer sNo = room.getsNo();
			service.insertRoom(room,request);
			mav.setViewName("redirect:roomList.sms?sNo="+sNo);
		}catch(Exception e) {
			e.printStackTrace();
			throw new ProjectException("throw new ProjectException(string,string주소)","roomForm.sms");
		}
	return mav;
}
	@RequestMapping("NewFile")
	public ModelAndView NewFile() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("room/NewFile");
		return mav;
	}
	@RequestMapping(value="building/roomList", method=RequestMethod.GET)
	public ModelAndView myBuildingList(HttpServletRequest request,Building building) {		
		ModelAndView mav = new ModelAndView();
			Integer sNo = building.getsNo();
		try {
			List<Room> myRoomList = service.getmyRoomList(sNo);
			mav.addObject("sNo",sNo);
			mav.addObject("myRoomList",myRoomList);
			mav.addObject("sNo", sNo); 
			mav.setViewName("room/roomList");
		}catch(Exception e) {
			e.printStackTrace();
			throw new ProjectException("throw new ProjectException(string,string주소)","roomForm.sms");
		}
		return mav;
	}
	@RequestMapping("building/roomDetail")
	public ModelAndView roomDetail(HttpServletRequest request,Room room) {
		ModelAndView mav = new ModelAndView();
		try {
	
		Room myRoom = service.getMyRoom(room);
		mav.addObject("myRoom", myRoom);
		mav.setViewName("room/roomDetail");
		}catch(Exception e){
			Integer sNo = room.getsNo();
			throw new ProjectException("throw new ProjectEx", "roomList.sms?sNo="+sNo);
		}
	return mav;
	}
	
	@RequestMapping("building/roomUpdateForm")
	public ModelAndView roomUpdateForm(HttpServletRequest request,Room room) {
		ModelAndView mav = new ModelAndView();
		try {
		Room myRoom = service.getMyRoom(room);
		mav.addObject("myRoom", myRoom);
		mav.setViewName("room/roomUpdateForm");
		}catch (Exception e){
			e.printStackTrace();
			throw new ProjectException("안돼", "redirect:roomUpdateForm");
		}
	return mav;	
	}
	@RequestMapping("building/roomUpdateSuccess.sms")
	public ModelAndView roomroomUpdate(HttpServletRequest request,@Valid Room room, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			mav.setViewName("room/roomUpdateForm");
			return mav;
		}
		try{
			Integer sNo= room.getsNo();
			Integer sRNo = room.getsRNo();
			service.updateRoom(room);
			mav.setViewName("redirect:roomDetail.sms?sRNo="+sRNo+"&sNo="+sNo); 
		}catch(Exception e) {
			e.printStackTrace();
			throw new ProjectException("throw new ProjectException(string,string주소)","roomForm.sms");
		}
	
	return mav;
}
	@RequestMapping("building/roomDeleteForm")
	public ModelAndView roomDeleteForm(HttpServletRequest request,Room room) {
		ModelAndView mav = new ModelAndView("room/roomDeleteForm");
		Room myRoom = service.getMyRoom(room);
		mav.addObject("myRoom", myRoom);
	return mav;	
	}
	
	@RequestMapping("building/roomDeleteSuccess")
	public ModelAndView roomDelete(HttpSession session,Integer sRNo,Integer sNo, String pass)throws ProjectException {
		ModelAndView mav = new ModelAndView();
		try{
			
			Member loginMember = (Member) session.getAttribute("loginMember");
			String loginMemberPass = loginMember.getPw();
				if(pass.equals(loginMemberPass)) {
				Room room = new Room(); 
				room.setsRNo(sRNo);
				room.setsNo(sNo);
				room =service.getMyRoom(room);
				Room myRoom = service.getMyRoom(room);
				service.deleteRoom(myRoom);
				mav.setViewName("redirect:roomList.sms?sNo="+sNo);
				}
			}catch(Exception e) {
			e.printStackTrace();
			throw new ProjectException("로그인하세요. try catch구문은 한번 밖에 못쓰나? 두 번 쓰면 안됨?","redirect:roomDeleteForm");
		}
	return mav;
	}
}


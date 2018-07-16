package controller;


import java.util.ArrayList;
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
	public ModelAndView roomForm(Room room) {
		ModelAndView mav = new ModelAndView();
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
	@RequestMapping(value="building/roomList", method=RequestMethod.GET)
	public ModelAndView myBuildingList(HttpServletRequest request,Building building,HttpSession session) {		
		ModelAndView mav = new ModelAndView();
			Integer sNo = building.getsNo();
		try {
			List<Room> myRoomList = service.getmyRoomList(sNo);
			mav.addObject("sNo",sNo);
			mav.addObject("myRoomList",myRoomList);
			String sNo1 = Integer.toString(sNo);
			building = service.getMyBuildingOne(sNo1);
			int roomCnt = myRoomList.size();
			mav.addObject("building", building);
			mav.addObject("sNo", sNo); 
			mav.addObject("roomCnt", roomCnt);
			mav.setViewName("room/roomList");
		}catch(Exception e) {
			e.printStackTrace();
			throw new ProjectException("throw new ProjectException(string,string주소)","roomForm.sms");
		}
		return mav;
	}
	@RequestMapping("building/roomDetail")
	public ModelAndView roomDetail(HttpServletRequest request,Room room,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		try {
		Room myRoom = service.getMyRoom(room);
		mav.addObject("room", myRoom);
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
			String sNo = Integer.toString(room.getsNo());
			Building building = service.getMyBuildingOne(sNo);
			mav.addObject("building", building);
			Room myRoom = service.getMyRoom(room);
			List<String> sRInfoNames1 = new ArrayList<String>();
			sRInfoNames1.add("TV/프로젝터");
			sRInfoNames1.add("복사기/인쇄기");
			sRInfoNames1.add("주류반입가능");
			sRInfoNames1.add("샤워시설");
			List<String> sRInfoNames2 = new ArrayList<String>();
			sRInfoNames2.add("인터넷/WIFI");
			sRInfoNames2.add("화이트보드");
			sRInfoNames2.add("음향/마이크");
			sRInfoNames2.add("취사시설");
			List<String> sRInfoNames3 = new ArrayList<String>();
			sRInfoNames3.add("음식물반입가능");
			sRInfoNames3.add("주차");
			sRInfoNames3.add("금연");
			sRInfoNames3.add("PC/노트북");
			List<String> sRInfoNames4 = new ArrayList<String>();
			sRInfoNames4.add("의자/테이블");
			sRInfoNames4.add("내부화장실");
			sRInfoNames4.add("탈의실");
			sRInfoNames4.add("테라스/루프탑");
			List<String> sRInfoNames5 = new ArrayList<String>();
			sRInfoNames5.add("공용라운지");
			sRInfoNames5.add("전신거울");
			sRInfoNames5.add("바베큐시설");
			sRInfoNames5.add("도어락");
			mav.addObject("sRInfoNames1", sRInfoNames1);
			mav.addObject("sRInfoNames2", sRInfoNames2);
			mav.addObject("sRInfoNames3", sRInfoNames3);
			mav.addObject("sRInfoNames4", sRInfoNames4);
			mav.addObject("sRInfoNames5", sRInfoNames5);
			
		mav.addObject("room", myRoom);
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
			System.out.println(room);
			service.updateRoom(room,request);
			
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

		mav.addObject("room", myRoom);
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
			throw new ProjectException("로그인하세요.","redirect:roomDeleteForm");
		}
	return mav;
	}
}


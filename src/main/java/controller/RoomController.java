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
   @RequestMapping("room/roomForm")
   public ModelAndView roomForm(Room room) {
      ModelAndView mav = new ModelAndView();
      String sNo = Integer.toString(room.getsNo());
      Building building = service.getMyBuildingOne(sNo);
      mav.addObject("building", building);
      mav.addObject("room",room);
      mav.setViewName("room/roomForm");
   return mav;
   }
   @RequestMapping("room/roomSuccess")
   public ModelAndView roomSuccess(HttpServletRequest request,@Valid Room room, BindingResult bindingResult) {
      ModelAndView mav = new ModelAndView();
      if(bindingResult.hasErrors()) {
         mav.getModel().putAll(bindingResult.getModel());
         String sNo = Integer.toString(room.getsNo());
         Building building = service.getMyBuildingOne(sNo);
         System.out.println("bindingresult####"+bindingResult.getModel());
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
         Integer sNo = room.getsNo();
         throw new ProjectException("세부 공간 작성에 실패했습니다.","roomList.sms?sNo="+sNo);
      }
   return mav;
}
   @RequestMapping(value="room/roomList", method=RequestMethod.GET)
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
         throw new ProjectException("리스트를 불러오는데 실패했다.","roomForm.sms");
      }
      return mav;
   }
   @RequestMapping("room/roomDetail")
   public ModelAndView roomDetail(HttpServletRequest request,Room room,HttpSession session) {
      ModelAndView mav = new ModelAndView();
      try {
      Room myRoom = service.getMyRoom(room);
      mav.addObject("room", myRoom);
      System.out.println(myRoom);
      mav.setViewName("room/roomDetail");
      }catch(Exception e){
         Integer sNo = room.getsNo();
         throw new ProjectException("throw new ProjectEx", "roomList.sms?sNo="+sNo);
      }
   return mav;
   }
   @RequestMapping("room/roomUpdateForm")
   public ModelAndView roomUpdateForm(HttpServletRequest request,Room room) {
      ModelAndView mav = new ModelAndView();
         String sNo = Integer.toString(room.getsNo());
         Building building = service.getMyBuildingOne(sNo);
         mav.addObject("building", building);
         Room myRoom = service.getMyRoom(room);
         List<String> sRInfoNames1 = new ArrayList<String>();
         sRInfoNames1.add("<i class='material-icons'>devices</i> TV/프로젝터");
         sRInfoNames1.add("<i class='material-icons'>local_printshop</i> 복사기/인쇄기");
         sRInfoNames1.add("<i class='material-icons'>local_bar</i> 주류반입가능");
         sRInfoNames1.add("<i class='material-icons'>hot_tub</i> 샤워시설");
         List<String> sRInfoNames2 = new ArrayList<String>();
         sRInfoNames2.add("<i class='material-icons'>wifi</i> 인터넷/WIFI");
         sRInfoNames2.add("<i class='material-icons'>airplay</i> 화이트보드");
         sRInfoNames2.add("<i class='material-icons'>settings_voice</i> 음향/마이크");
         sRInfoNames2.add("<i class='material-icons'>kitchen</i> 취사시설");
         List<String> sRInfoNames3 = new ArrayList<String>();
         sRInfoNames3.add("<i class='material-icons'>cake</i> 음식물반입가능");
         sRInfoNames3.add("<i class='material-icons'>time_to_leave</i> 주차");
         sRInfoNames3.add("<i class='material-icons'>smoke_free</i> 금연");
         sRInfoNames3.add("<i class='material-icons'>desktop_windows</i> PC/노트북");
         List<String> sRInfoNames4 = new ArrayList<String>();
         sRInfoNames4.add("<i class='material-icons'>event_seat</i> 의자/테이블");
         sRInfoNames4.add("<i class='material-icons'>wc</i> 내부화장실");
         sRInfoNames4.add("<i class='material-icons'>accessibility</i> 탈의실");
         sRInfoNames4.add("<i class='material-icons'>beach_access</i> 테라스/루프탑");
         List<String> sRInfoNames5 = new ArrayList<String>();
         sRInfoNames5.add("<i class='material-icons'>weekend</i> 공용라운지");
         sRInfoNames5.add("<i class='material-icons'>nature_people</i> 전신거울");
         sRInfoNames5.add("<i class='material-icons'>restaurant</i> 바베큐시설");
         sRInfoNames5.add("<i class='material-icons'>dialpad</i> 도어락");
         mav.addObject("sRInfoNames1", sRInfoNames1);
         mav.addObject("sRInfoNames2", sRInfoNames2);
         mav.addObject("sRInfoNames3", sRInfoNames3);
         mav.addObject("sRInfoNames4", sRInfoNames4);
         mav.addObject("sRInfoNames5", sRInfoNames5);
      mav.addObject("room", myRoom);
      mav.setViewName("room/roomUpdateForm");
   return mav;   
   }
   @RequestMapping("room/roomUpdateSuccess")
   public ModelAndView roomroomUpdate(HttpServletRequest request,@Valid Room room, BindingResult bindingResult) {
      ModelAndView mav = new ModelAndView();
      if(bindingResult.hasErrors()) {
         mav.getModel().putAll(bindingResult.getModel());
         String sNo = Integer.toString(room.getsNo());
         Integer sRNo = room.getsRNo();
         Building building = service.getMyBuildingOne(sNo);
         mav.addObject("building", building);
         mav.addObject("room",room);
         System.out.println("유효성검사"+room);
         mav.setViewName("room/roomUpdateForm");
         mav.addObject("sNo",sNo);
         mav.addObject("sRNo",sRNo);
         List<String> sRInfoNames1 = new ArrayList<String>();
         sRInfoNames1.add("<i class='material-icons'>devices</i> TV/프로젝터");
         sRInfoNames1.add("<i class='material-icons'>local_printshop</i> 복사기/인쇄기");
         sRInfoNames1.add("<i class='material-icons'>local_bar</i> 주류반입가능");
         sRInfoNames1.add("<i class='material-icons'>hot_tub</i> 샤워시설");
         List<String> sRInfoNames2 = new ArrayList<String>();
         sRInfoNames2.add("<i class='material-icons'>wifi</i> 인터넷/WIFI");
         sRInfoNames2.add("<i class='material-icons'>airplay</i> 화이트보드");
         sRInfoNames2.add("<i class='material-icons'>settings_voice</i> 음향/마이크");
         sRInfoNames2.add("<i class='material-icons'>kitchen</i> 취사시설");
         List<String> sRInfoNames3 = new ArrayList<String>();
         sRInfoNames3.add("<i class='material-icons'>cake</i> 음식물반입가능");
         sRInfoNames3.add("<i class='material-icons'>time_to_leave</i> 주차");
         sRInfoNames3.add("<i class='material-icons'>smoke_free</i> 금연");
         sRInfoNames3.add("<i class='material-icons'>desktop_windows</i> PC/노트북");
         List<String> sRInfoNames4 = new ArrayList<String>();
         sRInfoNames4.add("<i class='material-icons'>event_seat</i> 의자/테이블");
         sRInfoNames4.add("<i class='material-icons'>wc</i> 내부화장실");
         sRInfoNames4.add("<i class='material-icons'>accessibility</i> 탈의실");
         sRInfoNames4.add("<i class='material-icons'>beach_access</i> 테라스/루프탑");
         List<String> sRInfoNames5 = new ArrayList<String>();
         sRInfoNames5.add("<i class='material-icons'>weekend</i> 공용라운지");
         sRInfoNames5.add("<i class='material-icons'>nature_people</i> 전신거울");
         sRInfoNames5.add("<i class='material-icons'>restaurant</i> 바베큐시설");
         sRInfoNames5.add("<i class='material-icons'>dialpad</i> 도어락");
         mav.addObject("sRInfoNames1", sRInfoNames1);
         mav.addObject("sRInfoNames2", sRInfoNames2);
         mav.addObject("sRInfoNames3", sRInfoNames3);
         mav.addObject("sRInfoNames4", sRInfoNames4);
         mav.addObject("sRInfoNames5", sRInfoNames5);
         return mav;
      }
      try{
         Integer sNo= room.getsNo();
         Integer sRNo = room.getsRNo();
         service.updateRoom(room,request);
         System.out.println(room);
         mav.setViewName("redirect:/room/roomDetail.sms?sRNo="+sRNo+"&sNo="+sNo); 
      }catch(Exception e) {
         e.printStackTrace();
         Integer sNo= room.getsNo();
         throw new ProjectException("수정에 실패 했습니다.","roomList.sms?sNo="+sNo);
      }
   return mav;
}
   @RequestMapping("room/roomDeleteSuccess")
   public ModelAndView roomDelete(HttpSession session,Integer sRNo,Integer sNo, String pass)throws ProjectException {
      ModelAndView mav = new ModelAndView();
         Member loginMember = (Member) session.getAttribute("loginMember");
         if(loginMember != null) {
            Room room = new Room(); 
            room.setsRNo(sRNo);
            room.setsNo(sNo);
            Room myRoom = service.getMyRoom(room);
            service.deleteRoom(myRoom);
            mav.setViewName("roomList.sms?sNo="+sNo);
         }else {
            throw new ProjectException("로그인하세요.","roomList.sms?sNo="+sNo);
         }
         mav.setViewName("redirect:roomList.sms?sNo="+sNo);
         return mav;
}
}
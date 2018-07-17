<<<<<<< HEAD
package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exception.ProjectException;
import logic.Building;
import logic.Member;
import logic.ProjectService;
import logic.Reserve;
import logic.Room;

@Controller
public class ReserveController {

   @Autowired
   private ProjectService service;
   
   
   // 예약을 등록할 때 호출되는 메서드 : GET
   @RequestMapping(value = "reserve/regReserve", method = RequestMethod.GET)
   public ModelAndView regReserveForm(Integer sNo, Integer sRNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Room room = service.getRoom(sNo, sRNo);
      Building building = service.getMyBuildingOne("" + sNo);
      Member hostMember = service.getMember(building.getId());
      
      mav.addObject("room", room);           // 예약할 룸 정보를 가진 객체
      mav.addObject("building", building);    // 예약할 빌딩 정보를 가진 객체
      mav.addObject("hostMember", hostMember); // 해당 호스트 정보를 가진 객체
      mav.addObject("reserve", new Reserve()); // 예약 객체
      
      mav.setViewName("reserve/regReserve");
      
      return mav;
   }

   // 예약을 등록할 때 호출되는 메서드
   @RequestMapping(value = "reserve/regReserve", method = RequestMethod.POST)
   public ModelAndView registerReserve(Reserve reserve, HttpSession session, HttpServletRequest request) {
      ModelAndView mav = new ModelAndView();
      
      Date date = new Date();
      
      if (date.after(reserve.getReDate())) {
         throw new ProjectException("오늘 이전 날짜로는 예약할 수 없습니다.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }
      
      if(!resCheckDate(reserve)) { // 예약날짜 중복 체크 하는 메서드
         throw new ProjectException("예약 날짜가 중복됩니다.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }

      try {
         service.reserveInsert(reserve);
         mav.setViewName("redirect:resList.sms?id=" + reserve.getId());
         
      } catch (Exception e) {
         
         throw new ProjectException("전부 입력하셔야 합니다.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }
      
      return mav;
   }
   
   // 예약 시간 중복 체크를 하는 메서드
   private boolean resCheckDate(Reserve reserve) {
      
      Room chkRoom = service.getRoom(reserve.getsNo(), reserve.getSrNo());
      
      long startDateMiSec = 0;
      Date startChkDate = new Date();
      long endDateMiSec = 0;
      Date endChkDate = new Date();
      
      // 예약 단위 타입이 시간인 경우
      if(chkRoom.getsResType() == 0) {
         SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH");
         
         // 나의 예약 날짜부터 6시간 이전까지의 시작 시간을 구한다.
         startDateMiSec = reserve.getReDate().getTime() - (1000 * 60 * 60 * reserve.getReCnt());
         startChkDate = new Date(startDateMiSec);
                  
         // 나의 예약 날짜부터 6시간 이후까지의 종료 시간을 구한다.
         endDateMiSec = reserve.getReDate().getTime() + (1000 * 60 * 60 * reserve.getReCnt());
         endChkDate = new Date(endDateMiSec);
         
         // 시작 일자부터 종료 일자까지 기간(12시간)동안의 예약 객체를 불러온다.
         List <Reserve> list = service.getReserveDateChkList(transFormat.format(startChkDate), 
               transFormat.format(endChkDate), reserve.getsNo(), reserve.getSrNo());
         
         System.out.println("list : " + list);

         for(Reserve dbRes : list) {
            
            // DB의 시간이 예약시간 이전이라면..!
            if (dbRes.getReDate().before(reserve.getReDate())) {
               
               long a = reserve.getReDate().getTime();   // 예약날짜를 밀리세컨으로
               long b = dbRes.getReDate().getTime();   // DB날짜를 밀리세컨으로
               
               int c = (int)(a - b / (1000 * 60 * 60)); // 두 날짜의 차이를 시간 단위로 치환
               
               System.out.println("c:" + c);
               
               if(c <= dbRes.getReCnt()) { // DB상의 갯수보다 두 날짜의 차이가 작거나 같으면 false를 리턴
                  return false;
               }
            }
            
            // 같은 시간대라면..!
            if (transFormat.format(dbRes.getReDate()).equals(transFormat.format(reserve.getReDate()))) {
               return false;
            }
            
            // DB의 시간이 예약시간 이후라면..!
            if (dbRes.getReDate().after(reserve.getReDate())) {
               
               long a = dbRes.getReDate().getTime();   // DB날짜를 밀리세컨으로
               long b = reserve.getReDate().getTime();   // 예약날짜를 밀리세컨으로
               
               int c = (int)(a - b / (1000 * 60 * 60)); // 두 날짜의 차이를 시간 단위로 치환
               
               System.out.println("c:" + c);
               
               if(c <= reserve.getReCnt()) { // 나의 예약갯수보다 두 날짜의 차이가 작거나 같으면 false를 리턴
                  return false;
               }
            }
         }
      }
      
      // 예약 단위 타입이 일자인 경우
      else if(chkRoom.getsResType() == 1) {
         
         SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
                  
         // 나의 예약 날짜부터 6일 이전까지의 시작일자를 구한다.
         startDateMiSec = reserve.getReDate().getTime() - (1000 * 60 * 60 * 24 * reserve.getReCnt());
         startChkDate = new Date(startDateMiSec);
         
         // 나의 예약 날짜부터 6일 이후까지의 종료일자를 구한다.
         endDateMiSec = reserve.getReDate().getTime() + (1000 * 60 * 60 * 24 * reserve.getReCnt());
         endChkDate = new Date(endDateMiSec);
         
         // 시작 일자부터 종료 일자까지 기간(12일)동안의 예약 객체를 불러온다.
         List <Reserve> list = service.getReserveDateChkList(transFormat.format(startChkDate), 
               transFormat.format(endChkDate), reserve.getsNo(), reserve.getSrNo());
         
         for(Reserve dbRes : list) {
            // DB의 날짜가 예약날짜 이전이라면..!
            if (dbRes.getReDate().before(reserve.getReDate())) {
               
               long a = reserve.getReDate().getTime();   // 예약날짜를 밀리세컨으로
               long b = dbRes.getReDate().getTime();   // DB날짜를 밀리세컨으로
               
               int c = (int)(a - b / (1000 * 60 * 60 * 24)); // 두 날짜의 차이를 일자로 치환
               
               if(c <= dbRes.getReCnt()) { // DB상의 갯수보다 두 날짜의 차이가 작거나 같으면 false를 리턴
                  return false;
               }
            }

            // 같은 날짜라면..!
            if (transFormat.format(dbRes.getReDate()).equals(transFormat.format(reserve.getReDate()))) {
               return false;
            }
            
            // DB의 날짜가 예약날짜 이후라면..!
            if (dbRes.getReDate().after(reserve.getReDate())) {
               
               long a = dbRes.getReDate().getTime();   // DB날짜를 밀리세컨으로
               long b = reserve.getReDate().getTime();   // 예약날짜를 밀리세컨으로
               
               int c = (int)(a - b / (1000 * 60 * 60 * 24)); // 두 날짜의 차이를 일자로 치환
               
               if(c <= reserve.getReCnt()) { // 나의 예약갯수보다 두 날짜의 차이가 작거나 같으면 false를 리턴
                  return false;
               }
            }
         }
      }
      System.out.println("!!!!!!!!!!!!!!예약 메서드 조건 충족 확인!!!!!!!!!!!!!!!!!");
      return true;
   }

   // 예약을 수정할 때 호출되는 메서드
   @RequestMapping(value = "reserve/resUpdate", method = RequestMethod.POST)
   public ModelAndView updateReserve(Reserve reserve, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveUpdate(reserve);
         mav.setViewName("redirect:resDetail.sms?reNo=" + reserve.getReNo());
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("오류가 발생하였습니다.", "resUpdate.sms?reNo=" + reserve.getReNo());
      }

      return mav;

   }

   // 예약 리스트를 확인할 때 호출되는 메서드 (Guest계정용)
   @RequestMapping(value = "reserve/resList", method = RequestMethod.GET)
   public ModelAndView reserveList(String id, Integer pageNum, String searchType, String searchContent,
         HttpSession session, String startDate, String endDate) {

      if (pageNum == null || pageNum.toString().equals("")) {
         pageNum = 1;
      }

      ModelAndView mav = new ModelAndView();

      int limit = 100; // 한 페이지에 나올 게시글의 숫자
      int listcount = service.reserveCount(id, searchType, searchContent); // 표시될 총 게시글의 수


       List<Reserve> reservelist = service.selectReserveList(id, searchType, searchContent, pageNum, limit, startDate, endDate);


      int maxpage = (int) ((double) listcount / limit + 0.95);
      int startpage = ((int) ((pageNum / 10.0 + 0.9) - 1)) * 10 + 1; // 시작페이지
      int endpage = startpage + 9; // 마지막 페이지
      if (endpage > maxpage)   endpage = maxpage;
      int reservecnt = listcount - (pageNum - 1) * limit;
         
      mav.addObject("pageNum", pageNum);
      mav.addObject("maxpage", maxpage);
      mav.addObject("startpage", startpage);
      mav.addObject("endpage", endpage);
      mav.addObject("listcount", listcount);
      mav.addObject("list", reservelist);
      mav.addObject("reservecnt", reservecnt);

      return mav;
   }

   // 신규예약정보에 대해 확인할 때 호출되는 메서드 (Host계정용)
   @RequestMapping(value = "reserve/hostResInfo", method = RequestMethod.GET)
   public ModelAndView hostReserveInfo(HttpSession session) {

      ModelAndView mav = new ModelAndView();

      String hostId = ((Member) session.getAttribute("loginMember")).getId();
      
      Member hostMember = service.getMember(hostId);

      List<Integer> hostHaveBuild = service.hostHaveBuildsNo(hostId); // 해당 Host 앞으로 등록된 Building들의 건물관리번호를 List로 저장

      List<Building> list = new ArrayList<Building>();
      
      int buildCnt = service.hostBuildCount(hostId);
      
      int questCnt = 0;

      for (Integer sNo : hostHaveBuild) {   // 건물관리별로 Building 정보를 가져와서 list<Building>에 저장
         list.add(service.selectHostReserveInfo(hostId, sNo));
      }
      
      for (Integer sNo : hostHaveBuild) {
         questCnt += service.hostBoardCountQuest(sNo);
      }

      mav.addObject("list", list);
      mav.addObject("hMember", hostMember);
      mav.addObject("buildCnt", buildCnt);
      mav.addObject("questCnt", questCnt);

      return mav;
   }

   // 건물 별로 예약 리스트를 확인할 때 호출되는 메서드 (Host계정용)
   @RequestMapping(value = "reserve/hostResList", method = RequestMethod.GET)
   public ModelAndView hostReserveList(Integer sNo, Integer pageNum, String searchType, String searchContent, HttpSession session,
         String startDate, String endDate) {

      if (pageNum == null || pageNum.toString().equals("")) {
         pageNum = 1;
      }

      ModelAndView mav = new ModelAndView();

      int limit = 100; // 한 페이지에 나올 게시글의 숫자
      
      int listcount = service.hostReserveCount(sNo, searchType, searchContent); // 표시될 총 게시글의 수

      List<Reserve> reservelist = service.selectHostReserveList(sNo, searchType, searchContent, pageNum, limit, startDate, endDate);

      int maxpage = (int) ((double) listcount / limit + 0.95);
      int startpage = ((int) ((pageNum / 10.0 + 0.9) - 1)) * 10 + 1; // 시작페이지
      int endpage = startpage + 9; // 마지막 페이지
      if (endpage > maxpage)
         endpage = maxpage;
      int reservecnt = listcount - (pageNum - 1) * limit;
            
      mav.addObject("pageNum", pageNum);
      mav.addObject("maxpage", maxpage);
      mav.addObject("startpage", startpage);
      mav.addObject("endpage", endpage);
      mav.addObject("listcount", listcount);
      mav.addObject("list", reservelist);
      mav.addObject("reservecnt", reservecnt);
      mav.addObject("sNo", sNo);
      
      if(searchType != null && !searchType.equals("")) {
         mav.addObject("searchType", searchType);
      }

      return mav;
   }

   // 예약 정보 확인 할 때 사용하는 메서드
   @RequestMapping(value = "reserve/resDetail", method = RequestMethod.GET)
   public ModelAndView detailReserve2(Integer reNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Reserve reserve = service.getReserve(reNo);
      
      Room room = new Room();

      room = service.getRoom(reserve.getsNo(), reserve.getSrNo());
      
      Date endDate = new Date();
      long plusTime = reserve.getReDate().getTime();
      
      if(room.getsResType() == 0) { // 예약화면에서 보여질 내용
         endDate.setTime(plusTime + (3600000 * reserve.getReCnt()));
      } else {
         endDate.setTime(plusTime + (86400000 * reserve.getReCnt()));
      }

      mav.addObject("reserve", reserve);
      mav.addObject("room", room); // 세부공간에 대한 세부정보
      mav.addObject("endDate", endDate);

      return mav;

   }

   // Host계정이 결제 확인할 때 사용하는 메서드
   @RequestMapping(value = "reserve/hostResConfirm", method = RequestMethod.GET)
   public ModelAndView hostReserveConfirm(Integer reNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      String hostId = ((Member)session.getAttribute("loginMember")).getId();
      
      try {
         Integer dbSNo = service.getReserve(reNo).getsNo();
         List<Integer> hostSNoList = service.hostHaveBuildsNo(hostId);

         for (Integer hostSNo : hostSNoList) {
            if (hostSNo.intValue() == dbSNo.intValue()) {
               service.hostPaymentConfirm(reNo);
               mav.setViewName("redirect:/reserve/hostResInfo.sms");
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("오류가 발생하였습니다.", "redirect:/reserve/hostResInfo.sms");
      }

      return mav;
   }

   // 예약 취소 작업시 호출되는 메서드
   @RequestMapping(value = "reserve/resCancel", method = RequestMethod.GET)
   public ModelAndView reserveCancel(Integer reNo, Integer reStat, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveCancel(reNo, reStat);
         mav.setViewName("redirect:/main.sms");
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("오류가 발생하였습니다.", "reserve/list.sms");
      }
      return mav;
   }
   
   // 예약 취소 작업시 호출되는 메서드
   @RequestMapping(value = "reserve/hostResCancel", method = RequestMethod.GET)
   public ModelAndView hostResCancel(Integer reNo, Integer reStat, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveCancel(reNo, reStat);
         mav.setViewName("redirect:/main.sms");
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("오류가 발생하였습니다.", "reserve/list.sms");
      }
      return mav;
   }
   
   // 예약 등록 화면에서 특정 공간의 예약 확인 시 호출되는 메서드
   @RequestMapping(value="reserve/reserveCheckList", method=RequestMethod.GET)
   public ModelAndView resChkList(Integer sNo, Integer sRNo, HttpSession session) {
      
      ModelAndView mav = new ModelAndView();
      
      Room room = service.getRoom(sNo, sRNo);

      int chkCnt = service.reserveChkCnt(sNo, sRNo);
      List<Reserve> chkList = service.reserveChkList(sNo, sRNo);
      
      mav.addObject("chkCnt", chkCnt);
      mav.addObject("chkList", chkList);
      mav.addObject("sResType", room.getsResType());
      
      return mav;
      
   }

   // 예약업무 관련하여 Default 호출값으로 지정한 메서드 : 특정 예약 보기, 예약 등록 화면
   @RequestMapping(value = "reserve/*", method = RequestMethod.GET)
   public ModelAndView detailReserve(Integer reNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Reserve reserve = new Reserve();

      // 예약이 있다면 해당 예약정보를 보여주기 위한 조건식
      if (reNo != null) {
         reserve = service.getReserve(reNo);
      }

      mav.addObject("reserve", reserve);
      return mav;
   }

=======
package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exception.ProjectException;
import logic.Building;
import logic.Member;
import logic.ProjectService;
import logic.Reserve;
import logic.Room;

@Controller
public class ReserveController {

   @Autowired
   private ProjectService service;
   
   
   // �삁�빟�쓣 �벑濡앺븷 �븣 �샇異쒕릺�뒗 硫붿꽌�뱶 : GET
   @RequestMapping(value = "reserve/regReserve", method = RequestMethod.GET)
   public ModelAndView regReserveForm(Integer sNo, Integer sRNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Room room = service.getRoom(sNo, sRNo);
      Building building = service.getMyBuildingOne("" + sNo);
      Member hostMember = service.getMember(building.getId());
      
      mav.addObject("room", room);           // �삁�빟�븷 猷� �젙蹂대�� 媛�吏� 媛앹껜
      mav.addObject("building", building);    // �삁�빟�븷 鍮뚮뵫 �젙蹂대�� 媛�吏� 媛앹껜
      mav.addObject("hostMember", hostMember); // �빐�떦 �샇�뒪�듃 �젙蹂대�� 媛�吏� 媛앹껜
      mav.addObject("reserve", new Reserve()); // �삁�빟 媛앹껜
      
      mav.setViewName("reserve/regReserve");
      
      return mav;
   }

   // �삁�빟�쓣 �벑濡앺븷 �븣 �샇異쒕릺�뒗 硫붿꽌�뱶
   @RequestMapping(value = "reserve/regReserve", method = RequestMethod.POST)
   public ModelAndView registerReserve(Reserve reserve, HttpSession session, HttpServletRequest request) {
      ModelAndView mav = new ModelAndView();
      
      Date date = new Date();
      
      if (date.after(reserve.getReDate())) {
         throw new ProjectException("�삤�뒛 �씠�쟾 �궇吏쒕줈�뒗 �삁�빟�븷 �닔 �뾾�뒿�땲�떎.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }
      
      if(!resCheckDate(reserve)) { // �삁�빟�궇吏� 以묐났 泥댄겕 �븯�뒗 硫붿꽌�뱶
         throw new ProjectException("�삁�빟 �궇吏쒓�� 以묐났�맗�땲�떎.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }

      try {
         service.reserveInsert(reserve);
         mav.setViewName("redirect:resList.sms?id=" + reserve.getId());
         
      } catch (Exception e) {
         
         throw new ProjectException("�쟾遺� �엯�젰�븯�뀛�빞 �빀�땲�떎.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }
      
      return mav;
   }
   
   // �삁�빟 �떆媛� 以묐났 泥댄겕瑜� �븯�뒗 硫붿꽌�뱶
   private boolean resCheckDate(Reserve reserve) {
      
      Room chkRoom = service.getRoom(reserve.getsNo(), reserve.getSrNo());
      
      long startDateMiSec = 0;
      Date startChkDate = new Date();
      long endDateMiSec = 0;
      Date endChkDate = new Date();
      
      // �삁�빟 �떒�쐞 ����엯�씠 �떆媛꾩씤 寃쎌슦
      if(chkRoom.getsResType() == 0) {
         SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH");
         
         // �굹�쓽 �삁�빟 �궇吏쒕���꽣 6�떆媛� �씠�쟾源뚯���쓽 �떆�옉 �떆媛꾩쓣 援ы븳�떎.
         startDateMiSec = reserve.getReDate().getTime() - (1000 * 60 * 60 * reserve.getReCnt());
         startChkDate = new Date(startDateMiSec);
                  
         // �굹�쓽 �삁�빟 �궇吏쒕���꽣 6�떆媛� �씠�썑源뚯���쓽 醫낅즺 �떆媛꾩쓣 援ы븳�떎.
         endDateMiSec = reserve.getReDate().getTime() + (1000 * 60 * 60 * reserve.getReCnt());
         endChkDate = new Date(endDateMiSec);
         
         // �떆�옉 �씪�옄遺��꽣 醫낅즺 �씪�옄源뚯�� 湲곌컙(12�떆媛�)�룞�븞�쓽 �삁�빟 媛앹껜瑜� 遺덈윭�삩�떎.
         List <Reserve> list = service.getReserveDateChkList(transFormat.format(startChkDate), 
               transFormat.format(endChkDate), reserve.getsNo(), reserve.getSrNo());
         
         System.out.println("list : " + list);

         for(Reserve dbRes : list) {
            
            // DB�쓽 �떆媛꾩씠 �삁�빟�떆媛� �씠�쟾�씠�씪硫�..!
            if (dbRes.getReDate().before(reserve.getReDate())) {
               
               long a = reserve.getReDate().getTime();   // �삁�빟�궇吏쒕�� 諛�由ъ꽭而⑥쑝濡�
               long b = dbRes.getReDate().getTime();   // DB�궇吏쒕�� 諛�由ъ꽭而⑥쑝濡�
               
               int c = (int)(a - b / (1000 * 60 * 60)); // �몢 �궇吏쒖쓽 李⑥씠瑜� �떆媛� �떒�쐞濡� 移섑솚
               
               System.out.println("c:" + c);
               
               if(c <= dbRes.getReCnt()) { // DB�긽�쓽 媛��닔蹂대떎 �몢 �궇吏쒖쓽 李⑥씠媛� �옉嫄곕굹 媛숈쑝硫� false瑜� 由ы꽩
                  return false;
               }
            }
            
            // 媛숈�� �떆媛꾨���씪硫�..!
            if (transFormat.format(dbRes.getReDate()).equals(transFormat.format(reserve.getReDate()))) {
               return false;
            }
            
            // DB�쓽 �떆媛꾩씠 �삁�빟�떆媛� �씠�썑�씪硫�..!
            if (dbRes.getReDate().after(reserve.getReDate())) {
               
               long a = dbRes.getReDate().getTime();   // DB�궇吏쒕�� 諛�由ъ꽭而⑥쑝濡�
               long b = reserve.getReDate().getTime();   // �삁�빟�궇吏쒕�� 諛�由ъ꽭而⑥쑝濡�
               
               int c = (int)(a - b / (1000 * 60 * 60)); // �몢 �궇吏쒖쓽 李⑥씠瑜� �떆媛� �떒�쐞濡� 移섑솚
               
               System.out.println("c:" + c);
               
               if(c <= reserve.getReCnt()) { // �굹�쓽 �삁�빟媛��닔蹂대떎 �몢 �궇吏쒖쓽 李⑥씠媛� �옉嫄곕굹 媛숈쑝硫� false瑜� 由ы꽩
                  return false;
               }
            }
         }
      }
      
      // �삁�빟 �떒�쐞 ����엯�씠 �씪�옄�씤 寃쎌슦
      else if(chkRoom.getsResType() == 1) {
         
         SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
                  
         // �굹�쓽 �삁�빟 �궇吏쒕���꽣 6�씪 �씠�쟾源뚯���쓽 �떆�옉�씪�옄瑜� 援ы븳�떎.
         startDateMiSec = reserve.getReDate().getTime() - (1000 * 60 * 60 * 24 * reserve.getReCnt());
         startChkDate = new Date(startDateMiSec);
         
         // �굹�쓽 �삁�빟 �궇吏쒕���꽣 6�씪 �씠�썑源뚯���쓽 醫낅즺�씪�옄瑜� 援ы븳�떎.
         endDateMiSec = reserve.getReDate().getTime() + (1000 * 60 * 60 * 24 * reserve.getReCnt());
         endChkDate = new Date(endDateMiSec);
         
         // �떆�옉 �씪�옄遺��꽣 醫낅즺 �씪�옄源뚯�� 湲곌컙(12�씪)�룞�븞�쓽 �삁�빟 媛앹껜瑜� 遺덈윭�삩�떎.
         List <Reserve> list = service.getReserveDateChkList(transFormat.format(startChkDate), 
               transFormat.format(endChkDate), reserve.getsNo(), reserve.getSrNo());
         
         for(Reserve dbRes : list) {
            // DB�쓽 �궇吏쒓�� �삁�빟�궇吏� �씠�쟾�씠�씪硫�..!
            if (dbRes.getReDate().before(reserve.getReDate())) {
               
               long a = reserve.getReDate().getTime();   // �삁�빟�궇吏쒕�� 諛�由ъ꽭而⑥쑝濡�
               long b = dbRes.getReDate().getTime();   // DB�궇吏쒕�� 諛�由ъ꽭而⑥쑝濡�
               
               int c = (int)(a - b / (1000 * 60 * 60 * 24)); // �몢 �궇吏쒖쓽 李⑥씠瑜� �씪�옄濡� 移섑솚
               
               if(c <= dbRes.getReCnt()) { // DB�긽�쓽 媛��닔蹂대떎 �몢 �궇吏쒖쓽 李⑥씠媛� �옉嫄곕굹 媛숈쑝硫� false瑜� 由ы꽩
                  return false;
               }
            }

            // 媛숈�� �궇吏쒕씪硫�..!
            if (transFormat.format(dbRes.getReDate()).equals(transFormat.format(reserve.getReDate()))) {
               return false;
            }
            
            // DB�쓽 �궇吏쒓�� �삁�빟�궇吏� �씠�썑�씪硫�..!
            if (dbRes.getReDate().after(reserve.getReDate())) {
               
               long a = dbRes.getReDate().getTime();   // DB�궇吏쒕�� 諛�由ъ꽭而⑥쑝濡�
               long b = reserve.getReDate().getTime();   // �삁�빟�궇吏쒕�� 諛�由ъ꽭而⑥쑝濡�
               
               int c = (int)(a - b / (1000 * 60 * 60 * 24)); // �몢 �궇吏쒖쓽 李⑥씠瑜� �씪�옄濡� 移섑솚
               
               if(c <= reserve.getReCnt()) { // �굹�쓽 �삁�빟媛��닔蹂대떎 �몢 �궇吏쒖쓽 李⑥씠媛� �옉嫄곕굹 媛숈쑝硫� false瑜� 由ы꽩
                  return false;
               }
            }
         }
      }
      System.out.println("!!!!!!!!!!!!!!�삁�빟 硫붿꽌�뱶 議곌굔 異⑹” �솗�씤!!!!!!!!!!!!!!!!!");
      return true;
   }

   // �삁�빟�쓣 �닔�젙�븷 �븣 �샇異쒕릺�뒗 硫붿꽌�뱶
   @RequestMapping(value = "reserve/resUpdate", method = RequestMethod.POST)
   public ModelAndView updateReserve(Reserve reserve, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveUpdate(reserve);
         mav.setViewName("redirect:resDetail.sms?reNo=" + reserve.getReNo());
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("�삤瑜섍�� 諛쒖깮�븯����뒿�땲�떎.", "resUpdate.sms?reNo=" + reserve.getReNo());
      }

      return mav;

   }

   // �삁�빟 由ъ뒪�듃瑜� �솗�씤�븷 �븣 �샇異쒕릺�뒗 硫붿꽌�뱶 (Guest怨꾩젙�슜)
   @RequestMapping(value = "reserve/resList", method = RequestMethod.GET)
   public ModelAndView reserveList(String id, Integer pageNum, String searchType, String searchContent,
         HttpSession session, String startDate, String endDate) {

      if (pageNum == null || pageNum.toString().equals("")) {
         pageNum = 1;
      }

      ModelAndView mav = new ModelAndView();

      int limit = 100; // �븳 �럹�씠吏��뿉 �굹�삱 寃뚯떆湲��쓽 �닽�옄
      int listcount = service.reserveCount(id, searchType, searchContent); // �몴�떆�맆 珥� 寃뚯떆湲��쓽 �닔


       List<Reserve> reservelist = service.selectReserveList(id, searchType, searchContent, pageNum, limit, startDate, endDate);


      int maxpage = (int) ((double) listcount / limit + 0.95);
      int startpage = ((int) ((pageNum / 10.0 + 0.9) - 1)) * 10 + 1; // �떆�옉�럹�씠吏�
      int endpage = startpage + 9; // 留덉��留� �럹�씠吏�
      if (endpage > maxpage)   endpage = maxpage;
      int reservecnt = listcount - (pageNum - 1) * limit;
         
      mav.addObject("pageNum", pageNum);
      mav.addObject("maxpage", maxpage);
      mav.addObject("startpage", startpage);
      mav.addObject("endpage", endpage);
      mav.addObject("listcount", listcount);
      mav.addObject("list", reservelist);
      mav.addObject("reservecnt", reservecnt);

      return mav;
   }

   // �떊洹쒖삁�빟�젙蹂댁뿉 ����빐 �솗�씤�븷 �븣 �샇異쒕릺�뒗 硫붿꽌�뱶 (Host怨꾩젙�슜)
   @RequestMapping(value = "reserve/hostResInfo", method = RequestMethod.GET)
   public ModelAndView hostReserveInfo(HttpSession session) {

      ModelAndView mav = new ModelAndView();

      String hostId = ((Member) session.getAttribute("loginMember")).getId();
      
      Member hostMember = service.getMember(hostId);

      List<Integer> hostHaveBuild = service.hostHaveBuildsNo(hostId); // �빐�떦 Host �븵�쑝濡� �벑濡앸맂 Building�뱾�쓽 嫄대Ъ愿�由щ쾲�샇瑜� List濡� ����옣

      List<Building> list = new ArrayList<Building>();
      
      int buildCnt = service.hostBuildCount(hostId);
      
      int questCnt = 0;

      for (Integer sNo : hostHaveBuild) {   // 嫄대Ъ愿�由щ퀎濡� Building �젙蹂대�� 媛��졇����꽌 list<Building>�뿉 ����옣
         list.add(service.selectHostReserveInfo(hostId, sNo));
      }
      
      for (Integer sNo : hostHaveBuild) {
         questCnt += service.hostBoardCountQuest(sNo);
      }

      mav.addObject("list", list);
      mav.addObject("hMember", hostMember);
      mav.addObject("buildCnt", buildCnt);
      mav.addObject("questCnt", questCnt);

      return mav;
   }

   // 嫄대Ъ 蹂꾨줈 �삁�빟 由ъ뒪�듃瑜� �솗�씤�븷 �븣 �샇異쒕릺�뒗 硫붿꽌�뱶 (Host怨꾩젙�슜)
   @RequestMapping(value = "reserve/hostResList", method = RequestMethod.GET)
   public ModelAndView hostReserveList(Integer sNo, Integer pageNum, String searchType, String searchContent, HttpSession session,
         String startDate, String endDate) {

      if (pageNum == null || pageNum.toString().equals("")) {
         pageNum = 1;
      }

      ModelAndView mav = new ModelAndView();

      int limit = 100; // �븳 �럹�씠吏��뿉 �굹�삱 寃뚯떆湲��쓽 �닽�옄
      
      int listcount = service.hostReserveCount(sNo, searchType, searchContent); // �몴�떆�맆 珥� 寃뚯떆湲��쓽 �닔

      List<Reserve> reservelist = service.selectHostReserveList(sNo, searchType, searchContent, pageNum, limit, startDate, endDate);

      int maxpage = (int) ((double) listcount / limit + 0.95);
      int startpage = ((int) ((pageNum / 10.0 + 0.9) - 1)) * 10 + 1; // �떆�옉�럹�씠吏�
      int endpage = startpage + 9; // 留덉��留� �럹�씠吏�
      if (endpage > maxpage)
         endpage = maxpage;
      int reservecnt = listcount - (pageNum - 1) * limit;
            
      mav.addObject("pageNum", pageNum);
      mav.addObject("maxpage", maxpage);
      mav.addObject("startpage", startpage);
      mav.addObject("endpage", endpage);
      mav.addObject("listcount", listcount);
      mav.addObject("list", reservelist);
      mav.addObject("reservecnt", reservecnt);
      mav.addObject("sNo", sNo);
      
      if(searchType != null && !searchType.equals("")) {
         mav.addObject("searchType", searchType);
      }

      return mav;
   }

   // �삁�빟 �젙蹂� �솗�씤 �븷 �븣 �궗�슜�븯�뒗 硫붿꽌�뱶
   @RequestMapping(value = "reserve/resDetail", method = RequestMethod.GET)
   public ModelAndView detailReserve2(Integer reNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Reserve reserve = service.getReserve(reNo);
      
      Room room = new Room();

      room = service.getRoom(reserve.getsNo(), reserve.getSrNo());
      
      Date endDate = new Date();
      long plusTime = reserve.getReDate().getTime();
      
      if(room.getsResType() == 0) { // �삁�빟�솕硫댁뿉�꽌 蹂댁뿬吏� �궡�슜
         endDate.setTime(plusTime + (3600000 * reserve.getReCnt()));
      } else {
         endDate.setTime(plusTime + (86400000 * reserve.getReCnt()));
      }

      mav.addObject("reserve", reserve);
      mav.addObject("room", room); // �꽭遺�怨듦컙�뿉 ����븳 �꽭遺��젙蹂�
      mav.addObject("endDate", endDate);

      return mav;

   }

   // Host怨꾩젙�씠 寃곗젣 �솗�씤�븷 �븣 �궗�슜�븯�뒗 硫붿꽌�뱶
   @RequestMapping(value = "reserve/hostResConfirm", method = RequestMethod.GET)
   public ModelAndView hostReserveConfirm(Integer reNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      String hostId = ((Member)session.getAttribute("loginMember")).getId();
      
      try {
         Integer dbSNo = service.getReserve(reNo).getsNo();
         List<Integer> hostSNoList = service.hostHaveBuildsNo(hostId);

         for (Integer hostSNo : hostSNoList) {
            if (hostSNo.intValue() == dbSNo.intValue()) {
               service.hostPaymentConfirm(reNo);
               mav.setViewName("redirect:/reserve/hostResInfo.sms");
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("�삤瑜섍�� 諛쒖깮�븯����뒿�땲�떎.", "redirect:/reserve/hostResInfo.sms");
      }

      return mav;
   }

   // �삁�빟 痍⑥냼 �옉�뾽�떆 �샇異쒕릺�뒗 硫붿꽌�뱶
   @RequestMapping(value = "reserve/resCancel", method = RequestMethod.GET)
   public ModelAndView reserveCancel(Integer reNo, Integer reStat, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveCancel(reNo, reStat);
         mav.setViewName("redirect:/main.sms");
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("�삤瑜섍�� 諛쒖깮�븯����뒿�땲�떎.", "reserve/list.sms");
      }
      return mav;
   }
   
   // �삁�빟 痍⑥냼 �옉�뾽�떆 �샇異쒕릺�뒗 硫붿꽌�뱶
   @RequestMapping(value = "reserve/hostResCancel", method = RequestMethod.GET)
   public ModelAndView hostResCancel(Integer reNo, Integer reStat, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveCancel(reNo, reStat);
         mav.setViewName("redirect:/main.sms");
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("�삤瑜섍�� 諛쒖깮�븯����뒿�땲�떎.", "reserve/list.sms");
      }
      return mav;
   }
   
   // �삁�빟 �벑濡� �솕硫댁뿉�꽌 �듅�젙 怨듦컙�쓽 �삁�빟 �솗�씤 �떆 �샇異쒕릺�뒗 硫붿꽌�뱶
   @RequestMapping(value="reserve/reserveCheckList", method=RequestMethod.GET)
   public ModelAndView resChkList(Integer sNo, Integer sRNo, HttpSession session) {
      
      ModelAndView mav = new ModelAndView();
      
      Room room = service.getRoom(sNo, sRNo);

      int chkCnt = service.reserveChkCnt(sNo, sRNo);
      List<Reserve> chkList = service.reserveChkList(sNo, sRNo);
      
      mav.addObject("chkCnt", chkCnt);
      mav.addObject("chkList", chkList);
      mav.addObject("sResType", room.getsResType());
      
      return mav;
      
   }

   // �삁�빟�뾽臾� 愿��젴�븯�뿬 Default �샇異쒓컪�쑝濡� 吏��젙�븳 硫붿꽌�뱶 : �듅�젙 �삁�빟 蹂닿린, �삁�빟 �벑濡� �솕硫�
   @RequestMapping(value = "reserve/*", method = RequestMethod.GET)
   public ModelAndView detailReserve(Integer reNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Reserve reserve = new Reserve();

      // �삁�빟�씠 �엳�떎硫� �빐�떦 �삁�빟�젙蹂대�� 蹂댁뿬二쇨린 �쐞�븳 議곌굔�떇
      if (reNo != null) {
         reserve = service.getReserve(reNo);
      }

      mav.addObject("reserve", reserve);
      return mav;
   }

>>>>>>> branch 'master' of https://github.com/Choiguevara88/SMS.git
}
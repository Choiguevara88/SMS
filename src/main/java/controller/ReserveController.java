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
   
   
   // ¿¹¾àÀ» µî·ÏÇÒ ¶§ È£ÃâµÇ´Â ¸Ş¼­µå : GET
   @RequestMapping(value = "reserve/regReserve", method = RequestMethod.GET)
   public ModelAndView regReserveForm(Integer sNo, Integer sRNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Room room = service.getRoom(sNo, sRNo);
      Building building = service.getMyBuildingOne("" + sNo);
      Member hostMember = service.getMember(building.getId());
      
      mav.addObject("room", room);           // ¿¹¾àÇÒ ·ë Á¤º¸¸¦ °¡Áø °´Ã¼
      mav.addObject("building", building);    // ¿¹¾àÇÒ ºôµù Á¤º¸¸¦ °¡Áø °´Ã¼
      mav.addObject("hostMember", hostMember); // ÇØ´ç È£½ºÆ® Á¤º¸¸¦ °¡Áø °´Ã¼
      mav.addObject("reserve", new Reserve()); // ¿¹¾à °´Ã¼
      
      mav.setViewName("reserve/regReserve");
      
      return mav;
   }

   // ¿¹¾àÀ» µî·ÏÇÒ ¶§ È£ÃâµÇ´Â ¸Ş¼­µå
   @RequestMapping(value = "reserve/regReserve", method = RequestMethod.POST)
   public ModelAndView registerReserve(Reserve reserve, HttpSession session, HttpServletRequest request) {
      ModelAndView mav = new ModelAndView();
      
      Date date = new Date();
      
      if (date.after(reserve.getReDate())) {
         throw new ProjectException("¿À´Ã ÀÌÀü ³¯Â¥·Î´Â ¿¹¾àÇÒ ¼ö ¾ø½À´Ï´Ù.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }
      
      if(!resCheckDate(reserve)) { // ¿¹¾à³¯Â¥ Áßº¹ Ã¼Å© ÇÏ´Â ¸Ş¼­µå
         throw new ProjectException("¿¹¾à ³¯Â¥°¡ Áßº¹µË´Ï´Ù.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }

      try {
         service.reserveInsert(reserve);
         mav.setViewName("redirect:resList.sms?id=" + reserve.getId());
         
      } catch (Exception e) {
         
         throw new ProjectException("ÀüºÎ ÀÔ·ÂÇÏ¼Å¾ß ÇÕ´Ï´Ù.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }
      
      return mav;
   }
   
   // ¿¹¾à ½Ã°£ Áßº¹ Ã¼Å©¸¦ ÇÏ´Â ¸Ş¼­µå
   private boolean resCheckDate(Reserve reserve) {
      
      Room chkRoom = service.getRoom(reserve.getsNo(), reserve.getSrNo());
      
      long startDateMiSec = 0;
      Date startChkDate = new Date();
      long endDateMiSec = 0;
      Date endChkDate = new Date();
      
      // ¿¹¾à ´ÜÀ§ Å¸ÀÔÀÌ ½Ã°£ÀÎ °æ¿ì
      if(chkRoom.getsResType() == 0) {
         SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH");
         
         // ³ªÀÇ ¿¹¾à ³¯Â¥ºÎÅÍ 6½Ã°£ ÀÌÀü±îÁöÀÇ ½ÃÀÛ ½Ã°£À» ±¸ÇÑ´Ù.
         startDateMiSec = reserve.getReDate().getTime() - (1000 * 60 * 60 * reserve.getReCnt());
         startChkDate = new Date(startDateMiSec);
                  
         // ³ªÀÇ ¿¹¾à ³¯Â¥ºÎÅÍ 6½Ã°£ ÀÌÈÄ±îÁöÀÇ Á¾·á ½Ã°£À» ±¸ÇÑ´Ù.
         endDateMiSec = reserve.getReDate().getTime() + (1000 * 60 * 60 * reserve.getReCnt());
         endChkDate = new Date(endDateMiSec);
         
         // ½ÃÀÛ ÀÏÀÚºÎÅÍ Á¾·á ÀÏÀÚ±îÁö ±â°£(12½Ã°£)µ¿¾ÈÀÇ ¿¹¾à °´Ã¼¸¦ ºÒ·¯¿Â´Ù.
         List <Reserve> list = service.getReserveDateChkList(transFormat.format(startChkDate), 
               transFormat.format(endChkDate), reserve.getsNo(), reserve.getSrNo());
         
         System.out.println("list : " + list);

         for(Reserve dbRes : list) {
            
            // DBÀÇ ½Ã°£ÀÌ ¿¹¾à½Ã°£ ÀÌÀüÀÌ¶ó¸é..!
            if (dbRes.getReDate().before(reserve.getReDate())) {
               
               long a = reserve.getReDate().getTime();   // ¿¹¾à³¯Â¥¸¦ ¹Ğ¸®¼¼ÄÁÀ¸·Î
               long b = dbRes.getReDate().getTime();   // DB³¯Â¥¸¦ ¹Ğ¸®¼¼ÄÁÀ¸·Î
               
               int c = (int)(a - b / (1000 * 60 * 60)); // µÎ ³¯Â¥ÀÇ Â÷ÀÌ¸¦ ½Ã°£ ´ÜÀ§·Î Ä¡È¯
               
               System.out.println("c:" + c);
               
               if(c <= dbRes.getReCnt()) { // DB»óÀÇ °¹¼öº¸´Ù µÎ ³¯Â¥ÀÇ Â÷ÀÌ°¡ ÀÛ°Å³ª °°À¸¸é false¸¦ ¸®ÅÏ
                  return false;
               }
            }
            
            // °°Àº ½Ã°£´ë¶ó¸é..!
            if (transFormat.format(dbRes.getReDate()).equals(transFormat.format(reserve.getReDate()))) {
               return false;
            }
            
            // DBÀÇ ½Ã°£ÀÌ ¿¹¾à½Ã°£ ÀÌÈÄ¶ó¸é..!
            if (dbRes.getReDate().after(reserve.getReDate())) {
               
               long a = dbRes.getReDate().getTime();   // DB³¯Â¥¸¦ ¹Ğ¸®¼¼ÄÁÀ¸·Î
               long b = reserve.getReDate().getTime();   // ¿¹¾à³¯Â¥¸¦ ¹Ğ¸®¼¼ÄÁÀ¸·Î
               
               int c = (int)(a - b / (1000 * 60 * 60)); // µÎ ³¯Â¥ÀÇ Â÷ÀÌ¸¦ ½Ã°£ ´ÜÀ§·Î Ä¡È¯
               
               System.out.println("c:" + c);
               
               if(c <= reserve.getReCnt()) { // ³ªÀÇ ¿¹¾à°¹¼öº¸´Ù µÎ ³¯Â¥ÀÇ Â÷ÀÌ°¡ ÀÛ°Å³ª °°À¸¸é false¸¦ ¸®ÅÏ
                  return false;
               }
            }
         }
      }
      
      // ¿¹¾à ´ÜÀ§ Å¸ÀÔÀÌ ÀÏÀÚÀÎ °æ¿ì
      else if(chkRoom.getsResType() == 1) {
         
         SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
                  
         // ³ªÀÇ ¿¹¾à ³¯Â¥ºÎÅÍ 6ÀÏ ÀÌÀü±îÁöÀÇ ½ÃÀÛÀÏÀÚ¸¦ ±¸ÇÑ´Ù.
         startDateMiSec = reserve.getReDate().getTime() - (1000 * 60 * 60 * 24 * reserve.getReCnt());
         startChkDate = new Date(startDateMiSec);
         
         // ³ªÀÇ ¿¹¾à ³¯Â¥ºÎÅÍ 6ÀÏ ÀÌÈÄ±îÁöÀÇ Á¾·áÀÏÀÚ¸¦ ±¸ÇÑ´Ù.
         endDateMiSec = reserve.getReDate().getTime() + (1000 * 60 * 60 * 24 * reserve.getReCnt());
         endChkDate = new Date(endDateMiSec);
         
         // ½ÃÀÛ ÀÏÀÚºÎÅÍ Á¾·á ÀÏÀÚ±îÁö ±â°£(12ÀÏ)µ¿¾ÈÀÇ ¿¹¾à °´Ã¼¸¦ ºÒ·¯¿Â´Ù.
         List <Reserve> list = service.getReserveDateChkList(transFormat.format(startChkDate), 
               transFormat.format(endChkDate), reserve.getsNo(), reserve.getSrNo());
         
         for(Reserve dbRes : list) {
            // DBÀÇ ³¯Â¥°¡ ¿¹¾à³¯Â¥ ÀÌÀüÀÌ¶ó¸é..!
            if (dbRes.getReDate().before(reserve.getReDate())) {
               
               long a = reserve.getReDate().getTime();   // ¿¹¾à³¯Â¥¸¦ ¹Ğ¸®¼¼ÄÁÀ¸·Î
               long b = dbRes.getReDate().getTime();   // DB³¯Â¥¸¦ ¹Ğ¸®¼¼ÄÁÀ¸·Î
               
               int c = (int)(a - b / (1000 * 60 * 60 * 24)); // µÎ ³¯Â¥ÀÇ Â÷ÀÌ¸¦ ÀÏÀÚ·Î Ä¡È¯
               
               if(c <= dbRes.getReCnt()) { // DB»óÀÇ °¹¼öº¸´Ù µÎ ³¯Â¥ÀÇ Â÷ÀÌ°¡ ÀÛ°Å³ª °°À¸¸é false¸¦ ¸®ÅÏ
                  return false;
               }
            }

            // °°Àº ³¯Â¥¶ó¸é..!
            if (transFormat.format(dbRes.getReDate()).equals(transFormat.format(reserve.getReDate()))) {
               return false;
            }
            
            // DBÀÇ ³¯Â¥°¡ ¿¹¾à³¯Â¥ ÀÌÈÄ¶ó¸é..!
            if (dbRes.getReDate().after(reserve.getReDate())) {
               
               long a = dbRes.getReDate().getTime();   // DB³¯Â¥¸¦ ¹Ğ¸®¼¼ÄÁÀ¸·Î
               long b = reserve.getReDate().getTime();   // ¿¹¾à³¯Â¥¸¦ ¹Ğ¸®¼¼ÄÁÀ¸·Î
               
               int c = (int)(a - b / (1000 * 60 * 60 * 24)); // µÎ ³¯Â¥ÀÇ Â÷ÀÌ¸¦ ÀÏÀÚ·Î Ä¡È¯
               
               if(c <= reserve.getReCnt()) { // ³ªÀÇ ¿¹¾à°¹¼öº¸´Ù µÎ ³¯Â¥ÀÇ Â÷ÀÌ°¡ ÀÛ°Å³ª °°À¸¸é false¸¦ ¸®ÅÏ
                  return false;
               }
            }
         }
      }
      System.out.println("!!!!!!!!!!!!!!¿¹¾à ¸Ş¼­µå Á¶°Ç ÃæÁ· È®ÀÎ!!!!!!!!!!!!!!!!!");
      return true;
   }

   // ¿¹¾àÀ» ¼öÁ¤ÇÒ ¶§ È£ÃâµÇ´Â ¸Ş¼­µå
   @RequestMapping(value = "reserve/resUpdate", method = RequestMethod.POST)
   public ModelAndView updateReserve(Reserve reserve, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveUpdate(reserve);
         mav.setViewName("redirect:resDetail.sms?reNo=" + reserve.getReNo());
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("¿À·ù°¡ ¹ß»ıÇÏ¿´½À´Ï´Ù.", "resUpdate.sms?reNo=" + reserve.getReNo());
      }

      return mav;

   }

   // ¿¹¾à ¸®½ºÆ®¸¦ È®ÀÎÇÒ ¶§ È£ÃâµÇ´Â ¸Ş¼­µå (Guest°èÁ¤¿ë)
   @RequestMapping(value = "reserve/resList", method = RequestMethod.GET)
   public ModelAndView reserveList(String id, Integer pageNum, String searchType, String searchContent,
         HttpSession session, String startDate, String endDate) {

      if (pageNum == null || pageNum.toString().equals("")) {
         pageNum = 1;
      }

      ModelAndView mav = new ModelAndView();

      int limit = 100; // ÇÑ ÆäÀÌÁö¿¡ ³ª¿Ã °Ô½Ã±ÛÀÇ ¼ıÀÚ
      int listcount = service.reserveCount(id, searchType, searchContent); // Ç¥½ÃµÉ ÃÑ °Ô½Ã±ÛÀÇ ¼ö


       List<Reserve> reservelist = service.selectReserveList(id, searchType, searchContent, pageNum, limit, startDate, endDate);


      int maxpage = (int) ((double) listcount / limit + 0.95);
      int startpage = ((int) ((pageNum / 10.0 + 0.9) - 1)) * 10 + 1; // ½ÃÀÛÆäÀÌÁö
      int endpage = startpage + 9; // ¸¶Áö¸· ÆäÀÌÁö
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

   // ½Å±Ô¿¹¾àÁ¤º¸¿¡ ´ëÇØ È®ÀÎÇÒ ¶§ È£ÃâµÇ´Â ¸Ş¼­µå (Host°èÁ¤¿ë)
   @RequestMapping(value = "reserve/hostResInfo", method = RequestMethod.GET)
   public ModelAndView hostReserveInfo(HttpSession session) {

      ModelAndView mav = new ModelAndView();

      String hostId = ((Member) session.getAttribute("loginMember")).getId();
      
      Member hostMember = service.getMember(hostId);

      List<Integer> hostHaveBuild = service.hostHaveBuildsNo(hostId); // ÇØ´ç Host ¾ÕÀ¸·Î µî·ÏµÈ BuildingµéÀÇ °Ç¹°°ü¸®¹øÈ£¸¦ List·Î ÀúÀå

      List<Building> list = new ArrayList<Building>();
      
      int buildCnt = service.hostBuildCount(hostId);
      
      int questCnt = 0;

      for (Integer sNo : hostHaveBuild) {   // °Ç¹°°ü¸®º°·Î Building Á¤º¸¸¦ °¡Á®¿Í¼­ list<Building>¿¡ ÀúÀå
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

   // °Ç¹° º°·Î ¿¹¾à ¸®½ºÆ®¸¦ È®ÀÎÇÒ ¶§ È£ÃâµÇ´Â ¸Ş¼­µå (Host°èÁ¤¿ë)
   @RequestMapping(value = "reserve/hostResList", method = RequestMethod.GET)
   public ModelAndView hostReserveList(Integer sNo, Integer pageNum, String searchType, String searchContent, HttpSession session,
         String startDate, String endDate) {

      if (pageNum == null || pageNum.toString().equals("")) {
         pageNum = 1;
      }

      ModelAndView mav = new ModelAndView();

      int limit = 100; // ÇÑ ÆäÀÌÁö¿¡ ³ª¿Ã °Ô½Ã±ÛÀÇ ¼ıÀÚ
      
      int listcount = service.hostReserveCount(sNo, searchType, searchContent); // Ç¥½ÃµÉ ÃÑ °Ô½Ã±ÛÀÇ ¼ö

      List<Reserve> reservelist = service.selectHostReserveList(sNo, searchType, searchContent, pageNum, limit, startDate, endDate);

      int maxpage = (int) ((double) listcount / limit + 0.95);
      int startpage = ((int) ((pageNum / 10.0 + 0.9) - 1)) * 10 + 1; // ½ÃÀÛÆäÀÌÁö
      int endpage = startpage + 9; // ¸¶Áö¸· ÆäÀÌÁö
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

   // ¿¹¾à Á¤º¸ È®ÀÎ ÇÒ ¶§ »ç¿ëÇÏ´Â ¸Ş¼­µå
   @RequestMapping(value = "reserve/resDetail", method = RequestMethod.GET)
   public ModelAndView detailReserve2(Integer reNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Reserve reserve = service.getReserve(reNo);
      
      Room room = new Room();

      room = service.getRoom(reserve.getsNo(), reserve.getSrNo());
      
      Date endDate = new Date();
      long plusTime = reserve.getReDate().getTime();
      
      if(room.getsResType() == 0) { // ¿¹¾àÈ­¸é¿¡¼­ º¸¿©Áú ³»¿ë
         endDate.setTime(plusTime + (3600000 * reserve.getReCnt()));
      } else {
         endDate.setTime(plusTime + (86400000 * reserve.getReCnt()));
      }

      mav.addObject("reserve", reserve);
      mav.addObject("room", room); // ¼¼ºÎ°ø°£¿¡ ´ëÇÑ ¼¼ºÎÁ¤º¸
      mav.addObject("endDate", endDate);

      return mav;

   }

   // Host°èÁ¤ÀÌ °áÁ¦ È®ÀÎÇÒ ¶§ »ç¿ëÇÏ´Â ¸Ş¼­µå
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
         throw new ProjectException("¿À·ù°¡ ¹ß»ıÇÏ¿´½À´Ï´Ù.", "redirect:/reserve/hostResInfo.sms");
      }

      return mav;
   }

   // ¿¹¾à Ãë¼Ò ÀÛ¾÷½Ã È£ÃâµÇ´Â ¸Ş¼­µå
   @RequestMapping(value = "reserve/resCancel", method = RequestMethod.GET)
   public ModelAndView reserveCancel(Integer reNo, Integer reStat, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveCancel(reNo, reStat);
         mav.setViewName("redirect:/main.sms");
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("¿À·ù°¡ ¹ß»ıÇÏ¿´½À´Ï´Ù.", "reserve/list.sms");
      }
      return mav;
   }
   
   // ¿¹¾à Ãë¼Ò ÀÛ¾÷½Ã È£ÃâµÇ´Â ¸Ş¼­µå
   @RequestMapping(value = "reserve/hostResCancel", method = RequestMethod.GET)
   public ModelAndView hostResCancel(Integer reNo, Integer reStat, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveCancel(reNo, reStat);
         mav.setViewName("redirect:/main.sms");
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("¿À·ù°¡ ¹ß»ıÇÏ¿´½À´Ï´Ù.", "reserve/list.sms");
      }
      return mav;
   }
   
   // ¿¹¾à µî·Ï È­¸é¿¡¼­ Æ¯Á¤ °ø°£ÀÇ ¿¹¾à È®ÀÎ ½Ã È£ÃâµÇ´Â ¸Ş¼­µå
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

   // ¿¹¾à¾÷¹« °ü·ÃÇÏ¿© Default È£Ãâ°ªÀ¸·Î ÁöÁ¤ÇÑ ¸Ş¼­µå : Æ¯Á¤ ¿¹¾à º¸±â, ¿¹¾à µî·Ï È­¸é
   @RequestMapping(value = "reserve/*", method = RequestMethod.GET)
   public ModelAndView detailReserve(Integer reNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Reserve reserve = new Reserve();

      // ¿¹¾àÀÌ ÀÖ´Ù¸é ÇØ´ç ¿¹¾àÁ¤º¸¸¦ º¸¿©ÁÖ±â À§ÇÑ Á¶°Ç½Ä
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
   
   
   // ì˜ˆì•½ì„ ë“±ë¡í•  ë•Œ í˜¸ì¶œë˜ëŠ” ë©”ì„œë“œ : GET
   @RequestMapping(value = "reserve/regReserve", method = RequestMethod.GET)
   public ModelAndView regReserveForm(Integer sNo, Integer sRNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Room room = service.getRoom(sNo, sRNo);
      Building building = service.getMyBuildingOne("" + sNo);
      Member hostMember = service.getMember(building.getId());
      
      mav.addObject("room", room);           // ì˜ˆì•½í•  ë£¸ ì •ë³´ë¥¼ ê°€ì§„ ê°ì²´
      mav.addObject("building", building);    // ì˜ˆì•½í•  ë¹Œë”© ì •ë³´ë¥¼ ê°€ì§„ ê°ì²´
      mav.addObject("hostMember", hostMember); // í•´ë‹¹ í˜¸ìŠ¤íŠ¸ ì •ë³´ë¥¼ ê°€ì§„ ê°ì²´
      mav.addObject("reserve", new Reserve()); // ì˜ˆì•½ ê°ì²´
      
      mav.setViewName("reserve/regReserve");
      
      return mav;
   }

   // ì˜ˆì•½ì„ ë“±ë¡í•  ë•Œ í˜¸ì¶œë˜ëŠ” ë©”ì„œë“œ
   @RequestMapping(value = "reserve/regReserve", method = RequestMethod.POST)
   public ModelAndView registerReserve(Reserve reserve, HttpSession session, HttpServletRequest request) {
      ModelAndView mav = new ModelAndView();
      
      Date date = new Date();
      
      if (date.after(reserve.getReDate())) {
         throw new ProjectException("ì˜¤ëŠ˜ ì´ì „ ë‚ ì§œë¡œëŠ” ì˜ˆì•½í•  ìˆ˜ ì—†ìŠµë‹ˆë‹¤.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }
      
      if(!resCheckDate(reserve)) { // ì˜ˆì•½ë‚ ì§œ ì¤‘ë³µ ì²´í¬ í•˜ëŠ” ë©”ì„œë“œ
         throw new ProjectException("ì˜ˆì•½ ë‚ ì§œê°€ ì¤‘ë³µë©ë‹ˆë‹¤.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }

      try {
         service.reserveInsert(reserve);
         mav.setViewName("redirect:resList.sms?id=" + reserve.getId());
         
      } catch (Exception e) {
         
         throw new ProjectException("ì „ë¶€ ì…ë ¥í•˜ì…”ì•¼ í•©ë‹ˆë‹¤.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }
      
      return mav;
   }
   
   // ì˜ˆì•½ ì‹œê°„ ì¤‘ë³µ ì²´í¬ë¥¼ í•˜ëŠ” ë©”ì„œë“œ
   private boolean resCheckDate(Reserve reserve) {
      
      Room chkRoom = service.getRoom(reserve.getsNo(), reserve.getSrNo());
      
      long startDateMiSec = 0;
      Date startChkDate = new Date();
      long endDateMiSec = 0;
      Date endChkDate = new Date();
      
      // ì˜ˆì•½ ë‹¨ìœ„ íƒ€ì…ì´ ì‹œê°„ì¸ ê²½ìš°
      if(chkRoom.getsResType() == 0) {
         SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH");
         
         // ë‚˜ì˜ ì˜ˆì•½ ë‚ ì§œë¶€í„° 6ì‹œê°„ ì´ì „ê¹Œì§€ì˜ ì‹œì‘ ì‹œê°„ì„ êµ¬í•œë‹¤.
         startDateMiSec = reserve.getReDate().getTime() - (1000 * 60 * 60 * reserve.getReCnt());
         startChkDate = new Date(startDateMiSec);
                  
         // ë‚˜ì˜ ì˜ˆì•½ ë‚ ì§œë¶€í„° 6ì‹œê°„ ì´í›„ê¹Œì§€ì˜ ì¢…ë£Œ ì‹œê°„ì„ êµ¬í•œë‹¤.
         endDateMiSec = reserve.getReDate().getTime() + (1000 * 60 * 60 * reserve.getReCnt());
         endChkDate = new Date(endDateMiSec);
         
         // ì‹œì‘ ì¼ìë¶€í„° ì¢…ë£Œ ì¼ìê¹Œì§€ ê¸°ê°„(12ì‹œê°„)ë™ì•ˆì˜ ì˜ˆì•½ ê°ì²´ë¥¼ ë¶ˆëŸ¬ì˜¨ë‹¤.
         List <Reserve> list = service.getReserveDateChkList(transFormat.format(startChkDate), 
               transFormat.format(endChkDate), reserve.getsNo(), reserve.getSrNo());
         
         System.out.println("list : " + list);

         for(Reserve dbRes : list) {
            
            // DBì˜ ì‹œê°„ì´ ì˜ˆì•½ì‹œê°„ ì´ì „ì´ë¼ë©´..!
            if (dbRes.getReDate().before(reserve.getReDate())) {
               
               long a = reserve.getReDate().getTime();   // ì˜ˆì•½ë‚ ì§œë¥¼ ë°€ë¦¬ì„¸ì»¨ìœ¼ë¡œ
               long b = dbRes.getReDate().getTime();   // DBë‚ ì§œë¥¼ ë°€ë¦¬ì„¸ì»¨ìœ¼ë¡œ
               
               int c = (int)(a - b / (1000 * 60 * 60)); // ë‘ ë‚ ì§œì˜ ì°¨ì´ë¥¼ ì‹œê°„ ë‹¨ìœ„ë¡œ ì¹˜í™˜
               
               System.out.println("c:" + c);
               
               if(c <= dbRes.getReCnt()) { // DBìƒì˜ ê°¯ìˆ˜ë³´ë‹¤ ë‘ ë‚ ì§œì˜ ì°¨ì´ê°€ ì‘ê±°ë‚˜ ê°™ìœ¼ë©´ falseë¥¼ ë¦¬í„´
                  return false;
               }
            }
            
            // ê°™ì€ ì‹œê°„ëŒ€ë¼ë©´..!
            if (transFormat.format(dbRes.getReDate()).equals(transFormat.format(reserve.getReDate()))) {
               return false;
            }
            
            // DBì˜ ì‹œê°„ì´ ì˜ˆì•½ì‹œê°„ ì´í›„ë¼ë©´..!
            if (dbRes.getReDate().after(reserve.getReDate())) {
               
               long a = dbRes.getReDate().getTime();   // DBë‚ ì§œë¥¼ ë°€ë¦¬ì„¸ì»¨ìœ¼ë¡œ
               long b = reserve.getReDate().getTime();   // ì˜ˆì•½ë‚ ì§œë¥¼ ë°€ë¦¬ì„¸ì»¨ìœ¼ë¡œ
               
               int c = (int)(a - b / (1000 * 60 * 60)); // ë‘ ë‚ ì§œì˜ ì°¨ì´ë¥¼ ì‹œê°„ ë‹¨ìœ„ë¡œ ì¹˜í™˜
               
               System.out.println("c:" + c);
               
               if(c <= reserve.getReCnt()) { // ë‚˜ì˜ ì˜ˆì•½ê°¯ìˆ˜ë³´ë‹¤ ë‘ ë‚ ì§œì˜ ì°¨ì´ê°€ ì‘ê±°ë‚˜ ê°™ìœ¼ë©´ falseë¥¼ ë¦¬í„´
                  return false;
               }
            }
         }
      }
      
      // ì˜ˆì•½ ë‹¨ìœ„ íƒ€ì…ì´ ì¼ìì¸ ê²½ìš°
      else if(chkRoom.getsResType() == 1) {
         
         SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
                  
         // ë‚˜ì˜ ì˜ˆì•½ ë‚ ì§œë¶€í„° 6ì¼ ì´ì „ê¹Œì§€ì˜ ì‹œì‘ì¼ìë¥¼ êµ¬í•œë‹¤.
         startDateMiSec = reserve.getReDate().getTime() - (1000 * 60 * 60 * 24 * reserve.getReCnt());
         startChkDate = new Date(startDateMiSec);
         
         // ë‚˜ì˜ ì˜ˆì•½ ë‚ ì§œë¶€í„° 6ì¼ ì´í›„ê¹Œì§€ì˜ ì¢…ë£Œì¼ìë¥¼ êµ¬í•œë‹¤.
         endDateMiSec = reserve.getReDate().getTime() + (1000 * 60 * 60 * 24 * reserve.getReCnt());
         endChkDate = new Date(endDateMiSec);
         
         // ì‹œì‘ ì¼ìë¶€í„° ì¢…ë£Œ ì¼ìê¹Œì§€ ê¸°ê°„(12ì¼)ë™ì•ˆì˜ ì˜ˆì•½ ê°ì²´ë¥¼ ë¶ˆëŸ¬ì˜¨ë‹¤.
         List <Reserve> list = service.getReserveDateChkList(transFormat.format(startChkDate), 
               transFormat.format(endChkDate), reserve.getsNo(), reserve.getSrNo());
         
         for(Reserve dbRes : list) {
            // DBì˜ ë‚ ì§œê°€ ì˜ˆì•½ë‚ ì§œ ì´ì „ì´ë¼ë©´..!
            if (dbRes.getReDate().before(reserve.getReDate())) {
               
               long a = reserve.getReDate().getTime();   // ì˜ˆì•½ë‚ ì§œë¥¼ ë°€ë¦¬ì„¸ì»¨ìœ¼ë¡œ
               long b = dbRes.getReDate().getTime();   // DBë‚ ì§œë¥¼ ë°€ë¦¬ì„¸ì»¨ìœ¼ë¡œ
               
               int c = (int)(a - b / (1000 * 60 * 60 * 24)); // ë‘ ë‚ ì§œì˜ ì°¨ì´ë¥¼ ì¼ìë¡œ ì¹˜í™˜
               
               if(c <= dbRes.getReCnt()) { // DBìƒì˜ ê°¯ìˆ˜ë³´ë‹¤ ë‘ ë‚ ì§œì˜ ì°¨ì´ê°€ ì‘ê±°ë‚˜ ê°™ìœ¼ë©´ falseë¥¼ ë¦¬í„´
                  return false;
               }
            }

            // ê°™ì€ ë‚ ì§œë¼ë©´..!
            if (transFormat.format(dbRes.getReDate()).equals(transFormat.format(reserve.getReDate()))) {
               return false;
            }
            
            // DBì˜ ë‚ ì§œê°€ ì˜ˆì•½ë‚ ì§œ ì´í›„ë¼ë©´..!
            if (dbRes.getReDate().after(reserve.getReDate())) {
               
               long a = dbRes.getReDate().getTime();   // DBë‚ ì§œë¥¼ ë°€ë¦¬ì„¸ì»¨ìœ¼ë¡œ
               long b = reserve.getReDate().getTime();   // ì˜ˆì•½ë‚ ì§œë¥¼ ë°€ë¦¬ì„¸ì»¨ìœ¼ë¡œ
               
               int c = (int)(a - b / (1000 * 60 * 60 * 24)); // ë‘ ë‚ ì§œì˜ ì°¨ì´ë¥¼ ì¼ìë¡œ ì¹˜í™˜
               
               if(c <= reserve.getReCnt()) { // ë‚˜ì˜ ì˜ˆì•½ê°¯ìˆ˜ë³´ë‹¤ ë‘ ë‚ ì§œì˜ ì°¨ì´ê°€ ì‘ê±°ë‚˜ ê°™ìœ¼ë©´ falseë¥¼ ë¦¬í„´
                  return false;
               }
            }
         }
      }
      System.out.println("!!!!!!!!!!!!!!ì˜ˆì•½ ë©”ì„œë“œ ì¡°ê±´ ì¶©ì¡± í™•ì¸!!!!!!!!!!!!!!!!!");
      return true;
   }

   // ì˜ˆì•½ì„ ìˆ˜ì •í•  ë•Œ í˜¸ì¶œë˜ëŠ” ë©”ì„œë“œ
   @RequestMapping(value = "reserve/resUpdate", method = RequestMethod.POST)
   public ModelAndView updateReserve(Reserve reserve, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveUpdate(reserve);
         mav.setViewName("redirect:resDetail.sms?reNo=" + reserve.getReNo());
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("ì˜¤ë¥˜ê°€ ë°œìƒí•˜ì˜€ìŠµë‹ˆë‹¤.", "resUpdate.sms?reNo=" + reserve.getReNo());
      }

      return mav;

   }

   // ì˜ˆì•½ ë¦¬ìŠ¤íŠ¸ë¥¼ í™•ì¸í•  ë•Œ í˜¸ì¶œë˜ëŠ” ë©”ì„œë“œ (Guestê³„ì •ìš©)
   @RequestMapping(value = "reserve/resList", method = RequestMethod.GET)
   public ModelAndView reserveList(String id, Integer pageNum, String searchType, String searchContent,
         HttpSession session, String startDate, String endDate) {

      if (pageNum == null || pageNum.toString().equals("")) {
         pageNum = 1;
      }

      ModelAndView mav = new ModelAndView();

      int limit = 100; // í•œ í˜ì´ì§€ì— ë‚˜ì˜¬ ê²Œì‹œê¸€ì˜ ìˆ«ì
      int listcount = service.reserveCount(id, searchType, searchContent); // í‘œì‹œë  ì´ ê²Œì‹œê¸€ì˜ ìˆ˜


       List<Reserve> reservelist = service.selectReserveList(id, searchType, searchContent, pageNum, limit, startDate, endDate);


      int maxpage = (int) ((double) listcount / limit + 0.95);
      int startpage = ((int) ((pageNum / 10.0 + 0.9) - 1)) * 10 + 1; // ì‹œì‘í˜ì´ì§€
      int endpage = startpage + 9; // ë§ˆì§€ë§‰ í˜ì´ì§€
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

   // ì‹ ê·œì˜ˆì•½ì •ë³´ì— ëŒ€í•´ í™•ì¸í•  ë•Œ í˜¸ì¶œë˜ëŠ” ë©”ì„œë“œ (Hostê³„ì •ìš©)
   @RequestMapping(value = "reserve/hostResInfo", method = RequestMethod.GET)
   public ModelAndView hostReserveInfo(HttpSession session) {

      ModelAndView mav = new ModelAndView();

      String hostId = ((Member) session.getAttribute("loginMember")).getId();
      
      Member hostMember = service.getMember(hostId);

      List<Integer> hostHaveBuild = service.hostHaveBuildsNo(hostId); // í•´ë‹¹ Host ì•ìœ¼ë¡œ ë“±ë¡ëœ Buildingë“¤ì˜ ê±´ë¬¼ê´€ë¦¬ë²ˆí˜¸ë¥¼ Listë¡œ ì €ì¥

      List<Building> list = new ArrayList<Building>();
      
      int buildCnt = service.hostBuildCount(hostId);
      
      int questCnt = 0;

      for (Integer sNo : hostHaveBuild) {   // ê±´ë¬¼ê´€ë¦¬ë³„ë¡œ Building ì •ë³´ë¥¼ ê°€ì ¸ì™€ì„œ list<Building>ì— ì €ì¥
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

   // ê±´ë¬¼ ë³„ë¡œ ì˜ˆì•½ ë¦¬ìŠ¤íŠ¸ë¥¼ í™•ì¸í•  ë•Œ í˜¸ì¶œë˜ëŠ” ë©”ì„œë“œ (Hostê³„ì •ìš©)
   @RequestMapping(value = "reserve/hostResList", method = RequestMethod.GET)
   public ModelAndView hostReserveList(Integer sNo, Integer pageNum, String searchType, String searchContent, HttpSession session,
         String startDate, String endDate) {

      if (pageNum == null || pageNum.toString().equals("")) {
         pageNum = 1;
      }

      ModelAndView mav = new ModelAndView();

      int limit = 100; // í•œ í˜ì´ì§€ì— ë‚˜ì˜¬ ê²Œì‹œê¸€ì˜ ìˆ«ì
      
      int listcount = service.hostReserveCount(sNo, searchType, searchContent); // í‘œì‹œë  ì´ ê²Œì‹œê¸€ì˜ ìˆ˜

      List<Reserve> reservelist = service.selectHostReserveList(sNo, searchType, searchContent, pageNum, limit, startDate, endDate);

      int maxpage = (int) ((double) listcount / limit + 0.95);
      int startpage = ((int) ((pageNum / 10.0 + 0.9) - 1)) * 10 + 1; // ì‹œì‘í˜ì´ì§€
      int endpage = startpage + 9; // ë§ˆì§€ë§‰ í˜ì´ì§€
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

   // ì˜ˆì•½ ì •ë³´ í™•ì¸ í•  ë•Œ ì‚¬ìš©í•˜ëŠ” ë©”ì„œë“œ
   @RequestMapping(value = "reserve/resDetail", method = RequestMethod.GET)
   public ModelAndView detailReserve2(Integer reNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Reserve reserve = service.getReserve(reNo);
      
      Room room = new Room();

      room = service.getRoom(reserve.getsNo(), reserve.getSrNo());
      
      Date endDate = new Date();
      long plusTime = reserve.getReDate().getTime();
      
      if(room.getsResType() == 0) { // ì˜ˆì•½í™”ë©´ì—ì„œ ë³´ì—¬ì§ˆ ë‚´ìš©
         endDate.setTime(plusTime + (3600000 * reserve.getReCnt()));
      } else {
         endDate.setTime(plusTime + (86400000 * reserve.getReCnt()));
      }

      mav.addObject("reserve", reserve);
      mav.addObject("room", room); // ì„¸ë¶€ê³µê°„ì— ëŒ€í•œ ì„¸ë¶€ì •ë³´
      mav.addObject("endDate", endDate);

      return mav;

   }

   // Hostê³„ì •ì´ ê²°ì œ í™•ì¸í•  ë•Œ ì‚¬ìš©í•˜ëŠ” ë©”ì„œë“œ
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
         throw new ProjectException("ì˜¤ë¥˜ê°€ ë°œìƒí•˜ì˜€ìŠµë‹ˆë‹¤.", "redirect:/reserve/hostResInfo.sms");
      }

      return mav;
   }

   // ì˜ˆì•½ ì·¨ì†Œ ì‘ì—…ì‹œ í˜¸ì¶œë˜ëŠ” ë©”ì„œë“œ
   @RequestMapping(value = "reserve/resCancel", method = RequestMethod.GET)
   public ModelAndView reserveCancel(Integer reNo, Integer reStat, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveCancel(reNo, reStat);
         mav.setViewName("redirect:/main.sms");
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("ì˜¤ë¥˜ê°€ ë°œìƒí•˜ì˜€ìŠµë‹ˆë‹¤.", "reserve/list.sms");
      }
      return mav;
   }
   
   // ì˜ˆì•½ ì·¨ì†Œ ì‘ì—…ì‹œ í˜¸ì¶œë˜ëŠ” ë©”ì„œë“œ
   @RequestMapping(value = "reserve/hostResCancel", method = RequestMethod.GET)
   public ModelAndView hostResCancel(Integer reNo, Integer reStat, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveCancel(reNo, reStat);
         mav.setViewName("redirect:/main.sms");
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("ì˜¤ë¥˜ê°€ ë°œìƒí•˜ì˜€ìŠµë‹ˆë‹¤.", "reserve/list.sms");
      }
      return mav;
   }
   
   // ì˜ˆì•½ ë“±ë¡ í™”ë©´ì—ì„œ íŠ¹ì • ê³µê°„ì˜ ì˜ˆì•½ í™•ì¸ ì‹œ í˜¸ì¶œë˜ëŠ” ë©”ì„œë“œ
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

   // ì˜ˆì•½ì—…ë¬´ ê´€ë ¨í•˜ì—¬ Default í˜¸ì¶œê°’ìœ¼ë¡œ ì§€ì •í•œ ë©”ì„œë“œ : íŠ¹ì • ì˜ˆì•½ ë³´ê¸°, ì˜ˆì•½ ë“±ë¡ í™”ë©´
   @RequestMapping(value = "reserve/*", method = RequestMethod.GET)
   public ModelAndView detailReserve(Integer reNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Reserve reserve = new Reserve();

      // ì˜ˆì•½ì´ ìˆë‹¤ë©´ í•´ë‹¹ ì˜ˆì•½ì •ë³´ë¥¼ ë³´ì—¬ì£¼ê¸° ìœ„í•œ ì¡°ê±´ì‹
      if (reNo != null) {
         reserve = service.getReserve(reNo);
      }

      mav.addObject("reserve", reserve);
      return mav;
   }

>>>>>>> branch 'master' of https://github.com/Choiguevara88/SMS.git
}
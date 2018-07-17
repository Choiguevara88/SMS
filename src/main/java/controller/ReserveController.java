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
   
   
   // ¿¹¾àÀ» µî·ÏÇÒ ¶§ È£ÃâµÇ´Â ¸Þ¼­µå : GET
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

   // ¿¹¾àÀ» µî·ÏÇÒ ¶§ È£ÃâµÇ´Â ¸Þ¼­µå
   @RequestMapping(value = "reserve/regReserve", method = RequestMethod.POST)
   public ModelAndView registerReserve(Reserve reserve, HttpSession session, HttpServletRequest request) {
      ModelAndView mav = new ModelAndView();
      
      if(!resCheckDate(reserve)) { // ¿¹¾à³¯Â¥ Áßº¹ Ã¼Å© ÇÏ´Â ¸Þ¼­µå
         throw new ProjectException("¿¹¾à ³¯Â¥°¡ Áßº¹µË´Ï´Ù.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }
	// ???½ì?? ?±ë??? ?? ?¸ì????? ë©?????
	@RequestMapping(value = "reserve/regReserve", method = RequestMethod.POST)
	public ModelAndView registerReserve(Reserve reserve, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		Date date = new Date();
		
		if (date.after(reserve.getReDate())) {
			throw new ProjectException("?¤ë?? ?´ì?? ??ì§?ë¡??? ???½í?? ?? ???µë????.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
		}
		
		if(!resCheckDate(reserve)) { // ???½ë??ì§? ì¤?ë³? ì²´í?? ???? ë©?????
			throw new ProjectException("???? ??ì§?ê°? ì¤?ë³µë?©ë????.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
		}

      try {
         service.reserveInsert(reserve);
         mav.setViewName("redirect:resList.sms?id=" + reserve.getId());
         
      } catch (Exception e) {
         
         throw new ProjectException("ÀüºÎ ÀÔ·ÂÇÏ¼Å¾ß ÇÕ´Ï´Ù.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }
      
      return mav;
   }
   
   // ¿¹¾à ½Ã°£ Áßº¹ Ã¼Å©¸¦ ÇÏ´Â ¸Þ¼­µå
   private boolean resCheckDate(Reserve reserve) {
      
      Room chkRoom = service.getRoom(reserve.getsNo(), reserve.getSrNo());
      
      long startDateMiSec = 0;
      Date startChkDate = new Date();
      long endDateMiSec = 0;
      Date endChkDate = new Date();
      
      System.out.println("chkRoomÀÇ resType : " + chkRoom.getsResType());
      
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
		try {
			service.reserveInsert(reserve);
			mav.setViewName("redirect:resList.sms?id=" + reserve.getId());
			
		} catch (Exception e) {
			
			throw new ProjectException("??ë¶? ???¥í?????? ?©ë????.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
		}
		
		return mav;
	}
	
	// ???? ??ê°? ì¤?ë³? ì²´í?¬ë?? ???? ë©?????
	private boolean resCheckDate(Reserve reserve) {
		
		Room chkRoom = service.getRoom(reserve.getsNo(), reserve.getSrNo());
		
		long startDateMiSec = 0;
		Date startChkDate = new Date();
		long endDateMiSec = 0;
		Date endChkDate = new Date();
		
		// ???? ?¨ì?? ?????? ??ê°??? ê²½ì??
		if(chkRoom.getsResType() == 0) {
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH");
			
			// ???? ???? ??ì§?ë¶??? 6??ê°? ?´ì??ê¹?ì§??? ???? ??ê°??? êµ¬í????.
			startDateMiSec = reserve.getReDate().getTime() - (1000 * 60 * 60 * reserve.getReCnt());
			startChkDate = new Date(startDateMiSec);
						
			// ???? ???? ??ì§?ë¶??? 6??ê°? ?´í??ê¹?ì§??? ì¢?ë£? ??ê°??? êµ¬í????.
			endDateMiSec = reserve.getReDate().getTime() + (1000 * 60 * 60 * reserve.getReCnt());
			endChkDate = new Date(endDateMiSec);
			
			// ???? ?¼ì??ë¶??? ì¢?ë£? ?¼ì??ê¹?ì§? ê¸°ê?(12??ê°?)?????? ???? ê°?ì²´ë?? ë¶??¬ì?¨ë??.
			List <Reserve> list = service.getReserveDateChkList(transFormat.format(startChkDate), 
					transFormat.format(endChkDate), reserve.getsNo(), reserve.getSrNo());
			
			System.out.println("list : " + list);

			for(Reserve dbRes : list) {
				
				// DB?? ??ê°??? ???½ì??ê°? ?´ì???´ë?¼ë©´..!
				if (dbRes.getReDate().before(reserve.getReDate())) {
					
					long a = reserve.getReDate().getTime();	// ???½ë??ì§?ë¥? ë°?ë¦¬ì?¸ì»¨?¼ë?
					long b = dbRes.getReDate().getTime();	// DB??ì§?ë¥? ë°?ë¦¬ì?¸ì»¨?¼ë?
					
					int c = (int)(a - b / (1000 * 60 * 60)); // ?? ??ì§??? ì°¨ì?´ë?? ??ê°? ?¨ì??ë¡? ì¹???
					
					System.out.println("c:" + c);
					
					if(c <= dbRes.getReCnt()) { // DB???? ê°???ë³´ë?? ?? ??ì§??? ì°¨ì?´ê? ??ê±°ë?? ê°??¼ë©´ falseë¥? ë¦¬í??
						return false;
					}
				}
				
				// ê°??? ??ê°????¼ë©´..!
				if (transFormat.format(dbRes.getReDate()).equals(transFormat.format(reserve.getReDate()))) {
					return false;
				}
				
				// DB?? ??ê°??? ???½ì??ê°? ?´í???¼ë©´..!
				if (dbRes.getReDate().after(reserve.getReDate())) {
					
					long a = dbRes.getReDate().getTime();	// DB??ì§?ë¥? ë°?ë¦¬ì?¸ì»¨?¼ë?
					long b = reserve.getReDate().getTime();	// ???½ë??ì§?ë¥? ë°?ë¦¬ì?¸ì»¨?¼ë?
					
					int c = (int)(a - b / (1000 * 60 * 60)); // ?? ??ì§??? ì°¨ì?´ë?? ??ê°? ?¨ì??ë¡? ì¹???
					
					System.out.println("c:" + c);
					
					if(c <= reserve.getReCnt()) { // ???? ???½ê°¯??ë³´ë?? ?? ??ì§??? ì°¨ì?´ê? ??ê±°ë?? ê°??¼ë©´ falseë¥? ë¦¬í??
						return false;
					}
				}
			}
		}
		
		// ???? ?¨ì?? ?????? ?¼ì???? ê²½ì??
		else if(chkRoom.getsResType() == 1) {
			
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
						
			// ???? ???? ??ì§?ë¶??? 6?? ?´ì??ê¹?ì§??? ?????¼ì??ë¥? êµ¬í????.
			startDateMiSec = reserve.getReDate().getTime() - (1000 * 60 * 60 * 24 * reserve.getReCnt());
			startChkDate = new Date(startDateMiSec);
			
			// ???? ???? ??ì§?ë¶??? 6?? ?´í??ê¹?ì§??? ì¢?ë£??¼ì??ë¥? êµ¬í????.
			endDateMiSec = reserve.getReDate().getTime() + (1000 * 60 * 60 * 24 * reserve.getReCnt());
			endChkDate = new Date(endDateMiSec);
			
			// ???? ?¼ì??ë¶??? ì¢?ë£? ?¼ì??ê¹?ì§? ê¸°ê?(12??)?????? ???? ê°?ì²´ë?? ë¶??¬ì?¨ë??.
			List <Reserve> list = service.getReserveDateChkList(transFormat.format(startChkDate), 
					transFormat.format(endChkDate), reserve.getsNo(), reserve.getSrNo());
			
			for(Reserve dbRes : list) {
				// DB?? ??ì§?ê°? ???½ë??ì§? ?´ì???´ë?¼ë©´..!
				if (dbRes.getReDate().before(reserve.getReDate())) {
					
					long a = reserve.getReDate().getTime();	// ???½ë??ì§?ë¥? ë°?ë¦¬ì?¸ì»¨?¼ë?
					long b = dbRes.getReDate().getTime();	// DB??ì§?ë¥? ë°?ë¦¬ì?¸ì»¨?¼ë?
					
					int c = (int)(a - b / (1000 * 60 * 60 * 24)); // ?? ??ì§??? ì°¨ì?´ë?? ?¼ì??ë¡? ì¹???
					
					if(c <= dbRes.getReCnt()) { // DB???? ê°???ë³´ë?? ?? ??ì§??? ì°¨ì?´ê? ??ê±°ë?? ê°??¼ë©´ falseë¥? ë¦¬í??
						return false;
					}
				}

            // °°Àº ³¯Â¥¶ó¸é..!
            if (transFormat.format(dbRes.getReDate()).equals(transFormat.format(reserve.getReDate()))) {
               return false;
            }
            
            // DBÀÇ ³¯Â¥°¡ ¿¹¾à³¯Â¥ ÀÌÈÄ¶ó¸é..!
            if (dbRes.getReDate().after(reserve.getReDate())) {
               
               long a = dbRes.getReDate().getTime();   // DB³¯Â¥¸¦ ¹Ð¸®¼¼ÄÁÀ¸·Î
               long b = reserve.getReDate().getTime();   // ¿¹¾à³¯Â¥¸¦ ¹Ð¸®¼¼ÄÁÀ¸·Î
               
               int c = (int)(a - b / (1000 * 60 * 60 * 24)); // µÎ ³¯Â¥ÀÇ Â÷ÀÌ¸¦ ÀÏÀÚ·Î Ä¡È¯
               
               if(c <= reserve.getReCnt()) { // ³ªÀÇ ¿¹¾à°¹¼öº¸´Ù µÎ ³¯Â¥ÀÇ Â÷ÀÌ°¡ ÀÛ°Å³ª °°À¸¸é false¸¦ ¸®ÅÏ
                  return false;
               }
            }
         }
      }
      System.out.println("!!!!!!!!!!!!!!¿¹¾à ¸Þ¼­µå Á¶°Ç ÃæÁ· È®ÀÎ!!!!!!!!!!!!!!!!!");
      return true;
   }

   // ¿¹¾àÀ» ¼öÁ¤ÇÒ ¶§ È£ÃâµÇ´Â ¸Þ¼­µå
   @RequestMapping(value = "reserve/resUpdate", method = RequestMethod.POST)
   public ModelAndView updateReserve(Reserve reserve, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveUpdate(reserve);
         mav.setViewName("redirect:resDetail.sms?reNo=" + reserve.getReNo());
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("¿À·ù°¡ ¹ß»ýÇÏ¿´½À´Ï´Ù.", "resUpdate.sms?reNo=" + reserve.getReNo());
      }

      return mav;

   }

   // ¿¹¾à ¸®½ºÆ®¸¦ È®ÀÎÇÒ ¶§ È£ÃâµÇ´Â ¸Þ¼­µå (Guest°èÁ¤¿ë)
   @RequestMapping(value = "reserve/resList", method = RequestMethod.GET)
   public ModelAndView reserveList(String id, Integer pageNum, String searchType, String searchContent,
         HttpSession session, String startDate, String endDate) {

      if (pageNum == null || pageNum.toString().equals("")) {
         pageNum = 1;
      }

      ModelAndView mav = new ModelAndView();

      int limit = 100; // ÇÑ ÆäÀÌÁö¿¡ ³ª¿Ã °Ô½Ã±ÛÀÇ ¼ýÀÚ
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

   // ½Å±Ô¿¹¾àÁ¤º¸¿¡ ´ëÇØ È®ÀÎÇÒ ¶§ È£ÃâµÇ´Â ¸Þ¼­µå (Host°èÁ¤¿ë)
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

   // °Ç¹° º°·Î ¿¹¾à ¸®½ºÆ®¸¦ È®ÀÎÇÒ ¶§ È£ÃâµÇ´Â ¸Þ¼­µå (Host°èÁ¤¿ë)
   @RequestMapping(value = "reserve/hostResList", method = RequestMethod.GET)
   public ModelAndView hostReserveList(Integer sNo, Integer pageNum, String searchType, String searchContent, HttpSession session,
         String startDate, String endDate) {

      if (pageNum == null || pageNum.toString().equals("")) {
         pageNum = 1;
      }

      ModelAndView mav = new ModelAndView();

      int limit = 100; // ÇÑ ÆäÀÌÁö¿¡ ³ª¿Ã °Ô½Ã±ÛÀÇ ¼ýÀÚ
      
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

   // ¿¹¾à Á¤º¸ È®ÀÎ ÇÒ ¶§ »ç¿ëÇÏ´Â ¸Þ¼­µå
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

   // Host°èÁ¤ÀÌ °áÁ¦ È®ÀÎÇÒ ¶§ »ç¿ëÇÏ´Â ¸Þ¼­µå
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
         throw new ProjectException("¿À·ù°¡ ¹ß»ýÇÏ¿´½À´Ï´Ù.", "redirect:/reserve/hostResInfo.sms");
      }

      return mav;
   }

   // ¿¹¾à Ãë¼Ò ÀÛ¾÷½Ã È£ÃâµÇ´Â ¸Þ¼­µå
   @RequestMapping(value = "reserve/resCancel", method = RequestMethod.GET)
   public ModelAndView reserveCancel(Integer reNo, Integer reStat, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveCancel(reNo, reStat);
         mav.setViewName("redirect:/main.sms");
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("¿À·ù°¡ ¹ß»ýÇÏ¿´½À´Ï´Ù.", "reserve/list.sms");
      }
      return mav;
   }
   
   // ¿¹¾à Ãë¼Ò ÀÛ¾÷½Ã È£ÃâµÇ´Â ¸Þ¼­µå
   @RequestMapping(value = "reserve/hostResCancel", method = RequestMethod.GET)
   public ModelAndView hostResCancel(Integer reNo, Integer reStat, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveCancel(reNo, reStat);
         mav.setViewName("redirect:/main.sms");
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("¿À·ù°¡ ¹ß»ýÇÏ¿´½À´Ï´Ù.", "reserve/list.sms");
      }
      return mav;
   }
   
   // ¿¹¾à µî·Ï È­¸é¿¡¼­ Æ¯Á¤ °ø°£ÀÇ ¿¹¾à È®ÀÎ ½Ã È£ÃâµÇ´Â ¸Þ¼­µå
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

   // ¿¹¾à¾÷¹« °ü·ÃÇÏ¿© Default È£Ãâ°ªÀ¸·Î ÁöÁ¤ÇÑ ¸Þ¼­µå : Æ¯Á¤ ¿¹¾à º¸±â, ¿¹¾à µî·Ï È­¸é
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

}
package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
   
   
   // ������ ����� �� ȣ��Ǵ� �޼��� : GET
   @RequestMapping(value = "reserve/regReserve", method = RequestMethod.GET)
   public ModelAndView regReserveForm(Integer sNo, Integer sRNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Room room = service.getRoom(sNo, sRNo);
      Building building = service.getMyBuildingOne("" + sNo);
      Member hostMember = service.getMember(building.getId());
      
      mav.addObject("room", room);           // ������ �� ������ ���� ��ü
      mav.addObject("building", building);    // ������ ���� ������ ���� ��ü
      mav.addObject("hostMember", hostMember); // �ش� ȣ��Ʈ ������ ���� ��ü
      mav.addObject("reserve", new Reserve()); // ���� ��ü
      
      mav.setViewName("reserve/regReserve");
      
      return mav;
   }

   // ������ ����� �� ȣ��Ǵ� �޼���
   @RequestMapping(value = "reserve/regReserve", method = RequestMethod.POST)
   public ModelAndView registerReserve(Reserve reserve, HttpSession session, HttpServletRequest request) {
      ModelAndView mav = new ModelAndView();
      
      if(!resCheckDate(reserve)) { // ���೯¥ �ߺ� üũ �ϴ� �޼���
         throw new ProjectException("���� ��¥�� �ߺ��˴ϴ�.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }

      try {
         service.reserveInsert(reserve);
         mav.setViewName("redirect:resList.sms?id=" + reserve.getId());
         
      } catch (Exception e) {
         
         throw new ProjectException("���� �Է��ϼž� �մϴ�.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
      }
      
      return mav;
   }
   
   // ���� �ð� �ߺ� üũ�� �ϴ� �޼���
   private boolean resCheckDate(Reserve reserve) {
      
      Room chkRoom = service.getRoom(reserve.getsNo(), reserve.getSrNo());
      
      long startDateMiSec = 0;
      Date startChkDate = new Date();
      long endDateMiSec = 0;
      Date endChkDate = new Date();
      
      System.out.println("chkRoom�� resType : " + chkRoom.getsResType());
      
      // ���� ���� Ÿ���� �ð��� ���
      if(chkRoom.getsResType() == 0) {
         SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH");
         
         // ���� ���� ��¥���� 6�ð� ���������� ���� �ð��� ���Ѵ�.
         startDateMiSec = reserve.getReDate().getTime() - (1000 * 60 * 60 * reserve.getReCnt());
         startChkDate = new Date(startDateMiSec);
                  
         // ���� ���� ��¥���� 6�ð� ���ı����� ���� �ð��� ���Ѵ�.
         endDateMiSec = reserve.getReDate().getTime() + (1000 * 60 * 60 * reserve.getReCnt());
         endChkDate = new Date(endDateMiSec);
         
         // ���� ���ں��� ���� ���ڱ��� �Ⱓ(12�ð�)������ ���� ��ü�� �ҷ��´�.
         List <Reserve> list = service.getReserveDateChkList(transFormat.format(startChkDate), 
               transFormat.format(endChkDate), reserve.getsNo(), reserve.getSrNo());
         
         System.out.println("list : " + list);

         for(Reserve dbRes : list) {
            
            // DB�� �ð��� ����ð� �����̶��..!
            if (dbRes.getReDate().before(reserve.getReDate())) {
               
               long a = reserve.getReDate().getTime();   // ���೯¥�� �и���������
               long b = dbRes.getReDate().getTime();   // DB��¥�� �и���������
               
               int c = (int)(a - b / (1000 * 60 * 60)); // �� ��¥�� ���̸� �ð� ������ ġȯ
               
               System.out.println("c:" + c);
               
               if(c <= dbRes.getReCnt()) { // DB���� �������� �� ��¥�� ���̰� �۰ų� ������ false�� ����
                  return false;
               }
            }
            
            // ���� �ð�����..!
            if (transFormat.format(dbRes.getReDate()).equals(transFormat.format(reserve.getReDate()))) {
               return false;
            }
            
            // DB�� �ð��� ����ð� ���Ķ��..!
            if (dbRes.getReDate().after(reserve.getReDate())) {
               
               long a = dbRes.getReDate().getTime();   // DB��¥�� �и���������
               long b = reserve.getReDate().getTime();   // ���೯¥�� �и���������
               
               int c = (int)(a - b / (1000 * 60 * 60)); // �� ��¥�� ���̸� �ð� ������ ġȯ
               
               System.out.println("c:" + c);
               
               if(c <= reserve.getReCnt()) { // ���� ���హ������ �� ��¥�� ���̰� �۰ų� ������ false�� ����
                  return false;
               }
            }
         }
      }
      
      // ���� ���� Ÿ���� ������ ���
      else if(chkRoom.getsResType() == 1) {
         
         SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
                  
         // ���� ���� ��¥���� 6�� ���������� �������ڸ� ���Ѵ�.
         startDateMiSec = reserve.getReDate().getTime() - (1000 * 60 * 60 * 24 * reserve.getReCnt());
         startChkDate = new Date(startDateMiSec);
         
         // ���� ���� ��¥���� 6�� ���ı����� �������ڸ� ���Ѵ�.
         endDateMiSec = reserve.getReDate().getTime() + (1000 * 60 * 60 * 24 * reserve.getReCnt());
         endChkDate = new Date(endDateMiSec);
         
         // ���� ���ں��� ���� ���ڱ��� �Ⱓ(12��)������ ���� ��ü�� �ҷ��´�.
         List <Reserve> list = service.getReserveDateChkList(transFormat.format(startChkDate), 
               transFormat.format(endChkDate), reserve.getsNo(), reserve.getSrNo());
         
         for(Reserve dbRes : list) {
            // DB�� ��¥�� ���೯¥ �����̶��..!
            if (dbRes.getReDate().before(reserve.getReDate())) {
               
               long a = reserve.getReDate().getTime();   // ���೯¥�� �и���������
               long b = dbRes.getReDate().getTime();   // DB��¥�� �и���������
               
               int c = (int)(a - b / (1000 * 60 * 60 * 24)); // �� ��¥�� ���̸� ���ڷ� ġȯ
               
               if(c <= dbRes.getReCnt()) { // DB���� �������� �� ��¥�� ���̰� �۰ų� ������ false�� ����
                  return false;
               }
            }

            // ���� ��¥���..!
            if (transFormat.format(dbRes.getReDate()).equals(transFormat.format(reserve.getReDate()))) {
               return false;
            }
            
            // DB�� ��¥�� ���೯¥ ���Ķ��..!
            if (dbRes.getReDate().after(reserve.getReDate())) {
               
               long a = dbRes.getReDate().getTime();   // DB��¥�� �и���������
               long b = reserve.getReDate().getTime();   // ���೯¥�� �и���������
               
               int c = (int)(a - b / (1000 * 60 * 60 * 24)); // �� ��¥�� ���̸� ���ڷ� ġȯ
               
               if(c <= reserve.getReCnt()) { // ���� ���హ������ �� ��¥�� ���̰� �۰ų� ������ false�� ����
                  return false;
               }
            }
         }
      }
      System.out.println("!!!!!!!!!!!!!!���� �޼��� ���� ���� Ȯ��!!!!!!!!!!!!!!!!!");
      return true;
   }

   // ������ ������ �� ȣ��Ǵ� �޼���
   @RequestMapping(value = "reserve/resUpdate", method = RequestMethod.POST)
   public ModelAndView updateReserve(Reserve reserve, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveUpdate(reserve);
         mav.setViewName("redirect:resDetail.sms?reNo=" + reserve.getReNo());
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("������ �߻��Ͽ����ϴ�.", "resUpdate.sms?reNo=" + reserve.getReNo());
      }

      return mav;

   }

   // ���� ����Ʈ�� Ȯ���� �� ȣ��Ǵ� �޼��� (Guest������)
   @RequestMapping(value = "reserve/resList", method = RequestMethod.GET)
   public ModelAndView reserveList(String id, Integer pageNum, String searchType, String searchContent,
         HttpSession session, String startDate, String endDate) {

      if (pageNum == null || pageNum.toString().equals("")) {
         pageNum = 1;
      }

      ModelAndView mav = new ModelAndView();

      int limit = 100; // �� �������� ���� �Խñ��� ����
      int listcount = service.reserveCount(id, searchType, searchContent); // ǥ�õ� �� �Խñ��� ��


       List<Reserve> reservelist = service.selectReserveList(id, searchType, searchContent, pageNum, limit, startDate, endDate);


      int maxpage = (int) ((double) listcount / limit + 0.95);
      int startpage = ((int) ((pageNum / 10.0 + 0.9) - 1)) * 10 + 1; // ����������
      int endpage = startpage + 9; // ������ ������
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

   // �űԿ��������� ���� Ȯ���� �� ȣ��Ǵ� �޼��� (Host������)
   @RequestMapping(value = "reserve/hostResInfo", method = RequestMethod.GET)
   public ModelAndView hostReserveInfo(HttpSession session) {

      ModelAndView mav = new ModelAndView();

      String hostId = ((Member) session.getAttribute("loginMember")).getId();
      
      Member hostMember = service.getMember(hostId);

      List<Integer> hostHaveBuild = service.hostHaveBuildsNo(hostId); // �ش� Host ������ ��ϵ� Building���� �ǹ�������ȣ�� List�� ����

      List<Building> list = new ArrayList<Building>();
      
      int buildCnt = service.hostBuildCount(hostId);
      
      int questCnt = 0;

      for (Integer sNo : hostHaveBuild) {   // �ǹ��������� Building ������ �����ͼ� list<Building>�� ����
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

   // �ǹ� ���� ���� ����Ʈ�� Ȯ���� �� ȣ��Ǵ� �޼��� (Host������)
   @RequestMapping(value = "reserve/hostResList", method = RequestMethod.GET)
   public ModelAndView hostReserveList(Integer sNo, Integer pageNum, String searchType, String searchContent, HttpSession session,
         String startDate, String endDate) {

      if (pageNum == null || pageNum.toString().equals("")) {
         pageNum = 1;
      }

      ModelAndView mav = new ModelAndView();

      int limit = 100; // �� �������� ���� �Խñ��� ����
      
      int listcount = service.hostReserveCount(sNo, searchType, searchContent); // ǥ�õ� �� �Խñ��� ��

      List<Reserve> reservelist = service.selectHostReserveList(sNo, searchType, searchContent, pageNum, limit, startDate, endDate);

      int maxpage = (int) ((double) listcount / limit + 0.95);
      int startpage = ((int) ((pageNum / 10.0 + 0.9) - 1)) * 10 + 1; // ����������
      int endpage = startpage + 9; // ������ ������
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

   // ���� ���� Ȯ�� �� �� ����ϴ� �޼���
   @RequestMapping(value = "reserve/resDetail", method = RequestMethod.GET)
   public ModelAndView detailReserve2(Integer reNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Reserve reserve = service.getReserve(reNo);
      
      Room room = new Room();

      room = service.getRoom(reserve.getsNo(), reserve.getSrNo());
      
      Date endDate = new Date();
      long plusTime = reserve.getReDate().getTime();
      
      if(room.getsResType() == 0) { // ����ȭ�鿡�� ������ ����
         endDate.setTime(plusTime + (3600000 * reserve.getReCnt()));
      } else {
         endDate.setTime(plusTime + (86400000 * reserve.getReCnt()));
      }

      mav.addObject("reserve", reserve);
      mav.addObject("room", room); // ���ΰ����� ���� ��������
      mav.addObject("endDate", endDate);

      return mav;

   }

   // Host������ ���� Ȯ���� �� ����ϴ� �޼���
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
         throw new ProjectException("������ �߻��Ͽ����ϴ�.", "redirect:/reserve/hostResInfo.sms");
      }

      return mav;
   }

   // ���� ��� �۾��� ȣ��Ǵ� �޼���
   @RequestMapping(value = "reserve/resCancel", method = RequestMethod.GET)
   public ModelAndView reserveCancel(Integer reNo, Integer reStat, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveCancel(reNo, reStat);
         mav.setViewName("redirect:/main.sms");
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("������ �߻��Ͽ����ϴ�.", "reserve/list.sms");
      }
      return mav;
   }
   
   // ���� ��� �۾��� ȣ��Ǵ� �޼���
   @RequestMapping(value = "reserve/hostResCancel", method = RequestMethod.GET)
   public ModelAndView hostResCancel(Integer reNo, Integer reStat, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      try {
         service.reserveCancel(reNo, reStat);
         mav.setViewName("redirect:/main.sms");
      } catch (Exception e) {
         e.printStackTrace();
         throw new ProjectException("������ �߻��Ͽ����ϴ�.", "reserve/list.sms");
      }
      return mav;
   }
   
   // ���� ��� ȭ�鿡�� Ư�� ������ ���� Ȯ�� �� ȣ��Ǵ� �޼���
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

   // ������� �����Ͽ� Default ȣ�Ⱚ���� ������ �޼��� : Ư�� ���� ����, ���� ��� ȭ��
   @RequestMapping(value = "reserve/*", method = RequestMethod.GET)
   public ModelAndView detailReserve(Integer reNo, HttpSession session) {

      ModelAndView mav = new ModelAndView();

      Reserve reserve = new Reserve();

      // ������ �ִٸ� �ش� ���������� �����ֱ� ���� ���ǽ�
      if (reNo != null) {
         reserve = service.getReserve(reNo);
      }

      mav.addObject("reserve", reserve);
      return mav;
   }

}
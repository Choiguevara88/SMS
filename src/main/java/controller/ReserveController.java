package controller;

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
import logic.ProjectService;
import logic.Reserve;
import logic.Room;

@Controller
public class ReserveController {
	
	@Autowired
	private ProjectService service;
	
	// 예약을 등록할 때 호출되는 메서드
	@RequestMapping(value="reserve/roomReserve", method=RequestMethod.POST)
	public ModelAndView roomReserve(Reserve reserve, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			service.reserveInsert(reserve);
			mav.setViewName("redirect:/main.sms");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("오류가 발생하였습니다." , "reserve/list.sms");
		}
		return mav;
	}
	
	// 예약을 수정할 때 호출되는 메서드
	@RequestMapping(value="", method=RequestMethod.POST)
	public ModelAndView updateReserve(Reserve reserve, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			service.reserveUpdate(reserve);
			mav.setViewName("redirect:reserve/detail.sms");
		} catch(Exception e) {
			e.printStackTrace();
			throw new ProjectException("오류가 발생하였습니다.", "reserve/detail.sms");
		}
		
		return mav;
		
	}
	
	// 예약 리스트를 확인할 때 호출되는 메서드 (Guest계정용)
	@RequestMapping(value="reserve/resList", method=RequestMethod.GET)
	public ModelAndView reserveList(String id, Integer pageNum, String searchType, String searchContent, HttpSession session) {
		
		if(pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		
		ModelAndView mav = new ModelAndView();
		
		int limit = 100;		// 한 페이지에 나올 게시글의 숫자
		int listcount = service.reserveCount(id, searchType, searchContent);	// 표시될 총 게시글의 수
		
		if (id != null) {
		List<Reserve> reservelist = service.selectReserveList(id, searchType, searchContent, pageNum, limit);
		
		int maxpage = (int)((double)listcount/limit + 0.95);
		int startpage = ((int)((pageNum/10.0 + 0.9) - 1)) * 10 + 1; // 시작페이지
		int endpage = startpage + 9;	// 마지막 페이지
		if(endpage > maxpage) endpage = maxpage;
		int reservecnt = listcount - (pageNum - 1) * limit;
		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("listcount",listcount);
		mav.addObject("list",reservelist);
		mav.addObject("reservecnt",reservecnt);
		}
				
		return mav;
	}
	
	// 신규예약정보에 대해 확인할 때 호출되는 메서드 (Host계정용)
	@RequestMapping(value="reserve/hostResInfo", method=RequestMethod.GET)
	public ModelAndView hostReserveInfo(String hostName, HttpSession session) {
		 
		ModelAndView mav = new ModelAndView();
		int buildCnt = service.hostBuildCount(hostName);
		List<Building> list = service.selectHostReserveInfo(hostName);
		mav.addObject("list", list);
		mav.addObject("buildCnt", buildCnt);
		
		return mav;
	}
	
	
	// 예약 리스트를 확인할 때 호출되는 메서드 (Host계정용)
	@RequestMapping(value="reserve/hostResList", method=RequestMethod.GET)
	public ModelAndView hostReserveList(Integer sNo, Integer pageNum, String searchType, String searchContent, HttpSession session) {
		
		if(pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		
		ModelAndView mav = new ModelAndView();
		
		int limit = 100;		// 한 페이지에 나올 게시글의 숫자
		int listcount = service.hostReserveCount(sNo, searchType, searchContent);	// 표시될 총 게시글의 수
		
		
		List<Reserve> reservelist = service.selectHostReserveList(sNo, searchType, searchContent, pageNum, limit);
		
		int maxpage = (int)((double)listcount/limit + 0.95);
		int startpage = ((int)((pageNum/10.0 + 0.9) - 1)) * 10 + 1; // 시작페이지
		int endpage = startpage + 9;	// 마지막 페이지
		if(endpage > maxpage) endpage = maxpage;
		int reservecnt = listcount - (pageNum - 1) * limit;
		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("listcount",listcount);
		mav.addObject("list",reservelist);
		mav.addObject("reservecnt",reservecnt);
				
		return mav;
	}
	
	// 예약 정보 확인 후 구매/취소여부 확인할 때 사용하는 메서드
	@RequestMapping(value="reserve/resDetail", method=RequestMethod.GET)
	public ModelAndView detailReserve2(Integer reNo, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		Reserve reserve = service.getReserve(reNo);
		Room room = service.getRoom(reserve.getSrNo());
		
		mav.addObject("reserve",reserve);
		mav.addObject("room",room);	// 세부공간에 대한 세부정보
		
		return mav;
		
	}
	
	// 예약 취소 작업시 호출되는 메서드
	@RequestMapping(value="reserve/resCancel", method=RequestMethod.GET)
	public ModelAndView reserveCancel(Integer reNo, Integer reStat, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		try {
			service.reserveCancel(reNo, reStat);
			mav.setViewName("redirect:/main.sms");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("오류가 발생하였습니다." , "reserve/list.sms");
		}
		return mav;
	}

	
	
	// 예약업무 관련하여 Default 호출값으로 지정한 메서드 : 특정 예약 보기, 예약 등록 화면
	@RequestMapping(value="reserve/*", method=RequestMethod.GET) 
	public ModelAndView detailReserve(Integer reNo, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		Reserve reserve = new Reserve();
		
		// 예약이 있다면 해당 예약정보를 보여주기 위한 조건식
		if(reNo != null) {
			reserve = service.getReserve(reNo);
		}
		
		mav.addObject("reserve", reserve);
		return mav;
	}
	
}

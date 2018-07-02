package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exception.ProjectException;
import logic.Building;
import logic.ProjectService;
import logic.Reserve;

@Controller
public class ReserveController {
	
	@Autowired
	private ProjectService service;
	
	// 예약을 등록할 때 호출되는 메서드
	@RequestMapping(value="reserve/roomReserve", method=RequestMethod.POST)
	public ModelAndView roomReserve(Reserve reserve) {
		
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
	public ModelAndView updateReserve(Reserve reserve) {
		
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
	public ModelAndView reserveList(String id, Integer pageNum, String searchType, String searchContent) {
		
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
	public ModelAndView hostReserveInfo(String hostName) {
		 
		ModelAndView mav = new ModelAndView(); 
		
		List<Building> list = service.selectHostReserveInfo(hostName);
		mav.addObject("list", list);
		
		return mav;
	}
	
	
	// 예약 리스트를 확인할 때 호출되는 메서드 (Guest계정용)
	@RequestMapping(value="reserve/hostResList", method=RequestMethod.GET)
	public ModelAndView hostReserveList(String hostName, Integer pageNum, String searchType, String searchContent) {
		
		if(pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		
		ModelAndView mav = new ModelAndView();
		
		int limit = 100;		// 한 페이지에 나올 게시글의 숫자
		int listcount = service.reserveCount(hostName, searchType, searchContent);	// 표시될 총 게시글의 수
		
		if (hostName != null) {
		List<Reserve> reservelist = service.selectHostReserveList(hostName, searchType, searchContent, pageNum, limit);
		
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
		System.out.println(reservelist.size());
		System.out.println(reservecnt);
		}
				
		return mav;
	}

	
	
	// 예약업무 관련하여 Default 호출값으로 지정한 메서드 : 특정 예약보기, 예약 등록화면, 수정화면
	@RequestMapping(value="reserve/*", method=RequestMethod.GET) 
	public ModelAndView detailReserve(Reserve res, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		Reserve reserve = new Reserve();
		
		// 예약이 있다면 해당 예약정보를 보여주기 위한 조건식
		if(res != null) {
			if(res.getReNo() != null) {
				service.getReserve(res.getReNo());
			}
		}
		
		mav.addObject("reserve", reserve);
		return mav;
	}
	
}

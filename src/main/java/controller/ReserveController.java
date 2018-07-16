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
	
	
	// 예약을 등록할 때 호출되는 메서드 : GET
	@RequestMapping(value = "reserve/regReserve", method = RequestMethod.GET)
	public ModelAndView regReserveForm(Integer sNo, Integer sRNo, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		Room room = service.getRoom(sNo, sRNo);
		Building building = service.getMyBuildingOne("" + sNo);
		Member hostMember = service.getMember(building.getId());
		
		mav.addObject("room", room); 			 // 예약할 룸 정보를 가진 객체
		mav.addObject("building", building);	 // 예약할 빌딩 정보를 가진 객체
		mav.addObject("hostMember", hostMember); // 해당 호스트 정보를 가진 객체
		mav.addObject("reserve", new Reserve()); // 예약 객체
		
		mav.setViewName("reserve/regReserve");
		
		return mav;
	}

	// 예약을 등록할 때 호출되는 메서드
	@RequestMapping(value = "reserve/regReserve", method = RequestMethod.POST)
	public ModelAndView registerReserve(Reserve reserve, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		if(!resCheckDate(reserve)) { // 예약날짜 중복 체크 하는 메서드
			throw new ProjectException("예약 날짜가 중복됩니다.", "regReserve.sms?sNo=" + reserve.getsNo() +"&sRNo=" + reserve.getSrNo());
		}

		try {
			service.reserveInsert(reserve);
			mav.setViewName("redirect:/main.sms");
			
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

			for(Reserve dbRes : list) {
				// DB의 시간이 예약시간 이전이라면..!
				if (dbRes.getReDate().before(reserve.getReDate())) {
					
					long a = reserve.getReDate().getTime();	// 예약날짜를 밀리세컨으로
					long b = dbRes.getReDate().getTime();	// DB날짜를 밀리세컨으로
					
					int c = (int)(a - b / (1000 * 60 * 60)); // 두 날짜의 차이를 시간 단위로 치환
					
					System.out.println("c:" + c);
					
					if(c <= dbRes.getReCnt()) { // DB상의 갯수보다 두 날짜의 차이가 작거나 같으면 false를 리턴
						return false;
					}
				}
				
				// DB의 시간이 예약시간 이후라면..!
				if (dbRes.getReDate().after(reserve.getReDate())) {
					
					long a = dbRes.getReDate().getTime();	// DB날짜를 밀리세컨으로
					long b = reserve.getReDate().getTime();	// 예약날짜를 밀리세컨으로
					
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
					
					long a = reserve.getReDate().getTime();	// 예약날짜를 밀리세컨으로
					long b = dbRes.getReDate().getTime();	// DB날짜를 밀리세컨으로
					
					int c = (int)(a - b / (1000 * 60 * 60 * 24)); // 두 날짜의 차이를 일자로 치환
					
					if(c <= dbRes.getReCnt()) { // DB상의 갯수보다 두 날짜의 차이가 작거나 같으면 false를 리턴
						return false;
					}
				}
				
				// DB의 날짜가 예약날짜 이후라면..!
				if (dbRes.getReDate().after(reserve.getReDate())) {
					
					long a = dbRes.getReDate().getTime();	// DB날짜를 밀리세컨으로
					long b = reserve.getReDate().getTime();	// 예약날짜를 밀리세컨으로
					
					int c = (int)(a - b / (1000 * 60 * 60 * 24)); // 두 날짜의 차이를 일자로 치환
					
					if(c <= reserve.getReCnt()) { // 나의 예약갯수보다 두 날짜의 차이가 작거나 같으면 false를 리턴
						return false;
					}
				}
			}
		} 
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
		if (endpage > maxpage)	endpage = maxpage;
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

		for (Integer sNo : hostHaveBuild) {	// 건물관리별로 Building 정보를 가져와서 list<Building>에 저장
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

}

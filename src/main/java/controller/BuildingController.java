package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.Board;
import logic.Building;
import logic.ProjectService;
import logic.Room;

@Controller
public class BuildingController {
	
	@Autowired
	private ProjectService service;
	
//	@ModelAttribute
//	public Building getBuilding() {
//		return new Building();
//	}
	
	//빌딩폼 만들기
	@RequestMapping(value="building/buildingForm")
	public ModelAndView buildingForm(HttpServletRequest request) {
		Building building = new Building();
		List<String> sTypeNames = new ArrayList<String>();
		sTypeNames.add("스터디룸");
		sTypeNames.add("연습실");
		sTypeNames.add("작업실");
		sTypeNames.add("회의실");
		sTypeNames.add("세미나실");
		sTypeNames.add("카페");
		sTypeNames.add("파티룸");
		sTypeNames.add("공연장");
		String id = request.getParameter("id");
		ModelAndView mav = new ModelAndView();
		mav.addObject("sTypeNames",sTypeNames);
		mav.addObject("building", building);
		mav.addObject("id", id);
		return mav;
	}
	
	//빌딩폼 등록
	@RequestMapping(value="building/buildingReg", method=RequestMethod.POST)
	public ModelAndView buildingReg(Building building, HttpServletRequest request) {
		System.out.println(building);
		service.buildingReg(building, request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/building/myBuildingList.sms?id="+building.getId());
		return mav;
	}
	
	//등록된 내 빌딩폼 리스트 불러오기
	@RequestMapping(value="building/myBuildingList", method=RequestMethod.GET)
	public ModelAndView myBuildingList(HttpServletRequest request) {
		String id = request.getParameter("id");
		ModelAndView mav = new ModelAndView();
		List<Building> MyBuildingList = service.getMyBuildings(id);
		int buildCnt = service.hostBuildCount(id);
		mav.addObject("myBuildingList",MyBuildingList);
		mav.addObject("id", id);
		mav.addObject("buildCnt", buildCnt);
		return mav;
	}
	
	//빌딩정보 수정하기
	@RequestMapping(value="building/buildingUpdate", method=RequestMethod.GET)
	public ModelAndView buildingUpdate(HttpServletRequest request) {
		String sNo = request.getParameter("sNo");
		Building myBuildingOne = service.getMyBuildingOne(sNo);
		List<String> sTypeNames = new ArrayList<String>();
		sTypeNames.add("스터디룸");
		sTypeNames.add("연습실");
		sTypeNames.add("작업실");
		sTypeNames.add("회의실");
		sTypeNames.add("세미나실");
		sTypeNames.add("카페");
		sTypeNames.add("파티룸");
		sTypeNames.add("공연장");
		String address1 = myBuildingOne.getsAddress().split(",")[0];
		String address2 = myBuildingOne.getsAddress().split(",")[1];
		String address3 = myBuildingOne.getsAddress().split(",")[2];
		System.out.println(myBuildingOne);
		ModelAndView mav = new ModelAndView();
		mav.addObject("building", myBuildingOne);
		mav.addObject("sTypeNames", sTypeNames);
		mav.addObject("address1", address1);
		mav.addObject("address2", address2);
		mav.addObject("address3", address3);
		return mav;
	}
	
	//빌딩정보 수정사항 등록하기
	@RequestMapping(value="building/buildingUpdateReg", method=RequestMethod.POST)
	public ModelAndView buildingUpdateReg(Building building, HttpServletRequest request) {
		System.out.println(building);
		service.buildingUpdateReg(building, request);
		ModelAndView mav = new ModelAndView();
		String id = building.getId();
		System.out.println(id);
		mav.setViewName("redirect:/building/myBuildingList.sms?id=" + id);
		return mav;
	}
	
	//빌딩 상세정보 보기
	@RequestMapping(value="building/buildingDetail", method=RequestMethod.GET)
	public ModelAndView buildingDetail(Building building, HttpServletRequest request) {
		String sNo = request.getParameter("sNo");
		Integer ssNo = Integer.parseInt(sNo);
		building = service.getMyBuildingOne(sNo);
		List<Room> roomList = service.getmyRoomList(ssNo);
		System.out.println(roomList);
		ModelAndView mav = new ModelAndView();
		String address = building.getsAddress().split(",")[1];
		String address1 = building.getsAddress().split(",")[1] +" "+building.getsAddress().split(",")[2];
		mav.addObject("building", building);
		mav.addObject("address",address);
		mav.addObject("address1", address1);
		mav.addObject("roomList", roomList);
		return mav;
	}
	@ModelAttribute
	public Board getBoard() {
		return new Board();
	}
	@RequestMapping("building/Rlist") //http://localhost:8080/TestProject/review/Rlist.sms?sno=2
	public ModelAndView Rlist(Integer sNo,Integer pageNum) {
		int kind = 2;
		if(pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		
		ModelAndView mav = new ModelAndView();
		
		int limit = 5;		// 한 페이지에 나올 게시글의 숫자
		int listcount = service.boardcount(kind,sNo);	// 표시될 총 게시글의 수
		List<Board> boardlist = service.boardList(kind, sNo, pageNum, limit);
		double avg = 0;
		if(kind == 2) {	// 리뷰 게시글인 경우 MAV 객체에 해당 Building의 평점을 추가하는 로직
			if(boardlist != null && !boardlist.isEmpty() ) {
				 List<Board> boardlist2 = service.boardList(kind, sNo);
				 avg = boardlist2.stream().mapToInt(Board :: getScore).average().getAsDouble();
			}
			mav.addObject("avgScore",avg);
		}
		
		int maxpage = (int)((double)listcount/limit + 0.95);
		int startpage = ((int)((pageNum/5.0 + 0.9) - 1)) * 5 + 1; // 시작페이지
		int endpage = startpage + 4;	// 마지막 페이지
		if(endpage > maxpage) endpage = maxpage;
		int boardcnt = listcount - (pageNum - 1) * limit;
		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("listcount",listcount);
		mav.addObject("boardlist",boardlist);
		mav.addObject("boardcnt",boardcnt);
		mav.addObject("kind",kind);
		mav.addObject("sNo",sNo);
				
		return mav;
	}
}

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import exception.ProjectException;
import logic.Board;
import logic.Building;
import logic.Favorite;
import logic.Member;
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
	
	//鍮��⑺�� 留��ㅺ린
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
	
	//鍮��⑺�� �깅�
	@RequestMapping(value="building/buildingReg", method=RequestMethod.POST)
	public ModelAndView buildingReg(Building building, HttpServletRequest request) {
		System.out.println(building);
		service.buildingReg(building, request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/building/myBuildingList.sms?id="+building.getId());
		return mav;
	}
	
	//�깅��� �� 鍮��⑺�� 由ъ�ㅽ�� 遺��ъ�ㅺ린
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
	
	//鍮��⑹��蹂� ������湲�
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
	
	//鍮��⑹��蹂� �����ы�� �깅���湲�
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
	
	//鍮��� ���몄��蹂� 蹂닿린
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
		double avg = service.boardList(kind, sNo);
		//avg = boardlist2.stream().mapToInt(Board :: getScore).average().getAsDouble();
		mav.addObject("avgScore",avg);
		Building building = service.getMyBuildingOne(sNo.toString());
		
		int maxpage = (int)((double)listcount/limit + 0.95);
		int startpage = ((int)((pageNum/10.0 + 0.9) - 1)) * 10 + 1; // 시작페이지
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
		mav.addObject("building",building);
				
		return mav;
	}
	@RequestMapping("building/Qlist")
	public ModelAndView Qlist(Integer pageNum, Integer sNo) {
		int kind = 3;
		if(pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		
		ModelAndView mav = new ModelAndView();
		
		int limit = 5;		// 한 페이지에 나올 게시글의 숫자
		int listcount = service.boardcount(kind, sNo);	// 표시될 총 게시글의 수
		List<Board> boardlist = service.boardList(kind, sNo, pageNum, limit);
		Building building = service.getMyBuildingOne(sNo.toString());
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
		mav.addObject("building",building);
				
		return mav;
	}
	@RequestMapping(value="building/Rwrite", method=RequestMethod.GET) // 게시글 작성 시 호출되는 메서드
	public ModelAndView write(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		int kind = 2;
		mav.addObject("kind",kind);
		return mav;
	}
	@RequestMapping(value="building/Rwrite", method=RequestMethod.POST) // 게시글 작성 시 호출되는 메서드
	public ModelAndView Rwrite(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		int sNo = Integer.parseInt(request.getParameter("sNo"));
		int kind = Integer.parseInt(request.getParameter("kind"));
		int reNo = Integer.parseInt(request.getParameter("reNo"));
		
		service.reserveStatusUpdate(reNo);
		
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		
		try {
			service.reserveStatusUpdate(reNo);
			service.boardWrite(board, request);
			mav.setViewName("redirect:/building/buildingDetail.sms");
			
		} catch (Exception e) {
			throw new ProjectException("오류가 발생하였습니다." , "/building/buildingDetail.sms");
//			e.printStackTrace();
		}
		mav.addObject("sNo",sNo);
		mav.addObject("kind",kind);
		mav.addObject("board",board);
		return mav;
	}
	@RequestMapping(value="building/Qwrite", method=RequestMethod.POST) // 게시글 작성 시 호출되는 메서드
	public ModelAndView Qwrite(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		int sNo = Integer.parseInt(request.getParameter("sNo"));
		int kind = Integer.parseInt(request.getParameter("kind"));
		
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			throw new ProjectException("다시 작성해 주세요.","buildingDetail.sms?sNo="+sNo);
		}
		
		try {
			service.boardWrite(board);
			mav.setViewName("redirect:/building/buildingDetail.sms");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("오류가 발생하였습니다." , "/building/buildingDetail.sms");
		}
		mav.addObject("sNo",sNo);
		mav.addObject("kind",kind);
		return mav;
	}
	@RequestMapping(value="building/Rreply", method=RequestMethod.GET) 
	public ModelAndView Rdetail(Integer bNo, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		int kind = 2;
		Board board = new Board();
		
		if(bNo != null) {
			board = service.getBoard(bNo);
		}
		mav.addObject("kind",kind);
		mav.addObject("board", board);
		return mav;
	}
	@RequestMapping(value="building/Rreply", method=RequestMethod.POST) // 댓글 작성 시 호출되는 메서드
	public ModelAndView Rreply(@Valid Board board,HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		int sNo = board.getsNo();
		int kind = board.getKind();
				
		try {
			service.boardReply(board);
			//mav.setViewName("redirect:/building/buildingDetail.sms");
			mav.setViewName("building/qlistsuccess");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("오류가 발생하였습니다." , "/building/buildingDetail.sms");
		}
		mav.addObject("kind",kind);
		mav.addObject("sNo",sNo);
		return mav;
	}
	@RequestMapping(value="building/Qreply", method=RequestMethod.GET) // 게시글 작성 View로 접속할 때 호출되는 메서드
	public ModelAndView Qdetail(Integer bNo, HttpServletRequest request) {
				
		ModelAndView mav = new ModelAndView();
		
		Board board = new Board();
		int kind = 3;
		System.out.println(bNo);
		board = service.getBoard(bNo);
		mav.addObject("kind",kind);
		mav.addObject("board", board);
		return mav;
	}
	@RequestMapping(value="building/Qreply", method=RequestMethod.POST) // 댓글 작성 시 호출되는 메서드
	public ModelAndView Qreply(@Valid Board board,HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		int kind = board.getKind();
		int sNo = board.getsNo();
		try {
			service.boardReply(board);
//			mav.setViewName("redirect:/building/buildingDetail.sms");
			mav.setViewName("building/qlistsuccess");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("오류가 발생하였습니다." , "/building/buildingDetail.sms");
		}
		mav.addObject("kind",kind);
		mav.addObject("sNo",sNo);
		return mav;
	}
	@RequestMapping(value="building/delete", method=RequestMethod.POST)
	public ModelAndView delete(Integer bNo, Integer sNo,Integer kind) {
		ModelAndView mav = new ModelAndView();
		service.boardDelete(bNo);
		mav.setViewName("redirect:/building/buildingDetail.sms?sNo="+sNo);
		return mav;
	}
	
	/*@RequestMapping(value="building/*", method=RequestMethod.GET) // 게시글 작성 View로 접속할 때 호출되는 메서드
	public ModelAndView detail(Integer bNo, HttpServletRequest request) {
				
		ModelAndView mav = new ModelAndView();
		
		Board board = new Board();
		int kind = 2;
		if(bNo != null) {
			board = service.getBoard(bNo);
			String url = request.getServletPath();
			
			if(url.contains("/review/detail.sms")) {
				service.updateReadCnt(bNo);
			}
		}
		mav.addObject("kind",kind);
		mav.addObject("board", board);
		return mav;
	}*/
	
	@RequestMapping(value="building/favorite")
	public @ResponseBody String favorite(String id, String sNo, HttpSession session) {
		Favorite favor = new Favorite();
		Integer sno = Integer.parseInt(sNo);
		favor = service.find(id, sno);
		if(favor == null) {
			return "1";
		} else {
			return "0";
		}
	}
	
	@RequestMapping(value="building/favoriteclick")
	public @ResponseBody String favoriteclick(String id, String sNo, HttpSession session) {
		Favorite favor = new Favorite();
		Integer sno = Integer.parseInt(sNo);
		favor = service.find(id, sno);
		if(favor == null) {
			service.addfavorite(id, sno);
			return "1";
		} else {
			service.deletefavorite(id,sno);
			return "0";
		}
	}
	
	@RequestMapping(value="building/buildingDelete")
	public ModelAndView buildingDelete(HttpServletRequest request, HttpSession session) {
		Integer sNo = Integer.parseInt(request.getParameter("sNo"));
		service.buildingDelete(sNo);
		Member mem  = (Member) session.getAttribute("loginMember");
		String id = mem.getId();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/building/myBuildingList.sms?id=" + id);
		return mav;
	}
	
	@RequestMapping(value="building/wishlist.sms", method=RequestMethod.GET) // 찜한 공간 목록 불러올 때 사용하는 메서드
	public ModelAndView buildingWishList(HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		Member mem = (Member)session.getAttribute("loginMember");
		String id = mem.getId();
		
		List<Building> list = service.getMyWishBuildings(id); // 찜한 건물 목록
		
		for(Building build : list) {
			build.setRoom(service.getmyRoomList(build.getsNo()));
			build.setsTagList(Arrays.asList(build.getsTag().split("[|]")));
			build.setsTypeList(Arrays.asList(build.getsType().split("[|]")));
		}
		mav.addObject("list", list);
		
		return mav;
	}
}

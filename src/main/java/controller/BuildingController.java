package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.Building;
import logic.ProjectService;

@Controller
public class BuildingController {
	
	@Autowired
	private ProjectService service;
	
	//빌딩폼 만들기
	@RequestMapping(value="building/buildingForm")
	public ModelAndView buildingForm() {
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
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("sTypeNames",sTypeNames);
		mav.addObject("building", building);
		return mav;
	}
	
	//빌딩폼 등록
	@RequestMapping(value="building/buildingReg", method=RequestMethod.POST)
	public ModelAndView buildingReg(Building building, HttpServletRequest request) {		
		ModelAndView mav = new ModelAndView();
		service.buildingReg(building, request);
		mav.setViewName("myBuildingList");
		return mav;
	}
	
	//등록된 내 빌딩폼 리스트 불러오기
	@RequestMapping(value="building/myBuildingList", method=RequestMethod.GET)
	public ModelAndView myBuildingList(HttpServletRequest request) {		
		ModelAndView mav = new ModelAndView();
		/* 추후에 리퀘스트로 로그인된 아이디 가져와야함.*/
		String id = "id6";
		List<Building> MyBuildingList = service.getMyBuildings(id);
		mav.addObject("myBuildingList",MyBuildingList);
		mav.addObject("id", id);
		return mav;
	}
	
	//빌딩정보 수정하기
	@RequestMapping(value="building/buildingUpdate", method=RequestMethod.GET)
	public ModelAndView buildingUpdate(HttpServletRequest request) {
		String sNo = request.getParameter("sNo");
		ModelAndView mav = new ModelAndView();
		return mav;
	}
}

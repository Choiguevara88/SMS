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

import logic.Building;
import logic.ProjectService;

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
		building = service.getMyBuildingOne(sNo);
		
		ModelAndView mav = new ModelAndView();
		String address = building.getsAddress().split(",")[1];
		String address1 = building.getsAddress().split(",")[1] +" "+building.getsAddress().split(",")[2];
		mav.addObject("building", building);
		mav.addObject("address",address);
		mav.addObject("address1", address1);
		return mav;
	}
}

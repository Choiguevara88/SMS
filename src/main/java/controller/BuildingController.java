package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Building;
import logic.ProjectService;

@Controller
public class BuildingController {
	
	@Autowired
	private ProjectService service;
	
	@RequestMapping(value="building/buildingForm")
	public ModelAndView buildindForm(Integer num, HttpServletRequest request) {
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
}

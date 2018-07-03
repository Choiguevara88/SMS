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
	
	@RequestMapping(value="building/buildingForm")
	public ModelAndView buildingForm() {
		Building building = new Building();
		List<String> sTypeNames = new ArrayList<String>();
		sTypeNames.add("���͵��");
		sTypeNames.add("������");
		sTypeNames.add("�۾���");
		sTypeNames.add("ȸ�ǽ�");
		sTypeNames.add("���̳���");
		sTypeNames.add("ī��");
		sTypeNames.add("��Ƽ��");
		sTypeNames.add("������");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("sTypeNames",sTypeNames);
		mav.addObject("building", building);
		return mav;
	}
	
	@RequestMapping(value="building/buildingReg", method=RequestMethod.POST)
	public ModelAndView buildingReg(Building building, HttpServletRequest request) {
		
		System.out.println(building);		
		ModelAndView mav = new ModelAndView();

		return mav;
	}
}

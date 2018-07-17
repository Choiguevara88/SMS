package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Board;
import logic.Building;
import logic.Member;
import logic.ProjectService;
import logic.Room;

@Controller
public class MainController {
	
	@Autowired
	private ProjectService service;
	
	@RequestMapping("main")
	public ModelAndView mainPage() {
		ModelAndView mav = new ModelAndView("main");
		int sNo[] = new int[6];
		for (int i = 0; i < sNo.length; i++) {
			sNo[i] = (int) (Math.random() * service.getBuildingCount()) + 1;
			if (service.getBuildingCount() >= sNo.length) {
				for (int j = 0; j < i; j++) {
					if (sNo[i] == sNo[j]) {
						i--;
						break;
					}
				}
			} else {
				sNo = new int[service.getBuildingCount()];
				for (int j = 0; j < i; j++) {
					if (sNo[i] == sNo[j]) {
						i--;
						break;
					}
				}
			}
		}
		int sNo2[] = new int [sNo.length];
		
		for(int i=0; i<sNo.length; i++) {
			System.out.println("sNo[" + i + "]=" + sNo[i]);
		}
		
		for(int i = 0; i < sNo2.length; i++) {
			sNo2[i] = service.getbuilding_mainpage_2(sNo[i]);
			System.out.println("sNo2[" + i + "]=" + sNo2[i]);
		}
		 
		List<Room> sPrice1 = new ArrayList<Room>();
		List<Building> building1 = new ArrayList<Building>();
		for(int i = 0; i< sNo.length;i++) { 
			building1.add(service.getbuilding_mainpage(sNo[i]));
			System.out.println(service.getbuilding_mainpage(sNo[i]).getsTagList());
			sPrice1.add(service.getmyRoom_lowestprice(sNo[i]));
		}
		mav.addObject("today_buildings", building1);
		mav.addObject("today_buildings_price", sPrice1);
		
		//building ¸®½ºÆ®¶û sPrice¸®½ºÆ®ÀÇ ÀÎµ¦½ºµéÀº °°ÀÌ ¿òÁ÷ÀÓ
		//»ç¶÷µéÀÌ °¡Àå ¸¹ÀÌ ¿¹¾àÇÑ °÷! ÇÖÇÃ·¹ÀÌ½º 6°³
		List<Room> sPrice2 = new ArrayList<Room>();
		List<Building> building2 = new ArrayList<Building>();
		List<Board> review = new ArrayList<Board>();
		
		review = service.getbuilding_reviewCount();
		
		if(review != null && !review.isEmpty()) {
		
			for(int i = 0; i<sNo2.length;i++) {
				building2.add(service.getbuilding_mainpage_reviewCount(review.get(i).getsNo()));
				sPrice2.add(service.getmyRoom_lowestprice(building2.get(i).getsNo())); 
			}
		}
		
		System.out.println("building2 : " + building2);
		
		mav.addObject("buildings_review", building2);
		mav.addObject("buildings_review_price", sPrice2);
		
		
		//ÆòÁ¡ ÁÁÀº¼ø~
		//Board¿¡¼­ Æò±Õ ÆòÁ¡À» °è»êÇÑµÚ Æò±ÕÆòÁ¡ÀÌ ³ôÀº ¼øÀ¸·Î 6°³ÀÇ ºôµù sNo°¡Á®¿À±â
		//sNo·Î Building °¡Á®¿À°í, Room°¡Á®¿À±â
		//º¸³»±â
		List<Room> sPrice3 = new ArrayList<Room>();
		List<Building> building3 = new ArrayList<Building>();
		List<Board> board = new ArrayList<Board>();
		board = service.getSNo_byScore(); // Board 6ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		
		if(board != null && !board.isEmpty()) {
		
			for(int i = 0; i<sNo2.length;i++) {
				building3.add(service.getbuilding_mainpage(board.get(i).getsNo()));
				sPrice3.add(service.getmyRoom_lowestprice(building3.get(i).getsNo()));
			}
		
		}
		
		System.out.println("building3 : " + building3);
		
		mav.addObject("building_score",building3);
		mav.addObject("building_score_price",sPrice3);
		mav.addObject(new Member());
		return mav;
	}
	
}

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
		for(int i = 0; i< sNo.length;i++) {
			sNo[i] = (int)(Math.random()*12)+1;
			for(int j = 0; j <i;j++) {
				if(sNo[i] == sNo[j]) {
					i--;
					break;
				}
			}
		}
<<<<<<< HEAD
		//·£´ýÀ¸·Î °Ç¹° ³Ö±â
		//°Ç¹°À» °¡Á® ¿À¸é¼­ µ¿½Ã¿¡ °ü·Ã Roomµµ °¡Á®¿Í¾ßµÈ´Ù
		List<Room> sPrice1 = new ArrayList<Room>();
		List<Building> building1 = new ArrayList<Building>();
		for(int i = 0; i< sNo.length;i++) { 
			building1.add(service.getbuilding_mainpage(sNo[i]));
			System.out.println(service.getbuilding_mainpage(sNo[i]).getsTagList());
			sPrice1.add(service.getmyRoom_lowestprice(sNo[i]));
		}
		//building ¸®½ºÆ®¶û sPrice¸®½ºÆ®ÀÇ ÀÎµ¦½ºµéÀº °°ÀÌ ¿òÁ÷ÀÓ
		
		//°Ç¹° ÆòÁ¡ Æò±Õ ³»¼­ °Ç¹° ¹øÈ£¶û Æò±Õ ÆòÁ¡ °¡Á®¿À±â
		List<Integer> sPrice2 = new ArrayList<Integer>();
		List<Building> building2 = new ArrayList<Building>(); 
		List<Board> buildingNo = service.getbuildingNo_by_score();  //sNo¸¦ °¡Á®¿À´Â °÷
		for(int i = 0; i< sNo.length;i++) {
			//building2.add(service.getbuilding_mainpage(buildingNo.get(i).getsNo()));
			//sPrice2 = service.getmyRoom_lowestprice(buildingNo.get(i).getsNo()); //sNoÁß¿¡¼­ °¡Àå ³·Àº°ªµéÀÇ ¸®½ºÆ®
		}
		mav.addObject(new Member());
		mav.addObject("today_buildings", building1);
		mav.addObject("today_buildings_price", sPrice1);
		mav.addObject("boardScore_buildings", building2);
		mav.addObject("boardScore_buildings_price", sPrice2);
=======
		System.out.println("1111");
		//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ç¹ï¿½ ï¿½Ö±ï¿½
		//ï¿½Ç¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½é¼­ ï¿½ï¿½ï¿½Ã¿ï¿½ ï¿½ï¿½ï¿½ï¿½ Roomï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Í¾ßµÈ´ï¿½
//		List<Integer> sPrice1 = new ArrayList<Integer>();
//		List<Building> building1 = new ArrayList<Building>();
//		for(int i = 0; i< sNo.length;i++) { 
//			building1.add(service.getbuilding_mainpage(sNo[i]));
//			System.out.println(service.getmyRoom_lowestprice(sNo[i]).getsPrice());
//			sPrice1.add(service.getmyRoom_lowestprice(sNo[i]).getsPrice());
//		}
//		//building ï¿½ï¿½ï¿½ï¿½Æ®ï¿½ï¿½ sPriceï¿½ï¿½ï¿½ï¿½Æ®ï¿½ï¿½ ï¿½Îµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
//		System.out.println("12345");
//		System.out.println(building1);
//		System.out.println(sPrice1);
//		
//		//ï¿½Ç¹ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½Ç¹ï¿½ ï¿½ï¿½È£ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
//		List<Integer> sPrice2 = new ArrayList<Integer>();
//		List<Building> building2 = new ArrayList<Building>(); 
//		List<Board> buildingNo = service.getbuildingNo_by_score();  //sNoï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½
//		for(int i = 0; i< sNo.length;i++) {
//			//building2.add(service.getbuilding_mainpage(buildingNo.get(i).getsNo()));
//			//sPrice2 = service.getmyRoom_lowestprice(buildingNo.get(i).getsNo()); //sNoï¿½ß¿ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½Æ®
//		}
//		mav.addObject(new Member());
//		mav.addObject("today_buildings", building1);
//		mav.addObject("today_buildings_price", sPrice1);
//		mav.addObject("boardScore_buildings", building2);
//		mav.addObject("boardScore_buildings_price", sPrice2);
>>>>>>> branch 'master' of https://github.com/Choiguevara88/SMS.git
		return mav;
	}
	
}

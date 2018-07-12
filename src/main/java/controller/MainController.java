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
			sNo[i] = (int)(Math.random()*23)+1;
			for(int j = 0; j <i;j++) {
				if(sNo[i] == sNo[j]) {
					i--;
					break;
				}
			}
		}
		//랜덤으로 건물 넣기
		//건물을 가져 오면서 동시에 관련 Room도 가져와야된다
		List<Room> sPrice1 = new ArrayList<Room>();
		List<Building> building1 = new ArrayList<Building>();
		for(int i = 0; i< sNo.length;i++) { 
			building1.add(service.getbuilding_mainpage(sNo[i]));
			System.out.println(service.getbuilding_mainpage(sNo[i]).getsTagList());
			sPrice1.add(service.getmyRoom_lowestprice(sNo[i]));
		}
		//building 리스트랑 sPrice리스트의 인덱스들은 같이 움직임
		
		//건물 평점 평균 내서 건물 번호랑 평균 평점 가져오기
		List<Integer> sPrice2 = new ArrayList<Integer>();
		List<Building> building2 = new ArrayList<Building>(); 
		List<Board> buildingNo = service.getbuildingNo_by_score();  //sNo를 가져오는 곳
		for(int i = 0; i< sNo.length;i++) {
			//building2.add(service.getbuilding_mainpage(buildingNo.get(i).getsNo()));
			//sPrice2 = service.getmyRoom_lowestprice(buildingNo.get(i).getsNo()); //sNo중에서 가장 낮은값들의 리스트
		}
		mav.addObject(new Member());
		mav.addObject("today_buildings", building1);
		mav.addObject("today_buildings_price", sPrice1);
		mav.addObject("boardScore_buildings", building2);
		mav.addObject("boardScore_buildings_price", sPrice2);
		return mav;
	}
	
}

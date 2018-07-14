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
		
		//랜덤으로 건물 넣기
		//건물을 가져 오면서 동시에 관련 Room도 가져와야된다
		List<Room> sPrice1 = new ArrayList<Room>();
		List<Building> building1 = new ArrayList<Building>();
		for(int i = 0; i< sNo.length;i++) { 
			building1.add(service.getbuilding_mainpage(sNo[i]));
			System.out.println(service.getbuilding_mainpage(sNo[i]).getsTagList());
			sPrice1.add(service.getmyRoom_lowestprice(sNo[i]));
		}
		mav.addObject("today_buildings", building1);
		mav.addObject("today_buildings_price", sPrice1);
		//building 리스트랑 sPrice리스트의 인덱스들은 같이 움직임
		
		//사람들이 가장 많이 예약한 곳! 핫플레이스 6개
		List<Room> sPrice2 = new ArrayList<Room>();
		List<Building> building2 = new ArrayList<Building>();
		List<Board> review = service.getbuilding_reviewCount();
		for(int i = 0; i<sNo.length;i++) {
			building2.add(service.getbuilding_mainpage_reviewCount(review.get(i).getsNo())); //review순서대로 빌딩을 가져오고
			sPrice2.add(service.getmyRoom_lowestprice(building2.get(i).getsNo())); //그 빌딩의 sNo로 lowest Price가져오기
		}
		System.out.println(building2);
		mav.addObject("buildings_review", building2);
		mav.addObject("buildings_review_price", sPrice2);
		
		
		//평점 좋은순~
		//Board에서 평균 평점을 계산한뒤 평균평점이 높은 순으로 6개의 빌딩 sNo가져오기
		//sNo로 Building 가져오고, Room가져오기
		//보내기
		List<Room> sPrice3 = new ArrayList<Room>();
		List<Building> building3 = new ArrayList<Building>();
		List<Board> board = service.getSNo_byScore(); // Board 6개 들어옴 평점 순으로
		for(int i = 0; i<sNo.length;i++) {
			building3.add(service.getbuilding_mainpage(board.get(i).getsNo()));
			sPrice3.add(service.getmyRoom_lowestprice(building3.get(i).getsNo()));
		}
		mav.addObject("building_score",building3);
		mav.addObject("building_score_price",sPrice3);
		mav.addObject(new Member());
		return mav;
	}
	
}

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
		System.out.println("1111");
		//�������� �ǹ� �ֱ�
		//�ǹ��� ���� ���鼭 ���ÿ� ���� Room�� �����;ߵȴ�
//		List<Integer> sPrice1 = new ArrayList<Integer>();
//		List<Building> building1 = new ArrayList<Building>();
//		for(int i = 0; i< sNo.length;i++) { 
//			building1.add(service.getbuilding_mainpage(sNo[i]));
//			System.out.println(service.getmyRoom_lowestprice(sNo[i]).getsPrice());
//			sPrice1.add(service.getmyRoom_lowestprice(sNo[i]).getsPrice());
//		}
//		//building ����Ʈ�� sPrice����Ʈ�� �ε������� ���� ������
//		System.out.println("12345");
//		System.out.println(building1);
//		System.out.println(sPrice1);
//		
//		//�ǹ� ���� ��� ���� �ǹ� ��ȣ�� ��� ���� ��������
//		List<Integer> sPrice2 = new ArrayList<Integer>();
//		List<Building> building2 = new ArrayList<Building>(); 
//		List<Board> buildingNo = service.getbuildingNo_by_score();  //sNo�� �������� ��
//		for(int i = 0; i< sNo.length;i++) {
//			//building2.add(service.getbuilding_mainpage(buildingNo.get(i).getsNo()));
//			//sPrice2 = service.getmyRoom_lowestprice(buildingNo.get(i).getsNo()); //sNo�߿��� ���� ���������� ����Ʈ
//		}
//		mav.addObject(new Member());
//		mav.addObject("today_buildings", building1);
//		mav.addObject("today_buildings_price", sPrice1);
//		mav.addObject("boardScore_buildings", building2);
//		mav.addObject("boardScore_buildings_price", sPrice2);
		return mav;
	}
	
}

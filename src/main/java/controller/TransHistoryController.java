package controller;


import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.Member;
import logic.ProjectService;

@Controller
public class TransHistoryController {
	
	@Autowired
	private ProjectService service;
	
	// 거래 내역을 확인할 수 있는 그래프
	@RequestMapping(value="trans/graphTransHistory", method=RequestMethod.GET)
	public ModelAndView graphTransHistory(HttpSession session, String searchType) {
		ModelAndView mav = new ModelAndView();
		
		Member loginMem = (Member)session.getAttribute("loginMember");
		
		String id = loginMem.getId();
				
		Map<String, Object> mapCnt = service.graphTransHistoryCnt(searchType, id);
		Map<String, Object> mapSum = service.graphTransHistorySum(searchType, id);
		Map<String, Object> treeMapCnt = new TreeMap<String, Object>(mapCnt);
		Map<String, Object> treeMapSum = new TreeMap<String, Object>(mapSum);
		mav.addObject("cntMap", treeMapCnt);
		mav.addObject("sumMap", treeMapSum);
		mav.setViewName("graphCanvas");

		return mav;
	}

}

/*
 * 	select %{searchType} 'key', %{searchContent}(*) 'value' from TransactionHistory
 *  	where id = #{id}
 * 	group by %{searchType} having %{searchContent}(*) >= 1
 */
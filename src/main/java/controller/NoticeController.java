package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.Board;
import logic.ProjectService;

@Controller
public class NoticeController {
	
	@Autowired
	private ProjectService service;
	
	// 공지사항 리스트 조회용 메서드
	@RequestMapping("notice/list")
	public ModelAndView noticeList(Integer pageNum, String searchType, String searchContent) {

		if (pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}

		ModelAndView mav = new ModelAndView();

		int limit = 10; // 한 페이지에 나올 게시글의 숫자
		int listcount = service.boardcount(searchType, searchContent); // 표시될 총 게시글의 수
		List<Board> boardlist = service.boardList(searchType, searchContent, pageNum, limit, 1);

		int maxpage = (int) ((double) listcount / limit + 0.95);
		int startpage = ((int) ((pageNum / 10.0 + 0.9) - 1)) * 10 + 1; // 시작페이지
		int endpage = startpage + 9; // 마지막 페이지
		if (endpage > maxpage)
			endpage = maxpage;
		int boardcnt = listcount - (pageNum - 1) * limit;
		
		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("listcount", listcount);
		mav.addObject("boardlist", boardlist);
		mav.addObject("boardcnt", boardcnt);

		return mav;
	}
	
	// 공지사항 게시글 세부 조회용 메서드
	@RequestMapping(value="notice/detail", method=RequestMethod.GET)
	public ModelAndView noticeDetail(Integer pageNum, Integer bNo) {
		
		ModelAndView mav = new ModelAndView();
		Board board = service.getBoard(bNo);
		mav.addObject("board", board);
		
		return mav;
	}
}

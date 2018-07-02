package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exception.ProjectException;
import logic.Board;
import logic.Member;
import logic.ProjectService;

@Controller
public class NoticeController {
	@Autowired
	private ProjectService service;
	int kind = 1;

	
	@ModelAttribute
	public Board getBoard() {
		return new Board();
	}

	@RequestMapping("notice/list")
	public ModelAndView list(Integer pageNum, String searchType, String searchContent) {

		if (pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}

		ModelAndView mav = new ModelAndView();

		int limit = 10; // 한 페이지에 나올 게시글의 숫자
		int listcount = service.boardcount(searchType, searchContent); // 표시될 총 게시글의 수
		List<Board> boardlist = service.boardList(searchType, searchContent, pageNum, limit);

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
		mav.addObject("kind",kind);
		return mav;
	}

	@RequestMapping(value = "notice/write", method = RequestMethod.POST) // 게시글 작성 시 호출되는 메서드
	public ModelAndView write(@Valid Board board, BindingResult bindingResult, HttpServletRequest request, Integer pageNum) {

		ModelAndView mav = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			System.out.println(bindingResult.getModel());
			return mav;
		}

		try {
			service.boardWrite(board, request);
			mav.setViewName("redirect:/notice/list.sms?pageNum="+pageNum);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("오류가 발생하였습니다.", "redirect:/notice/list.sms");
		}
		mav.addObject("kind",kind);
		return mav;
	}

	@RequestMapping(value = "notice/update", method = RequestMethod.POST) // 게시글 작성 시 호출되는 메서드
	public ModelAndView update(@Valid Board board, BindingResult bindingResult, HttpServletRequest request, Integer pageNum) {

		ModelAndView mav = new ModelAndView();

		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		try {
			service.boardUpdate(board, request);
			mav.setViewName("redirect:/notice/list.sms?pageNum="+pageNum);
		} catch (Exception e) {
			throw new ProjectException("오류가 발생하였습니다.", "list.sms");
		}
		mav.addObject("kind",kind);
		return mav;
	}

	@RequestMapping(value = "notice/delete", method = RequestMethod.POST)
	public ModelAndView delete(Integer bNo, Integer pageNum) {

		ModelAndView mav = new ModelAndView();
		try {
			service.boardDelete(bNo);
			mav.setViewName("redirect:/notice/list.sms?pageNum="+pageNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("kind",kind);
		return mav;
	}

	@RequestMapping(value = "notice/*", method = RequestMethod.GET)
	public ModelAndView detail(Integer bNo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Board board = new Board();
		if (bNo != null) {
			board = service.getBoard(bNo);
			String url = request.getServletPath();
			if (url.contains("/notice/detail.sms")) {
				service.updateReadCnt(bNo);
			}
		}
		mav.addObject("kind",kind);
		mav.addObject("pageNum",1);
		mav.addObject("board", board);
		return mav;
	}

}

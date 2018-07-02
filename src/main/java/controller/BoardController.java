package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exception.ProjectException;
import logic.Board;
import logic.Member;
import logic.ProjectService;

@Controller
public class BoardController {
	@Autowired
	private ProjectService service;
	
	@RequestMapping("board/listex")
	public ModelAndView list (Integer pageNum, String searchType, String searchContent,Integer kind) {
		
		if(pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		
		ModelAndView mav = new ModelAndView();
		
		int limit = 10;		// 한 페이지에 나올 게시글의 숫자
		int listcount = service.boardcount(searchType, searchContent);	// 표시될 총 게시글의 수
		List<Board> boardlist = service.boardList(searchType, searchContent, pageNum, limit);
		
		int maxpage = (int)((double)listcount/limit + 0.95);
		int startpage = ((int)((pageNum/10.0 + 0.9) - 1)) * 10 + 1; // 시작페이지
		int endpage = startpage + 9;	// 마지막 페이지
		if(endpage > maxpage) endpage = maxpage;
		int boardcnt = listcount - (pageNum - 1) * limit;
		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("listcount",listcount);
		mav.addObject("boardlist",boardlist);
		mav.addObject("boardcnt",boardcnt);
		mav.addObject("kind",kind);
				
		return mav;
	}
	
	// 게시글 등록하기
	@RequestMapping(value="board/write", method=RequestMethod.POST) // 게시글 작성 시 호출되는 메서드
	public ModelAndView write(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		
		try {
			service.boardWrite(board, request);
			mav.setViewName("redirect:/board/listex.sms");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("오류가 발생하였습니다." , "/board/write.sms");
		}
		return mav;
	}
	
	// 게시글 수정하기
	@RequestMapping(value="board/update", method=RequestMethod.POST) // 게시글 작성 시 호출되는 메서드
	public ModelAndView update(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
			
			ModelAndView mav = new ModelAndView();
			
			if(bindingResult.hasErrors()) {
				mav.getModel().putAll(bindingResult.getModel());
				return mav;
			}
			try {
				service.boardUpdate(board, request);
				mav.setViewName("redirect:/board/listex.sms");
			} catch (Exception e) {
				throw new ProjectException("오류가 발생하였습니다." , "/board/listex.sms");
			}
			return mav;
		}

	
	@RequestMapping(value="board/reply", method=RequestMethod.POST) // 댓글 작성 시 호출되는 메서드
	public ModelAndView reply(@Valid Board board, BindingResult bindingResult) {
		
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			board = service.getBoard(board.getbNo());
			mav.addObject("board", board);
			return mav;
		}
		
		try {
			service.boardReply(board);
			mav.setViewName("redirect:/board/list.shop");
		} catch (Exception e) {
			throw new ProjectException("오류가 발생하였습니다." , "/board/list.shop");
		}
		
		return mav;
	}
	
	@RequestMapping(value="board/delete", method=RequestMethod.POST)
	public ModelAndView delete(Integer bNo, Member mem, Integer pageNum) {
		
		ModelAndView mav = new ModelAndView();
		
		Board dbBoard = service.getBoard(bNo);
		
//		 if(dbBoard.getId().equals(mem.getId())) {	
			service.boardDelete(bNo);
			mav.setViewName("redirect:/board/listex.sms?pageNum=" + pageNum);
			return mav;
//		} else {
//			throw new ProjectException("비밀번호 오류","delete.sms?bNo=" + bNo + "&pageNum=" + pageNum);
//		}
	}
	
	
	@RequestMapping(value="board/*", method=RequestMethod.GET) // 게시글 작성 View로 접속할 때 호출되는 메서드
	public ModelAndView detail(Integer bNo, HttpServletRequest request) {
				
		ModelAndView mav = new ModelAndView();
		
		Board board = new Board();

		if(bNo != null) {
			board = service.getBoard(bNo);
			String url = request.getServletPath();
			
			if(url.contains("/board/detail.sms")) {
				service.updateReadCnt(bNo);
			}
		}
		
		mav.addObject("board", board);
		return mav;
	}

}

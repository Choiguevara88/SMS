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
public class QAController {
	@Autowired
	private ProjectService service;
	
	int kind = 3;
	
	// 1:1 문의 작성 시 호출 되는 메서드
	@RequestMapping(value="qa/questionAdmin", method=RequestMethod.GET)
	public ModelAndView writeQuestion (String id) {
		
		ModelAndView mav = new ModelAndView();
		
		Member writer = service.getMember(id);
		
		Board board = new Board();
		
		if(writer.getHostName() != null) {	// 해당 아이디가 Host계정이라면?
			board.setKind(5);				// Board 객체 Kind = 5 부여
		} else {							// 해당 아이디가 Host 계정이 아니라면?
			board.setKind(4);				// Board 객체 Kind = 4 부여
		}
		
		mav.addObject("board", board);
		mav.setViewName("qa/write");
		
		return mav;
	}
	
	@RequestMapping("qa/list")
	public ModelAndView list (Integer pageNum, Integer kind, Integer sNo) {
		
		if(pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		
		ModelAndView mav = new ModelAndView();
		
		int limit = 5;		// 한 페이지에 나올 게시글의 숫자
		int listcount = service.boardcount(kind, sNo);	// 표시될 총 게시글의 수
		List<Board> boardlist = service.boardList(kind, sNo, pageNum, limit);
		
		int maxpage = (int)((double)listcount/limit + 0.95);
		int startpage = ((int)((pageNum/5.0 + 0.9) - 1)) * 5 + 1; // 시작페이지
		int endpage = startpage + 4;	// 마지막 페이지
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
		mav.addObject("sNo",sNo);
				
		return mav;
	}
	
	// 게시글 등록하기
	@RequestMapping(value="qa/write", method=RequestMethod.POST) // 게시글 작성 시 호출되는 메서드
	public ModelAndView write(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		int sNo = Integer.parseInt(request.getParameter("sNo"));
		
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		
		try {
			service.boardWrite(board, request);
			mav.setViewName("redirect:/qa/list.sms");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("오류가 발생하였습니다." , "/qa/write.sms");
		}
		mav.addObject("sNo",sNo);
		mav.addObject("kind",kind);
		return mav;
	}
	
	// 게시글 수정하기
	@RequestMapping(value="qa/update", method=RequestMethod.POST) // 게시글 작성 시 호출되는 메서드
	public ModelAndView update(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
			
			ModelAndView mav = new ModelAndView();
			int sNo = board.getsNo();
			
			if(bindingResult.hasErrors()) {
				mav.getModel().putAll(bindingResult.getModel());
				return mav;
			}
			try {
				service.boardUpdate(board, request);
				mav.setViewName("redirect:/qa/list.sms");
			} catch (Exception e) {
				e.printStackTrace();
				throw new ProjectException("오류가 발생하였습니다." , "/qa/list.sms");
			}
			mav.addObject("kind",kind);
			mav.addObject("sNo",sNo);
			return mav;
		}
	
	@RequestMapping(value="qa/reply", method=RequestMethod.POST) // 댓글 작성 시 호출되는 메서드
	public ModelAndView reply(@Valid Board board, BindingResult bindingResult) {
		
		ModelAndView mav = new ModelAndView();
		int sNo = board.getsNo();
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			board = service.getBoard(board.getbNo());
			mav.addObject("board", board);
			return mav;
		}
		
		try {
			service.boardReply(board);
			mav.setViewName("redirect:/qa/list.sms");
		} catch (Exception e) {
			throw new ProjectException("오류가 발생하였습니다." , "/review/list.shop");
		}
		mav.addObject("kind",kind);
		mav.addObject("sNo",sNo);
		return mav;
	}
	
	@RequestMapping(value="qa/delete", method=RequestMethod.POST)
	public ModelAndView delete(Integer bNo, Integer pageNum,Integer sNo,Integer kind) {
		
		ModelAndView mav = new ModelAndView();
		
			service.boardDelete(bNo);
			mav.setViewName("redirect:/qa/list.sms?sNo="+sNo+"&kind="+kind);
			return mav;
	}
	
	@RequestMapping(value="qa/*", method=RequestMethod.GET) // 게시글 작성 View로 접속할 때 호출되는 메서드
	public ModelAndView detail(Integer bNo, HttpServletRequest request) {
				
		ModelAndView mav = new ModelAndView();
		
		Board board = new Board();

		if(bNo != null) {
			board = service.getBoard(bNo);
			String url = request.getServletPath();
			
			if(url.contains("/qa/detail.sms")) {
				service.updateReadCnt(bNo);
			}
		}
		mav.addObject("kind",kind);
		mav.addObject("board", board);
		return mav;
	}

}

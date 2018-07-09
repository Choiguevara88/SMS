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
import logic.ProjectService;

@Controller
public class ReViewController {
	@Autowired
	private ProjectService service;
	
	@ModelAttribute
	public Board getBoard() {
		return new Board();
	}
	
	@RequestMapping("review/Rlist") //http://localhost:8080/TestProject/review/Rlist.sms?sno=2
	public ModelAndView Rlist(Integer sNo,Integer pageNum) {
		int kind = 2;
		if(pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		
		ModelAndView mav = new ModelAndView();
		
		int limit = 5;		// 한 페이지에 나올 게시글의 숫자
		int listcount = service.boardcount(kind,sNo);	// 표시될 총 게시글의 수
		List<Board> boardlist = service.boardList(kind, sNo, pageNum, limit);
		double avg = 0;
		if(kind == 2) {	// 리뷰 게시글인 경우 MAV 객체에 해당 Building의 평점을 추가하는 로직
			if(boardlist != null && !boardlist.isEmpty() ) {
				 List<Board> boardlist2 = service.boardList(kind, sNo);
				 avg = boardlist2.stream().mapToInt(Board :: getScore).average().getAsDouble();
			}
			mav.addObject("avgScore",avg);
		}
		
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
	@RequestMapping("review/Qlist")
	public ModelAndView Qlist(Integer pageNum, Integer sNo) {
		int kind = 3;
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
	@RequestMapping(value="review/Rwrite", method=RequestMethod.POST) // 게시글 작성 시 호출되는 메서드
	public ModelAndView Rwrite(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		int sNo = Integer.parseInt(request.getParameter("sNo"));
		int kind = Integer.parseInt(request.getParameter("kind"));
		System.out.println(sNo);
		System.out.println(kind);
		
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		
		try {
			service.boardWrite(board, request);
			mav.setViewName("redirect:/review/Rlist.sms");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("오류가 발생하였습니다." , "/review/write.sms");
		}
		mav.addObject("sNo",sNo);
		mav.addObject("kind",kind);
		return mav;
	}
	@RequestMapping(value="building/Qwrite", method=RequestMethod.POST) // 게시글 작성 시 호출되는 메서드
	public ModelAndView Qwrite(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		int sNo = Integer.parseInt(request.getParameter("sNo"));
		int kind = Integer.parseInt(request.getParameter("kind"));
		
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		
		try {
			service.boardWrite(board);
			mav.setViewName("redirect:/building/buildingDetail.sms");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("오류가 발생하였습니다." , "/review/write.sms");
		}
		mav.addObject("sNo",sNo);
		mav.addObject("kind",kind);
		return mav;
	}
	
	// 게시글 수정하기
	@RequestMapping(value="review/*update", method=RequestMethod.POST) // 게시글 작성 시 호출되는 메서드
	public ModelAndView Rupdate(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
			
			ModelAndView mav = new ModelAndView();
			int sNo = board.getsNo();
			int kind = board.getKind();
			
			if(bindingResult.hasErrors()) {
				mav.getModel().putAll(bindingResult.getModel());
				return mav;
			}
			try {
			if(kind==2) {
				service.boardUpdate(board, request);
				mav.setViewName("redirect:/review/Rlist.sms");
			}else {
				service.boardUpdate(board);
				mav.setViewName("redirect:/review/Qlist.sms");
			}
			} catch (Exception e) {
				e.printStackTrace();
				if(kind==2) {
					throw new ProjectException("오류가 발생하였습니다." , "/review/Rupdate.sms");
				}else {
					throw new ProjectException("오류가 발생하였습니다." , "/review/Qupdate.sms");
				}
			}
			mav.addObject("kind",kind);
			mav.addObject("sNo",sNo);
			return mav;
		}
	
	@RequestMapping(value="review/*reply", method=RequestMethod.POST) // 댓글 작성 시 호출되는 메서드
	public ModelAndView reply(@Valid Board board, BindingResult bindingResult) {
		
		ModelAndView mav = new ModelAndView();
		int sNo = board.getsNo();
		int kind = board.getKind();
		if(bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			board = service.getBoard(board.getbNo());
			mav.addObject("board", board);
			return mav;
		}
		
		try {
			service.boardReply(board);
			if(kind==2) {
				mav.setViewName("redirect:/review/Rlist.sms");
			}else {
				mav.setViewName("redirect:/review/Qlist.sms");
			}
		} catch (Exception e) {
			if(kind==2) {
				throw new ProjectException("오류가 발생하였습니다." , "/review/Rreply.sms");
			}else {
				throw new ProjectException("오류가 발생하였습니다." , "/review/Qreply.sms");
			}
		}
		mav.addObject("kind",kind);
		mav.addObject("sNo",sNo);
		return mav;
	}
	
	@RequestMapping(value="building/*delete", method=RequestMethod.POST)
	public ModelAndView delete(Integer bNo, Integer pageNum,Integer sNo,Integer kind) {
		
		ModelAndView mav = new ModelAndView();
		service.boardDelete(bNo);
		mav.setViewName("redirect:/building/buildingDetail.sms?sNo="+sNo);
		return mav;
	}
	
	@RequestMapping(value="review/*", method=RequestMethod.GET) // 게시글 작성 View로 접속할 때 호출되는 메서드
	public ModelAndView detail(Integer bNo, HttpServletRequest request) {
				
		ModelAndView mav = new ModelAndView();
		
		Board board = new Board();
		int kind = 2;
		if(bNo != null) {
			board = service.getBoard(bNo);
			String url = request.getServletPath();
			
			if(url.contains("/review/detail.sms")) {
				service.updateReadCnt(bNo);
			}
		}
		mav.addObject("kind",kind);
		mav.addObject("board", board);
		return mav;
	}
}

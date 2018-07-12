package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import logic.Board;
import logic.Member;
import logic.ProjectService;

@Controller
public class QAController {
	@Autowired
	private ProjectService service;

	//1:1 문의 작성 시 호출 되는 메서드
	@RequestMapping(value="qa/questionAdmin", method = RequestMethod.GET)
	public ModelAndView writeQuestion(String id, HttpSession session) {
		Member writer = service.getMember(id);
		ModelAndView mav = new ModelAndView();
		int kind = 0;
		if (writer.getHostName() != null) { // 해당 아이디가 Host계정이라면?
			kind = 5; // Board 객체 Kind = 5 부여
		} else { // 해당 아이디가 Host 계정이 아니라면?
			kind = 4; // Board 객체 Kind = 4 부여
		}
		mav.addObject("kind", kind);
		return mav;
	}
	@ResponseBody
	@RequestMapping(value="qa/questionAdmin.sms",method =RequestMethod.POST)
	public void insert(@ModelAttribute Board board,String id,String content,Integer kind) {
		board.setId(id);
		board.setContent(content);
		board.setKind(kind);
		service.boardWrite(board);
	}
	@RequestMapping("qa/questionList.sms")
	public ModelAndView list(String id, Integer kind, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<Board> list = service.boardList(kind, id);
		mav.setViewName("qa/questionList");
		mav.addObject("list",list);
		mav.addObject("kind",kind);
		return mav;
	}

	/*@RequestMapping(value = "qa/questionAdmin", method = RequestMethod.POST)
	public ModelAndView write(String id, HttpSession session, Integer kind, String content) {
		ModelAndView mav = new ModelAndView();
		Board board = new Board();
		board.setContent(content);
		board.setId(id);
		board.setKind(kind);
		board.setqType(0);
		try {
			service.boardWrite(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("redirect:/qa/quetstionList.sms");
		mav.addObject("id", id);
		return mav;
	}
*/
/*	@RequestMapping(value = "qa/questionList", method = RequestMethod.GET)
	public ModelAndView list(String id, HttpSession session, Integer kind) {
		ModelAndView mav = new ModelAndView();
		List<Board> boardlist = service.boardList(kind, id);
		mav.addObject("board", boardlist);
		return mav;
	}*/

/*	@RequestMapping(value = "qa/questionUpdate", method = RequestMethod.POST) // 게시글 작성 시 호출되는 메서드
	public ModelAndView Rupdate(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		int kind = board.getKind();

		try {
			service.boardUpdate(board);
			mav.setViewName("redirect:/review/Qlist.sms");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("오류가 발생하였습니다.", "/qa/questionUpdate.sms");
		}
		mav.addObject("kind", kind);
		return mav;
	}*/

/*	@RequestMapping(value = "qa/questionReply", method = RequestMethod.POST) // 댓글 작성 시 호출되는 메서드
	public ModelAndView reply(@Valid Board board, BindingResult bindingResult) {

		ModelAndView mav = new ModelAndView();
		int kind = board.getKind();
		String id = board.getId();
		try {
			service.boardReply(board);
			mav.setViewName("redirect:/qa/questionAdmin.sms");
		} catch (Exception e) {
			throw new ProjectException("오류가 발생하였습니다.", "/qa/Rreply.sms");
		}
		mav.addObject("kind", kind);
		mav.addObject("id", id);
		return mav;
	}*/

/*	@RequestMapping(value = "qa/questiondelete", method = RequestMethod.POST)
	public ModelAndView delete(Integer bNo, Integer kind) {

		ModelAndView mav = new ModelAndView();
		service.boardDelete(bNo);
		Board board = service.getBoard(bNo);
		String id = board.getId();
		mav.setViewName("redirect:/qa/questionAdmin.sms");
		mav.addObject("kind",kind);
		mav.addObject("id",id);
		return mav;
	}*/
	
/*	@RequestMapping(value = "qa/*", method = RequestMethod.GET) // 게시글 작성 View로 접속할 때 호출되는 메서드
	public ModelAndView detail(Integer bNo, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		Board board = new Board();
		
		if (bNo != null) {
			board = service.getBoard(bNo);
			String url = request.getServletPath();
			
			if (url.contains("/qa/detail.sms")) {
				service.updateReadCnt(bNo);
			}
		}
		mav.addObject("board", board);
		return mav;
	}*/
}

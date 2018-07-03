package logic;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dao.BoardDao;
import dao.BuildingDao;
import dao.MemberDao;
import dao.ReserveDao;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private MemberDao memDao;
	@Autowired
	private BoardDao boDao;
	@Autowired
	private ReserveDao reDao;
	@Autowired
	private BuildingDao buDao;

	@Override
	public Member getMember(String id) {
		return memDao.select(id);
	}

	@Override
	public int boardcount(String searchType, String searchContent) {
		return boDao.count(searchType, searchContent);
	}

	@Override
	public List<Board> boardList(String searchType, String searchContent, Integer pageNum, int limit) {
		return boDao.list(searchType, searchContent, pageNum, limit);
	}

	@Override // board Write Method()
	public void boardWrite(Board board, HttpServletRequest request) {
		
		if (board.getImg1File() != null && !board.getImg1File().isEmpty()) { 
			String img = uploadImgCreate(board.getImg1File(),request);	
			if(img != null) board.setImg1(img); 
		}
		
		if (board.getImg2File() != null && !board.getImg2File().isEmpty()) { 
			String img = uploadImgCreate(board.getImg2File(),request);
			if(img != null) board.setImg2(img);
		}
		
		if (board.getImg3File() != null && !board.getImg3File().isEmpty()) { 
			String img = uploadImgCreate(board.getImg3File(),request);
			if(img != null) board.setImg3(img);
		}
		
		if (board.getImg4File() != null && !board.getImg4File().isEmpty()) { 
			String img = uploadImgCreate(board.getImg1File(),request);
			if(img != null) board.setImg1(img);
		}
		int num = boDao.maxNum();
		
		board.setbNo(++num);
		board.setRef(num);
		board.setRefLevel(0);

		boDao.insert(board);

	}

	@Override
	public Board getBoard(int num) {
		return boDao.getBoard(num);
	}

	@Override
	public void boardReply(Board board) {
		// TODO Auto-generated method stub

	}

	@Override // board Update Method()
	public void boardUpdate(Board board, HttpServletRequest request) {	
		
		if (board.getImg1File() != null && !board.getImg1File().isEmpty()) { // img1  
			String img = uploadImgCreate(board.getImg1File(),request);	// img1 upload & img1Name setting 
			if(img != null) board.setImg1(img); // img1Name input
		}
		
		if (board.getImg2File() != null && !board.getImg2File().isEmpty()) { 
			String img = uploadImgCreate(board.getImg2File(),request);
			if(img != null) board.setImg2(img);
		}
		
		if (board.getImg3File() != null && !board.getImg3File().isEmpty()) { 
			String img = uploadImgCreate(board.getImg3File(),request);
			if(img != null) board.setImg3(img);
		}
		
		if (board.getImg4File() != null && !board.getImg4File().isEmpty()) { 
			String img = uploadImgCreate(board.getImg1File(),request);
			if(img != null) board.setImg1(img);
		}
		
		boDao.update(board);

	}

	@Override
	public void boardDelete(Integer num) {
		boDao.delete(num);
	}

	@Override
	public void updateReadCnt(Integer num) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public Reserve getReserve(Integer reNo) {
		return reDao.getReserve(reNo);
	}
	

	@Override
	public List<Reserve> getReserveList(String id) {
		return reDao.getReserveList(id);
	}
	
	@Override
	public int reserveCount(String id, String searchType, String searchContent) {
		return reDao.count(id, searchType, searchContent);
	}
	
	@Override
	public int hostReserveCount(Integer sNo, String searchType, String searchContent) {
		return reDao.hostCount(sNo, searchType, searchContent);
	}
	
	@Override
	public List<Reserve> selectReserveList(String id, String searchType, String searchContent, Integer pageNum, int limit) {
		return reDao.list(id, searchType, searchContent, pageNum, limit);
	}
	
	@Override
	public int hostBuildCount(String hostName) {
		return buDao.hostBuildCnt(hostName);
	}
	
	@Override
	public List<Building> selectHostReserveInfo(String hostName) {
		return buDao.resList(hostName);
	}
	
	@Override
	public List<Reserve> selectHostReserveList(Integer sNo, String searchType, String searchContent, Integer pageNum, int limit) {
		return reDao.hostlist(sNo, searchType, searchContent, pageNum, limit);
	}

	@Override
	public void reserveInsert(Reserve reserve) {
		reserve.setReNo(reserve.getSrNo() + (int)new Date().getTime());
		reDao.insert(reserve);
	}
	
	@Override
	public void reserveUpdate(Reserve reserve) {
		
	}

	private String uploadImgCreate(MultipartFile picture, HttpServletRequest request) { // imgUploadMethod()

		Date date = new Date();

		try {
			
			String uploadPath = request.getServletContext().getRealPath("/") + "/picture/"; // upload path setting
			String orgFile = date.getTime() + picture.getOriginalFilename(); // imgFileName setting
			picture.transferTo(new File(uploadPath + orgFile)); // new File(uploadPath + orgFile) : img upload complite
			
			return orgFile; // imgFileName Return
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} // uploadImgCreate() end

	@Override
	public void joinsms(Member member) {
		memDao.joinsms(member);
	}

	@Override
	public void updateMember(Member member) {
		memDao.updateMember(member);
	}

	@Override
	public int boardcount(Integer kind, int sNo) {
		return boDao.count(kind, sNo);
	}

	@Override
	public List<Board> boardList(Integer kind, int sNo, Integer pageNum, int limit) {
		return boDao.list(kind, sNo, pageNum, limit);
	}



} // ProjectServiceImpl end

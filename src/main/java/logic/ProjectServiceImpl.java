package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dao.BoardDao;
import dao.BuildingDao;
import dao.FavoriteDao;
import dao.MemberDao;
import dao.ReserveDao;
import dao.RoomDao;
import dao.TransactionHistoryDao;

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
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private TransactionHistoryDao tranDao;
	@Autowired
	private FavoriteDao faDao;

	@Override
	public Member getMember(String id) {
		return memDao.select(id);
	}

	@Override
	public int boardcount(String searchType, String searchContent, int kind) {
		return boDao.count(searchType, searchContent, kind);
	}
	
	@Override
	public int boardcount(Integer kind, int sNo) {
		return boDao.count(kind, sNo);
	}

	@Override
	public List<Board> boardList(String searchType, String searchContent, Integer pageNum, int limit, int kind) {
		return boDao.list(searchType, searchContent, pageNum, limit, kind);
	}

	@Override // board Write Method()
	public void boardWrite(Board board, HttpServletRequest request) {

		if (board.getImg1File() != null) {
			String img = uploadImgCreate(board.getImg1File(), request);
			if (img != null)
				board.setImg1(img);
		}

		if (board.getImg2File() != null) {
			String img = uploadImgCreate(board.getImg2File(), request);
			if (img != null)
				board.setImg2(img);
		}

		if (board.getImg3File() != null) {
			String img = uploadImgCreate(board.getImg3File(), request);
			if (img != null)
				board.setImg3(img);
		}

		if (board.getImg4File() != null) {
			String img = uploadImgCreate(board.getImg4File(), request);
			if (img != null)
				board.setImg4(img);
		}
		int num = boDao.maxNum();

		board.setbNo(++num);
		board.setRef(num);
		board.setRefLevel(0);
		boDao.insert(board);

	}
	
	@Override
	public void boardWrite(Board board) {
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
		int num = boDao.maxNum();
		board.setbNo(++num);
		board.setRefLevel(board.getRefLevel() + 1);
		boDao.replyInsert(board);
		boDao.qTypeAdd(board);
	}

	@Override // board Update Method()
	public void boardUpdate(Board board, HttpServletRequest request) {

		if (board.getImg1File() != null) { // img1
			String img = uploadImgCreate(board.getImg1File(), request); // img1 upload & img1Name setting
			if (img != null)
				board.setImg1(img); // img1Name input
		}

		if (board.getImg2File() != null) {
			String img = uploadImgCreate(board.getImg2File(), request);
			if (img != null)
				board.setImg2(img);
		}

		if (board.getImg3File() != null) {
			String img = uploadImgCreate(board.getImg3File(), request);
			if (img != null)
				board.setImg3(img);
		}

		if (board.getImg4File() != null) {
			String img = uploadImgCreate(board.getImg4File(), request);
			if (img != null)
				board.setImg4(img);
		}

		boDao.update(board);

	}
	@Override
	public void boardUpdate(Board board) {
		boDao.update(board);
	}
	
	@Override
	public void boardDelete(Integer num) {
		boDao.delete(num);
	}

	@Override
	public void updateReadCnt(Integer num) {

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
	public List<Reserve> selectReserveList(String id, String searchType, String searchContent, Integer pageNum,
			int limit, String startDate, String endDate) {
		return reDao.list(id, searchType, searchContent, pageNum, limit, startDate, endDate);
	}

	@Override
	public void reserveCancel(Integer reNo, Integer reStat) {
		reDao.cancel(reNo, reStat);
	}

	@Override
	public int hostBuildCount(String hostName) {
		return buDao.hostBuildCnt(hostName);
	}

	@Override
	public Building selectHostReserveInfo(String hostName, Integer sNo) {
		return buDao.resInfo(hostName, sNo);
	}

	@Override
	public void reserveInsert(Reserve reserve) {
		reserve.setReNo(Math.abs((int)new Date().getTime()) + reserve.getSrNo());
		reDao.insert(reserve);
	}

	@Override
	public void reserveUpdate(Reserve reserve) {
		reDao.update(reserve);
	}

	private String uploadImgCreate(MultipartFile picture, HttpServletRequest request) { // imgUploadMethod()
		String uploadPath = request.getServletContext().getRealPath("/") + "/picture/"; // upload path setting
		Date date = new Date();
		String orgFile = "";
		
		if(!picture.getOriginalFilename().equals("")) {
			orgFile = date.getTime() + picture.getOriginalFilename(); // imgFileName setting
		}
		
		try {
			picture.transferTo(new File(uploadPath + orgFile)); // new File(uploadPath + orgFile) : img upload complite
			return orgFile; // imgFileName Return

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} // uploadImgCreate() end

	private String uploadImgCreate2(List<MultipartFile> pictures, HttpServletRequest request) { // imgUploadMethod()

		String uploadPath = request.getServletContext().getRealPath("/") + "/picture/"; // upload path setting
		Date date = new Date();
		String orgFile = "";
		String fileName = "";

		for (MultipartFile picture : pictures) {
			
			if(!picture.getOriginalFilename().equals("")) {
				fileName = date.getTime() + picture.getOriginalFilename(); // imgFileName setting
			}
			
			try {
				picture.transferTo(new File(uploadPath + fileName)); // new File(uploadPath + orgFile) : img upload
																		// complite
				orgFile = orgFile + fileName +"|";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return orgFile;
	}

	@Override
	public void joinsms(Member member) {
		memDao.joinsms(member);
	}

	@Override
	public void updateMember(Member member) {
		memDao.updateMember(member);
	}



	@Override
	public List<Board> boardList(Integer kind, int sNo, Integer pageNum, int limit) {
		return boDao.list(kind, sNo, pageNum, limit);
	}

	@Override
	public void insertRoom(Room room,HttpServletRequest request) {
		if (room.getsRImgList() != null) {
			String imgs = uploadImgCreate2(room.getsRImgList(), request);
			if (imgs != null && !imgs.equals(""))
				room.setsRImg(imgs);
		} else {
			room.setsRImg(listToString(room.getsRImgNameList()));
		}
		int sRNo = roomDao.maxNum();
		room.setsRNo(++sRNo);
		roomDao.insertRoom(room);
	}

	@Override
	public int hostReserveCount(Integer sNo, String searchType, String searchContent) {
		return reDao.hostCount(sNo, searchType, searchContent);
	}

	@Override
	public List<Reserve> selectHostReserveList(Integer sNo, String searchType, String searchContent, Integer pageNum, int limit, String startDate, String endDate) {
		return reDao.hostlist(sNo, searchType, searchContent, pageNum, limit, startDate, endDate);
	}

	@Override
	public List<Integer> hostHaveBuildsNo(String hostId) {
		return buDao.hostHaveBuildsNo(hostId);
	}

	@Override
	public void hostPaymentConfirm(Integer reNo) {
		reDao.hostPaymentConfirm(reNo);
	}

	@Override
	public double boardList(Integer kind, Integer sNo) {
		return boDao.list(kind, sNo);
	}

	@Override
	public void buildingReg(Building building, HttpServletRequest request) {
		if (building.getsImg1File() != null) {
			String img1 = uploadImgCreate(building.getsImg1File(), request);
			if (img1 != null)
				building.setsImg1(img1);
		}

		if (!(building.getsImg2Files().isEmpty())) {
			String img2 = uploadImgCreate2(building.getsImg2Files(), request);
			if (img2 != null)
				building.setsImg2(img2);
		}

		int sNo = buDao.maxNum();
		building.setsNo(++sNo);
		String sType = listToString(building.getsTypeList());
		String sTag = listToString(building.getsTagList());
		String sInfoSub = listToString(building.getsInfoSubList());
		String sRule = listToString(building.getsRuleList());
		String sBHour = listToString(building.getsBHourList());
		building.setsType(sType);
		building.setsTag(sTag);
		building.setsInfoSub(sInfoSub);
		building.setsRule(sRule);
		building.setsBHour(sBHour);
		System.out.println(sType);
	
	
		buDao.buRegist(building);
	}
	private String listToString(List<String> list) {
		String li = "";
		for (int i = 0; i < list.size(); i++) {
			li += list.get(i) + "|";
		}
		return li;
	}
	private String listToString2(List<String> list) {
		String li = "";
		for (int i = 0; i < list.size(); i++) {
			li += list.get(i) + ",";
		}
		return li;
	}
	@Override
	public List<Board> guestQuestionList() {
		return boDao.guestQuestionList();
	}
	@Override
	public List<Board> guestQuestionList1(String searchType, String searchContent) {
		return boDao.guestQuestionList1(searchType, searchContent);
	}

	@Override
	public List<Board> hostQuestionList() {
		return boDao.hostQuestionList();
	}
	@Override
	public List<Board> hostQuestionList1(String searchType, String searchContent) {
		return boDao.hostQuestionList1(searchType, searchContent);
	}

	@Override
	public List<Member> hostRegList() {
		return memDao.getHostRegList();
	}

	@Override
	public void hostRegister(String id) {
		memDao.hostRegister(id);
	}

	@Override
	public void becomeaHost(Member member, HttpServletRequest request) {
		if (member.getPicture() != null && !member.getPicture().isEmpty()) {
			uploadFileCreate(member.getPicture(), request);
			member.setPictureUrl(member.getPicture().getOriginalFilename());
		}
		memDao.becomeaHost(member);
	}

	private void uploadFileCreate(MultipartFile picture, HttpServletRequest request) {
		String uploadPath = request.getServletContext().getRealPath("/") + "/picture/";
		String orgFile = picture.getOriginalFilename();
		try {
			picture.transferTo(new File(uploadPath + orgFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Member find_member(String name, String email) {
		return memDao.find_member(name, email);
	}

	@Override
	public Member find_password(String id, String email, String name) {
		return memDao.find_password(id, email, name);
	}

	@Override
	public List<Building> getMyBuildings(String id) {
		return buDao.getMyBuildings(id);
	}

	@Override
	public Building getMyBuildingOne(String sNo) {
		Building myBuildingOne = buDao.getMyBuildingOne(sNo);
		String sTypes = myBuildingOne.getsType();
		String sTags = myBuildingOne.getsTag();
		String sInfoSubs = myBuildingOne.getsInfoSub();
		String sRules = myBuildingOne.getsRule();
		String sBHours = myBuildingOne.getsBHour();
		String sImg2s = myBuildingOne.getsImg2();
		List<String> sTypeList = new ArrayList<String>(Arrays.asList(sTypes.split("[|]")));
		List<String> sTagList = new ArrayList<String>(Arrays.asList(sTags.split("[|]")));
		List<String> sInfoSubList = new ArrayList<String>(Arrays.asList(sInfoSubs.split("[|]")));
		List<String> sRuleList = new ArrayList<String>(Arrays.asList(sRules.split("[|]")));
		List<String> sBHourList = new ArrayList<String>(Arrays.asList(sBHours.split("[|]")));
		if(sImg2s != null) {
			List<String> sImg2Name = new ArrayList<String>(Arrays.asList(sImg2s.split("[|]")));
			myBuildingOne.setsImg2Name(sImg2Name);
		}
		myBuildingOne.setsTypeList(sTypeList);
		myBuildingOne.setsTagList(sTagList);
		myBuildingOne.setsInfoSubList(sInfoSubList);
		myBuildingOne.setsRuleList(sRuleList);
		myBuildingOne.setsBHourList(sBHourList);
		
		return myBuildingOne;
	}

	@Override
	public void buildingUpdateReg(Building building, HttpServletRequest request) {
		
		if (building.getsImg1File() != null) {
			
			String img1 = uploadImgCreate(building.getsImg1File(), request);
			
			if (img1 != null)
				building.setsImg1(img1);
		}

		if (building.getsImg2Files() != null) {
			
			String img2 = uploadImgCreate2(building.getsImg2Files(), request);
			
			if (img2 != null && !img2.equals(""))
				building.setsImg2(img2);
		} else {
			building.setsImg2(listToString(building.getsImg2Name()));
		}
		
		String sType = listToString(building.getsTypeList());
		String sTag = listToString(building.getsTagList());
		String sInfoSub = listToString(building.getsInfoSubList());
		String sRule = listToString(building.getsRuleList());
		String sBHour = listToString(building.getsBHourList());
		building.setsType(sType);
		building.setsTag(sTag);
		building.setsInfoSub(sInfoSub);
		building.setsRule(sRule);
		building.setsBHour(sBHour);
		buDao.buUpdateReg(building);
	}
	
	@Override
	public List<Board> boardList(Integer kind,String id) {
		return boDao.list(kind,id);
	}

	@Override
	public Member find_member_by_email(String email) {
		return memDao.find_member_by_email(email);
	}

	@Override
	public List<TransactionHistory> hostTransHistoryList(String first) {
		return tranDao.transHistory(first);
	}
	
	@Override
	public Room getMyRoom(Room room) {
		Room myRoom = roomDao.getMyRoom(room);
		String sRImg = myRoom.getsRImg();
		String sRInfo = myRoom.getsRInfo();
		if(sRImg != null) {
			List<String> sRImgName = new ArrayList<String>(Arrays.asList(sRImg.split("[|]")));
			myRoom.setsRImgNameList(sRImgName);
		}
		if(sRInfo != null) {
			List<String> sRInfoList = new ArrayList<String>(Arrays.asList(sRInfo.split("[,]")));
			myRoom.setsRInfoList(sRInfoList);
		
		}
		return myRoom;
	}

	@Override
	public List<TransactionHistory> searchTransHistoryList(String searchType, String searchContent, String startDate, String endDate) {
		return tranDao.searchTransHistory(searchType, searchContent, startDate, endDate);
	}
	
	@Override
	public Map<String, Object> graphTransHistoryCnt(String searchType, String id) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		for(Map<String, Object> m : tranDao.graphTransCnt(searchType, id)) {
			map.put((String)m.get("key"), m.get("value"));
		}
		
		return map;
	}

	@Override
	public Map<String, Object> graphTransHistorySum(String searchType, String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		for(Map<String, Object> m : tranDao.graphTransSum(searchType, id)) {
			map.put((String)m.get("key"), m.get("value"));
		}
		
		return map;

	}

	@Override
	public List<Room> getmyRoomList(Integer sNo) {
		return roomDao.getmyRoomList(sNo);
	}

	@Override
	public void updateRoom(Room room,HttpServletRequest request) {
		
		if (room.getsRImgList() != null) {
			String img2 = uploadImgCreate2(room.getsRImgList(), request);	
			if (img2 != null && !img2.equals(""))
				room.setsRImg(img2);
		} else {
			room.setsRImg(listToString(room.getsRImgNameList()));
		}
		 
		
		String sRInfo = listToString2(room.getsRInfoList());
		room.setsRInfo(sRInfo);
		roomDao.updateRoom(room);
		}

	@Override
	public void deleteRoom(Room room) {
		roomDao.deleteRoom(room);
		
	}

	@Override
	public void deleteAccount(Member member) {
		memDao.deleteAccount(member);
	}

	@Override
	public List<Member> getMemberList(String searchType, String searchContent, String startDate, String endDate, Integer limit, Integer pageNum) {
		return memDao.getMemberList(searchType, searchContent, startDate, endDate, limit, pageNum);
	}

	@Override
	public int getMemberCnt(String searchType, String searchContent, String startDate, String endDate, Integer pageNum, Integer limit) {
		return memDao.getMemberCnt(searchType, searchContent, startDate, endDate, limit, pageNum);
	}

	@Override
	public int getHostCnt(String searchType, String searchContent, String startDate, String endDate, Integer pageNum, Integer limit) {
		return memDao.getHostCnt(searchType, searchContent, startDate, endDate, limit, pageNum);
	}

	@Override
	public List<Member> getHostList(String searchType, String searchContent, String startDate, String endDate, Integer pageNum, Integer limit) {
		return memDao.getHostList(searchType, searchContent, startDate, endDate, limit, pageNum);
	}

	@Override
	public Building getbuilding_mainpage(int sNo) {
		Building building = buDao.getbuilding_mainpage(sNo);
		String sTypes = building.getsType();
		String sTags = building.getsTag();
		List<String> sTypeList = new ArrayList<String>(Arrays.asList(sTypes.split("[|]")));
		List<String> sTagList = new ArrayList<String>(Arrays.asList(sTags.split("[|]")));
		building.setsTypeList(sTypeList);
		building.setsTagList(sTagList);
		return building;
	}

	@Override
	public List<Board> getbuildingNo_by_score() {
		return boDao.getbuildingNo_by_score();
	}

	@Override
	public Room getmyRoom_lowestprice(int i) {
		return roomDao.getmyRoom_lowestprice(i);
	}


	@Override
	public List<Member> selectMemberList(String[] idchks) {
		return memDao.getSelectMemberList(idchks);
	}

	@Override
	public int hostBoardCountQuest(Integer sNo) {
		return boDao.hostBoardCntQuest(sNo);
	}

	@Override
	public void reserveStatusUpdate(int reNo) {
		reDao.resStatUpdate(reNo);
		
	}

	@Override
	public Room getRoom(Integer sNo, Integer sRNo) {
		
		Room returnRoom = roomDao.getRoom(sNo, sRNo);
		
		List<String> imgList = new ArrayList<String>();
		List<String> infoList = new ArrayList<String>();
		
		if(returnRoom.getsRImg() != null && !returnRoom.getsRImg().equals("")) {
			imgList = Arrays.asList(returnRoom.getsRImg().split("[|]"));
		}
		
		if(returnRoom.getsRInfo() != null && !returnRoom.getsRInfo().equals("")) {
			infoList = Arrays.asList(returnRoom.getsRInfo());
		
		}
		
		returnRoom.setsRImgNameList(imgList);
		returnRoom.setsRInfoList(infoList);
	
		return returnRoom;
	}

	@Override
	public Favorite find(String id, Integer sNo) {
		return faDao.find(id, sNo);
	}

	@Override
	public void addfavorite(String id, Integer sNo) {
		faDao.addFavorite(id, sNo);
	}

	@Override
	public void deletefavorite(String id, Integer sNo) {
		faDao.deleteFavorite(id, sNo);
	}

	@Override
	public void buildingDelete(Integer sNo) {
		buDao.budelete(sNo);
		faDao.budelete(sNo);
		boDao.budelete(sNo);
		roomDao.budelete(sNo);
	}

	@Override
	public Building getbuilding_mainpage_reviewCount(Integer Integer) {
		Building building = buDao.getbuilding_mainpage(Integer);
		String sTypes = building.getsType();
		String sTags = building.getsTag();
		List<String> sTypeList = new ArrayList<String>(Arrays.asList(sTypes.split("[|]")));
		List<String> sTagList = new ArrayList<String>(Arrays.asList(sTags.split("[|]")));
		building.setsTypeList(sTypeList);
		building.setsTagList(sTagList);
		return building;
	}

	@Override
	public List<Board> getbuilding_reviewCount() {
		return boDao.getbuilding_reviewCount();
	}

	@Override
	public List<Board> getSNo_byScore() {
		return boDao.getSno_byScore();
	}

	@Override
	public List<Building> getMyWishBuildings(String id) {
		return buDao.getMyWishBuildings(id);
	}

}// ProjectServiceImpl end
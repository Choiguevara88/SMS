package logic;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ProjectService {

	int boardcount(String searchType, String searchContent, int kind);
	int boardcount(Integer kind, int sNo);
	int boardcountNR(int kind, Integer sNo);

	List<Board> boardList(String searchType, String searchContent, Integer pageNum, int limit, int kind);
	List<Board> boardList(Integer kind, int sNo, Integer pageNum, int limit);
	List<Board> boardListNR(int kind, Integer sNo, Integer pageNum, int limit);
	
	double boardList(Integer kind, Integer sNo);
	
	Board getBoard(int num);

	void boardWrite(Board board, HttpServletRequest request);
	void boardWrite(Board board);
	void boardUpdate(Board board, HttpServletRequest request);
	void boardUpdate(Board board);
	void boardDelete(Integer num);
	void boardReply(Board board);
	void updateReadCnt(Integer num);

	void joinsms(Member member);

	Reserve getReserve(Integer reNo);

	List<Reserve> getReserveList(String id);

	int reserveCount(String id, String searchType, String searchContent);
	
	List<Reserve> selectReserveList(String id, String searchType, String searchContent, Integer pageNum, int limit, String startDate, String endDate);

	void reserveInsert(Reserve reserve);

	void reserveUpdate(Reserve reserve);

	Building selectHostReserveInfo(String hostName, Integer sNo);

	void updateMember(Member member);

	int hostBuildCount(String hostName);

	void insertRoom(Room room, HttpServletRequest request);

	void reserveCancel(Integer reNo, Integer reStat);

	int hostReserveCount(Integer sNo, String searchType, String searchContent);

	List<Reserve> selectHostReserveList(Integer sNo, String searchType, String searchContent, Integer pageNum, int limit, String startDate, String endDate);

	List<Integer> hostHaveBuildsNo(String hostId);

	void hostPaymentConfirm(Integer reNo);

	void buildingReg(Building building, HttpServletRequest request);

	void becomeaHost(Member member, HttpServletRequest request);

	List<Board> guestQuestionList();

	List<Board> hostQuestionList();

	List<Member> hostRegList();

	void hostRegister(String id);

	Member find_member(String name, String email);
	
	Member getMember(String id);

	Member find_password(String id, String email, String name);

	List<Building> getMyBuildings(String id);
	
	List<Board> boardList(Integer kind,String id);
	
	Member find_member_by_email(String email);
	
	List<TransactionHistory> hostTransHistoryList(String first);
	
	Building getMyBuildingOne(String sNo);
	
	void buildingUpdateReg(Building building, HttpServletRequest request);

	Room getMyRoom(Room room);
	
	List<TransactionHistory> searchTransHistoryList(String searchType, String searchContent, String startDate, String endDate);
	
	List<Room> getmyRoomList(Integer sNo);
	
	void updateRoom(Room room, HttpServletRequest request);
	
	void deleteRoom(Room room);
	
	Map<String, Object> graphTransHistoryCnt(String searchType, String id);
	
	Map<String, Object> graphTransHistorySum(String searchType, String id);
	
	void deleteAccount(Member member);
	
	List<Member> getMemberList(String searchType, String searchContent, String startDate, String endDate, Integer limit, Integer pageNum);
	
	int getMemberCnt(String searchType, String searchContent, String startDate, String endDate, Integer pageNum, Integer limit);
	
	int getHostCnt(String searchType, String searchContent, String startDate, String endDate, Integer pageNum, Integer limit);
	
	List<Member> getHostList(String searchType, String searchContent, String startDate, String endDate, Integer pageNum, Integer limit);
	
	Building getbuilding_mainpage(int sNo);
	
	List<Board> getbuildingNo_by_score();
	
	Room getmyRoom_lowestprice(int i);	
	
	List<Member> selectMemberList(String[] idchks);
	
	int hostBoardCountQuest(Integer sNo);
	
	void reserveStatusUpdate(int reNo);
	List<Board> guestQuestionList1(String searchType, String searchContent);
	List<Board> hostQuestionList1(String searchType, String searchContent);
	
	Room getRoom(Integer sNo, Integer sRNo);
	
	Favorite find(String id, Integer sNo);
	void addfavorite(String id, Integer sNo);
	void deletefavorite(String id, Integer sno);
	
	void buildingDelete(Integer sNo);
	Building getbuilding_mainpage_reviewCount(Integer integer);
	List<Board> getbuilding_reviewCount();
	List<Board> getSNo_byScore();
	
	List<Building> getMyWishBuildings(String id);
	Integer getBuildingCount();
	
	int reserveChkCnt(Integer sNo, Integer sRNo);
	
	List<Reserve> reserveChkList(Integer sNo, Integer sRNo);

	List<Reserve> getReserveDateChkList(String startChkDate, String endChkDate, Integer sNo, Integer sRNo);
	
	int getbuilding_mainpage_2(int sNo);
	
	int getBuildingCnt(String searchType, String searchContent);
	
	List<Building> getBuildingList(String searchType, String searchContent, Integer pageNum, int limit);
	
}

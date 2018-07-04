package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface ProjectService {

	Member getMember(String id);

	int boardcount(String searchType, String searchContent);

	List<Board> boardList(String searchType, String searchContent, Integer pageNum, int limit);

	void boardWrite(Board board, HttpServletRequest request);

	Board getBoard(int num);

	void boardReply(Board board);

	void boardUpdate(Board board, HttpServletRequest request);

	void boardDelete(Integer num);

	void updateReadCnt(Integer num);

	void joinsms(Member member);

	Reserve getReserve(Integer reNo);

	List<Reserve> getReserveList(String id);

	int reserveCount(String id, String searchType, String searchContent);
	
	List<Reserve> selectReserveList(String id, String searchType, String searchContent, Integer pageNum, int limit);

	void reserveInsert(Reserve reserve);

	void reserveUpdate(Reserve reserve);

	Building selectHostReserveInfo(String hostName, Integer sNo);

	void updateMember(Member member);

	int hostBuildCount(String hostName);

	int boardcount(Integer kind, int sNo);

	List<Board> boardList(Integer kind, int sNo, Integer pageNum, int limit);

	Room getRoom(Integer srNo);

	void insertRoom(Room room);

	void reserveCancel(Integer reNo, Integer reStat);

	int hostReserveCount(String hostName, Integer sNo, String searchType, String searchContent);

	List<Reserve> selectHostReserveList(Integer sNo, String hostName, String searchType, String searchContent, Integer pageNum, int limit);

	List<Integer> hostHaveBuildsNo(String hostId);

	void hostPaymentConfirm(Integer reNo);

	void buildingReg(Building building, HttpServletRequest request);
	
	List<Board> boardList(Integer kind, int sNo);

	void becomeaHost(Member member, HttpServletRequest request);

	List<Board> guestQuestionList();

	List<Board> hostQuestionList();

	List<Member> hostRegList();

	void hostRegister(String id);

	List<Building> getMyBuildings(String id);

	List<Room> getMyRoom(Integer sNo);




	



}

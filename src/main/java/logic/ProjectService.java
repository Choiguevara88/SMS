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
	
	int hostReserveCount(Integer sNo, String searchType, String searchContent);

	List<Reserve> selectReserveList(String id, String searchType, String searchContent, Integer pageNum, int limit);

	void reserveInsert(Reserve reserve);

	void reserveUpdate(Reserve reserve);

	List<Reserve> selectHostReserveList(Integer sNo, String searchType, String searchContent, Integer pageNum, int limit);

	List<Building> selectHostReserveInfo(String hostName);

	void updateMember(Member member);

	int hostBuildCount(String hostName);

	Room getRoom(Integer srNo);

	void insertRoom(Room room);

	void reserveCancel(Integer reNo, Integer reStat);
}

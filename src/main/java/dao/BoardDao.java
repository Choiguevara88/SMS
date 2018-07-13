package dao;

import java.util.List;
import java.util.Map;

import logic.Board;

public interface BoardDao {

	int count(String searchType, String searchContent, int kind);

	List<Board> list(String searchType, String searchContent, Integer pageNum, int limit, int kind);

	Board getBoard(Integer num);

	void insert(Board board);

	int maxNum();

	void update(Board board);

	void delete(Integer num);

	List<Map<String,Object>>graph();

	int count(Integer kind, int sNo);

	List<Board> list(Integer kind, int sNo, Integer pageNum, int limit);
	
	double list(Integer kind, Integer sNo);

	List<Board> guestQuestionList();

	List<Board> hostQuestionList();

	void qTypeAdd(Board board);

	List<Board> list(Integer kind, String id);

	void replyInsert(Board board);

	List<Board> getbuildingNo_by_score();

	int hostBoardCntQuest(Integer sNo);

	List<Board> guestQuestionList1(String searchType, String searchContent);

	List<Board> hostQuestionList1(String searchType, String searchContent);

	void budelete(Integer sNo);
	
}


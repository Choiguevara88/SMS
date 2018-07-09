package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.BoardMapper;
import logic.Board;


@Repository
public class BoardDaoImpl implements BoardDao{

	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS = "dao.mapper.BoardMapper.";
			
	@Override
	public int count(String searchType, String searchContent, int kind) {
	
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(searchType==null || searchType.equals("")) searchType=null;
		if(searchContent==null||searchContent.equals("")) searchContent=null;
		
		map.put("searchType", searchType);
		map.put("searchContent",searchContent);
		map.put("kind", kind);
		
		return sqlSession.selectOne(NS + "count", map);
	}

	@Override
	public List<Board> list(String searchType, String searchContent, Integer pageNum, int limit, int kind) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(searchType==null || searchType.equals("")) searchType=null;
		if(searchContent==null||searchContent.equals("")) searchContent=null;
		
		int startrow = (pageNum - 1) * limit;
		
		map.put("kind", kind);
		map.put("startrow", startrow);
		map.put("limit", limit);
		map.put("searchType", searchType);
		map.put("searchContent",searchContent);
			
		return sqlSession.selectList(NS + "list", map);
	}

	@Override
	public Board getBoard(Integer bNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("bNo", bNo);
		return sqlSession.selectOne(NS + "list", map);
	}
	
	@Override
	public int maxNum() {
		return sqlSession.getMapper(BoardMapper.class).maxNum();
	}

	@Override
	public void insert(Board board) {
		sqlSession.getMapper(BoardMapper.class).insert(board);
	}
	
	@Override
	public void replyInsert(Board board) {
		sqlSession.getMapper(BoardMapper.class).rInsert(board);
	}
	
	@Override
	public void update(Board board) {
		sqlSession.getMapper(BoardMapper.class).update(board);
	}

	@Override
	public void delete(Integer bNo) {
		sqlSession.getMapper(BoardMapper.class).delete(bNo);
	}

	@Override
	public List<Map<String, Object>> graph() {
		return null;
	}

	@Override
	public int count(Integer kind, int sNo) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		map.put("kind", kind);
		map.put("sNo",sNo);
		
		return sqlSession.selectOne(NS + "count", map);
	}


	@Override
	public List<Board> list(Integer kind, int sNo, Integer pageNum, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		int startrow = (pageNum - 1) * limit;
		
		map.put("startrow", startrow);
		map.put("limit", limit);
		map.put("kind", kind);
		map.put("sNo",sNo);
		
		return sqlSession.selectList(NS + "list", map);
	}
	
	@Override
	public List<Board> list(Integer kind, int sNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("kind", kind);
		map.put("sNo",sNo);
		
		return sqlSession.selectList(NS + "list2", map);
	}

	@Override
	public List<Board> guestQuestionList() {
		return sqlSession.selectList(NS + "guestQuestion");
	}

	@Override
	public List<Board> hostQuestionList() {
		return sqlSession.selectList(NS + "hostQuestion");
	}

	@Override
	public void qTypeAdd(Board board) {
		sqlSession.getMapper(BoardMapper.class).qTypeAdd(board);
	}

	@Override
	public List<Board> list(Integer kind,String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kind",kind);
		map.put("id",id);
		return sqlSession.selectList(NS + "list", map);
	}


}

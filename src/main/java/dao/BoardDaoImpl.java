package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.BoardMapper;
import logic.Board;
import logic.Building;


@Repository
public class BoardDaoImpl implements BoardDao{

	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS = "dao.mapper.BoardMapper.";
			
	@Override
	public int count(String searchType, String searchContent) {
	
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("searchType", searchType);
		map.put("searchContent",searchContent);
		
		return sqlSession.selectOne(NS + "count", map);
	}

	@Override
	public List<Board> list(String searchType, String searchContent, Integer pageNum, int limit) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startrow = (pageNum - 1) * limit;
		
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

}

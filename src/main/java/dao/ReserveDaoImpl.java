package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.ReserveMapper;
import logic.Reserve;

@Repository
public class ReserveDaoImpl implements ReserveDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS = "dao.mapper.ReserveMapper.";

	@Override
	public Reserve getReserve(Integer reNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("reNo", reNo);
		return sqlSession.selectOne(NS + "selectOne", map);
	}

	@Override
	public List<Reserve> getReserveList(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return sqlSession.selectList(NS + "list", map);
	}
	
	@Override
	public void insert(Reserve reserve) {
		sqlSession.getMapper(ReserveMapper.class).insert(reserve);
	}


	@Override
	public int count(String searchType, String searchContent) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("searchType", searchType);
		map.put("searchContent", searchContent);

		return sqlSession.selectOne(NS + "count", map);
	}

	@Override
	public List<Reserve> list(String id, String searchType, String searchContent, Integer pageNum, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();

		int startrow = (pageNum - 1) * limit;
		
		map.put("id", id);
		map.put("startrow", startrow);
		map.put("limit", limit);
		map.put("searchType", searchType);
		map.put("searchContent", searchContent);

		return sqlSession.selectList(NS + "list", map);
	}
}

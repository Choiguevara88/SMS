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
		return sqlSession.selectList(NS + "selectList", map);
	}
	
	@Override
	public void insert(Reserve reserve) {
		sqlSession.getMapper(ReserveMapper.class).insert(reserve);
	}
	
	@Override
	public void update(Reserve reserve) {
		sqlSession.getMapper(ReserveMapper.class).update(reserve);
	}


	@Override
	public int count(String id, String searchType, String searchContent) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("id", id);
		map.put("searchType", searchType);
		map.put("searchContent", searchContent);

		return sqlSession.selectOne(NS + "count", map);
	}
	
	@Override
	public int hostCount(Integer sNo, String hostName, String searchType, String searchContent) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("sNo", sNo);
		map.put("hostName", hostName);
		map.put("searchType", searchType);
		map.put("searchContent", searchContent);
		
		return sqlSession.selectOne(NS + "hostCount", map);
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

		return sqlSession.selectList(NS + "selectList", map);
	}

	@Override
	public List<Reserve> hostlist(Integer sNo, String hostName, String searchType, String searchContent, Integer pageNum, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startrow = (pageNum - 1) * limit;
		
		map.put("sNo", sNo);
		map.put("hostName", hostName);
		map.put("startrow", startrow);
		map.put("limit", limit);
		map.put("searchType", searchType);
		map.put("searchContent", searchContent);
		
		return sqlSession.selectList(NS + "selectHostList", map);
	}

	@Override
	public void cancel(Integer reNo, Integer reStat) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("reNo", reNo);
		map.put("reStat", reStat);
		
		sqlSession.update(NS + "cancel", map);
	}

	@Override
	public void hostPaymentConfirm(Integer reNo) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("reNo", reNo);
		
		sqlSession.update(NS + "paymentConfirm", map);
	}
}

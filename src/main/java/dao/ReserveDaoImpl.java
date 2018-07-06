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
	
	@Override	// 예약을 등록할 때 호출되는 메서드
	public void insert(Reserve reserve) {
		sqlSession.getMapper(ReserveMapper.class).insert(reserve);
	}
	
	@Override	// 예약을 수정할 때 호출되는 메서드
	public void update(Reserve reserve) {
		sqlSession.getMapper(ReserveMapper.class).update(reserve);
	}


	@Override	// Guest계정에서 본인 앞으로 등록된 예약 갯수를 확인할 때 호출되는 메서드
	public int count(String id, String searchType, String searchContent) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("id", id);
		map.put("searchType", searchType);
		map.put("searchContent", searchContent);

		return sqlSession.selectOne(NS + "count", map);
	}
	
	@Override // Host계정에서 본인의 사업장 앞으로 등록된 예약 갯수를 확인 할 때 호출되는 메서드
	public int hostCount(Integer sNo, String hostName, String searchType, String searchContent) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("sNo", sNo);
		map.put("hostName", hostName);
		map.put("searchType", searchType);
		map.put("searchContent", searchContent);
		
		return sqlSession.selectOne(NS + "hostCount", map);
	}

	@Override	// Guest계정에서 예약리스트 확인 할 때 호출되는 메서드
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

	@Override	// Host계정에서 예약리스트 확인 할 때 호출되는 메서드
	public List<Reserve> hostlist(Integer sNo, String id, String searchType, String searchContent, Integer pageNum, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startrow = (pageNum - 1) * limit;
		
		map.put("sNo", sNo);
		map.put("id", id);
		map.put("startrow", startrow);
		map.put("limit", limit);
		map.put("searchType", searchType);
		map.put("searchContent", searchContent);
		
		return sqlSession.selectList(NS + "selectHostList", map);
	}

	@Override	// Guest계정에서 예약을 취소할 때 호출되는 메서드 = 예약취소는 여전히 DB에 남음
	public void cancel(Integer reNo, Integer reStat) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("reNo", reNo);
		map.put("reStat", reStat);
		sqlSession.update(NS + "cancel", map);
	}

	@Override	// Host계정에서 결제확인작업을 할 때 호출되는 메서드
	public void hostPaymentConfirm(Integer reNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("reNo", reNo);
		sqlSession.update(NS + "paymentConfirm", map);
	}
}

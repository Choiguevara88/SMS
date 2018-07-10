package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import logic.TransactionHistory;

@Repository
public class TransactionHistoryDaoImpl implements TransactionHistoryDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS = "dao.mapper.TransactionHistoryMapper.";
	
	@Override // 이번달(금월 1일부터 오늘 날짜까지)의 사업자들 거래량 조회할 때 사용되는 메서드
	public List<TransactionHistory> transHistory(String first) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sum", first);
		return sqlSession.selectList(NS + "transHistoryList", map);
	}

	@Override
	public List<TransactionHistory> searchTransHistory(String searchType, String searchContent, String startDate, String endDate) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		if(!searchType.equals("") && searchType != null) {
			map.put("searchType", searchType);
			map.put("searchContent", searchContent);
		}
		
		if(!startDate.equals("") && startDate != null) {
			map.put("startDate", startDate);
			map.put("endDate", endDate);
		}
		
		return sqlSession.selectList(NS + "searchTransList", map);
	}
	
	/* 거래량에 대한 그래프를 그릴 때 호출되는 메서드
	 * searchType = 그래프로 나타내고자 하는 키워드
	 * id = 해당 내용을 검색하고자 하는 주체 (로그인한 유저)
	 */
	
	@Override
	public List<Map<String, Object>> graphTransCnt(String searchType, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(id.equals("admin")) {
			id = null;
		}
		
		map.put("searchType", searchType);
		map.put("id", id);
		
		return sqlSession.selectList(NS + "graphTransCnt", map);
	}

	@Override
	public List<Map<String, Object>> graphTransSum(String searchType, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(id.equals("admin")) {
			id = null;
		}
		
		map.put("searchType", searchType);
		map.put("id", id);
		
		return sqlSession.selectList(NS + "graphTransSum", map);
	}
}

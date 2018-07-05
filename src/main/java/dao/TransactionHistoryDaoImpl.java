package dao;

import java.util.List;

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
	public List<TransactionHistory> transHistory() {
		return sqlSession.selectList(NS + "transHistoryList");
	}
}

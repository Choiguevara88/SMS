package dao;

import java.util.List;
import java.util.Map;

import logic.TransactionHistory;

public interface TransactionHistoryDao {
	
	List<TransactionHistory> transHistory(String first);

	List<TransactionHistory> searchTransHistory(String searchType, String searchContent, String startDate, String endDate);

	List<Map<String,Object>> graphTransCnt(String searchType, String id);

	List<Map<String,Object>> graphTransSum(String searchType, String id);
	
}

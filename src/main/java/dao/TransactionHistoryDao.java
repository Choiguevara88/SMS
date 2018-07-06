package dao;

import java.util.List;

import logic.TransactionHistory;

public interface TransactionHistoryDao {
	
	List<TransactionHistory> transHistory(String first);

	List<TransactionHistory> searchTransHistory(String searchType, String searchContent, String startDate, String endDate);
}

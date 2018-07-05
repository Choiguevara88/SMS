package dao;

import java.util.List;

import logic.TransactionHistory;

public interface TransactionHistoryDao {
	List<TransactionHistory> transHistory();
}

package dao;

import java.util.List;

import logic.Reserve;

public interface ReserveDao {

	Reserve getReserve(Integer reNo);

	List<Reserve> getReserveList(String id);

	int count(String id, String searchType, String searchContent);

	List<Reserve> list(String id, String searchType, String searchContent, Integer pageNum, int limit, String startDate, String endDate);

	void insert(Reserve reserve);

	void cancel(Integer reNo, Integer reStat);

	int hostCount(Integer sNo, String searchType, String searchContent);

	List<Reserve> hostlist(Integer sNo, String searchType, String searchContent, Integer pageNum, int limit, String startDate, String endDate);

	void hostPaymentConfirm(Integer reNo);

	void update(Reserve reserve);

	void resStatUpdate(int reNo);

	int chkCnt(Integer sNo, Integer sRNo);

	List<Reserve> chkList(Integer sNo, Integer sRNo);

	List<Reserve> dateChkList(String startChkDate, String endChkDate, Integer sNo, Integer sRNo);

}

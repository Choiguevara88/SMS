package dao;


import java.util.List;

import logic.Member;

public interface MemberDao {
	Member select(String id);

	void joinsms(Member member);

	void updateMember(Member member);

	void becomeaHost(Member member);

	List<Member> getHostRegList();

	void hostRegister(String id);

	Member find_member(String name, String email);

	Member find_password(String id, String email, String name);

	Member find_member_by_email(String email);

	void deleteAccount(Member member);

	List<Member> getMemberList(String searchType, String searchContent, String startDate, String endDate, Integer limit, Integer pageNum);

	int getMemberCnt(String searchType, String searchContent, String startDate, String endDate, Integer limit, Integer pageNum);

	List<Member> getHostList(String searchType, String searchContent, String startDate, String endDate, Integer limit, Integer pageNum);
	
	int getHostCnt(String searchType, String searchContent, String startDate, String endDate, Integer limit, Integer pageNum);

	List<Member> getSelectMemberList(String[] idchks);

}

package dao;

import java.util.List;

import logic.Member;

public interface MemberDao {
	Member select(String id);

	void joinsms(Member member);

	void updateMember(Member member);

	List<Member> getHostRegList();

	void hostRegister(String id);

}

package dao;

import logic.Member;

public interface MemberDao {
	Member select(String id);

	void joinsms(Member member);

	void updateMember(Member member);

}

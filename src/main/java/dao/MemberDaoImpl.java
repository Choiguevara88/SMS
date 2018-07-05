package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.MemberMapper;
import logic.Member;

@Repository
public class MemberDaoImpl implements MemberDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS = "dao.mapper.MemberMapper.";


	@Override
	public Member select(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id);
		return sqlSession.selectOne(NS + "list", map);
	}


	@Override
	public void joinsms(Member member) {
		sqlSession.getMapper(MemberMapper.class).insert(member);
	}


	@Override
	public void updateMember(Member member) {
		sqlSession.getMapper(MemberMapper.class).update(member);
	}


	@Override
	public void becomeaHost(Member member) {
		sqlSession.getMapper(MemberMapper.class).becomeaHost(member);
	}

	@Override
	public Member find_member(String name, String email) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("email", email);
		return sqlSession.selectOne(NS+"findmyID", map);
	}


	@Override
	public Member find_password(String id, String email, String name) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put(email, email);
		map.put("name", name);
		return sqlSession.selectOne(NS+"findmypassword",map);
	}
	
	
	@Override // 호스트 승인 요청 목록을 불러오는 메서드
	public List<Member> getHostRegList() {
		return sqlSession.selectList(NS + "hostRegList");
	}


	@Override // 호스트 승인 요청 등록할 때 사용되는 메서드
	public void hostRegister(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id);
		sqlSession.update(NS + "hostRegister", map);
	}
}

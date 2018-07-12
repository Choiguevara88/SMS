package dao;

import java.util.Arrays;
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
	public void hostRegister(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id);
		sqlSession.update(NS + "hostRegister", map);
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
		map.put("email", email);
		map.put("name", name);
		return sqlSession.selectOne(NS+"findmypassword",map);
	}
	
	@Override 
	public List<Member> getHostRegList() {	// 관리자가 Host전환 신청한 계정들의 정보를 불러올 때 사용
		return sqlSession.selectList(NS + "hostRegList");
	}


	@Override
	public Member find_member_by_email(String email) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("email", email);
		return sqlSession.selectOne(NS+"findmemberbyemail",map);
	}


	@Override
	public void deleteAccount(Member member) {
		sqlSession.getMapper(MemberMapper.class).delete(member);
	}


	@Override	// Administrator Process
	public List<Member> getMemberList(String searchType, String searchContent, String startDate, String endDate, Integer limit, Integer pageNum) {
		
		Map <String, Object> map = new HashMap<String, Object>();
		
		pageNum = (pageNum - 1) * limit;
		
		map.put("searchType", searchType);
		map.put("searchContent", searchContent);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("limit", limit);
		map.put("pageNum", pageNum);
		
		return sqlSession.selectList(NS + "adminMemberList", map);
	}


	@Override	// Administrator Process
	public int getMemberCnt(String searchType, String searchContent, String startDate, String endDate, Integer limit, Integer pageNum) {
		
		Map <String, Object> map = new HashMap<String, Object>();
		
		pageNum = (pageNum - 1) * limit;
		
		map.put("searchType", searchType);
		map.put("searchContent", searchContent);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("limit", limit);
		map.put("pageNum", pageNum);
		
		return sqlSession.selectOne(NS + "adminMemberCnt", map);
	}
	
	@Override	// Administrator Process
	public List<Member> getHostList(String searchType, String searchContent, String startDate, String endDate, Integer limit, Integer pageNum) {
		
		Map <String, Object> map = new HashMap<String, Object>();
		
		pageNum = (pageNum - 1) * limit;
		
		map.put("searchType", searchType);
		map.put("searchContent", searchContent);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("limit", limit);
		map.put("pageNum", pageNum);
		
		return sqlSession.selectList(NS + "adminHostList", map);
	}


	@Override	// Administrator Process
	public int getHostCnt(String searchType, String searchContent, String startDate, String endDate, Integer limit, Integer pageNum) {
		
		Map <String, Object> map = new HashMap<String, Object>();
		
		pageNum = (pageNum - 1) * limit;
		
		map.put("searchType", searchType);
		map.put("searchContent", searchContent);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("limit", limit);
		map.put("pageNum", pageNum);
		
		return sqlSession.selectOne(NS + "adminHostCnt", map);
	}


	@Override	// Administrator Process
	public List<Member> getSelectMemberList(String[] idchks) {
		
		List<String> list = Arrays.asList(idchks);
		
		return sqlSession.selectList(NS + "selectList", list);
	}

}

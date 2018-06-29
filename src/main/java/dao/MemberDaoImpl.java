package dao;

import java.util.HashMap;
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
}

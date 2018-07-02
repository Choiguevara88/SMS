package dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import dao.mapper.RoomMapper;
import logic.Room;

@Repository
public class RoomDaoImpl implements RoomDao{
	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS = "dao.mapper.RoomMapper.";
	@Override
	public void insertRoom(Room room) {
		sqlSession.getMapper(RoomMapper.class).insert(room);
		
	}
	
	
	

}

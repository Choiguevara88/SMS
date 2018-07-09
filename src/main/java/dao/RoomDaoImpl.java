package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.BuildingMapper;
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
	
	@Override
	public Room getMyRoom(Integer sRNo) {
		return sqlSession.getMapper(RoomMapper.class).selectMyRoom(sRNo);
	}
	
	@Override
	public List<Room> getmyRoomList(Integer sNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("sNo", sNo);
		return sqlSession.selectList(NS + "list", map);
	}

	@Override
	public void updateRoom(Room room) {
		sqlSession.getMapper(RoomMapper.class).updateRoom(room);
		
	}

	@Override
	public void deleteRoom(Integer sRNo) {
		sqlSession.getMapper(RoomMapper.class).deleteRoom(sRNo);
		
	}

	
}

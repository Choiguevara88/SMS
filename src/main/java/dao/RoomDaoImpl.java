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
	public Room getRoom(Integer srNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("srNo", srNo);
		return sqlSession.selectOne(NS + "selectOne", map);
	}

	@Override
	public void insertRoom(Room room) {
		sqlSession.getMapper(RoomMapper.class).insert(room);
		
	}



	@Override
	public List<Room> getMyroom(Integer sNo) {
		return sqlSession.getMapper(RoomMapper.class).selectMyRoom(sNo);
	}

	
}

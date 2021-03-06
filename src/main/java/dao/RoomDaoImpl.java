package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.BuildingMapper;
import dao.mapper.RoomMapper;
import logic.Building;
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
	public Room getMyRoom(Room room) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		Integer sRNo = room.getsRNo();
		Integer sNo = room.getsNo();
		
		map.put("sRNo",sRNo);
		map.put("sNo", sNo);
		return sqlSession.selectOne(NS + "one",map);
	}
	
	@Override
	public List<Room> getmyRoomList(Integer sNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("sNo", sNo);
		return sqlSession.selectList(NS + "list", map);
	}

	@Override
	public void updateRoom(Room room) {
		sqlSession.getMapper(RoomMapper.class).update(room);	
	}

	@Override
	public void deleteRoom(Room room) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer sRNo = room.getsRNo();
		Integer sNo = room.getsNo();
		
		map.put("sRNo",sRNo);
		map.put("sNo", sNo);
		sqlSession.selectOne(NS + "oneD",map);
		
	}
	@Override
	public Room getmyRoom_lowestprice(int i) {
		Map <String, Integer> map = new HashMap<String, Integer>();
		map.put("sNo", i);
		return sqlSession.selectOne(NS+"lowestprice_main",map);
	}
	@Override
	public int maxNum() {
		
		return sqlSession.getMapper(RoomMapper.class).maxNum();
	}
		@Override
	public Room getRoom(Integer sNo, Integer sRNo) {
		Map <String, Integer> map = new HashMap<String, Integer>();
		map.put("sNo", sNo);
		map.put("sRNo", sRNo);
		return sqlSession.selectOne(NS + "one", map);
	}
		@Override
		public void budelete(Integer sNo) {
			sqlSession.getMapper(RoomMapper.class).budelete(sNo);
		}
		
		@Override
		public List<String> getImgList(String srno) {
			String imgList = sqlSession.getMapper(RoomMapper.class).getImgList(srno);
			List<String> list = new ArrayList<String>(Arrays.asList(imgList.split("[|]")));
			return list;
		}
}

package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.BuildingMapper;
import logic.Building;

@Repository
public class BuildingDaoImpl implements BuildingDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS = "dao.mapper.BuildingMapper.";

	@Override
	public Building resInfo(String hostName, Integer sNo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", hostName);
		map.put("sNo", sNo);
		
		return sqlSession.selectOne(NS + "hostInfo", map);
	}

	@Override
	public int hostBuildCnt(String hostName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", hostName);
	return sqlSession.selectOne(NS + "hostCount", map);
	}

	@Override
	public List<Integer> hostHaveBuildsNo(String hostId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", hostId);
		return sqlSession.selectList(NS+"hostHaveBuildsNo", map);
	}

	@Override
	public int maxNum() {
		return sqlSession.getMapper(BuildingMapper.class).maxNum();
	}

	@Override
	public void buRegist(Building building) {
		sqlSession.getMapper(BuildingMapper.class).insert(building);
	}

	@Override
	public List<Building> getMyBuildings(String id) {
		return sqlSession.getMapper(BuildingMapper.class).selectMyBuildings(id);
	}

	@Override
	public Building getMyBuildingOne(String sNo) {
		return sqlSession.getMapper(BuildingMapper.class).selectMyBuildingOne(sNo);
	}

	@Override
	public void buUpdateReg(Building building) {
		sqlSession.getMapper(BuildingMapper.class).update(building);
	}


	@Override
	public Building getbuilding_mainpage(int sNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("sNo", sNo);
		return sqlSession.selectOne(NS+"mainpagebuilding", map);
	}

	@Override
	public void budelete(Integer sNo) {
		sqlSession.getMapper(BuildingMapper.class).delete(sNo);
	}

	@Override
	public Building getbuilding_mainpage_reviewCount(Integer integer) {
		Map <String, Integer> map = new HashMap<String, Integer>();
		map.put("sNo", integer);
		return sqlSession.selectOne(NS+"mainpage_reviewCount",map);
	}

	@Override
	public List<Building> getMyWishBuildings(String id) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("id", id);
		
		return sqlSession.selectList(NS+"wishSelect", map);
	}

	@Override
	public Integer getBuildingCount() {
		return sqlSession.selectOne(NS+"buildingCount");
	}

	@Override
	public int getbuilding_mainpage_2(int sNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("sNo", sNo - 1);
		return sqlSession.selectOne(NS+"mainpagebuilding2", map);
		}

	@Override // 리스트용 BuildingCount
	public int getBuildingCount(String searchType, String searchContent) {
		Map<String, String> map = new HashMap<String, String>();
		
		if(searchType != null && !(searchType.equals(""))) {
			map.put("searchType", searchType);
			map.put("searchContent", searchContent);
		}
		
		
		return sqlSession.selectOne(NS + "buildingCnt" , map);
	}

	@Override // 리스트용 BuildingList
	public List<Building> getBuildingList(String searchType, String searchContent, Integer pageNum, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();

		int startrow = (pageNum - 1) * limit;
		
		if(searchType != null && !(searchType.equals(""))) {
			map.put("searchType", searchType);
			map.put("searchContent", searchContent);
		}
		
		map.put("startrow", startrow);
		map.put("limit", limit);
		
		return sqlSession.selectList(NS + "buildingList", map);
	}
}


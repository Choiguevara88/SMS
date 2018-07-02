package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import logic.Building;

@Repository
public class BuildingDaoImpl implements BuildingDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS = "dao.mapper.BuildingMapper.";

	@Override
	public List<Building> resList(String hostName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", hostName);
		return sqlSession.selectList(NS + "hostList", map);
	}

}

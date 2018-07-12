package dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.FavoriteMapper;
import logic.Favorite;

@Repository
public class FavoriteDaoImpl implements FavoriteDao{

	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS = "dao.mapper.FavoriteMapper.";
	
	@Override
	public Favorite find(String id, Integer sNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("sNo", sNo);
		return sqlSession.selectOne(NS+"find", map);
	}

	@Override
	public void addFavorite(String id, Integer sNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("sNo", sNo);
		sqlSession.getMapper(FavoriteMapper.class).insert(map);
	}

	@Override
	public void deleteFavorite(String id, Integer sNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("sNo", sNo);
		sqlSession.getMapper(FavoriteMapper.class).delete(map);
	}
	
}

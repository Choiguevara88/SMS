package dao.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface FavoriteMapper {

	@Insert("insert into favorite(id, sNo) values(#{id}, #{sNo})")
	void insert(Map<String, Object> map);

	@Delete("delete from favorite where id=#{id} and sNo=#{sNo}")
	void delete(Map<String, Object> map);
	
}

package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Board;
import logic.Building;

public interface BuildingMapper {

	@Select("select ifnull(max(sNo),0) from building")
	int maxNum();
	
	@Insert("insert into building(sNo, id, sName, sPreview, sContent, sType, sTag, sInfoSub, sRule, sBHour, sHDay, sImg1, sImg2, sAddress, sStat) "
			+ "values(#{sNo}, #{id}, #{sName}, #{sPreview}, #{sContent}, #{sType}, #{sTag}, #{sInfoSub}, #{sRule}, #{sBHour}, #{sHDay}, #{sImg1}, #{sImg2}, #{sAddress}, #{sStat})")
	void insert(Building building);

	@Update("update board set readcnt=readcnt+1 where num=#{value}")
	void addReadCnt(Integer num);

	@Update("update board set refstep = refstep+1 where ref = #{ref} and refstep > #{refstep}")
	void refStepAdd(Board board);

	@Update("update board set subject=#{subject}, content=#{content}, file1=#{fileurl}, regdate=now() where num = #{num}")
	void update(Board board);

	@Delete("delete from board where num=#{value}")
	void delete(Integer num);

	@Select("select name 'key', count(*) 'value' from board group by name having count(*) >= 1")
	List<Map<String, Object>> graph();
	
	@Select("select * from building where id=#{value}")
	List<Building> selectMyBuildings(String id);

}

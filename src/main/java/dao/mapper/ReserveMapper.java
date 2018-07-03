package dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import logic.Reserve;

public interface ReserveMapper {
	
	@Insert("insert into reserve(reNo, id, sNo, srNo, reDate, RegDate, totPrice, reCnt, reStat) "
			+ "values(#{reNo}, #{id}, #{sNo}, #{srNo}, #{reDate}, now(), #{totPrice}, #{reCnt}, 0)")
	void insert(Reserve reserve);

	@Update("update reserve set reDate=#{reDate}, RegDate=now(), totPrice=#{totPrice}, reCnt=#{reCnt} where reNo=#{reNo}")
	void update(Reserve reserve);

}

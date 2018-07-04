package dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Board;

public interface BoardMapper {

	@Select("select ifnull(max(bNo),0) from board")
	int maxNum();
	
	@Insert("insert into board(bNo, sNo, id, email, subject, content, qType, regDate, ref, refLevel, score, img1, img2, img3, img4, kind) "
			+ "values(#{bNo}, #{sNo}, #{id}, #{email},#{subject}, #{content}, #{qType}, now(), #{ref}, #{refLevel}, #{score}, "
			+ "#{img1}, #{img2}, #{img3}, #{img4}, #{kind})")
	void insert(Board board);

	@Update("update board set content=#{content}, regDate=now(), score=#{score},"
			+ " subject=#{subject}, img1=#{img1}, img2=#{img2}, img3=#{img3}, img4=#{img4} "
			+ " where bNo=#{bNo}")
	void update(Board board);

	@Delete("delete from board where bNo=#{bNo}")
	void delete(Integer bNo);

	@Update("update board set qType = ifnull(qType,0) + 1 where ref=#{ref}")
	void qTypeAdd(Board board);
}

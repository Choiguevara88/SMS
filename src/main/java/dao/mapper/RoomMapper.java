package dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import logic.Room;

public interface RoomMapper {

	@Insert("insert into room (sno,srno,srname,srtype,srcontent,srinfo,srestype,srpersonlimit,sprice,srimg) values(#{sNo},#{sRNo},#{sRName},#{sRType},#{sRContent},#{sRInfo},#{sResType},#{sRPersonLimit},#{sPrice},#{sRImg})")
	void insert(Room room);

	@Select("select * from room where sNo=#{value}")
	List<Room> selectMyRoom(Integer sNo);
	
	
	
//	@Update("update userAccount set userName=#{userName}, phoneNo=#{phoneNo},"
//		+ " postcode=#{postcode}, address=#{address}, email=#{email}, birthDay=#{birthDay} where userId=#{userId}")
//	void update(User user);
//
//	@Delete("delete from userAccount where userId=#{value}")
//	void delete(String userId);
}

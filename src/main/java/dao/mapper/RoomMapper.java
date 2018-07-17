package dao.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Room;

public interface RoomMapper {

	@Insert("insert into room (sno,srno,srname,srtype,srcontent,srinfo,srestype,srpersonlimit,sprice,srimg) values(#{sNo},#{sRNo},#{sRName},#{sRType},#{sRContent},#{sRInfo},#{sResType},#{sRPersonLimit},#{sPrice},#{sRImg})")
	void insert(Room room);
	@Update("update room set srname=#{sRName},srtype=#{sRType},srcontent=#{sRContent},srinfo=#{sRInfo},srestype=#{sResType},srpersonlimit=#{sRPersonLimit},sprice=#{sPrice},sRImg=#{sRImg} where srno=#{sRNo} and sNo=#{sNo}")
	void update(Room room);
	@Select("select ifnull(max(sRNo),0) from room")
	int maxNum();
	
	@Delete("delete from room where sNo=#{value}")
	void budelete(Integer sNo);

	//@Select("select * from room where sRNo=#{value}")
	//Room selectMyRoom(Integer sRNo);

	//@Update("update room set srname=#{sRName},srtype=#{sRType},srcontent=#{sRContent},srinfo=#{sRInfo},srestype=#{sResType},srpersonlimit=#{sRPersonLimit},sprice=#{sPrice} where srno=#{sRNo}")
	
	//@Delete("delete from room where srNo=#{sRNo}")
	//void deleteRoom(Integer sRNo);
	
	
	
//	@Update("update userAccount set userName=#{userName}, phoneNo=#{phoneNo},"
//		+ " postcode=#{postcode}, address=#{address}, email=#{email}, birthDay=#{birthDay} where userId=#{userId}")
//	void update(User user);
//
//	@Delete("delete from userAccount where userId=#{value}")
//	void delete(String userId);
}

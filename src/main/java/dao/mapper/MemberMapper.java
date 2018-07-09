package dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Member;

public interface MemberMapper {
	
	@Insert("insert into Member (id, pw, name, email, mob, regDate, memType, hostName, hostRegNo, address, accountNo) "
			+ "values(#{id}, #{pw}, #{name}, #{email}, #{mob}, now() ,0, #{hostName}, #{hostRegNo}, #{address}, #{accountNo})")
	void insert(Member member);

	@Update("update Member set pw=#{pw}, email=#{email}, mob=#{mob} where id=#{id}")
	void update(Member member);

	@Insert("update Member set hostName=#{hostName}, pictureUrl=#{pictureUrl}, hostRegNo=#{hostRegNo}, address=#{address}, accountNo=#{accountNo}, tel=#{tel}, regStatus=#{regStatus} where id=#{id}")
	void becomeaHost(Member member);

	@Delete("Delete from Member where id=#{id}")
	void delete(Member member);


	
//	@Update("update userAccount set userName=#{userName}, phoneNo=#{phoneNo},"
//		+ " postcode=#{postcode}, address=#{address}, email=#{email}, birthDay=#{birthDay} where userId=#{userId}")
//	void update(User user);
//
//	@Delete("delete from userAccount where userId=#{value}")
//	void delete(String userId);
}

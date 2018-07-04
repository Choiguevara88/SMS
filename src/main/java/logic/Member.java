package logic;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class Member {
   private String id;      // ID
   private String pw;      // Password
   private String name;   // 이름
   private String email;   // 이메일
   private String mob;      // 휴대폰
   private Date regDate;   // 가입일자 (회원가입시 자동으로 입력되게끔) now()
   private Integer memType;// 회원 상태 0:게스트, 1:호스트 2:admin
   
   /* 호스트계정으로 전환 시 사용 될 항목들 */
   private String pictureUrl;
   private MultipartFile picture; 
   private String hostName;   // 호스트계정명( ex:상호명 )
   private Integer hostRegNo;   // 사업자등록번호
   private String address;      // 사업자주소지
   private String accountNo;   // 계좌정보 (은행 + 계좌번호 + 예금주)
   private String tel;         // 사업자 연락처
   private Integer regStatus;   //호스트 등록 승인 상태. 0 :대기, 1 : 승인 2: 거부 
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getPw() {
	return pw;
}
public void setPw(String pw) {
	this.pw = pw;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getMob() {
	return mob;
}
public void setMob(String mob) {
	this.mob = mob;
}
public Date getRegDate() {
	return regDate;
}
public void setRegDate(Date regDate) {
	this.regDate = regDate;
}
public Integer getMemType() {
	return memType;
}
public void setMemType(Integer memType) {
	this.memType = memType;
}
public String getPictureUrl() {
	return pictureUrl;
}
public void setPictureUrl(String pictureUrl) {
	this.pictureUrl = pictureUrl;
}
public MultipartFile getPicture() {
	return picture;
}
public void setPicture(MultipartFile picture) {
	this.picture = picture;
}
public String getHostName() {
	return hostName;
}
public void setHostName(String hostName) {
	this.hostName = hostName;
}
public Integer getHostRegNo() {
	return hostRegNo;
}
public void setHostRegNo(Integer hostRegNo) {
	this.hostRegNo = hostRegNo;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getAccountNo() {
	return accountNo;
}
public void setAccountNo(String accountNo) {
	this.accountNo = accountNo;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public Integer getRegStatus() {
	return regStatus;
}
public void setRegStatus(Integer regStatus) {
	this.regStatus = regStatus;
}
@Override
public String toString() {
	return "Member [id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email + ", mob=" + mob + ", regDate="
			+ regDate + ", memType=" + memType + ", pictureUrl=" + pictureUrl + ", picture=" + picture + ", hostName="
			+ hostName + ", hostRegNo=" + hostRegNo + ", address=" + address + ", accountNo=" + accountNo + ", tel="
			+ tel + ", regStatus=" + regStatus + "]";
}

}
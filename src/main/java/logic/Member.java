package logic;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Member {
   private String id;      // ID
   private String pw;      // Password
   private String name;   // �̸�
   private String email;   // �̸���
   private String mob;      // �޴���
   private Date regDate;   // �������� (ȸ�����Խ� �ڵ����� �ԷµǰԲ�) now()
   private Integer memType;// ȸ�� ���� 0:�Խ�Ʈ, 1:ȣ��Ʈ 2:admin
   
   /* ȣ��Ʈ�������� ��ȯ �� ��� �� �׸�� */
   private String pictureUrl;
   private MultipartFile picture; 
   private String hostName;   // ȣ��Ʈ������( ex:��ȣ�� )
   private Integer hostRegNo;   // ����ڵ�Ϲ�ȣ
   private String address;      // ������ּ���
   private String accountNo;   // �������� (���� + ���¹�ȣ + ������)
   private String tel;         // ����� ����ó
   private Integer regStatus;   //ȣ��Ʈ ��� ���� ����. 0 :���, 1 : ���� 2: �ź� 
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
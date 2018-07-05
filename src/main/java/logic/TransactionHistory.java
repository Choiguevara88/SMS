package logic;

import java.util.Date;

public class TransactionHistory {
	/* TransactionHistory View Column : 거래장부 조회용 뷰*/
	
	private String host;			// 사업자 ID
	private String guest;			// 구매자 ID
	private String email; 			// 사업자 Email
	private String mob; 			// 사업자 연락처
	private Date regDate; 			// 사업자 가입일자	
	private String hostName;		// 사업자명
	private Integer hostRegNo;		// 사업자번호
	private String address;			// 사업자 주소
	private String accountNo;		// 사업자 계좌 정보
	private String tel;				// 사업자 연락처
	private Integer sNo;			// 건물명
	private String sName;			// 상호명
	private Integer sRNo;			// Room 번호
	private String sRName;			// Room 이름
	private Integer reNo;			// 예약별관리번호
	private Integer totPrice;		// 거래금액
	private Integer cnt;			// 거래수량
	
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
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
	public Integer getsNo() {
		return sNo;
	}
	public void setsNo(Integer sNo) {
		this.sNo = sNo;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public Integer getsRNo() {
		return sRNo;
	}
	public void setsRNo(Integer sRNo) {
		this.sRNo = sRNo;
	}
	public String getsRName() {
		return sRName;
	}
	public void setsRName(String sRName) {
		this.sRName = sRName;
	}
	public Integer getReNo() {
		return reNo;
	}
	public void setReNo(Integer reNo) {
		this.reNo = reNo;
	}
	public Integer getTotPrice() {
		return totPrice;
	}
	public void setTotPrice(Integer totPrice) {
		this.totPrice = totPrice;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	
	@Override
	public String toString() {
		return "TransactionHistory [host=" + host + ", guest=" + guest + ", email=" + email + ", mob=" + mob
				+ ", regDate=" + regDate + ", hostName=" + hostName + ", hostRegNo=" + hostRegNo + ", address="
				+ address + ", accountNo=" + accountNo + ", tel=" + tel + ", sNo=" + sNo + ", sName=" + sName
				+ ", sRNo=" + sRNo + ", sRName=" + sRName + ", reNo=" + reNo + ", totPrice=" + totPrice + ", cnt=" + cnt
				+ "]";
	}
}

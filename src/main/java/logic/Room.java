package logic;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Room {
	private Integer sNo;				// 빌딩번호
	private Integer sRNo;				// Room 번호
	private String sRName;				// Room 이름
	private String sRType;				// Room 유형
	private List<String> sRTypeList;	// Room 유형 View 전달용 리스트 객체
	private String sRContent;			// Room 설명
	private String sRInfo;				// Room시설안내 DB 저장용
	private List<String> sRInfoList;	// Room시설안내 View 전달용 리스트 객체
	private Integer sResType;			// 0 = 시간 단위 예약 / 1 = 일자 단위 예약
	private String sRPersonLimit;		// 예약제한인원 설명 작성
	private Integer sPrice;				// Room 가격
	private String sRImg;				// Room 이미지파일명 DB 저장용
	private List<MultipartFile> sRImgList;	// Room 이미지파일 View 전달용 리스트 객체
	private List<String> sRImgNameList;		// Room 이미지파일명 View 전달용 리스트 객체
	
	public Integer getSNo() {
		return sNo;
	}
	public void setSNo(Integer sNo) {
		this.sNo = sNo;
	}
	public Integer getSrNo() {
		return sRNo;
	}
	public void setSrNo(Integer sRNo) {
		this.sRNo = sRNo;
	}
	public String getSrName() {
		return sRName;
	}
	public void setSrName(String sRName) {
		this.sRName = sRName;
	}
	public String getSrType() {
		return sRType;
	}
	public void setSrType(String sRType) {
		this.sRType = sRType;
	}
	public List<String> getSrTypeList() {
		return sRTypeList;
	}
	public void setSrTypeList(List<String> sRTypeList) {
		this.sRTypeList = sRTypeList;
	}
	public String getSrContent() {
		return sRContent;
	}
	public void setSrContent(String sRContent) {
		this.sRContent = sRContent;
	}
	public String getSrInfo() {
		return sRInfo;
	}
	public void setSrInfo(String sRInfo) {
		this.sRInfo = sRInfo;
	}
	public List<String> getSrInfoList() {
		return sRInfoList;
	}
	public void setSrInfoList(List<String> sRInfoList) {
		this.sRInfoList = sRInfoList;
	}
	public Integer getSresType() {
		return sResType;
	}
	public void setSresType(Integer sResType) {
		this.sResType = sResType;
	}
	public String getSrPersonLimit() {
		return sRPersonLimit;
	}
	public void setSrPersonLimit(String sRPersonLimit) {
		this.sRPersonLimit = sRPersonLimit;
	}
	public Integer getSprice() {
		return sPrice;
	}
	public void setSprice(Integer sPrice) {
		this.sPrice = sPrice;
	}
	public String getSrImg() {
		return sRImg;
	}
	public void setSrImg(String sRImg) {
		this.sRImg = sRImg;
	}
	public List<MultipartFile> getSrImgList() {
		return sRImgList;
	}
	public void setSrImgList(List<MultipartFile> sRImgList) {
		this.sRImgList = sRImgList;
	}
	public List<String> getSrImgNameList() {
		return sRImgNameList;
	}
	public void setSrImgNameList(List<String> sRImgNameList) {
		this.sRImgNameList = sRImgNameList;
	}
	
	@Override
	public String toString() {
		return "Room [sNo=" + sNo + ", sRNo=" + sRNo + ", sRName=" + sRName + ", sRType=" + sRType + ", sRTypeList="
				+ sRTypeList + ", sRContent=" + sRContent + ", sRInfo=" + sRInfo + ", sRInfoList=" + sRInfoList
				+ ", sResType=" + sResType + ", sRPersonLimit=" + sRPersonLimit + ", sPrice=" + sPrice + ", sRImg="
				+ sRImg + ", sRImgList=" + sRImgList + ", sRImgNameList=" + sRImgNameList + "]";
	}
}

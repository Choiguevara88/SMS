package logic;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.web.multipart.MultipartFile;

public class Room {
	private Integer sNo;				// 빌딩번호
	
	private Integer sRNo;				// Room 번호
	@NotNull
	private String sRName;				// Room 이름
	private String sRType;				// Room 유형
	private List<String> sRTypeList;	// Room 유형 View 전달용 리스트 객체
	private String sRContent;			// Room 설명
	private String sRInfo;				// Room시설안내 DB 저장용
	private List<String> sRInfoList;	// Room시설안내 View 전달용 리스트 객체
	private Integer sResType;			// 0 = 시간 단위 예약 / 1 = 일자 단위 예약
	private String sRPersonLimit;		// 예약제한인원 설명 작성
	@Min(2)
	private Integer sPrice;				// Room 가격
	private String sRImg;				// Room 이미지파일명 DB 저장용
	private List<MultipartFile> sRImgList;	// Room 이미지파일 View 전달용 리스트 객체
	private List<String> sRImgNameList;		// Room 이미지파일명 View 전달용 리스트 객체
	
	
	
	public Integer getsNo() {
		return sNo;
	}



	public void setsNo(Integer sNo) {
		this.sNo = sNo;
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



	public String getsRType() {
		return sRType;
	}



	public void setsRType(String sRType) {
		this.sRType = sRType;
	}



	public List<String> getsRTypeList() {
		return sRTypeList;
	}



	public void setsRTypeList(List<String> sRTypeList) {
		this.sRTypeList = sRTypeList;
	}



	public String getsRContent() {
		return sRContent;
	}



	public void setsRContent(String sRContent) {
		this.sRContent = sRContent;
	}



	public String getsRInfo() {
		return sRInfo;
	}



	public void setsRInfo(String sRInfo) {
		this.sRInfo = sRInfo;
	}



	public List<String> getsRInfoList() {
		return sRInfoList;
	}



	public void setsRInfoList(List<String> sRInfoList) {
		this.sRInfoList = sRInfoList;
	}



	public Integer getsResType() {
		return sResType;
	}



	public void setsResType(Integer sResType) {
		this.sResType = sResType;
	}



	public String getsRPersonLimit() {
		return sRPersonLimit;
	}



	public void setsRPersonLimit(String sRPersonLimit) {
		this.sRPersonLimit = sRPersonLimit;
	}



	public Integer getsPrice() {
		return sPrice;
	}



	public void setsPrice(Integer sPrice) {
		this.sPrice = sPrice;
	}



	public String getsRImg() {
		return sRImg;
	}



	public void setsRImg(String sRImg) {
		this.sRImg = sRImg;
	}



	public List<MultipartFile> getsRImgList() {
		return sRImgList;
	}



	public void setsRImgList(List<MultipartFile> sRImgList) {
		this.sRImgList = sRImgList;
	}



	public List<String> getsRImgNameList() {
		return sRImgNameList;
	}



	public void setsRImgNameList(List<String> sRImgNameList) {
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

package logic;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Building {
	private Integer sNo;				// 빌딩번호
	private String id;					// 호스트계정의 아이디
	private String sName;				// 빌딩의 이름
	private String sPreview;			// 빌딩의 한줄소개
	private String sContent;			// 빌딩 설명 내용
	private List<String> sTypeList;	    // 빌딩 유형 (연습실, 세미나실, 회의실 등등)
	private String sType;               // 빌딩 유형 DB추가용 변수
	private List<String> sTagList;		// 빌딩 태그
	private String sTag;                // 빌딩 태그 DB 추가용 변수
	private List<String> sInfoSubList;	// 기타 시설 안내 (시설안내는 개별적으로 Room에 설정)
	private String sInfoSub;            // 기타 시설 안내 DB추가용 변수
	private List<String> sRuleList;		// 이용 규칙
	private String sRule;               // 이용 규칙 DB 추가용 변수
	private List<String> sBHourList;	// 운영 시간 list0=시작시간 list1=종료시간 
	private String sBHour;              // 운영 시간 DB 추가용 변수
	private String sHDay;				// 휴무요일
	private String sImg1;					// 대표이미지파일명을 DB에 저장하기 위한 객체
	private MultipartFile sImg1File;		// 대표이미지파일을 업로드 하기 위한 객체
	private String sImg2;					// 이미지(여러장)파일명들을 DB에 저장하기 위한 객체
	private List<MultipartFile> sImg2Files;	// 다중이미지파일을 업로드 하기 위한 객체
	private List<String> sImg2Name;			// View단으로 이미지파일명들을 보내기 위한 List 객체
	private String sAddress;			// 빌딩 주소
	private String sTel;           //////////빌딩에 전화번호 추가!!!!!!
	private List<Room> room;				// 화면에 표시할 Room 객체들
	private Integer boCnt;					// 아직 읽지 않은 문의 갯수
	private Integer reCnt;					// 아직 확인 안 된 예약 갯수
	public Integer getsNo() {
		return sNo;
	}
	public void setsNo(Integer sNo) {
		this.sNo = sNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsPreview() {
		return sPreview;
	}
	public void setsPreview(String sPreview) {
		this.sPreview = sPreview;
	}
	public String getsContent() {
		return sContent;
	}
	public void setsContent(String sContent) {
		this.sContent = sContent;
	}
	public List<String> getsTypeList() {
		return sTypeList;
	}
	public void setsTypeList(List<String> sTypeList) {
		this.sTypeList = sTypeList;
	}
	public String getsType() {
		return sType;
	}
	public void setsType(String sType) {
		this.sType = sType;
	}
	public List<String> getsTagList() {
		return sTagList;
	}
	public void setsTagList(List<String> sTagList) {
		this.sTagList = sTagList;
	}
	public String getsTag() {
		return sTag;
	}
	public void setsTag(String sTag) {
		this.sTag = sTag;
	}
	public List<String> getsInfoSubList() {
		return sInfoSubList;
	}
	public void setsInfoSubList(List<String> sInfoSubList) {
		this.sInfoSubList = sInfoSubList;
	}
	public String getsInfoSub() {
		return sInfoSub;
	}
	public void setsInfoSub(String sInfoSub) {
		this.sInfoSub = sInfoSub;
	}
	public List<String> getsRuleList() {
		return sRuleList;
	}
	public void setsRuleList(List<String> sRuleList) {
		this.sRuleList = sRuleList;
	}
	public String getsRule() {
		return sRule;
	}
	public void setsRule(String sRule) {
		this.sRule = sRule;
	}
	public List<String> getsBHourList() {
		return sBHourList;
	}
	public void setsBHourList(List<String> sBHourList) {
		this.sBHourList = sBHourList;
	}
	public String getsBHour() {
		return sBHour;
	}
	public void setsBHour(String sBHour) {
		this.sBHour = sBHour;
	}
	public String getsHDay() {
		return sHDay;
	}
	public void setsHDay(String sHDay) {
		this.sHDay = sHDay;
	}
	public String getsImg1() {
		return sImg1;
	}
	public void setsImg1(String sImg1) {
		this.sImg1 = sImg1;
	}
	public MultipartFile getsImg1File() {
		return sImg1File;
	}
	public void setsImg1File(MultipartFile sImg1File) {
		this.sImg1File = sImg1File;
	}
	public String getsImg2() {
		return sImg2;
	}
	public void setsImg2(String sImg2) {
		this.sImg2 = sImg2;
	}
	public List<MultipartFile> getsImg2Files() {
		return sImg2Files;
	}
	public void setsImg2Files(List<MultipartFile> sImg2Files) {
		this.sImg2Files = sImg2Files;
	}
	public List<String> getsImg2Name() {
		return sImg2Name;
	}
	public void setsImg2Name(List<String> sImg2Name) {
		this.sImg2Name = sImg2Name;
	}
	public String getsTel() {
		return sTel;
	}
	public void setsTel(String sTel) {
		this.sTel = sTel;
	}
	public String getsAddress() {
		return sAddress;
	}
	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}
	public List<Room> getRoom() {
		return room;
	}
	public void setRoom(List<Room> room) {
		this.room = room;
	}
	public Integer getBoCnt() {
		return boCnt;
	}
	public void setBoCnt(Integer boCnt) {
		this.boCnt = boCnt;
	}
	public Integer getReCnt() {
		return reCnt;
	}
	public void setReCnt(Integer reCnt) {
		this.reCnt = reCnt;
	}
	@Override
	public String toString() {
		return "Building [sNo=" + sNo + ", id=" + id + ", sName=" + sName + ", sPreview=" + sPreview + ", sContent="
				+ sContent + ", sTypeList=" + sTypeList + ", sType=" + sType + ", sTagList=" + sTagList + ", sTag="
				+ sTag + ", sInfoSubList=" + sInfoSubList + ", sInfoSub=" + sInfoSub + ", sRuleList=" + sRuleList
				+ ", sRule=" + sRule + ", sBHourList=" + sBHourList + ", sBHour=" + sBHour + ", sHDay=" + sHDay
				+ ", sImg1=" + sImg1 + ", sImg1File=" + sImg1File + ", sImg2=" + sImg2 + ", sImg2Files=" + sImg2Files
				+ ", sImg2Name=" + sImg2Name + ", sAddress=" + sAddress + ", sTel=" + sTel + ", room=" + room
				+ ", boCnt=" + boCnt + ", reCnt=" + reCnt + "]";
	}
	
	
}

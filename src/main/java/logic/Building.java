package logic;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Building {
	private Integer sNo;				// 빌딩번호
	private String id;					// 호스트계정의 아이디
	private String sName;				// 빌딩의 이름
	private String sPreview;			// 빌딩의 한줄소개
	private String sContent;			// 빌딩 설명 내용
	private String sType;				// 빌딩 유형 (연습실, 세미나실, 회의실 등등)
	private String sTag;				// 빌딩 태그
	private String sInfoSub;			// 기타 시설 안내 (시설안내는 개별적으로 Room에 설정)
	private String sRule;				// 이용 규칙
	private List<String> sTagList;		// STag List
	private List<String> sInfoSubList;	// SInfoSub List
	private List<String> sRuleList;		// SRule List
	private String sBHour;				// 운영 시간
	private String sHDay;				// 휴무요일
	private String sImg1;					// 대표이미지파일명을 DB에 저장하기 위한 객체
	private MultipartFile sImg1File;		// 대표이미지파일을 업로드 하기 위한 객체
	private String sImg2;					// 이미지(여러장)파일명들을 DB에 저장하기 위한 객체
	private List<MultipartFile> sImg2Files;	// 다중이미지파일을 업로드 하기 위한 객체
	private List<String> sImg2Name;			// View단으로 이미지파일명들을 보내기 위한 List 객체
	private String sAddress;			// 빌딩 주소
	private Integer sStat;				// 승인 여부
	private List<Room> room;				// 화면에 표시할 Room 객체들
	
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
	public String getsType() {
		return sType;
	}
	public void setsType(String sType) {
		this.sType = sType;
	}
	public String getsTag() {
		return sTag;
	}
	public void setsTag(String sTag) {
		this.sTag = sTag;
	}
	public String getsInfoSub() {
		return sInfoSub;
	}
	public void setsInfoSub(String sInfoSub) {
		this.sInfoSub = sInfoSub;
	}
	public String getsRule() {
		return sRule;
	}
	public void setsRule(String sRule) {
		this.sRule = sRule;
	}
	public List<String> getsTagList() {
		return sTagList;
	}
	public void setsTagList(List<String> sTagList) {
		this.sTagList = sTagList;
	}
	public List<String> getsInfoSubList() {
		return sInfoSubList;
	}
	public void setsInfoSubList(List<String> sInfoSubList) {
		this.sInfoSubList = sInfoSubList;
	}
	public List<String> getsRuleList() {
		return sRuleList;
	}
	public void setsRuleList(List<String> sRuleList) {
		this.sRuleList = sRuleList;
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
	public String getsAddress() {
		return sAddress;
	}
	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}
	public Integer getsStat() {
		return sStat;
	}
	public void setsStat(Integer sStat) {
		this.sStat = sStat;
	}
	public List<Room> getRoom() {
		return room;
	}
	public void setRoom(List<Room> room) {
		this.room = room;
	}
	
	@Override
	public String toString() {
		return "Building [sNo=" + sNo + ", id=" + id + ", sName=" + sName + ", sPreview=" + sPreview + ", sContent="
				+ sContent + ", sType=" + sType + ", sTag=" + sTag + ", sInfoSub=" + sInfoSub + ", sRule=" + sRule
				+ ", sTagList=" + sTagList + ", sInfoSubList=" + sInfoSubList + ", sRuleList=" + sRuleList + ", sBHour="
				+ sBHour + ", sHDay=" + sHDay + ", sImg1=" + sImg1 + ", sImg1File=" + sImg1File + ", sImg2=" + sImg2
				+ ", sImg2Files=" + sImg2Files + ", sImg2Name=" + sImg2Name + ", sAddress=" + sAddress + ", sStat="
				+ sStat + ", room=" + room + "]";
	}
}

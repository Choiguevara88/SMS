<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공간 정보 보기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	//댓글 목록 출력
	listRlist(1);
	listQlist(1);
	
/*태그 관련 스크립트*/
var tagih = "";
var tagstag = document.getElementById("tags");
var tag = "";
<c:forEach items="${building.sTagList}" var="item">
    tag= ("${item}");
	tagih+="<span class='w3-tag w3-white w3-round-xlarge w3-border w3-border-gray w3-center'>#"
	+tag+"</span>&nbsp;&nbsp;"
	</c:forEach>
	tagstag.innerHTML = tagih;
	
/*시설안내 스크립트*/
var infoSubih="";
var infoSubstag = document.getElementById("infoSubs");
var infoSub = "";
var infoSubidx = 1;
<c:forEach items="${building.sInfoSubList}" var="item">
	infoSub = ("${item}");
	infoSubih+="<div>"+infoSubidx+". "+infoSub+"</div>&nbsp;&nbsp;"
	infoSubidx++;
</c:forEach>
	infoSubstag.innerHTML = infoSubih;

/*이용규칙 스크립트*/
var ruleih="";
var ruletag = document.getElementById("rules");
var rule = "";
var ruleidx = 1;
<c:forEach items="${building.sRuleList}" var="item">
	rule = ("${item}");
	ruleih+="<div>"+ruleidx+". "+rule+"</div>&nbsp;&nbsp;"
	ruleidx++;
</c:forEach>
	ruletag.innerHTML = ruleih;

/*이미지 관련 스크립트*/
var imgarr = new Array();
var imgidx = 0;
var it = "";
<c:forEach items="${building.sImg2Name}" var="item">
imgarr[imgidx++] = ("${item}"); 
</c:forEach>
if(imgidx > 4) {
	for(var i = 0 ; i < imgarr.length ; i++) {
		it="<img src='../picture/"+imgarr[i]+"' style='width:100%'>"
		document.getElementById("img"+i).innerHTML = it;
	}
}
if(imgidx < 5) {
	for(var i = 0 ; i < imgarr.length ; i += 2) {
		it="<img src='../picture/"+imgarr[i]+"' style='width:100%'>"
		document.getElementById("img"+i).innerHTML = it;
	}
}

/*룸 선택 관련 스크립트*/
$("input:radio[name=room]").click(function(){
selectedRoom = $("input:radio[name=room]:checked").val();
});
$("#res").click(function(){
	location.href = "../reserve/regReserve.sms?sNo="+${param.sNo}+"&sRNo="+selectedRoom;
});

/*좋아요 관련 스크립트*/
var a = "1";
var id = "${sessionScope.loginMember.id}";
var sNo = "${param.sNo}";
var oheart = "<i class='fa fa-heart-o' style='font-size:36px; color:gray'></i>";
var heart = "<i class='fa fa-heart' style='font-size:36px; color:purple'></i>";
var fb = document.getElementById("fabtn");

$.ajax({
	data: {
		id : id,
		sNo: sNo
	},
	url : "favorite.sms",
	success:function(data){
		if(data=="1"){
			fb.innerHTML = oheart;
		} else{
			fb.innerHTML = heart;
		}
	}
});

$("#fabtn").click(function(){
	$.ajax({
		data: {
			id : id,
			sNo: sNo
		},
		url : "favoriteclick.sms",
		success:function(data){
			if(data=="1"){
				$("#fabtn").empty();
				fabtn.innerHTML = heart;
				alert("찜한 공간에 등록~");
			} else{
				$("#fabtn").empty();
				fabtn.innerHTML = oheart;
				alert("찜한 공간에서 제거~");
			}
		}
    });

});

function listRlist(pageNum){
	$.ajax({
		type: "get",
		url : "${path}/building/Rlist.sms?sNo=${param.sNo}&pageNum="+pageNum,
		success: function(result){
			$("#listRlist").html(result)
		}
	});
}
function listQlist(pageNum){
	$.ajax({
		type: "get",
		url : "${path}/building/Qlist.sms?sNo=${param.sNo}&pageNum="+pageNum,
		success: function(result){
			$("#listQlist").html(result)
		}
	});
}

});
</script>

<!-- 지도관련 스크립트 -->
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=2bA8v55yYLf1omsHnKFk&submodules=geocoder"></script>
</head>
<body>

<!-- 공간(Building)헤더정보 -->
<br>
<br>
<div class="w3-row">
<div class="w3-col s11">
<div><span><font class="w3-xxlarge">${building.sName}</font></span>
     <span class="w3-right">
        <a id="fabtn" style="cursor:pointer">
        </a>
     </span>
</div>
<div><h4>${building.sPreview}</h4></div>
<div id="tags">
<!-- 태그가 보여질 곳 -->
</div>
</div>
</div>
<hr>

<!-- 공간상세, Q&A, 댓글(리뷰) start -->
<div class="w3-row">
<div class="w3-col s7 w3-padding">
<!-- 공간(Building)정보 -->
<div>

<div class="w3-padding">
<div id="img0"></div>
<div id="img1"></div>
</div>

<div class="w3-panel w3-leftbar w3-border-purple">
 <h3>공간소개</h3>
</div>
<div class="w3-margin-left">${building.sContent}</div>
<hr>

<div class="w3-padding">
<div id="img2"></div>
<div id="img3"></div>
</div>

<div class="w3-panel w3-leftbar w3-border-purple">
<h3>시설안내</h3>
</div>
<div id="infoSubs" class="w3-margin-left">
<!-- 시설안내가 보여질 곳 -->
</div>
<hr>

<div class="w3-padding">
<div id="img4"></div>
<div id="img5"></div>
</div>

<div class="w3-panel w3-leftbar w3-border-purple">
<h3>이용안내</h3>
</div>
<div class="w3-margin-left w3-margin-bottom">
<div class="w3-margin-top"><span><b>휴무일</b></span>&nbsp;&nbsp;&nbsp;<span>${building.sHDay}</span></div>
<div class="w3-margin-top"><span><b>이용시간</b></span>&nbsp;&nbsp;&nbsp;<span>${building.sBHourList[0]} ~ ${building.sBHourList[1]}</span></div>
<div class="w3-margin-top"><span><b>연락처</b></span>&nbsp;&nbsp;&nbsp;<span>${building.sTel}</span></div>
</div>
<br>
<div class="w3-light-gray w3-padding">
<div><h3>${building.sName}</h3></div>
<div>${address1}</div>
</div>
<div id="map" style="width:100%;height:400px;"></div>
<hr size="1">

<div class="w3-padding">
<div id="img6"></div>
<div id="img7"></div>
</div>

<div class="w3-panel w3-leftbar w3-border-purple">
 <h3>공간 이용시 주의사항</h3>
</div>
<div id = "rules" class="w3-margin-left">
<!-- 이용규칙이 보여질 곳 -->
</div>
 <hr>

<div class="w3-padding">
<div id="img8"></div>
<div id="img9"></div>
</div>

<!-- 이용후기, Q&A -->
<div id="listRlist"></div> 

<hr size="1">

<div id="listQlist"></div>


</div>
</div>

<!-- 세부공간(Room)정보 -->
<div class="w3-col s5 w3-padding">
<h5><b>세부공간 선택</b></h5>
<div class="w3-panel w3-border w3-border-purple">

<c:forEach items="${roomList}" var="room">
<hr>
<input type="radio" name="room" value="${room.sRNo}">
<span>${room.sRName}</span>
<span class="w3-right"><font class="w3-xlarge w3-text-purple">
￦<fmt:formatNumber value="${room.sPrice}" groupingUsed="true"/>
 </font> &nbsp;/
  <c:if test="${room.sResType == 0}">시간</c:if>
  <c:if test="${room.sResType == 1}">일</c:if>
</span>
</c:forEach>
<hr style="border: solid 1px gray;">
<div class="w3-center w3-margin-bottom">
<input type="button" class="w3-btn w3-purple" id="res" value="예약하기">
</div>
</div>
</div>

</div>

<!-- 지도관련 스크립트 -->
<script>
var map = new naver.maps.Map('map');
var myaddress = "${address}";// 도로명 주소나 지번 주소만 가능 (건물명 불가!!!!)
console.log(myaddress);
naver.maps.Service.geocode({address: myaddress}, function(status, response) {
    if (status !== naver.maps.Service.Status.OK) {
        return alert(myaddress + '의 검색 결과가 없거나 기타 네트워크 에러');
    }
    var result = response.result;
    // 검색 결과 갯수: result.total
    // 첫번째 결과 결과 주소: result.items[0].address
    // 첫번째 검색 결과 좌표: result.items[0].point.y, result.items[0].point.x
    var myaddr = new naver.maps.Point(result.items[0].point.x, result.items[0].point.y);
    map.setCenter(myaddr); // 검색된 좌표로 지도 이동
    // 마커 표시
    var marker = new naver.maps.Marker({
      position: myaddr,
      map: map
    });
    // 마커 클릭 이벤트 처리
    naver.maps.Event.addListener(marker, "click", function(e) {
      if (infowindow.getMap()) {
          infowindow.close();
      } else {
          infowindow.open(map, marker);
      }
    });
});
</script>
</body>
</html>
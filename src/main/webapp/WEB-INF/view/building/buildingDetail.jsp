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
</script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=2bA8v55yYLf1omsHnKFk&submodules=geocoder"></script>
</head>
<body>
<!-- 공간(Building)헤더정보 -->
<img src="../picture/${building.sImg1}" style="width:100% ; height:300px">
<div><h1>${building.sName}</h1></div>
<div><h4>${building.sPreview}</h4></div>
<div><h5>${building.sTagList}</h5></div>
<hr>

<div class="w3-row">
<div class="w3-col s7">
<!-- 공간(Building)정보 -->
<div>

<h3>공간소개</h3>
<div>${building.sContent}</div>
<hr>

<%-- <div>${building.sTypeList}</div> --%>
<h3>이용정보</h3>
<div>${building.sInfoSubList}</div>
<hr>

<h3>이용규칙</h3>
<div>${building.sRuleList}</div>
<hr>

<h3>이용시간</h3>
<div>${building.sBHourList}</div>
<hr>

<h3>휴무일</h3>
<div>${building.sHDay}</div>
<hr>

<h3>전화번호</h3>
<div>${building.sTel}</div>
<hr>

<h3>주소</h3>
<div>${address1}</div>
<div id="map" style="width:100%;height:200px;"></div>
<hr size="1">

<!-- 이용후기, Q&A -->
<div id="listRlist"></div> 

<hr size="1">

<div id="listQlist"></div>


</div>
</div>
<!-- 세부공간(Room)정보 -->
<div class="w3-col s5">
<h1>룸정보가 들어올 공간</h1>
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
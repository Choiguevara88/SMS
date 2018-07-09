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
<script>
$(document).ready(function(){
	//댓글 목록 출력
	listRlist();
	listQlist();
	//댓글 쓰기
});

function listRlist(){
	console.log("listRlist 호출")
	$.ajax({
		type: "get",
		url : "${path}/building/Rlist.sms?sNo=${param.sNo}",
		success: function(result){
			console.log(result)
			$("#listRlist").html(result)
		}
	});
	}
function listQlist(){
	console.log("listQlist 호출")
	$.ajax({
		type: "get",
		url : "${path}/building/Qlist.sms?sNo=${param.sNo}",
		success: function(result){
			console.log(result)
			$("#listQlist").html(result)
		}
	});
}

</script>
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

<h3>주소</h3>
<div>${building.sAddress}</div>
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
</body>
</html>
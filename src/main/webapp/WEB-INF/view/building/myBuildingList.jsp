<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공간 리스트</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<h1>${id}님의 공간 정보 관리</h1>
<c:if test="${buildCnt == 0}">
등록된 건물이 없습니다.
</c:if>
<a href="../building/buildingForm.sms?id=${sessionScope.loginMember.id}">[공간등록하기]</a>
<div class="w3-row-padding">
<c:forEach var="build" items="${myBuildingList}">
<div class = "w3-third">
  <div class="w3-card-4 w3-margin-top w3-margin-bottom">
    <img src="../picture/${build.sImg1}" style="width:100%; height:250px">
      <div class="w3-container w3-margin-left w3-margin-top">
      <a href="buildingDetail.sms?sNo=${build.sNo}" class="w3-btn w3-white">
      <font class="w3-large">${build.sName}</font>
      </a>
      </div>
      <div class="w3-container w3-center w3-margin-top">
      <a href="buildingUpdate.sms?sNo=${build.sNo}" class="w3-btn w3-black">공간수정</a>
      <a href="roomList.sms?sNo=${build.sNo}" class="w3-btn w3-black">세부공간추가/수정</a>
      <a href="buildingDelete.sms?sNo=${build.sNo}" class="w3-btn w3-black">삭제</a>
      </div>
    <br>
  </div>
  </div>
</c:forEach>
</div>

</body>
</html>
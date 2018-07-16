<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>세부 공간 리스트</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<br>
<div class="w3-left">
<h1> ${building.sName}의 세부공간 관리</h1>
<c:if test="${roomCnt == 0}">
<br>
<br>
<h3>
등록된 세부공관공간이 없습니다. 세부공간 추가하기를 눌러 세부공간을 추가해주세요.
</h3>
<br>
<br>
</c:if>
</div>
<div class="w3-right w3-margin-top w3-margin-right">
<a href="roomForm.sms?sNo=${sNo}">
<input type="button" class="w3-btn w3-deep-purple" value="세부공간 추가하기">
</a>
</div>

<div class="w3-row-padding">
<c:forEach var="room" items="${myRoomList}">
<div class = "w3-third">
<div class="w3-card-4 w3-margin-top w3-margin-bottom">
<form:form modelAttribute="room" action="roomDetail.sms" method="post">
      <c:forEach var="roomImg" items="${room.sRImgNameList}" end="0">
      <div>
      <img src="../picture/${roomImg}" style="width:100%; height:250px">
      </div>
      </c:forEach>
      <div class="w3-container w3-margin-left w3-margin-top">
       <font class="w3-large">${room.sRName}</font>
      </div>
   		<form:hidden path="sNo" value="${room.sNo}" />
   		<form:hidden path="sRNo" value="${room.sRNo}" />
  <div class="w3-container w3-center w3-margin-top">
  <input type="submit" value="세부정보 보기" class="w3-button w3-black"/>
  </div>
  <br>
</form:form>
</div>
</div>
</c:forEach>
</div>
<br>
</body>
</html>
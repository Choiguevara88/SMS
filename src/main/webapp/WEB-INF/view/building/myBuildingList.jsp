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
<c:forEach var="build" items="${myBuildingList}">
<div class="w3-container">
  <div class="w3-card-4 w3-hover-shadow" style="width:30%">
    <img src="../picture/${build.sImg1}" style="width:100%">
    <div class="w3-container w3-center">
      <p>${build.sName}</p><br>
      <input type="button" value="공간수정" />
      <input type="button" value="세부공간추가/수정" />
      <input type="button" value="삭제" />
    </div>
  </div>
</div>
</c:forEach>
</body>
</html>
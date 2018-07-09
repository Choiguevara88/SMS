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
<h1>${id}님의 세부공간 정보</h1>

<div class="w3-container">
  <div class="w3-card-4" style="width:30%">
    <div class="w3-container w3-center">
      <p>sRName : ${myRoom.sRName}</p><br>	
      <p>sRNo : ${myRoom.sRNo}</p><br>	
    </div>
  </div>
</div>
<p>roomDetail</p>
<a href="roomUpdateForm.sms?sRNo=${myRoom.sRNo }&sNo=${myRoom.sNo}" class="w3-button w3-black">세부 공간 정보 수정</a>
<a href="roomDeleteForm.sms?sRNo=${myRoom.sRNo }&sNo=${myRoom.sNo}" class="w3-button w3-black">세부 공간 삭제</a>
</body>
</html>
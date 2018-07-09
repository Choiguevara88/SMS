<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>지울거야</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<p>roomDeleteForm 지울거야?</p>
<form method="post" action="roomDeleteSuccess.sms" name="f">
<div class="w3-container">
  <div class="w3-card-4" style="width:30%">
    <div class="w3-container w3-center">
  		<input type="hidden" name="sRNo" value="${myRoom.sRNo}"/>
		<input type="hidden" name="sNo" value="${myRoom.sNo}"/>
		<label for="pass">
		<input type="text" name="pass"/>
		</label>
      <p>sRName : ${myRoom.sRName}</p><br>	
      <p>sRNo : ${myRoom.sRNo}</p><br>
    </div>
  </div>
</div>
<a href="javascript:document.f.submit()" class="w3-button w3-black">삭제 확정</a>
</form>
</body>
</html>
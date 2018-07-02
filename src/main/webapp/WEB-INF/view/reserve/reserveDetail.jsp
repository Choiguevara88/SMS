<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>reNo detail Test</title>
</head>
<body>

<div class="w3-container">
	<div class="w3-panel w3-leftbar w3-border-teal">
	</div>
	<div class="w3-panel w3-leftbar w3-border-green w3-text-dark-gray">
	${reserve.reNo} (<fmt:formatDate value="${reserve.regDate}" pattern="yyyy-MM-dd hh:mm:ss" />)
	<br>
	<p>예약일자 : <fmt:formatDate value="${reserve.reDate}"  pattern="yyyy-MM-dd HH시" /></p>
	<p>예약수량 : ${reserve.reCnt}시간</p>
	<p>예약금액 : ${reserve.totPrice}</p>
	</div>
  	
</div>

</body>
</html>
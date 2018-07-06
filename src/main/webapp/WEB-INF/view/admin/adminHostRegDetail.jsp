<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사업자 요청 등록 확인 작업</title>
</head>
<body>

	<table border="1">
		<caption>사업자등록요청정보</caption>
		<tr><td rowspan="7"><img src="../picture/${hostMem.pictureUrl}" width="496px" height="701px"/></td><td>상호명</td><td>${hostMem.hostName}</td></tr>
		<tr><td>ID</td><td>${hostMem.id}</td></tr>
		<tr><td>가입일자</td><td><fmt:formatDate value="${hostMem.regDate}" pattern="yy-MM-dd"/></td></tr>
		<tr><td>휴대전화</td><td>${hostMem.mob}</td></tr>
		<tr><td>Email</td><td>${hostMem.email}</td></tr>
		<tr><td>Tel</td><td>${hostMem.tel}</td></tr>
		<tr><td>계좌정보</td><td>${hostMem.accountNo}</td></tr>
		<tr><td><a href="adminHostRegister.sms?id=${hostMem.id}">[등록하기]</a></td><td colspan="2"><a href="javascript:history.go(-1)">[뒤로가기]</a></td></tr>
	</table>
</body>
</html>
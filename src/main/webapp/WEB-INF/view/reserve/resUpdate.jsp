<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 수정</title>
</head>
<body>
<form:form modelAttribute="reserve" action="resUpdate.sms" method="post" name="f">
	<form:hidden path="reNo"/>
	<table>
		<tr><td align="center">회원</td><td><form:input path="id"/></tr>
		<tr><td align="center">건물번호</td><td><form:input path="sNo"/></tr>
		<tr><td align="center">Room번호</td><td><form:input path="srNo"/></tr>
		<tr><td align="center">수량</td><td><form:input path="reCnt"/></tr>
		<tr><td align="center">대금</td><td><form:input path="totPrice"/></tr>
		<tr><td align="center">예약일자</td><td><form:input path="reDate"/></tr>
		<tr><td><input type="submit" value="등록"></td></tr>
	</table>
</form:form>
</body>
</html>
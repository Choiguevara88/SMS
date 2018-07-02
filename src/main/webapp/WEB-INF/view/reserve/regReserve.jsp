<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!-- /WebContent/reserve/regReserve.jsp -->    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>예약 등록 화면</title>
</head>
<body>
<form:form modelAttribute="reserve" action="roomReserve.sms" method="post" name="f">
	<table border="1" cellpadding="0" cellspacing="0">
		<caption>예약등록하기</caption>
		<tr><td align="center">회원</td><td><form:input path="id"/></tr>
		<tr><td align="center">건물번호</td><td><form:input path="sNo"/></tr>
		<tr><td align="center">Room번호</td><td><form:input path="srNo"/></tr>
		<tr><td align="center">수량</td><td><form:input path="reCnt"/></tr>
		<tr><td align="center">대금</td><td><form:input path="totPrice"/></tr>
		<tr><td align="center">예약일자</td><td><form:input path="reDate"/></tr>
		<tr><td align="center" colspan="2"><a href="javascript:document.f.submit()">[게시물등록]</a>&nbsp;&nbsp;<a href="listex.sms">[게시판목록]</a></td></tr>
	</table>
</form:form>

</body>
</html>
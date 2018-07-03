<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 글 등록</title>
</head>
<body>
<form:form modelAttribute="board" action="write.sms" method="post" name="f">
<input type="hidden" name="kind" value="2">
<input type="hidden" name="pageNum" value="1">
<input type="hidden" name="sNo" value="${param.sNo}">
	<table border="1" cellpadding="0" cellspacing="0" align="center">
		<caption>공지사항</caption>
		<tr><td align="center">제목</td><td><form:input path="subject"/><font color="red"><form:errors path="subject"/></font></td></tr>
		<tr><td align="center">내용</td><td><form:textarea rows="15" cols="80" path="content"/><font color="red"><form:errors path="content"/></font></td></tr>
		<tr><td align="center" colspan="2">
		<a href="javascript:document.f.submit()">[리뷰등록]</a>&nbsp;&nbsp;
	</table>
</form:form>

</body>
</html>
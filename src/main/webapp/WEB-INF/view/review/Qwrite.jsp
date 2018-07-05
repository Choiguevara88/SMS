<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>질문 글 등록</title>
</head>
<body>
<form:form modelAttribute="board" action="Qwrite.sms" method="post" name="f">
<form:hidden path="kind" value="3"/>
<input type="hidden" name="pageNum" value="1"/>
<form:hidden path="sNo" value="${param.sNo }"/>
	<table border="1" cellpadding="0" cellspacing="0" align="center">
		<caption><h3>질문 글 등록하기</h3></caption>
		<tr><td>제목</td>
			<td><form:input path="subject"/>
			<font color="red">
			<form:errors path="subject"/>
			</font></td>
		</tr>
		
		<tr><td align="center" colspan="2">내용</td></tr>
        <tr><td colspan="2"><form:textarea rows="15" cols="80" path="content"/>
        <font color="red"><form:errors path="content"/></font></td></tr>
        <tr><td colspan="2" align="center">
        <a href="javascript:document.f.submit()">[질문등록]</a>
		<a href="javascript:document.f.reset()">[다시작성]</a>
		<a href="javascript:history.go(-1)">[뒤로가기]</a>
		</td></tr>
	</table>
</form:form>

</body>
</html>
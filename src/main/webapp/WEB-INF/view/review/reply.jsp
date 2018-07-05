<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<%-- /WebContent/WEB-INF/view/board/reply.jsp
	1. 원글에 대한 정보 : num, ref, reflevel,refstep
	2. 답변글로 입력 된 정보 
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>리뷰 답글 달기</title>
</head>
<body>
<form:form action="reply.sms" method="post" name="f" modelAttribute="board">
	<input type="hidden" name="bNo" value="${board.bNo}">
	<input type="hidden" name="sNo" value="${board.sNo}">
	<input type="hidden" name="kind" value="${board.kind}">
	<input type="hidden" name="refLevel" value="${board.refLevel}">
	<input type="hidden" name="ref" value="${board.ref}">
	<input type="hidden" name="pageNum"	value="${param.pageNum}">
	<table border="1" cellpadding="0" cellspacing="0" align="center">
		<tr><td colspan="2" align="center">리뷰 답글</td></tr>
		<tr><td>제목</td><td>
			<input type="text" name="subject" value="${board.subject}에 대한 답글입니다."/>
			<font color="red"><form:errors path="subject"/></font></td></tr>
		<tr><td align="center">내용</td>
		<td><textarea rows="15" cols="80" name="content"></textarea>	
		<font color="red"><form:errors path="content"/></font></td></tr>
		<tr><td align="center" colspan="2">
		<a href="javascript:document.f.submit()">[답변등록]</a>
		<a href="javascript:document.f.reset()">[다시작성]</a>
		<a href="javascript:history.go(-1)">[뒤로가기]</a>
		</td></tr>
	</table>
</form:form></body></html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 삭제</title>
</head>
<body>
<form name="f" method="post" action="delete.sms">
	<input type="hidden" name="bNo"	value="${param.bNo}">
	<input type="hidden" name="sNo"	value="${board.sNo}">
	<input type="hidden" name="kind" value="${board.kind}">
	<input type="hidden" name="pageNum"	value="${param.pageNum}">
	
	<table border="1" cellspacing="0" cellpadding="0" align="center">
	 <tr>
	 	<td align="center">제목: ${board.subject}</td>
  	</tr>
	<tr>
		<td>삭제하시면 복구 불가능합니다. 그래도 지우시겠습니까? </td>
	</tr>
	<tr><td align="center">
		<a href="javascript:document.f.submit()">[삭제]</a>
		<a href="javascript:history.back()">[뒤로가기]</a>
	</td></tr>
	</table>
</form>
</body>
</html>
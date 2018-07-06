<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1:1 문의</title>
<script>
</script>
</head> 
<body>
<table border="1">
<% pageContext.setAttribute("newLineChar","\n"); %>
	<c:forEach var="board" items="${list}">
	<c:set var="str" value="${fn:replace(board.content,'  ','&nbsp;&nbsp;') }"/>
	<c:set var="str" value="${fn:replace(board.content,newLineChar,'<br>') }"/>
		<tr>
			<td border="1">${board.id} 
			(<fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd a hh:mm:ss"/>)<br>
	<c:if test="${sessionScope.loginMember.id == board.id }">
		<input type="button" value="수정"/></c:if>
	<c:if test="${sessionScope.loginMember.id == board.id }">
		<input type="button" value="삭제"/>
		</c:if></td>
		<td>
		${str}
		</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1:1 문의</title>

</head> 
<body>
<% pageContext.setAttribute("newLineChar","\n"); %>
	<c:forEach var="board" items="${list}">
	<c:if test="${board.refLevel != 0 }">
<div align="left">
	<c:set var="str" value="${fn:replace(board.content,'  ','&nbsp;&nbsp;') }"/>
	<c:set var="str" value="${fn:replace(board.content,newLineChar,'<br>') }"/>
		<div>
			<div>관리자 
			(<fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd a hh:mm:ss"/>)<br>
		<div>
		${str}
		</div>
		</div>
		</div>
</div>
	</c:if>
	<c:if test="${board.refLevel == 0 }">
<div align="right">
	<c:set var="str" value="${fn:replace(board.content,'  ','&nbsp;&nbsp;') }"/>
	<c:set var="str" value="${fn:replace(board.content,newLineChar,'<br>') }"/>
		<div>
			<div>${board.id} 
			(<fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd a hh:mm:ss"/>)<br>
		<div>
		${str}
		</div>
		</div>
		</div>
</div>
	</c:if>
	</c:forEach>
	
</body>
</html>

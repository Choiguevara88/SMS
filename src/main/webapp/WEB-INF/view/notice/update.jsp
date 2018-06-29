<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 수정</title>
</head>
<body>
<form:form modelAttribute="board" action="update.sms?bNo=${board.bNo}&pageNum=${param.pageNum}" method="post" name="f">
  <spring:hasBindErrors name="board">
  	<font color="red">
  	  <c:forEach items="${errors.globalErrors}" var="error">
  	  	<spring:message code="${error.code}"/>
  	  </c:forEach>
  	</font>
  </spring:hasBindErrors>
  <input type="hidden" name="bNo" value="${board.bNo}">
  <input type="hidden" name="pageNum" value="${param.pageNum}">
  <input type="hidden" name="kind" value="${board.kind}">
  <table border="1" cellpadding="0" cellspacing="0" align="center">
  <caption>공지사항 수정</caption>
 <tr><td align="center">제목</td><td><form:input path="id" value="${board.id}"/>
  		<font color="red"><form:errors path="id"/></font></td></tr>
  	<tr><td align="center">내용</td>
  		<td><form:textarea rows="15" cols="80" path="content" value="${board.content}"/>
  		<font color="red"><form:errors path="content"/></font></td></tr>
  	<tr><td align="center" colspan="2">
  		<a href="javascript:document.f.submit()">[수정]</a>
  		<a href="list.shop">[목록]</a>
  	</td></tr></table></form:form>
</body>
</html>
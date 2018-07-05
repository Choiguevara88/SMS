<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>쉐마쉐 로그인! &gt;&lt;</title>
</head>
<body>
 <form:form modelAttribute="member" action="login.sms" method="post">
 	<!--<form:hidden path="id" value="의미없음"/><!-->
 	<spring:hasBindErrors name="member">
 		<font color="red">
 			<c:forEach items="${errors.globalErrors }" var="error">
 				<spring:message code="${error.code }"/>
 			</c:forEach>
 		</font>
 	</spring:hasBindErrors>
 	<table align="center" cellpadding="1" cellspacing="1" border="1" height="150" width="400">
 		<tr>
 			<td align="center" width="20%">아이디</td><td colspan="2"><form:input path="id" size="35"/>
 			<font color="red"><form:errors path="id"/></font></td></tr>
 		<tr>
 			<td align="center">비밀번호</td><td colspan="2"><form:password align="middle" path="pw" size="35"/>
 			<font color="red"><form:errors path="pw"/></font></td></tr>
 		<tr><td colspan="3" align="center"><a href="findmyID.sms"><font size="1">ID 찾기 !</font></a>&nbsp;&nbsp;&nbsp;||&nbsp;&nbsp;&nbsp;<a href="findmypassword.sms"><font size="1">비밀번호 찾기 !</font></a></td></tr>
 		<tr>
  			<td colspan="3" align="center">
  				<input type="submit" value="로그인">
  				<input type="button" onclick="location.href='joinForm.sms'" value="가입하기"></td></tr>
  		<tr>
  			<td colspan="3" style="text-align:center;"><a href="#">네이버 로그인</a>&nbsp;&nbsp;<a href="#">구글 로그인</a>&nbsp;&nbsp;<a href="#">페이스북로그인</a></td></tr>
 	</table>
 </form:form>
</body>
</html>
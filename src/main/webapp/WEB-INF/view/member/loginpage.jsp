<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="45091977731-oco9994onan69pb9jvq4emidqe7bi7vo.apps.googleusercontent.com">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<%
//네이버 로그인
    String clientId = "Gq6yEGwFqkB9pHnvlf6E";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://localhost:8080/TestProject/loginbyNaver.sms", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);
 %>
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
  			<td colspan="3" style="text-align:center;">
  			<a href="<%=apiURL%>"><img height="40" src="${path }/picture/네이버Green.PNG"/></a></td>
  			<td><div class="g-signin2" data-onsuccess="onSignIn"></td>
  			<td><a href="facebooklogin.sms">
	<button type="button" class="btn btn-primary">Facebook 로그인</button>
</a>
		</tr>
 	</table>
 </form:form>
</body>
</html>
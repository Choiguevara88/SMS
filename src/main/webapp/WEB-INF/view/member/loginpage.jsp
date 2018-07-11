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
 			<td colspan="3"><form:input path="id" placeholder="아이디" size="60px"/>
 			<font color="red"><form:errors path="id"/></font></td></tr>
 		<tr>
 			<td colspan="3"><form:password align="middle" placeholder="비밀번호" path="pw" size="60px"/>
 			<font color="red"><form:errors path="pw"/></font></td></tr>
 		<tr><td colspan="3" align="center"><a href="findmyID.sms"><font size="2">ID 찾기 !</font></a>&nbsp;&nbsp;&nbsp;||&nbsp;&nbsp;&nbsp;<a href="findmypassword.sms"><font size="2">비밀번호 찾기 !</font></a></td></tr>
 		<tr>
  			<td colspan="3" align="center">
  				<input type="submit" value="로그인">&nbsp;&nbsp;
  				<input type="button" onclick="location.href='joinForm.sms'" value="가입하기"></td></tr>
  		<tr>
  			<td colspan="3" align="center"><a href="<%=apiURL%>"><img style="width:55px; height:55px;" src="${path }/picture/naver.png"></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  			<a href="loginwithGoogle.sms"><img style="width:55px; height:55px;" src="${path }/picture/google.png"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  			<a href="facebooklogin.sms"><img style="width:55px; height:55px;" src="${path }/picture/facebook.png"></a></td>
		</tr>
 	</table>
 </form:form>
</body>
</html>
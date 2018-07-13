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

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언 -->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

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
<div class="w3-container w3-margin-top w3-margin-bottom">
<div class="w3-container w3-margin" style="text-align:center;">
	<h1 style="font-family:'Hanna'">쉐마쉐에 오신 것을 환영합니다! &#42;&#94;&#94;&#42; </h1>
</div>
<div class="w3-row w3-container w3-margin">
<div class="w3-col s3"><p>&nbsp;</p></div>

<div class="w3-col s6">

 <form:form modelAttribute="member" action="login.sms" method="post">
 	
 	<!--<form:hidden path="id" value="의미없음"/><!-->
 	<spring:hasBindErrors name="member">
 		<font color="red">
 			<c:forEach items="${errors.globalErrors }" var="error">
 				<spring:message code="${error.code }"/>
 			</c:forEach>	
 		</font>
 	</spring:hasBindErrors>
<div class="w3-container w3-border w3-round-large w3-padding">
<table class="w3-table" style="height:130; width:350;">
 		<tr>
 			<td colspan="3">
 			<label style="font-family:'Hanna';">Share my space 로그인</label>
 			<form:input path="id" placeholder="아이디" class="w3-input"/></td>
 		</tr>
 		<tr>
 			<td colspan="3"><font color="red"><form:errors path="id"/></font></td>
 		</tr>
 			
 		<tr>
 			<td colspan="3"><form:password align="middle" placeholder="비밀번호" path="pw" class="w3-input"/>
 			</td>
 		</tr>
 		<tr>
 			<td colspan="3"><font color="red"><form:errors path="pw"/></font></td>
 		</tr>
 		<tr>
 			<td colspan="3">
				<input type="submit" value="로그인" class="btn btn-outline-primary btn-block btn-lg">
 			</td>
 		</tr>
 		
 		<tr><td><input type="hidden"></td></tr>
 		
  		<tr><td style="text-align:center; vertical-align:middle;">
  			<img style="width:27px; height:27px;" src="${path }/picture/naver.png">
  			<a href="<%=apiURL%>">
  				<input type="button" value="네이버 ID 로그인" class="btn btn-outline-success btn-sm" style="font-family:'Hanna'">
  			</a>
  		</td>
  		<td style="text-align:center; vertical-align:middle;">
			<img style="width:27px; height:27px;" src="${path }/picture/google.png">
			<a href="loginwithGoogle.sms">
 				<input type="button" value="구글 ID 로그인" class="btn btn-outline-secondary btn-sm" style="font-family:'Hanna'">
  			</a>			
  		</td>
  		
  		<td style="text-align:center; vertical-align:middle;">
  			<img style="width:27px; height:27px;" src="${path }/picture/facebook.png">
  			<a href="facebooklogin.sms">
  				<input type="button" value="Facebook ID 로그인" class="btn btn-outline-info btn-sm" style="font-family:'Hanna'">
  			</a>
		</td>
		</tr>
		<tr><td><input type="hidden"></td></tr>
		<tr>
			<td colspan="2" style="font-family:'Hanna'; vertical-align:middle; font-size:large;">
				쉐마세가 처음이신가요?
			</td>
			<td><a href="joinForm.sms" class="btn btn-outline-danger btn-block" style="font-family:'Hanna'">가입하기!</a></td>
		</tr>
		<tr>
			<td colspan="2" style="font-family:'Hanna'; vertical-align:middle; font-size:large;">
				혹시 아이디가 생각나지 않으신가요?
			</td>
			<td><a href="findmyID.sms"  class="btn btn-outline-danger btn-block" style="font-family:'Hanna'">ID 찾기 !</a></td>
		</tr>
		<tr>
			<td colspan="2" style="font-family:'Hanna'; vertical-align:middle; font-size:large;">
				혹시 비밀번호가 생각나지 않으신가요?
			</td>
			<td><a href="findmypassword.sms"  class="btn btn-outline-danger btn-block" style="font-family:'Hanna'">비밀번호 찾기 !</a></td>
		</tr>
 	</table>
 </div>
 	
 
</form:form>
</div>



<div class="w3-col s3"><p>&nbsp;</p></div>
</div>
</div>
</body>
</html>
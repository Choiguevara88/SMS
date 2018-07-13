<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<br>
<br>
<div class="w3-row w3-container w3-margin">
<div class="w3-col s3"><p>&nbsp;</p></div>
<div class="w3-col w3-white s6">
<div class="container">
<label style="font-family:'Hanna';" class="w3-xxlarge w3-center">소중한 개인정보</label>
<div class="w3-container w3-border w3-round-large w3-padding">
	<table class="w3-table" style="height:80; width:100;">
		<tr><td colspan="1" class="w3-center w3-large">이름&nbsp;.&nbsp;.&nbsp;.</td><td colspan="1" class="w3-center w3-large">${sessionScope.loginMember.name}</td></tr>
		<tr><td colspan="1" class="w3-center w3-large">아이디&nbsp;.&nbsp;.&nbsp;.</td><td colspan="1" class="w3-center w3-large">${sessionScope.loginMember.id}</td></tr>
		<tr><td colspan="1" class="w3-center w3-large">이메일&nbsp;.&nbsp;.&nbsp;.</td><td colspan="1" class="w3-center w3-large">${sessionScope.loginMember.email}</td></tr>
		<tr><td colspan="1" class="w3-center w3-large">전화번호&nbsp;.&nbsp;.&nbsp;.</td><td colspan="1" class="w3-center w3-large">${sessionScope.loginMember.mob}</td></tr>
		<c:if test="${sessionScope.loginMember.memType == '0' }">
			<tr><td colspan="1" class="w3-center w3-large">계정 타입&nbsp;.&nbsp;.&nbsp;.</td><td colspan="1" class="w3-center w3-large">게스트</td></tr></c:if>
		<c:if test="${sessionScope.loginMember.memType == '1' }">
			<tr><td colspan="1" class="w3-center w3-large">계정 타입&nbsp;.&nbsp;.&nbsp;.</td><td colspan="1" class="w3-center w3-large">호스트</td></tr></c:if>
		<c:if test="${sessionScope.loginMember.regStatus == '0' }">
			<tr><td colspan="1" class="w3-center w3-large">계정 전환 상태&nbsp;.&nbsp;.&nbsp;.</td><td colspan="1" class="w3-center w3-large">승인 대기중</td></tr></c:if>
		<tr><td colspan="2" class="w3-center"><a href="personal_info_update.sms?id=${sessionScope.loginMember.id }" class="w3-button w3-deep-purple w3-center w3-large">정보 수정</a>
			<a href="personal_info_delete.sms?id=${sessionScope.loginMember.id }" class="w3-button w3-deep-purple w3-center w3-large">회원 탈퇴</a></td></tr>
	</table>
</div>
<br>
<br>
</div>
</div>
<div class="w3-col s3"><p>&nbsp;</p></div>
</div>
</body>
</html>
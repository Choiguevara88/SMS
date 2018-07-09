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
<div class="container">
	<table align="center" cellpadding="1" cellspacing="1" border="1">
		<tr><td>이름</td><td>${sessionScope.loginMember.name}</td></tr>
		<tr><td>아이디</td><td>${sessionScope.loginMember.id}</td></tr>
		<tr><td>이메일</td><td>${sessionScope.loginMember.email}</td></tr>
		<tr><td>전화번호</td><td>${sessionScope.loginMember.mob}</td></tr>
		<c:if test="${sessionScope.loginMember.memType == '0' }">
			<tr><td>계정 타입</td><td>게스트</td></tr></c:if>
		<c:if test="${sessionScope.loginMember.memType == '1' }">
			<tr><td>계정 타입</td><td>호스트</td></tr></c:if>
		<c:if test="${sessionScope.loginMember.regStatus == '0' }">
			<tr><td>호스트 계정 전환 상태</td><td>승인 대기중</td></tr></c:if>
		<tr><td><a href="personal_info_update.sms?id=${sessionScope.loginMember.id }" class="w3-button w3-black">정보 수정</a>
			<td><a href="personal_info_delete.sms?id=${sessionScope.loginMember.id }" class="w3-button w3-black">회원 탈퇴</a>
	</table>
</div>
</body>
</html>
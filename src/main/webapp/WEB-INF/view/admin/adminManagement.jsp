<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언-->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

<title>관리자 페이지</title>
</head>

<body>
<div class="w3-container w3-margin">
<div class="row">

<div class="col-4">
	<table class="w3-table w3-striped w3-small">
	<c:if test="${!empty hRegList}">
		<tr align="center" valign="middle">
			<td colspan="4"></td><td></td>
		</tr>
		<tr align="center" valign="middle">
			<th>아이디</th><th>상호명</th><th>이메일</th><th colspan="2">비고</th>
		</tr>
		<c:forEach var="hostMem" items="${hRegList}">
		<tr align="center" valign="middle">
			<td>${hostMem.id}</td>
			<td>${hostMem.hostName}</td>
			<td>${hostMem.email}</td>
			<td><a href="adminHostRegDetail.sms?id=${hostMem.id}">[확인]</a></td>
			<td><a href="adminHostRegister.sms?id=${hostMem.id}">[승인]</a></td>
		</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty hRegList}">
		<tr><td colspan="5" class="w3-large">등록된 Host 승인 요청이 없습니다.</td></tr>
	</c:if>
	</table>
</div>

<div class="col-4">
	<table class="w3-table w3-striped w3-small">
	<c:if test="${!empty gList}">
		<tr align="center" valign="middle">
			<td colspan="4"></td><td></td>
		</tr>
		<tr align="center" valign="middle">
			<th>아이디</th><th>내 용</th><th>등록일자</th><th>비고</th>
		</tr>
		<c:forEach var="gueQue" items="${gList}">
		<tr align="center" valign="middle">
			<td>${gueQue.id}</td>
			<td><a href="adminQuestionDetail.sms?bNo=${gueQue.bNo}">${fn:substring(gueQue.content,0,15)}...</a></td>
			<td><fmt:formatDate value="${gueQue.regDate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
			<td><a href="adminAnswerQuestion.sms?bNo=${gueQue.bNo}">[답변]</a></td>
		</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty gList}">
		<tr><td colspan="4" class="w3-large">등록된 Guest 고객 문의가 없습니다.</td></tr>
	</c:if>
	</table>
</div>

<div class="col-4">
	<table class="w3-table w3-striped w3-small">
	<c:if test="${!empty hList}">
		<tr align="center" valign="middle">
			<td colspan="4"></td><td></td>
		</tr>
		<tr align="center" valign="middle">
			<th>아이디</th><th>내 용</th><th>등록일자</th><th>비고</th>
		</tr>
		<c:forEach var="hosQue" items="${hList}">
		<tr align="center" valign="middle">
			<td>${hosQue.id}</td>
			<td><a href="adminQuestionDetail.sms?bNo=${hosQue.bNo}">${fn:substring(hosQue.content,0,15)}...</a></td>
			<td><fmt:formatDate value="${hosQue.regDate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
			<td><a href="adminAnswerQuestion.sms?bNo=${hosQue.bNo}">[답변]</a></td>
		</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty hList}">
		<tr><td colspan="4" class="w3-large">등록된 Host 고객 문의가 없습니다.</td></tr>
	</c:if>
	</table>
</div>

</div>
</div>
<div class="w3-container w3-margin">
	<table class="w3-table w3-striped w3-border">
		<tr><th>호스트ID</th><th>상호명</th><th>Room이름</th><th>가입일자</th><th>금월 수입금</th><th>거래량</th></tr>
	<c:forEach var="th" items="${thList}">
		<tr><td>${th.host}</td><td>${th.hostName}</td><td>${th.sRName}</td><td><fmt:formatDate value="${th.regDate}" pattern="yyyy-MM-dd"/></td>
			<td>${th.totPrice}</td><td>${th.cnt}</td></tr>
	</c:forEach>
	<tr><td colspan="6"><a href="adminTransHostList.sms" class="w3-button">[세부 거래 대장 보러가기]</a></td></tr>
	</table>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언-->
<title>관리자 문의 답변 작성</title>
</head>
<body>

<div class="row">
<div class="col-md-5">
	<table border="1">
		<tr><th>문의자</th><td>${board.id}</td></tr>
		<tr><th>제목</th><td>${board.subject}</td></tr>
		<tr><th>문의시간</th><td><fmt:formatDate value="${board.regDate}" pattern="yy-MM-dd hh:mm"/></td></tr>
		<tr><th>내용</th><td>${board.content}</td></tr>
	</table>
</div>
<!-- 중간 선 -->
<div class="col-md-2"></div>
<!-- 중간 선 -->
<div class="col-md-5">
	<form:form modelAttribute="answerBoard" action="adminAnswerQuestion.sms" name="f" method="post">
	<table border="1">
		<form:hidden path="id" value="${loginMember.id}"/>
		<form:hidden path="bNo" value="${board.bNo}"/>
		<form:hidden path="refLevel" value="${board.refLevel}"/>
		<tr><th>제목</th><td><form:input path="subject"/></td></tr>
		<tr><th>내용</th><td><form:textarea path="content"/></td></tr>
		<tr><th><input type="submit" value="작성"></th></tr>
	</table>
	</form:form>
</div>
</div>



</body>
</html>
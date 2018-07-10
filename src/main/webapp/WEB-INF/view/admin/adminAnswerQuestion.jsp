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

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

<title>관리자 문의 답변 작성</title>
</head>
<body>
<div class="w3-container w3-margin">
<div class="row">

	<div class="col-6">
		<table class="w3-table w3-striped w3-border">
			<tr><th>문의자</th><td>${board.id}</td></tr>
			<tr><th>문의시간</th><td><fmt:formatDate value="${board.regDate}" pattern="yy-MM-dd hh:mm"/></td></tr>
			<tr><th>내용</th><td>${board.content}</td></tr>
		</table>
	</div>

	<div class="col-6">
		<form:form modelAttribute="answerBoard" action="adminAnswerQuestion.sms" name="f" method="post">
		<table class="w3-table w3-striped w3-border">
			<form:hidden path="id" value="${loginMember.id}"/>
			<form:hidden path="bNo" value="${board.bNo}"/>	
			<form:hidden path="ref" value="${board.ref}"/>
			<form:hidden path="refLevel" value="${board.refLevel}"/>
			<tr><td>답변작성</td><th>내용</th><td><form:textarea path="content"/></td></tr>
			<tr><td colspan="3"><input type="submit" value="작성"></td></tr>
		</table>
		</form:form>
	</div>
	
</div>
</div>




</body>
</html>
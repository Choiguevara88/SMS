<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!-- 부트스트랩 사용 선언 -->
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%-- /WebContent/WEB-INF/view/board/reply.jsp
	1. 원글에 대한 정보 : num, ref, reflevel,refstep
	2. 답변글로 입력 된 정보 
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언-->
<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>문의 답글 달기</title>
<script type="text/javascript">
function check() {
	if($('#content').val()==''){
		alert("내용을 입력하세요~><");
		document.getElementById('content').focus();
	} else{
		document.qf.submit();
	}
}
</script>
</head>
<body>

<div class="w3-container w3-margin">
<table class="w3-table w3-border">
	<tr><td>
	<label style="font-family:'Hanna'; font-size:x-large;">작성자 : ${board.id}</label>
	</td></tr>
	<tr>
		<td class="w3-border w3-padding w3-margin">
			<p style="font-family:'Hanna'; font-size:x-large;">작성내용</p>${board.content}</td>
	</tr>
	</table>
</div>
<div class="w3-container w3-margin">
<form:form action="Qreply.sms" method="post" name="qf" modelAttribute="board">
	<input type="hidden" name="bNo" value="${board.bNo}">
	<input type="hidden" name="sNo" value="${board.sNo}">
	<input type="hidden" name="kind" value="3">
	<input type="hidden" name="refLevel" value="${board.refLevel}">
	<input type="hidden" name="ref" value="${board.ref}">
	<input type="hidden" name="pageNum"	value="${param.pageNum}">
	<input type="hidden" name="id"	value="${sessionScope.loginMember.id}">
	<table class="w3-table">
		<tr><td>
		<label style="font-family:'Hanna'; font-size:x-large;">문의 답변 달기</label>
		<textarea rows="15" cols="80" name="content" class="w3-input w3-border" id="content"></textarea></td></tr>
		<tr><td style="text-align:center">
			<input type="button" value="답변등록" onclick="check()" class="btn btn-outline-primary btn-lg" style="font-family:'Hanna'; font-size:x-large;">
			<a href="javascript:document.f.reset()" class="btn btn-outline-danger btn-lg" style="font-family:'Hanna'; font-size:x-large;">다시작성</a>
		</td></tr>
	</table>
</form:form>
</div>
</body></html>
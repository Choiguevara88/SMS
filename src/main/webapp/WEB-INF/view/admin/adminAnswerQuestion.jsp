<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>

<!DOCTYPE html>
<html>
<head>

<!-- 부트스트랩 사용 선언-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언-->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

<!-- Hanna 폰트 사용 선언 -->
<link href='http://fonts.googleapis.com/earlyaccess/hanna.css' rel='stylesheet'>
<!-- Hanna 폰트 사용 선언 -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>리뷰 답글 달기</title>

<script type="text/javascript">
	function check() {
		
		if($('#content').val()==''){
			alert("내용을 입력하세요~><");
			document.getElementById('content').focus();	
		} else {
			document.f.submit();
		}
	}
	
	
	
</script>

</head>

<body>
<div class="w3-container w3-margin">
<div class="w3-container w3-margin">
	<h1 style="font-family:'Hanna';">고객 문의 답변하기</h1>
</div>

<div class="w3-container w3-padding w3-margin w3-border w3-round">
<table class="w3-table">
	<tr><td>
	<label style="font-family:'Hanna'; font-size:x-large;">
	<c:if test="${board.kind == 4 }"><font class="w3-text-red">&lt;Guest&gt;</font></c:if>
	<c:if test="${board.kind == 5 }"><font class="w3-text-blue">&lt;Host&gt;</font></c:if>
	${board.id}님의 문의입니다.</label>
	</td></tr>
	<tr>
		<td class="w3-padding w3-margin">
			<p style="font-family:'Hanna'; font-size:x-large;">문의 내용</p>
			<font style="font-family:'Hanna'" class="w3-padding w3-text-gray">${board.content}</font>
		</td>
	</tr>
	</table>
</div>
</div>
<div class="w3-container w3-margin">
	<c:if test="${board.img1 != null }">
	<span><img src="${path }/picture/${board.img1}" style="width:175px; height:150px"></span></c:if>
	<c:if test="${board.img2 != null }">
	<span><img src="${path }/picture/${board.img2}" style="width:175px; height:150px"></span></c:if>
	<c:if test="${board.img3 != null }">
	<span><img src="${path }/picture/${board.img3}" style="width:175px; height:150px"></span></c:if>
	<c:if test="${board.img4 != null }">
	<span><img src="${path }/picture/${board.img4}" style="width:175px; height:150px"></span></c:if>
	</div>
	
<div class="w3-container w3-margin">
<form:form modelAttribute="answerBoard" action="adminAnswerQuestion.sms" name="f" method="post" id="qaf">
	<input type="hidden" name="bNo" value="${board.bNo}">
	<input type="hidden" name="sNo" value="${board.sNo}">
	<input type="hidden" name="kind" value="2">
	<input type="hidden" name="refLevel" value="${board.refLevel}">
	<input type="hidden" name="ref" value="${board.ref}">
	<input type="hidden" name="pageNum"	value="${param.pageNum}">
	<input type="hidden" name="id"	value="${sessionScope.loginMember.id}">
	<table class="w3-table">
		<tr><td>
		<label style="font-family:'Hanna'; font-size:x-large;">문의 답변</label>
		<textarea rows="15" cols="80" name="content" class="w3-input w3-border" id="content"></textarea></td></tr>
		<tr><td style="text-align:center">
			<input type="button" value="답변등록" onclick="check()" class="btn btn-outline-primary btn-lg" style="font-family:'Hanna'; font-size:x-large;">
			<a href="javascript:document.f.reset()" class="btn btn-outline-danger btn-lg" style="font-family:'Hanna'; font-size:x-large;">다시작성</a>
		</td></tr>
	</table>
</form:form>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<!-- Hanna체 -->
<link href='http://fonts.googleapis.com/earlyaccess/hanna.css' rel='stylesheet'>
<!-- Hanna체 -->

<title>:::문의글 답변달기:::</title>
</head>
<body>
<div class="w3-container w3-padding">


<div class="w3-container w3-margin">
	<h1 style="font-family:'Hanna';">문의글 답변 달기</h1>
</div>

<div class="w3-container w3-margin w3-border w3-round-large">

<div class="w3-row w3-margin">
	<div class="w3-col s2">
	<p style="font-family:'Hanna'" class="w3-text-gray">건물명<p>
		<h5 style="font-family:'Hanna'">${building.sName}</h5>
	</div>
	<div class="w3-col s3">
	<p style="font-family:'Hanna'" class="w3-text-gray">한 줄 설명<p>
		<h5 style="font-family:'Hanna'">${building.sPreview}</h5>
	</div>
	<div class="w3-col s7">
	<p style="font-family:'Hanna'" class="w3-text-gray">건물 주소<p>
		<h5 style="font-family:'Hanna'">
		${fn:replace(building.sAddress,",","&nbsp;")}</h5>
	</div>
</div>

</div>




<c:if test="${listcount > 0}">
<div class="w3-container w3-margin w3-card-2">
<table class="w3-table w3-striped" style="font-family:'Hanna';">
	<c:forEach var="board" items="${boardlist}">
	<tr><td colspan="4" style="font-size:x-large">미확인 문의 수 : <font style="color:red">${listcount}</font></td></tr>
	<tr class="w3-text-gray" style="font-size:large;">
		<td style="font-size:large; text-align:center; vertical-align:middle;">작성자</td>
		<td style="font-size:large; text-align:center; vertical-align:middle;">내 용</td>
		<td style="font-size:large; text-align:center; vertical-align:middle;">등록일자</td>
		<td style="font-size:large; text-align:center; vertical-align:middle;">비 고</td>
	</tr>
	<tr>
		<td style="font-size:large; text-align:center; vertical-align:middle;">${board.id}</td>
		<td style="vertical-align:middle;">${board.content}</td>
		<td style="text-align:center;  vertical-align:middle;"><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd H:mm:ss" /></td>
		<td style="font-size:large; text-align:center;  vertical-align:middle;">
			<a href="Qreply.sms?bNo=${board.bNo}" class="btn btn-outline-danger">답변하기</a>
		</td>
	</tr>
	
	</c:forEach>
</table>
<div class="w3-container w3-margin">
<table class="w3-table">
	<tr>
		<td style="font-family:'Hanna'; text-align:center;">
			<c:if test="${pageNum >1 }"><a href="javascript:listQlist(${pageNum -1 })"> [이전]</a></c:if>
			&nbsp; 
			<c:if test="${pageNum <= 1}">[이전]</c:if>&nbsp; 
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a == pageNum}">[${a}]</c:if>
				<c:if test="${a != pageNum}"><a href="javascript:listQlist(${a})">[${a}]</a></c:if>
			</c:forEach> 
			<c:if test="${pageNum < maxpage}"><a href="javascript:listQlist(${pageNum+1 })">[다음]</a></c:if>&nbsp; 
			<c:if test="${pageNum >= maxpage}">[다음]</c:if>&nbsp;
		</td>
</table>
</div>
</div>
</c:if>


<c:if test="${listcount == 0}">
<div class="w3-container w3-margin w3-card-2">
		<h1 style="text-align:center; font-family:'Hanna';">등록된 질문이 없습니다.</h1>
</div>
</c:if>




</div>
</body>
</html>
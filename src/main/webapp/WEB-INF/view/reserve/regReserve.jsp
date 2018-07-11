<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!-- /WebContent/reserve/regReserve.jsp -->    
<!DOCTYPE html>
<html>
<head>

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언-->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언 -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>예약 등록 화면</title>
</head>
<body>

<div class="w3-row">
<div class="w3-col s1"><p>&nbsp;</p></div><!-- 좌우공간확보용 -->
<form:form modelAttribute="reserve" action="regReserve.sms" method="post" name="f">

<div class="w3-col s7 w3-margin">
<div class="w3-container w3-margin">
	<h2 style="font-family:'Hanna';">공간 예약하기</h2>
</div>

<c:forEach items="${room.sRImgNameList}" varStatus="i" var="sRImg">
</c:forEach>

<div class="w3-container w3-margin w3-border">
	
	<div class="w3-container w3-margin">
		<h1 style="font-family:'Hanna'" class="w3-text-gray">${room.sRName}</h1>
	</div>
	
	<div class="w3-container w3-margin">
		<table class="w3-table w3-bordered">
		<tr>
			<td style="width:20%">한 줄 설명</td><td style="width:60%" class="w3-text-gray">${room.sRContent}</td>
		</tr>
		<tr>
			<td style="width:20%">공간유형</td><td style="width:60%" class="w3-text-gray">${room.sRType}</td>
		</tr>
		<tr>
			<td style="width:20%">최소 예약 인원</td><td style="width:60%" class="w3-text-gray">${room.sRPersonLimit}명</td>
		</tr>
		<tr>
			<td style="width:20%">이용 가능 시간</td>
			<td style="width:60%" class="w3-text-gray">
			<c:forEach items="${building.sBHourList}" var="bu" varStatus="i">
				${bu}<c:if test="${!(i.last)}"> ~ </c:if></c:forEach>&nbsp;까지
			</td>
		</tr>
		</table>
		<div class="">
		</div>
	</div>
</div>

	<table>
		<caption>예약등록하기</caption>
		<tr><td align="center">회원</td><td><form:input path="id"/></tr>
		<tr><td align="center">건물번호</td><td><form:input path="sNo"/></tr>
		<tr><td align="center">Room번호</td><td><form:input path="srNo"/></tr>
		<tr><td align="center">수량</td><td><form:input path="reCnt"/></tr>
		<tr><td align="center">대금</td><td><form:input path="totPrice"/></tr>
		<tr><td align="center">예약일자</td><td><form:input path="reDate"/></tr>
		<tr><td align="center" colspan="2"><a href="javascript:document.f.submit()">[게시물등록]</a>&nbsp;&nbsp;<a href="listex.sms">[게시판목록]</a></td></tr>
	</table>


</div>
<div class="w3-col s3 w3-margin"></div>

</form:form>
<div class="w3-col s1"><p>&nbsp;</p></div><!-- 좌우공간확보용 -->
</div>
</body>
</html>
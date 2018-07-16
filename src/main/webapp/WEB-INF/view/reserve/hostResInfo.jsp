<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<fmt:requestEncoding value="UTF-8" />

<!DOCTYPE html>
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
<!-- w3 css 사용 선언 -->

<title>Host 계정용 예약 정보 조회</title>

</head>

<body>

<div class="w3-row">
<div class="w3-col s2"><p>&nbsp;</p></div>

<div class="w3-col s8">
<div class="w3-container w3-margin"></div>

<div class="w3-container w3-margin">
	<h1 style="font-family:'Hanna';">나의 사업장 정보</h1>
</div>
<div class="w3-container w3-margin">

<div class="w3-container w3-card-2">
<div class="w3-row w3-margin">
	<div class="w3-col s2">
	<p style="font-family:'Hanna'" class="w3-text-gray">사업자명<p>
		<h5 style="font-family:'Hanna'">${hMember.hostName}</h5>
	</div>
	<div class="w3-col s3">
	<p style="font-family:'Hanna'" class="w3-text-gray">사업자 연락처<p>
		<h5 style="font-family:'Hanna'">${hMember.tel}</h5>
	</div>
	<div class="w3-col s7">
	<p style="font-family:'Hanna'" class="w3-text-gray">사업자 주소<p>
		<h6 style="font-family:'Hanna'">${hMember.address}</h6>
	</div>
</div>
</div>
</div>

<div class="w3-container w3-margin">
		<c:if test="${buildCnt == 0}">
		<div class="w3-container w3-border">
			<div style="text-align:center; font-family:'Hanna'; font-size:x-large;" class="w3-margin">
				등록된 건물이 없습니다.
			</div>
			<div style="text-align:center; font-family:'Hanna'; font-size:x-large;" class="w3-margin">
				<a href="../building/buildingForm.sms?id=${sessionScope.loginMember.id}" class="btn btn-outline-primary" style="font-family:'Hanna'; font-size:x-large;">
				등록하러 가기</a>
			</div>
		</div>
		</c:if>

	<c:if test="${buildCnt != 0 }">
	<table class="w3-table w3-striped w3-hoverable w3-border">
			<tr>
				<th style="text-align:center;">건물관리번호</th>
				<th style="text-align:center;">건물명</th>
				<th style="text-align:center;">신규예약</th>
				<th style="text-align:center;">신규문의</th>
				<th style="text-align:center;">비  고</th>
			</tr>

			<c:forEach var="build" items="${list}">
				<tr>
					<td style="text-align:center; vertical-align:middle;">${build.sNo}</td>
					<td style="text-align:center; vertical-align:middle;">${build.sName}</td>
					<td style="text-align:center; vertical-align:middle;" class='<c:if test="${build.reCnt != 0}">w3-text-red</c:if>'>[${build.reCnt}]</td>
					<td style="text-align:center; vertical-align:middle;" class='<c:if test="${build.boCnt != 0}">w3-text-red</c:if>'>[${build.boCnt}]</td>
					<td style="text-align:center; vertical-align:middle;">
						<a href="hostResList.sms?sNo=${build.sNo}" style="font-family:'Hanna'" class="btn btn-outline-secondary btn-sm"> 예약정보 </a>&nbsp;&nbsp;
						<a href="javascript:win_open('${build.sNo}')" style="font-family:'Hanna'" class="btn btn-outline-success btn-sm"> 문의글 </a>
						<!-- 윈도우 OPEN -->
						<script type="text/javascript">
								function win_open(num)	{
									var op = "width=800,height=700,scrollbars=yes,left=50,top=150";
									window.open("../building/QlistNotRead.sms?sNo="+num,"reply",op);
								}
						</script>
						<!-- 윈도우 OPEN -->
					</td>			
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>
</div>
<div class="w3-col s2"><p>&nbsp;</p></div>
</div>
</body>
</html>
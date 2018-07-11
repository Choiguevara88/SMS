<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언 -->



<title>:::나의 주문 정보:::</title>

</head>

<body>

<div class="w3-row">
<div class="w3-col s2"><p>&nbsp;</p></div><!-- 좌우 공간 확보용 -->

<div class="w3-col s8">
	<div class="w3-container w3-margin">
		<h1 style="font-family:'Hanna';">주문상세정보</h1>
	</div>

	<div class="w3-container w3-margin w3-border w3-padding-16">
		<span style="font-family:'Hanna';">주문일자 (<fmt:formatDate value="${reserve.regDate}" pattern="yyyy-MM-dd"/>) &nbsp;&nbsp;|&nbsp;&nbsp; 주문번호 : <font class="w3-text-gray">${reserve.reNo}</font></span> 
	</div>

	<div class="w3-container w3-card-2 w3-margin w3-padding-24">	
		<table class="w3-table">
		<tr>
			<td style="vertical-align : middle;">예약기간</td>
			<td style="vertical-align : middle;"><c:if test="${room.sResType == 0}">
				<fmt:formatDate value="${reserve.reDate}"  pattern="yyyy년 MM월 dd일 HH시" /> 부터&nbsp;&nbsp;
				<fmt:formatDate value="${endDate}"  pattern="yyyy년 MM월 dd일 HH시" /> 까지
				</c:if>
				<c:if test="${room.sResType != 0}">
				<fmt:formatDate value="${reserve.reDate}"  pattern="yyyy년 MM월 dd일" /> 부터&nbsp;&nbsp;
				<fmt:formatDate value="${endDate}"  pattern="yyyy년 MM월 dd일" /> 까지
				</c:if>
			</td>
		</tr>
		<tr>
			<td style="vertical-align : middle;">예약수량</td>
			<td class="w3-text-gray" style="vertical-align : middle;">${reserve.reCnt} ${room.sResType == 0 ? '시간' : '일' }</td>
		</tr>
		
		<tr>
			<td style="vertical-align : middle;">예약금액</td>
			<td class="w3-text-gray" style="vertical-align : middle;">${reserve.totPrice}</td>
		</tr>
		
		<tr>
			<td style="vertical-align : middle;">공간명</td>
			<td class="w3-text-gray" style="vertical-align : middle;">${room.sRName}</td>
		</tr>
		
		<tr>
			<td style="vertical-align : middle;">공간 한 줄 설명</td>
			<td class="w3-text-gray" style="vertical-align : middle;">${room.sRInfo}</td>
		</tr>
		
		<tr>
			<td style="vertical-align : middle;">예약금액</td>
			<td class="w3-text-gray" style="vertical-align : middle;">${reserve.totPrice}</td>
		</tr>
		
		<!-- 날짜 비교용 날짜 객체들 -->
		<jsp:useBean id="today" class="java.util.Date" />
		<fmt:formatDate var="today1" value="${today}" pattern="yyyyMMddhhmm" type="date" />
		<fmt:formatDate var="redate" value="${reserve.reDate}" pattern="yyyyMMddhhmm" type="date" /> 
		<!-- 날짜 비교용 날짜 객체들 -->
		
		<tr>
			<td style="vertical-align : middle;">진행상태</td>
			<td class="w3-text-gray" style="vertical-align : middle;">
				<c:if test="${reserve.reStat == 0}">예약 [결제 대기]&nbsp;&nbsp;&nbsp;<a href="resCancel.sms?reNo=${reserve.reNo}&reStat=${reserve.reStat}" class="btn btn-outline-danger btn-sm">예약취소</a></c:if>
				<c:if test="${reserve.reStat == 1}">
					<c:if test="${today1 > redate }">
						[이용 완료]
					</c:if>
					<c:if test="${today1 <= redate }">
						예약 [결제 완료]&nbsp;&nbsp;&nbsp;
						<a href="resCancel.sms?reNo=${reserve.reNo}&reStat=${reserve.reStat}" class="btn btn-outline-danger btn-sm">예약취소신청</a>	
					</c:if>
				</c:if>
				<c:if test="${reserve.reStat == 2}">환불 확인 중&nbsp;&nbsp;&nbsp;<a href="resCancel.sms?reNo=${reserve.reNo}&reStat=${reserve.reStat}" class="btn btn-outline-dark btn-sm">환불확인</a></c:if>
				<c:if test="${reserve.reStat == 3}">[환불완료]</c:if>
				<c:if test="${reserve.reStat == 4}">[예약취소완료]</c:if>
			</td>
		</tr>
		</table>
	</div>
	
	<div class="w3-container w3-margin" style="text-align:center;">
		<c:if test="${reserve.reStat==1 && today1 > redate}">
			<a href="../building/Rwrite.sms?sNo=${reserve.sNo}&reNo=${reserve.reNo}" class="btn btn-outline-primary btn-lg">리뷰작성 하러가기</a>
		</c:if>

		<c:if test="${reserve.reStat==0 && today1 <= redate}">
			<a href="resUpdate.sms?reNo=${reserve.reNo}" class="btn btn-outline-dark btn-lg">예약정보수정</a>
		</c:if>

		<c:if test="${reserve.reStat==5}">
			<a href="../building/buildingDetail.sms?sNo=${reserve.sNo}" class="btn btn-outline-primary btn-lg">작성된 리뷰 보러가기</a>
		</c:if>
	</div>
	
</div>
</div>
<div class="w3-col s2"><p>&nbsp;</p></div><!-- 좌우 공간 확보용 -->
</body>
</html>
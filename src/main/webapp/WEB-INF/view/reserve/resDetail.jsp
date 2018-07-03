<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>reNo detail Test</title>
</head>
<body>

<div class="w3-container">
	<div class="w3-panel w3-leftbar w3-border-teal">
	</div>
	<div class="w3-panel w3-leftbar w3-border-green w3-text-dark-gray">
	${reserve.reNo} (<fmt:formatDate value="${reserve.regDate}" pattern="yyyy-MM-dd hh:mm:ss" />)
	<br>
	<p>예약일자 : <fmt:formatDate value="${reserve.reDate}"  pattern="yyyy-MM-dd HH시" /></p>
	<p>예약수량 : ${reserve.reCnt}시간</p>
	<p>예약금액 : ${reserve.totPrice}</p>
	<p>Room이름 : ${room.sRName}</p>
	<p>Room한줄평 : ${room.sRInfo}</p>
	<p>예약수량 : ${reserve.reCnt}시간</p>
	<p>예약금액 : ${reserve.totPrice}</p>
	</div>
</div>

<c:if test="${reserve.reStat == 0}"><a href="resCancel.sms?reNo=${reserve.reNo}&reStat=${reserve.reStat}">[예약취소]</a></c:if>
<c:if test="${reserve.reStat == 1}">결제완료:<a href="resCancel.sms?reNo=${reserve.reNo}&reStat=${reserve.reStat}">[예약취소신청]</a></c:if>
<c:if test="${reserve.reStat == 2}">예약취소 신청상태:<a href="resCancel.sms?reNo=${reserve.reNo}&reStat=${reserve.reStat}">[환불확인]</a></c:if>
<c:if test="${reserve.reStat == 3}">[환불완료]</c:if>
<c:if test="${reserve.reStat == 4}">[예약취소완료]</c:if>

<jsp:useBean id="today" class="java.util.Date" />
<fmt:formatDate var="today1" value="${today}" type="date" />
<fmt:formatDate var="regdate" value="${reserve.reDate }" type="date" />

<c:if test="${reserve.reStat==1 && today1 > regdate}">
	<a href="../review/write.sms?sNo=${room.sNo}">[리뷰작성]</a>
</c:if>

<c:if test="${reserve.reStat==0 && today1 < regdate}">
	<a href="resUpdate.sms?reNo=${reserve.reNo}">[예약정보수정]</a>
</c:if>

<c:if test="${reserve.reStat==5}">[작성된 리뷰 보러가기]</c:if>

</body>
</html>
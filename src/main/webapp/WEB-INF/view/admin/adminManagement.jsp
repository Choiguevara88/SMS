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

<script type="text/javascript">
	function win_open(num)	{
		var op = "width=800,height=850,scrollbars=yes,left=50";
			window.open("adminAnswerQuestion.sms?bNo=" + num, "HostAnswer", op);
	}
</script>

</head>

<body>
<div class="w3-container w3-margin">
<div class="row">
<div class="col-4">
	<table class="w3-table w3-striped w3-small">
		<tr align="center" valign="middle">
			<td colspan="4" style="text-align:center;  font-size:medium;">
			<font class="btn btn-outline-dark btn-block disabled" style="font-family:'Hanna'; text-align:center;">Host 전환 신청</font>
			</td>
		</tr>
	<c:if test="${!empty hRegList}">
		<tr align="center" valign="middle">
			<th style="text-align:center;">id</th>
			<th style="text-align:center;">상호명</th>
			<th style="text-align:center;">지역</th>
			<th style="text-align:center;">비고</th>
		</tr>
		<c:forEach var="hostMem" items="${hRegList}">
		<tr align="center" valign="middle">
			<td style="text-align:center; vertical-align:middle;">${hostMem.id}</td>
			<td style="text-align:center; vertical-align:middle;">${hostMem.hostName}</td>
			<td style="text-align:center; vertical-align:middle;">${fn:substring(hostMem.address,6,8)}</td>
			<td style="text-align:center; vertical-align:middle;">
			<a href="adminHostRegDetail.sms?id=${hostMem.id}" style="font-family:'Hanna'" class="btn btn-outline-secondary btn-sm">정보</a>&nbsp;
			<a href="adminHostRegister.sms?id=${hostMem.id}" style="font-family:'Hanna'" class="btn btn-outline-secondary btn-sm">승인</a></td>
		</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty hRegList}">
		<tr><td colspan="4" class="w3-large" style="text-align:center;">등록된 Host 승인 요청이 없습니다.</td></tr>
	</c:if>
	</table>
</div>

<div class="col-4">
	<table class="w3-table w3-striped w3-small">
		<tr align="center" valign="middle">
			<td colspan="4" style=" font-size:medium;">
			<a href="Glist.sms" class="btn btn-outline-danger btn-block" style="font-family:'Hanna'; text-align:center;">Guest 고객 문의</a>
			</td>
		</tr>

	<c:if test="${!empty gList}">

		<tr align="center" valign="middle">
			<th style="text-align:center;">아이디</th>
			<th style="text-align:center;">내 용</th>
			<th style="text-align:center;">등록일자</th>
			<th style="text-align:center;">비고</th>
		</tr>
		<c:forEach var="gueQue" items="${gList}">
		<tr align="center" valign="middle">
			<td style="text-align:center; vertical-align:middle;">${gueQue.id}</td>
			<td style="text-align:center; vertical-align:middle;">${fn:substring(gueQue.content,0,15)}...</td>
			<td style="text-align:center; vertical-align:middle;"><fmt:formatDate value="${gueQue.regDate}" pattern="yy-MM-dd hh:mm:ss" /></td>
			<td style="text-align:center; vertical-align:middle;">
			<a href="javascript:win_open('${gueQue.bNo}')" style="font-family:'Hanna'" class="btn btn-outline-danger btn-sm">답변</a></td>
		</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty gList}">
		<tr><td colspan="4" class="w3-large" style="text-align:center;">등록된 Guest 고객 문의가 없습니다.</td></tr>
	</c:if>
	</table>
</div>

<div class="col-4">
	<table class="w3-table w3-striped w3-small">
		<tr align="center" valign="middle">
			<td colspan="4" style="font-size:medium;">
			<a href="Hlist.sms" class="btn btn-outline-primary btn-block" style="font-family:'Hanna'; text-align:center;">Host 고객 문의</a>
			</td>
		</tr>
	<c:if test="${!empty hList}">
		<tr align="center" valign="middle">
			<th style="text-align:center;">아이디</th>
			<th style="text-align:center;">내 용</th>
			<th style="text-align:center;">등록일자</th>
			<th style="text-align:center;">비고</th>
		</tr>
		<c:forEach var="hosQue" items="${hList}">
		<tr align="center" valign="middle">
			<td style="text-align:center; vertical-align:middle;">${hosQue.id}</td>
			<td style="text-align:center; vertical-align:middle;">${fn:substring(hosQue.content,0,15)}...</td>
			<td style="text-align:center; vertical-align:middle;"><fmt:formatDate value="${hosQue.regDate}" pattern="yy-MM-dd hh:mm:ss" /></td>
			<td style="text-align:center; vertical-align:middle;">
			<a href="javascript:win_open('${hosQue.bNo}')" style="font-family:'Hanna'" class="btn btn-outline-primary btn-sm">답변</a>
			
			</td>
		</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty hList}">
		<tr><td colspan="4" class="w3-large" style="text-align:center;">등록된 Host 고객 문의가 없습니다.</td></tr>
	</c:if>
	</table>
</div>
</div>

</div>
<div class="w3-container w3-margin">
	<table class="w3-table">
		<tr>
			<td style="text-align:center;">
				<a href="adminTransHostList.sms" class="w3-button w3-black w3-block w3-round-xxlarge" style="font-family:'Hanna';">총 거래대장</a>
			</td>
			<td style="text-align:center;">
				<a href="adminMemberList.sms" class="w3-button w3-black w3-block w3-round-xxlarge" style="font-family:'Hanna';">총 회원 정보</a>
			</td>
			<td style="text-align:center;">
				<a href="adminHostList.sms" class="w3-button w3-black w3-block w3-round-xxlarge" style="font-family:'Hanna';">Host 정보</a>
			</td>
		</tr>
	</table>
</div>

<div class="w3-container w3-margin">	
	<table class="w3-table w3-striped w3-border">
			<jsp:useBean id="todayDisp" class="java.util.Date" />
		<tr>
			<td colspan="6" class="w3-large" style="text-align:center;">
			<b><fmt:formatDate value="${todayDisp}" pattern="yyyy-MM"/> 거래량</b></td>
		</tr>
		<tr>
			<th style="text-align:center;">호스트ID</th>
			<th style="text-align:center;">상호명</th>
			<th style="text-align:center;">Room이름</th>
			<th style="text-align:center;">가입일자</th>
			<th style="text-align:center;">금월 수입금</th>
			<th style="text-align:center;">거래건수</th>
		</tr>
	
	
	<c:forEach var="th" items="${thList}">
		<tr>
			<td class="w3-small" style="text-align:center;">${th.host}</td>
			<td class="w3-small" style="text-align:center;">${th.hostName}</td>
			<td class="w3-small" style="text-align:center;">${th.sRName}</td>
			<td class="w3-small" style="text-align:center;"><fmt:formatDate value="${th.regDate}" pattern="yyyy-MM-dd"/></td>
			<td class="w3-small" style="text-align:center;"><fmt:formatNumber value="${th.totPrice}" pattern="###,###"/></td>
			<td class="w3-small" style="text-align:center;">${th.cnt}
				<c:set var="tAmount" value="${tAmount + th.totPrice}"/>
				<c:set var="tCnt" value="${tCnt + th.cnt}"/>
			</td></tr>
	</c:forEach>
		<tr>
			<th colspan="5" style="text-align:right;"><fmt:formatDate value="${todayDisp}" pattern="yyyy-MM"/> 월 총 거래금액</th>
			<th style="text-align:center;"><fmt:formatNumber value="${tAmount}" pattern="###,###"/></th>
		</tr>
		<tr>
			<th colspan="5" style="text-align:right;">총 거래건수</th>
			<th style="text-align:center;"><fmt:formatNumber value="${tCnt}" pattern="###,###"/></th>
		</tr>
	</table>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언 -->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

<title>사업자 요청 등록 확인 작업</title>

<style>
	th {
		font-family:'Hanna';
		font-size:large;
	}
	
	td {
		font-family:'Hanna';
		font-size:large;
	}
	
</style>

</head>
<body>
<div class="w3-row">
<div class="w3-col s2"><p>&nbsp;</p></div>
<div class="w3-col s8">

<div class="w3-container w3-margin">
	<h1 style="font-family:'Hanna'">사업자등록정보</h1>
</div>

<div class="w3-container w3-margin w3-border w3-round-large w3-padding">
	<table class="w3-table">
		<tr><td rowspan="7" colspan="2" style="text-align:center;">
			<img src="../picture/${hostMem.pictureUrl}" width="496px" height="701px"/>
			</td>
			<th style="text-align:center; vertical-align:middle;">상호명</th><td style="vertical-align: middle;">${hostMem.hostName}</td>
		</tr>
		<tr><th style="text-align:center; vertical-align:middle;">ID</th><td style="vertical-align: middle;">${hostMem.id}</td></tr>
		<tr><th style="text-align:center; vertical-align:middle;">가입일자</th><td style="vertical-align: middle;"><fmt:formatDate value="${hostMem.regDate}" pattern="yy-MM-dd"/></td></tr>
		<tr><th style="text-align:center; vertical-align:middle;">휴대전화</th><td style="vertical-align: middle;">${hostMem.mob}</td></tr>
		<tr><th style="text-align:center; vertical-align:middle;">Email</th><td style="vertical-align: middle;">${hostMem.email}</td></tr>
		<tr><th style="text-align:center; vertical-align:middle;">Tel</th><td style="vertical-align: middle;">${hostMem.tel}</td></tr>
		<tr><th style="text-align:center; vertical-align:middle;">계좌정보</th><td style="vertical-align: middle;">
		${fn:replace(hostMem.accountNo,',',' ')}</td></tr>
		<tr><td colspan="4" style="text-align:center;">
				<a href="javascript:history.go(-1)" class="btn btn-outline-danger btn-large" style="width:30%; font-family:'Hanna';">뒤로가기</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="adminHostRegister.sms?id=${hostMem.id}" class="btn btn-outline-primary btn-large" style="width:30%; font-family:'Hanna';">등록하기</a>
			</td>
		</tr>
	</table>
</div>

</div>
<div class="w3-col s2"><p>&nbsp;</p></div>
</div>
</body>
</html>
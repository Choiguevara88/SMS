<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

<title>사업자 요청 등록 확인 작업</title>

<style>
	
	td {
		 
	}
	
</style>

</head>
<body>
<div class="w3-container w3-margin">
	<table class="w3-table w3-bordered w3-border w3-card-4">
		<tr>
			<th colspan="4" style="text-align:center;" class="w3-xlarge w3-text-gray">
				[사업자등록정보] 
			</th>
		</tr>
		<tr><td rowspan="7" colspan="2" style="text-align:center;" class="w3-border-right">
			<img src="../picture/${hostMem.pictureUrl}" width="496px" height="701px"/>
			</td>
			<th style="text-align:center; vertical-align:middle;">상호명</th><td style="vertical-align: middle;">${hostMem.hostName}</td>
		</tr>
		<tr><th style="text-align:center; vertical-align:middle;">ID</th><td style="vertical-align: middle;">${hostMem.id}</td></tr>
		<tr><th style="text-align:center; vertical-align:middle;">가입일자</th><td style="vertical-align: middle;"><fmt:formatDate value="${hostMem.regDate}" pattern="yy-MM-dd"/></td></tr>
		<tr><th style="text-align:center; vertical-align:middle;">휴대전화</th><td style="vertical-align: middle;">${hostMem.mob}</td></tr>
		<tr><th style="text-align:center; vertical-align:middle;">Email</th><td style="vertical-align: middle;">${hostMem.email}</td></tr>
		<tr><th style="text-align:center; vertical-align:middle;">Tel</th><td style="vertical-align: middle;">${hostMem.tel}</td></tr>
		<tr><th style="text-align:center; vertical-align:middle;">계좌정보</th><td style="vertical-align: middle;">${hostMem.accountNo}</td></tr>
		<tr><th colspan="4" style="text-align:center;">
				<a href="javascript:history.go(-1)" class="w3-button w3-light-gray w3-round-xxlarge" style="width:30%;">뒤로가기</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="adminHostRegister.sms?id=${hostMem.id}" class="w3-button w3-black w3-round-xxlarge" style="width:30%;">등록하기</a>
				
			</th>
		</tr>
	</table>
</div>
</body>
</html>
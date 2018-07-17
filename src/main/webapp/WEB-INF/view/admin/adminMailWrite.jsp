<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언 -->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

<style type="text/css">
	th { font-family:'Hanna'; }
	td { font-family:'Hanna'; }
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:::메일 보내기:::</title>
<script type="text/javascript" src="//cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>
</head>
<body>
<div class="w3-container w3-margin w3-padding">
<h1 style="font-family:'Hanna'">메일 작성하기</h1>
</div>
	<form name="mailform" method="post" action="mailSend.sms" enctype="multipart/form-data">
	
	<div class="w3-container w3-margin">
		<div class="row">
		<div class="col-5"><input type="text" name="naverid" class="w3-input" placeholder="네이버 메일주소 @naver.com 이전까지 입력"></div>
		<div class="col-5"><input type="password" name="naverpass" class="w3-input" placeholder="네이버 메일주소 비밀번호 입력"></div>
		<div class="col-2"><input type="submit" value="메일 보내기" class="btn btn-outline-secondary" style="width:100%; font-family:'Hanna'"></div>
		</div>
	</div>
	
	<div class="w3-container w3-margin">
		<table class="w3-table w3-striped w3-border">
			<tr>
				<td style="text-align:center; vertical-align:middle;">보내는 사람</td>
				<td>${sessionScope.loginMember.email}</td>
			</tr>
			<tr>
				<td style="text-align:center; vertical-align:middle;">받는 사람</td>
				<td><input type="text" name="recipient" size="100" 
				value='<c:forEach items="${memberList}" var="mem" varStatus="i">${mem.name}&lt;${mem.email}&gt;<c:if test='${!i.last}'>,</c:if></c:forEach>' class="w3-input"></td>
			</tr>
			<tr>
				<td style="text-align:center; vertical-align:middle;">제    목</td>
				<td><input type="text" name="title" class="w3-input"></td>
			</tr>
			<tr>
				<td style="text-align:center; vertical-align:middle;">메시지형식</td>
				<td><select name="mtype" class="w3-select" style="width:10%;">
					<option value="text/html;charset=UTF-8" >HTML</option>
					<option value="text/plain;charset=UTF-8" >TEXT</option>
				</select></td>
			</tr>
			<tr>
				<td style="text-align:center; vertical-align:middle;">첨부파일 1</td>
				<td><input type="file" name="file1" class="w3-button" style="width:30%;"></td>
			</tr>
			<tr>
				<td style="text-align:center; vertical-align:middle;">첨부파일 2</td>
				<td><input type="file" name="file1" class="w3-button" style="width:30%;"></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="contents" style="height:600px;"></textarea>
					<script type="text/javascript">CKEDITOR.replace('contents');</script>
				</td>
			</tr>
		</table>
	</div>
	</form>
</body>
</html>
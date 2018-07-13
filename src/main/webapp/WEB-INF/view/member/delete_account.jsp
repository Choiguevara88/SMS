<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function confirm(){
	confirm("누르시면 회원탈퇴를 진행합니다...또르르르...ㅜㅜ");
}
</script>
</head>
<body>
<br>
<br>
<div class="w3-row w3-container w3-margin">
<div class="w3-col s3"><p>&nbsp;</p></div>

<div class="w3-col s6">
<label style="font-family:'Hanna';" class="w3-xxlarge w3-center">탈퇴 실화&nbsp;.&nbsp;.&nbsp;.&nbsp;? ㅠㅠ</label>
<form action="personal_info_delete_confirm.sms" method="POST" name="f">
	<div class="w3-container w3-border w3-round-large w3-padding">
	<table class="w3-table" style="height:80; width:100;">
		<tr><td colspan="1" class="w3-center w3-large">비밀번호</td><td><input class="w3-input" type="password" name="pw" id="pw"></td></tr>
		<tr><td class="w3-center"  colspan="2"><input type="button" onclick="confirm()" class="w3-button w3-black" value="탈퇴하기"> &nbsp;&nbsp;
		<a href="javascript:history.go(-1)" class="w3-button w3-black">뒤로가기</a>
	</table>
</div>
</form>
</div>
<div class="w3-col s3"><p>&nbsp;</p></div>
</div>
</body>
</html>
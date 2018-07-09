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
<div class="container">
<form action="personal_info_delete_confirm.sms" method="POST" name="f">
	<table align="center" cellpadding="1" cellspacing="1" border="1">
		<tr><td>비밀번호</td><td><input type="password" name="pw" id="pw"></td></tr>
		<tr><td colspan="2"><input type="button" onclick="confirm()" class="w3-button w3-black" value="탈퇴하기"> &nbsp;&nbsp;
		<a href="javascript:history.go(-1)" class="w3-button w3-black">뒤로가기</a>
	</table>
</form>
</div>
</body>
</html>
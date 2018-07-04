<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function emailcheck(){
	var inputed = $('#email').val();
	var reg_email = /@+/;
	if(!reg_email.test(inputed)){
		alert('이메일에 @이 없슴둥');
		document.getElementById('email').focus();
		$('#email').css("background-color","#FFCECE");
	} else{
		document.f.submit();
	}
}
</script>
</head>
<body>
<form action="letsfindID.sms" method="POST" name="f">
		<table cellspacing="1" cellpadding="1" border="1">
			<tr>
				<td>이름 입력</td><td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>이메일 입력</td><td><input type="text" name="email" id="email"></td>
			</tr>
			<tr><td colspan="2"><input type="button" onclick="emailcheck()" value="아이디 찾기"></td>
			</tr>
		</table>
	</form>
</body>
</html>
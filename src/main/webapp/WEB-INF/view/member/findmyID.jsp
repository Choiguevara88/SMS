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
<br>
<br>
<div class="w3-row w3-container w3-margin">
<div class="w3-col s3"><p>&nbsp;</p></div>

<div class="w3-col s6">
<label style="font-family:'Hanna';" class="w3-xxlarge w3-center">저런ㅜ 쉐마쉐 아이디가 기억이 나지 않으시군요..</label>
<div class="w3-container w3-border w3-round-large w3-padding">
<form action="letsfindID.sms" method="POST" name="f">
		<table class="w3-table" style="height:130; width:250;">
			<tr>
				<td class="w3-center w3-large">이름</td><td><input type="text" class="w3-input" name="name"></td>
			</tr>
			<tr>
				<td class="w3-center w3-large">이메일</td><td><input type="text" class="w3-input" name="email" id="email"></td>
			</tr>
			<tr>
				<td colspan="2" class="w3-center">
					<input class="w3-button w3-deep-purple w3-center w3-large" type="button" onclick="emailcheck()" value="아이디 찾기">
					<input class="w3-button w3-deep-purple w3-center w3-large" type="button" onclick="javascript:history.go(-1)" value="아! 기억나셨나요?"></td>
			</tr>
		</table>
	</form>
	</div>
	<div class="w3-col s3"><p>&nbsp;</p></div>
	</div>
	</div>
</body>
</html>
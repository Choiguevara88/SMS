<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function autohypen(){
	var x = document.getElementById("tel"); //tel을 선택해서
	x.value = x.value.replace(/[^0-9]/g, ''); //0-9이 아닌 모든걸 ''으로 바꾼다 x.value는 뭐 암것도 없음. 그냥 변수
	console.log(x.value); //11111111111 이렇게 나옴
	var tmp = "";

	if (x.value.length > 3 && x.value.length <= 7) { //length구해서 -필요한 곳마다 넣기
		tmp += x.value.substr(0, 3);
		tmp += '-';
		tmp += x.value.substr(3);
		x.value = tmp;
		return x.value;
	} else if (x.value.length > 7) {
		tmp += x.value.substr(0, 3);
		tmp += '-';
		tmp += x.value.substr(3, 4);
		tmp += '-';
		tmp += x.value.substr(7);
		x.value = tmp;
		return x.value;
	}
}
</script>
</head>
<body>
<form:form modelAttribute="member" action="addhostdata.sms" method="POST" enctype="multipart/form-data">
	<input type="hidden" id="memType" name="memType" value="1"/>
	<table cellpadding="1" cellspacing="1" border="1">
		<tr>
			<td align="center">상호명/호스트이름</td><td><input type="text" name="hostName" id="hostName"></td>
		</tr>
		<tr>
			<td align="center">사업자 등록번호</td><td><input type="text" name="hostRegNo" id="hostRegNo"></td>
		</tr>
		<tr>
			<td align="center">사업자주소지</td><td><input type="text" name="address" id="address"></td>
		</tr>
		
		<tr><td rowspan="3" align="center">계좌정보</td>
			<td><input type="text" name="accountNo" id="accountNo" placeholder="은행명: 뒤에 '은행'은 빼고"/></td></tr>
		<tr><td><input type="text" name="accountNo" id="accountNo" placeholder="계좌번호: - 제외"/></td></tr>
		<tr><td><input type="text" name="accountNo" id="accountNo" placeholder="예금주 : 이름"/></td></tr>  
		<tr>
			<td align="center">사업자 연락처</td>
					<td><input type="text" name="tel" id="tel" placeholder="123-456-7890" onkeyup="autohypen()" maxlength="13"></td>
		</tr>
		<tr><td colspan="2" align="center">
				<input type="submit" value="호스트 승인 요청">
				<input type="button" onclick="javascript:history.go(-1)" value="뒤로가기"></td></tr>
	</table>
</form:form>
</body>
</html>
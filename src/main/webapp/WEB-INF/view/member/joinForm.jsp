<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>즐거운 쉐마쉐 가입! &gt;&lt;</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var pwdCheck = 0;
var idCheck = 0;
function checkReg(){
	var inputed= $("#id").val();
	var reg_id = /^.*(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
	var reg_id2 = /^.*(?=.*[a-zA-Z]).*$/;
	if(reg_id.test(inputed) || reg_id2.test(inputed)){
		checkID();
	} else{
		$(".signup").prop("disabled",true);
		$("#id").css("background-color","#FFCECE");
		alert("숫자-영문조합 또는 영문을 사용해 주세요~");
		document.getElementById('id').focus();
	}
	
}
function checkID(){
	var inputed= $("#id").val();
	$.ajax({
		data : {
			id : inputed
		},
		url : "checkID.sms",
		success: function(data){
			console.log(data);
			if(inputed =="" && data=='0'){ //인풋이 없고 db에 없을때는 빨간색
				$(".signup").prop("disabled",true);
				$("#id").css("background-color","#FFCECE");
				idCheck = 0;
			} else if(data=='0'){
				$("#id").css("background-color","MediumSeaGreen");
				idCheck = 1;
			} else if(data =="1"){
				$(".signup").prop("disabled",true);
				$("#id").css("background-color","#FFCECE")
				alert("중복된 아이디입니다~");
				document.getElementById('id').focus();
				return false;
			}
		}
	});
}
function checkPwd(){
	var inputed = $('#pw').val(); //비번
	var reinputed = $('#repwd').val(); //비밀번호 재확인
	if(reinputed=="" && (inputed != reinputed || inputed == reinputed)){
		$(".signup").prop("disabled",true);
		$("#repwd").css("background-color","#FFCECE");
	}
	else if(inputed == reinputed){
		$('#repwd').css("background-color","MediumSeaGreen");
		pwdCheck = 1;
		if(idCheck==1 && pwdCheck==1){
			console.log(idCheck);
			console.log(pwdCheck);
			$(".signup").prop("disabled",false);
		}
	} else if(inputed != reinputed){
		pwdCheck = 0;
		$(".signup").prop("disabled",true);
		$("#repwd").css("background-color","#FFCECE");
	}
}
function autohypen(){
	var x = document.getElementById("mob"); //tel을 선택해서
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
function emailcheck(){
	var email = $('#email').val();
	console.log(email);
	var reg_email = /@+/;
	if(!reg_email.test(email)){
		alert('이메일 주소에 @이가 없습니다. 대충 쓰지 마셔요~ ><');
		document.getElementById('email').focus();
		$(".signup").prop("disabled",false);
		return false;
	}
}
</script>
<script type="text/javascript">
$(document).ready(function() { //이메일 자동 완성
    var emailaddress = ["@gmail.com",
    	                 	"@naver.com",
    	                 	"@hanmail.net",
    	                 	"@daum.net",
    	                 	"@hotmail.com"];
    $("#email").keyup(function() {
    	inputmail = new Array(emailaddress.length);
    	for(i=0;i<emailaddress.length;i++) {
    		inputmail[i] = $("#email").val() + emailaddress[i] //inputmail 배열을 내가키로 입력한것 + emailaddress로 저장
    	}
        $("#email").autocomplete({
            source: inputmail, //자동완성의 소스를 inputmail로
            select: function(event, ui) { //내가 클릭할것 
                console.log(ui.item);
            },
            focus: function(event, ui) { //마우스를 가져다 댈때
                return false;
                //event.preventDefault();
            }
        });
    });
});
function signup(){
	confirm("회원가입 완료!!! ><");
	document.f.submit();
}
</script>
</head>
<body>
<form:form name="f" modelAttribute="member" action="member/join.sms" method="post">
	<spring:hasBindErrors name="member">
		<font color="tomato">
			<c:forEach items="${errors.globalErrors }" var="error">
				<spring:message code="${error.code }"/>
			</c:forEach>
		</font>
	</spring:hasBindErrors>
<table align="center" cellpadding="1" cellspacing="1" border = "1">
	<tr><td>아이디 </td><td><form:input path="id" placeholder="영문과 숫자조합으로 가즈아!"/>
		<font color="red"><form:errors path="id"/></font></td></tr>
	<tr>
		<td>비밀번호 </td><td><form:password path="pw" placeholder="쉬운건 하지 말즈아!" onfocus="checkReg()" onkeyup="checkPwd()"/>
		 <font color="red"><form:errors path="pw"/></font></td></tr>
	<tr>
		<td>비번확인 </td><td><input type="password" placeholder="비번 입력 한번 더 가즈아!" name="pw-repeat" class="pass" id="repwd" onkeyup="checkPwd()"></td></tr>
	<tr>
		<c:if test="${!empty name }">
			<td>이름 </td><td><form:input path="name" value="${name }" placeholder="틀릴리 없겠쥬?" readonly="true"/>
			<font color="red"><form:errors path="name"/></font></td></c:if>
		<c:if test="${empty name }">
			<td>이름 </td><td><form:input path="name" placeholder="틀릴리 없겠쥬?"/>
			<font color="red"><form:errors path="name"/></font></td></tr></c:if>
	<tr>
		<c:if test="${!empty email }">
			<td>이메일 </td><td><form:input path="email" value= "${email }" placeholder="틀릴리 없겠쥬?" readonly="true"/>
			<font color="red"><form:errors path="email"/></font></td></c:if>
		<c:if test="${empty email }">
			<td>이메일 </td><td><form:input path="email" placeholder="자주 쓰는걸로 가즈아!"/>
			<font color="red"><form:errors path="email"/></font></td></tr></c:if>
	<tr>
		<td>휴대폰번호 </td><td><form:input path="mob" placeholder="예약시 필요!!" onfocus="emailcheck()" onkeyup="autohypen()" maxlength="13"/>
		<font color="red"><form:errors path="mob"/></font></td></tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" class="signup" onclick="signup()" disabled="disabled" value="가입하기">
			<input type="button" onclick="javascript:history.go(-1)" value="뒤로가기"></td>
	</tr>
</table>
</form:form>
</body>
</html>
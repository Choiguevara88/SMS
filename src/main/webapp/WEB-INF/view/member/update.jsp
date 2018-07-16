<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var pwdCheck = 0;
function checkPwd(){
	var inputed = $('#pw').val(); //비번
	var reinputed = $('#repwd').val(); //비밀번호 재확인
	if(reinputed=="" && (inputed != reinputed || inputed == reinputed)){
		$("#edit").prop("disabled",true);
		$("#repwd").css("background-color","#FFCECE");
	}
	else if(inputed == reinputed){
		$("#edit").prop("disabled",false);
		$('#repwd').css("background-color","MediumSeaGreen");
		pwdCheck = 1;
	} else if(inputed != reinputed){
		pwdCheck = 0;
		$("#edit").prop("disabled",true);
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
function confirmm(){
	confirm("확인을 누르시면 정보수정이 완료됩니다 ! ><");
	document.f.submit();
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
</script>
</head>
<body>
<br>
<br>
<div class="w3-row w3-container w3-margin">
<div class="w3-col s3"><p>&nbsp;</p></div>

<div class="w3-col s6">
<label style="font-family:'Hanna';" class="w3-xxlarge w3-center">Share my space 정보수정</label>
<form:form modelAttribute="member" name="f" action="personal_info_new.sms" method="post">
	<spring:hasBindErrors name="member">
		<font color="red">
			<c:forEach items="${error.globalErrors }" var="errors">
				<spring:message code="${error.code }"/>
			</c:forEach>
		</font>
	</spring:hasBindErrors>
	<div class="w3-container w3-border w3-round-large w3-padding">
	<table class="w3-table" style="height:130; width:250;">
		<tr><td colspan="1" class="w3-center w3-large">이름</td><td colspan="1"><form:input path="name" class="w3-input" value="${sessionScope.loginMember.name }" readonly="true"/></td></tr>
		<tr><td colspan="1" class="w3-center w3-large">아이디</td><td colspan="1"><form:input path="id" class="w3-input" value="${sessionScope.loginMember.id }" readonly="true"/></td></tr>
		<tr><td colspan="1" class="w3-center w3-large">새 비밀번호</td><td colspan="1"><form:password path="pw" class="w3-input" placeholder="쉬운건 하지 말즈아!" onkeyup="checkPwd()"/>
				<font color="red"><form:errors path="pw"/></font></td></tr>
		<tr><td colspan="1" class="w3-center w3-large">새 비밀번호 확인</td><td colspan="1"><input type="password" class="w3-input" name="pw-repeat" id="repwd" placeholder="한번 더 가즈아!!" onkeyup="checkPwd()"></td></tr>
		<tr><td colspan="1" class="w3-center w3-large">이메일</td><td><form:input path="email" class="w3-input" value="${sessionScope.loginMember.email }"/>
				<font color="red"><form:errors path="email"/></font></td></tr>
		<tr><td colspan="1" class="w3-center w3-large">전화번호</td><td colspan="1"><form:input path="mob" oninput="autohypen()" maxlength="13" class="w3-input" value="${sessionScope.loginMember.mob }"/>
				<font color="red"><form:errors path="mob"/></font></td></tr>
		<tr><td colspan="2" class="w3-center"><input type="button" value="수정 완료" onclick= "confirmm()" id="edit" class="w3-button w3-deep-purple w3-center w3-large">
			<a href="main.sms" class="w3-button w3-deep-purple w3-center w3-large">메인으로 가기</a></td>
	</table>
	</div>
</form:form>
</div>
<div class="w3-col s3"><p>&nbsp;</p></div>
</div>

</body>
</html>
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
<div class="container">
<form:form modelAttribute="member" action="personal_info_new.sms" method="post">
	<spring:hasBindErrors name="member">
		<font color="red">
			<c:forEach items="${error.globalErrors }" var="errors">
				<spring:message code="${error.code }"/>
			</c:forEach>
		</font>
	</spring:hasBindErrors>
	<table align="center" cellpadding="1" cellspacing="1" border="1">
		<tr><td>이름</td><td><form:input path="name" value="${sessionScope.loginMember.name }" readonly="true"/></td></tr>
		<tr><td>아이디</td><td><form:input path="id" value="${sessionScope.loginMember.id }" readonly="true"/></td></tr>
		<tr><td>새 비밀번호</td><td><form:password path="pw" value="${sessionScope.loginMember.pw }" placeholder="쉬운건 하지 말즈아!" onkeyup="checkPwd()"/>
				<font color="red"><form:errors path="pw"/></font></td></tr>
		<tr><td>새 비밀번호 확인</td><td><input type="password" name="pw-repeat" id="repwd" placeholder="한번 더 가즈아!!" onkeyup="checkPwd()"></td></tr>
		<tr><td>이메일</td><td><form:input path="email" value="${sessionScope.loginMember.email }"/>
				<font color="red"><form:errors path="email"/></font></td></tr>
		<tr><td>전화번호</td><td><form:input path="mob" value="${sessionScope.loginMember.mob }"/>
				<font color="red"><form:errors path="mob"/></font></td></tr>
		<tr><td colspan="2" align="center"><input type="submit" value="수정 완료" id= "edit" class="w3-button w3-black">
			<a href="main.sms" class="w3-button w3-black">메인으로 가기</a>
	</table>
</form:form>
</div>
</body>
</html>
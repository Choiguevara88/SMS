<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언-->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언 -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1:1 문의</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(function(){
	//댓글 목록 출력
	listReply();
	//댓글 쓰기

	$("#btnReply").click(function(){
		reply();
		});
function reply() {
	var content=$("#content").val();
	if(content == ''){
		alert("내용입력");
		document.getElementById('content').focus();
		return false;
	} 
	var id ="${sessionScope.loginMember.id}";
	var kind = "${kind}"; 
	var param = {"content" : content, "id" :id, "kind":kind};
	$.ajax({
		type:"post",
		url: "${path}/qa/questionAdmin.sms",
		data: param,
		success: function(){
			alert("댓글이 등록 되었습니다.")
			listReply();
			}
		});
	}
function listReply(){
	$.ajax({
		type: "get",
		url : "${path}/qa/questionList.sms?id=${sessionScope.loginMember.id}&kind=${kind}",
		success: function(result){
			$("#listReply").html(result)
		}
	});
	}
});
</script>
</head> 
<body>

<div class="w3-row w3-margin">
<div class="w3-col s2"><p>&nbsp;</p></div>
<div class="w3-col s8">

<div id="listReply" class="w3-container"></div>

<div class="w3-container w3-padding">
<form method="post" name="f" action="questionAdmin.sms">
<input type="hidden" name="kind" value="${kind}">
<input type="hidden" name="id" value="${sessionScope.loginMember.id }"/>

<table class="w3-table">
	<tr>
		<td><textarea id="content" placeholder="문의사항을 입력해 주세요." class="w3-input"></textarea>
	</td>
	<td>
		<input type="button" id="btnReply" value="문의하기" class="btn btn-outline-primary btn-lg btn-block">
	</td>
	</tr>
</table>
</form>

</div>

</div>
<div class="w3-col s2"><p>&nbsp;</p></div>
</div>
	
</body>
</html>
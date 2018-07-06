<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<div>
<div id="listReply"></div>
</div>
<form method="post" name="f" action="questionAdmin.sms">
	<input type="hidden" name="kind" value="${kind}">
	<input type="hidden" name="id" value="${sessionScope.loginMember.id }"/>
	<table border="1" align="center">
		<tr><td><textarea rows="2" cols="80" id="content" placeholder="입력해 주세요."></textarea>
	</td><td>
		<button type="button" id="btnReply">댓글쓰기</button>
	</tr>
</table>
</form>
</body>
</html>
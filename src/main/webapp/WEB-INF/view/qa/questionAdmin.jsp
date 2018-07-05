<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1:1 문의</title>
</head>
<body>

<form method="post" name="f" action="questionAdmin.sms">
	<input type="hidden" name="kind" value="${kind}">
	<input type="hidden" name="id" value="${sessionScope.loginMember.id }"/>
	<table border="1" align="center">
		<tr><td><textarea rows="2" cols="80" name="content" placeholder="입력해 주세요."></textarea>
	</td><td>
		<input type="submit" value="전송">
	</tr>
</table>

</form>
</body>
</html>
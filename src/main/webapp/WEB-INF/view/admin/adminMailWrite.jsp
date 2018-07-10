<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메일 보내기</title>
<script type="text/javascript" src="//cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>
</head>
<body>
	<h2>메일 보내기</h2>
	<form name="mailform" method="post" action="mail.test" enctype="multipart/form-data">
		네이버 메일 주소 : <input type="text" name="naverid" value=""> &nbsp;&nbsp;&nbsp;
		네이버 비밀 번호 : <input type="password" name="naverpass" value="">
		<table border="1" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td>보내는사람</td>
				<td>${sessionScope.loginMember.email}</td>
			</tr>
			<tr>
				<td>받는사람</td>
				<td><input type="text" name="recipient" size="100" value='<c:forEach items="${memberList}" var="mem">${mem.name}&lt; ${mem.email}&gt;</c:forEach>'></td>
			</tr>
			<tr>
				<td>제목</td>	<td><input type="text" name="title" size="100"></td>
			</tr>
			<tr>
				<td>메시지형식</td>
				<td><select name="mtype">
					<option value="text/html;charset=euc-kr">HTML</option>
					<option value="text/plain;charset=euc-kr">TEXT</option>
				</select></td>
			</tr>
			<tr>
				<td>첨부파일1</td>
				<td><input type="file" name="file1"></td>
			</tr>
			<tr>
				<td>첨부파일2</td>
				<td><input type="file" name="file1"></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="contents" cols="120" rows="10"></textarea>
					<script type="text/javascript">CKEDITOR.replace('contents');</script>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="메일보내기"></td>
			</tr>
		</table>
	</form>
</body>
</html>
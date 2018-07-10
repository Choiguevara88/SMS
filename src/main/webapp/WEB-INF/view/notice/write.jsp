<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 글 등록</title>
<script type="text/javascript" src="//cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>
</head>
<body>
<form:form modelAttribute="board" action="write.sms" method="post" name="f">
<input type="hidden" name="pageNum" value="1">
		<form:hidden path="kind" value="1"/>
	<table border="1" cellpadding="0" cellspacing="0" align="center">
		<caption>공지사항</caption>
		<tr><td>메시지형식</td>
	  	  <td><select name="mtype">
	  	  	  <option value="text/html;charset=UTF-8">HTML</option>
	  	  	  <option value="text/plain;charset=UTF-8">TEXT</option>
	  	  </select></td><td align="center">제목</td><td><form:input path="subject"/><font color="red"><form:errors path="subject"/></font></td></tr>
		
		<tr><td align="center" colspan="2">내용</td><td colspan="2"><form:textarea path="content" cols="120" rows="10"/>
	  <script type="text/javascript">CKEDITOR.replace('content');</script><font color="red"><form:errors path="content"/></font></td></tr>
	  
		<tr><td align="center" colspan="4">
		<a href="javascript:document.f.submit()">[게시물등록]</a>&nbsp;&nbsp;
		<a href="list.sms">[공지사항 목록]</a></td></tr>
	</table>
</form:form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언 -->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 글 등록</title>
<script type="text/javascript" src="//cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>
</head>
<body>
<div class="w3-row">
<div class="w3-col s2"><p>&nbsp;</p></div> <!-- 좌우 공간 확보용 -->

<div class="w3-col s8">
<form:form modelAttribute="board" action="write.sms" method="post" name="f">
<input type="hidden" name="pageNum" value="1">
<form:hidden path="kind" value="1"/>


<div class="w3-container w3-margin">
	<h2 style="font-family:'Hanna'">공지사항작성</h2>
</div>
<div class="w3-container w3-margin">
	<table class="w3-table">
	<tr>
		<td>
		메시지형식&nbsp;&nbsp;&nbsp;
		<select name="mtype">
	  	 	  <option value="text/html;charset=UTF-8">HTML</option>
	  	  	  <option value="text/plain;charset=UTF-8">TEXT</option>
	  	</select>
	  	</td>
	</tr>
	<tr>
	  	<td><form:input path="subject" class="w3-input" placeholder="제목을 입력하세요."/></td>
	</tr>
		
		<tr><td colspan="2"><form:textarea path="content" cols="120" rows="10"/>
	  <script type="text/javascript">CKEDITOR.replace('content');</script><font color="red"><form:errors path="content"/></font></td></tr>
	  
	<tr>
		<td align="center" colspan="2" style="text-align:center;">
			<a href="javascript:document.f.submit()" class="btn btn-outline-primary" style="font-family:'Hanna';">공지등록</a>&nbsp;&nbsp;
			<a href="list.sms" class="btn btn-outline-dark" style="font-family:'Hanna';">목록으로</a>
		</td>
	</tr>
	</table>
</div>
</form:form>
</div>

<div class="w3-col s2"><p>&nbsp;</p></div> <!-- 좌우 공간 확보용 -->
</div>
</body>
</html>
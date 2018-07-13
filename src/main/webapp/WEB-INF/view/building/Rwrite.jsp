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
<!-- 부트스트랩 사용 선언-->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>리뷰 글 등록</title>
</head>
<body>

<div class="w3-row">
<div class="w3-col s2"><p>&nbsp;</p></div> <!-- 좌우 공간 확보용 -->
<div class="w3-col s8">

<form:form modelAttribute="board" action="Rwrite.sms" method="post" name="f" enctype="multipart/form-data">

<spring:hasBindErrors name="board">
 	<font color="red">
 		<c:forEach items="${errors.globalErrors }" var="error">
 			<spring:message code="${error.code }"/>
 		</c:forEach>	
 	</font>
 </spring:hasBindErrors>
 
<form:hidden path="kind" value="2"/>
<input type="hidden" name="pageNum" value="1"/>
<input type="hidden" name="reNo" value="${param.reNo}"/>
<form:hidden path="sNo" value="${param.sNo}"/>
<form:hidden path="id" value="${sessionScope.loginMember.id}"/>

<div class="w3-container w3-margin">
	<h1 style="font-family:'Hanna'">리뷰 작성하기</h1>
</div>
<div class="w3-container w3-margin w3-border w3-round">
<table class="w3-table">
        <tr><td>
        <label style="font-family:'Hanna'; font-size:x-large;">내 용</label>
        <form:textarea rows="15" cols="80" path="content" class="w3-input w3-border"/></td></tr>
        <tr><td><font color="red"><form:errors path="content"/></font></td></tr>
		<tr><td>
		<div class="row">
		<div class="col-sm-4">
		<label style="font-family:'Hanna'; font-size:large;"><font style="color:gray;">${loginMember.id}</font>&nbsp;님의 평점은?</label>
			<form:select path="score" class="w3-select">
        		<option value="5"> 5점</option>
        		<option value="4"> 4점</option>
            	<option value="3"> 3점</option>
            	<option value="2"> 2점</option>
            	<option value="1"> 1점</option>
            </form:select>
        </div>
        <div class="col-sm-4">
        	<p><input type="file" name="img1File" accept="image/*"></p>
        	<p><input type="file" name="img2File" accept="image/*"></p>
        </div>
        <div class="col-sm-4">
        	<p><input type="file" name="img3File" accept="image/*"></p>
			<p><input type="file" name="img4File" accept="image/*"></p>
        </div>
        </div>
        </td></tr>
        <tr><td style="text-align:center;">
            <a href="javascript:document.f.submit()" class="btn btn-outline-primary" style="width:400px; font-family:'Hanna'">리뷰 등록</a>
        </td></tr>
	</table>
<div class="w3-container"><p>&nbsp;</p></div> <!-- 테이블 하단 공간 확보용 -->
</div>


</form:form>

</div>
<div class="w3-col s2"><p>&nbsp;</p></div> <!-- 좌우 공간 확보용 -->
</div>
</body>
</html>
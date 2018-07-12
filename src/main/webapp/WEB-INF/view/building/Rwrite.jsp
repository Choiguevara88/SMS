<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>리뷰 글 등록</title>
</head>
<body>
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
	<table border="1" cellpadding="0" cellspacing="0" align="center">
		<caption><h3>리 뷰</h3></caption>
		<tr><td colspan="2">제목</td>
			<td colspan="3"><form:input path="subject"/>
			<font color="red">
			<form:errors path="subject"/>
			</font></td>
		</tr>
		
		<tr><td align="center" colspan="5">내용</td></tr>
        <tr><td colspan="5"><form:textarea rows="15" cols="80" path="content"/><font color="red"><form:errors path="content"/></font></td></tr>
		<tr><td align="center" colspan="5">
			<form:select path="score">
        		<option value="5"> 5점</option>
        		<option value="4"> 4점</option>
            	<option value="3"> 3점</option>
            	<option value="2"> 2점</option>
            	<option value="1"> 1점</option>
            </form:select>
            <a href="javascript:document.f.submit()">[리뷰등록]</a>&nbsp;&nbsp;
	</table>
	<div><div align="center">사진(최대 4개)</div>
		 <div align="center">
		 	<input type="file" name="img1File" accept="image/*">
		 	<input type="file" name="img2File" accept="image/*">
		 </div><br>
		 <div align="center">
			 <input type="file" name="img3File" accept="image/*">
		 	<input type="file" name="img4File" accept="image/*">
		 </div>
	</div>
</form:form>

</body>
</html>
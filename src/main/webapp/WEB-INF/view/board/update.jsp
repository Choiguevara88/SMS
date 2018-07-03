<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /WebContent/WEB-INF/view/board/write.jsp --%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 글 수정</title>

</head>
<body>
<form:form modelAttribute="board" action="update.sms?bNo=${board.bNo}&pageNum=${param.pageNum}" method="post" enctype="multipart/form-data" name="f">
	<input type ="hidden" name ="file2" value="${board.img1}">
	<input type ="hidden" name = "bNo" value="${board.bNo}">
	
	<spring:hasBindErrors name="board">
		<font color="red">
			<c:forEach items="${errors.globalErrors}" var="error">
				<spring:message code="${error.code}" />
			</c:forEach>
		</font>
	</spring:hasBindErrors>
	
	<table border="1" cellpadding="0" cellspacing="0">
		<caption>SPRING 수정</caption>
		<tr><td align="center">글쓴이</td><td><form:input path="id"/><font color="red"><form:errors path="id"/></font></td></tr>
		<tr><td align="center">내용</td><td><form:textarea rows="15" cols="80" path="content"/><font color="red"><form:errors path="content"/></font></td></tr>
		
		<script type="text/javascript">
			function file_delete() {
				document.f.file2.value = "";				// file2의 값을 ""값으로 설정 = 값을 없앤다.
				file_desc.style.display = "none";			// 화면에서 보이지 않도록 속성을 설정하는 부분
				file1.style.display = "block";
			}
		</script>
	
		<tr><td align="center" colspan="2"><a href="javascript:document.f.submit()">[게시물수정]</a>&nbsp;&nbsp;<a href="listex.sms?pageNum=${param.pageNum}">[게시판목록]</a></td></tr>
	</table>
</form:form>

</body>
</html>
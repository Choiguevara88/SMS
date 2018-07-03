<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 수정</title>
<script type="text/javascript">
	function file_delete(a) {
		document.getElementById('file_desc'+a).style.display="none"; //보이지 않도록 설정
		document.getElementsByName('file'+a)[0].value = "";  //file1의 정보를 지우기.
	}
</script>
</head>
<body>
<form:form modelAttribute="board" action="update.sms" method="post" name="f" enctype="multipart/form-data">
  <spring:hasBindErrors name="board">
  	<font color="red">
  	  <c:forEach items="${errors.globalErrors}" var="error">
  	  	<spring:message code="${error.code}"/>
  	  </c:forEach>
  	</font>
  </spring:hasBindErrors>
  <input type="hidden" name="bNo" value="${board.bNo}">
  <input type="hidden" name="pageNum" value="${param.pageNum}">
  <input type="hidden" name="kind" value="${board.kind}">
  <input type="hidden" name="file1" value="${board.img1}">
  <input type="hidden" name="file2" value="${board.img2}">
  <input type="hidden" name="file3" value="${board.img3}">
  <input type="hidden" name="file4" value="${board.img4}">
  <input type="hidden" name="sNo" value="${board.sNo}">
  <table border="1" cellpadding="0" cellspacing="0" align="center">
  <caption>리뷰 수정</caption>
 <tr><td align="center">제목</td><td><form:input path="subject" value="${board.subject}"/>
  		<font color="red"><form:errors path="subject"/></font></td></tr>
  	<tr><td align="center">내용</td>
  		<td><form:textarea rows="15" cols="80" path="content" value="${board.content}"/>
  		<font color="red"><form:errors path="content"/></font></td></tr>
  	<tr><td align="center" colspan="2">
  	<form:select path="score">
        		<option value="5"> 5점</option>
        		<option value="4"> 4점</option>
            	<option value="3"> 3점</option>
            	<option value="2"> 2점</option>
            	<option value="1"> 1점</option>
            </form:select>
  		<a href="javascript:document.f.submit()">[수정]</a>
  		<a href="list.sms?sNo=${board.sNo}&kind=${board.kind}">[목록]</a>
  	</td></tr></table>
  	<div><div align="center">사진(최대 4개)</div>
		 <div align="center">
		 <c:if test="${!empty board.img1}">
  			<div id="file_desc1">
  				<a href="${path}/picture/${board.img1}">${board.img1}</a>
  				<a href="javascript:file_delete(1)">[첨부파일삭제]</a></div>
  	 	 </c:if>
		 		<input type="file" name="img1File"><br>
		 <c:if test="${!empty board.img2}">
  			<div id="file_desc2">
  				<a href="${path}/picture/${board.img2}">${board.img2}</a>
  				<a href="javascript:file_delete(2)">[첨부파일삭제]</a></div>
  		 </c:if>
		 		<input type="file" name="img2File"><br>
		  <c:if test="${!empty board.img3}">
  				<div id="file_desc3">
  					<a href="${path}/picture/${board.img3}">${board.img3}</a>
  					<a href="javascript:file_delete(3)">[첨부파일삭제]</a></div>
  	 		 </c:if>
		 			<input type="file" name="img3File"><br>
		 	 <c:if test="${!empty board.img4}">
  				<div id="file_desc4">
  					<a href="${path}/picture/${board.img4}">${board.img4}</a>
  					<a href="javascript:file_delete(4)">[첨부파일삭제]</a></div>
  			  </c:if>
		 	<input type="file" name="img4File">
		 </div>
	</div>
	</form:form>
</body>
</html>
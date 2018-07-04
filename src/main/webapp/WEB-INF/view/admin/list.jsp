<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1:1문의 목록보기</title>
<script type="text/javascript">
	function list(pageNum) {
		location.href="list.sms?pageNum=" + pageNum +"&sNo="+${param.sNo}+"&kind="+${param.kind};
		return false;
	}
</script>
</head>
<body>
<div align="center"><a href="../qa/write.sms">질문 작성하기</a></div>
<!--  리뷰 목록부분 -->
<table border="1" style="margin-top:30px" width="80%" align="center">

<c:if test="${listcount > 0}">
<c:forEach var="board" items="${boardlist}">
	<tr>
		<td width="50%">${board.subject}</td></tr>
	<tr><td>${board.content}</td> 
	</tr>
	<tr>
		<td colspan="4"><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd H:mm:ss"/></td>
	<tr>
  		<td colspan="4" align="center" class="content">
		<a href="../admin/update.sms?bNo=${board.bNo}&pageNum=${pageNum}">[수정]</a>
		<a href="../admin/delete.sms?bNo=${board.bNo}&pageNum=${pageNum}">[삭제]</a><br>
 	 </td>
</tr>
<!--  글 밑 부분 -->
</c:forEach>
<tr align="center" height="26"><td colspan="5">
		<c:if test="${pageNum >1 }">
			<a href="javascript:list(${pageNum -1 })"> [이전]</a>
		</c:if>&nbsp;
		<c:if test="${pageNum <= 1}">[이전]</c:if>&nbsp;
		<c:forEach var="a" begin="${startpage }" end="${endpage}"> 
			<c:if test="${a == pageNum}">[${a}]</c:if>
			<c:if test="${a != pageNum}">
				<a href="javascript:list(${a})">[${a}]</a></c:if>
		</c:forEach>
			<c:if test="${pageNum < maxpage}">
				<a href="javascript:list(${pageNum+1 })">[다음]</a>
			</c:if>&nbsp;
			<c:if test="${pageNum >= maxpage}">[다음]</c:if>&nbsp;
			</td></tr>
	</c:if>
<c:if test="${listcount == 0}">
	 <tr><td colspan="5">등록된 문의가 없습니다.</td></tr>
	 </c:if>
</table>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>목록보기</title>
<script type="text/javascript">
	function list(pageNum) {
		location.href="list.sms?pageNum=" + pageNum;
		return false;
	}
</script>
</head>
<body>

<div>
	<div style="float:left; display:inliine;">
	<h4 class="h_intro">이용 후기 <strong><em>${listcount}</em>개</strong><span></span>
						평균 평점 <strong><em>${avgScore}</em></strong>
	</h4></div>
	<span style="float:right">
	<a href="../review/write.sms"> 리뷰 작성하기</a>
	</span>
</div>
<!--  리뷰 목록부분 -->
<table border="1" style="margin-top:30px" width="80%" align="center">

<c:if test="${listcount > 0}">
<c:forEach var="board" items="${boardlist}" varStatus="i">
	<tr>
		<td>${board.id}</td>
		<td align="right"><select>
        		<option value="5"> 5점</option>
        		<option value="4"> 4점</option>
            	<option value="3"> 3점</option>
            	<option value="2"> 2점</option>
            	<option value="1"> 1점</option>
            </select>
        </td>
	<tr>
		<td colspan="2">${board.content}</td>
	</tr>
	<tr>
		<td colspan="2">${board.regDate}</td>
	<tr id="${i.count}-view">
  		<td colspan="3" align="center" class="content">
		<a href="../notice/update.sms?bNo=${board.bNo}&pageNum=${pageNum}">[수정]</a>
		<a href="../notice/delete.sms?bNo=${board.bNo}&pageNum=${pageNum}">[삭제]</a><br>
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
	 <tr><td colspan="5">등록된 게시물이 없습니다.</td></tr>
	 </c:if>
</table>

</body>
</html>
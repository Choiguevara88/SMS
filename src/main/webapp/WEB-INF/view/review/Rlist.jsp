<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>목록보기</title>
<script type="text/javascript">
	function list(pageNum) {
		location.href="list.sms?pageNum=" + pageNum +"&sNo="+${param.sNo}+"&kind="+${param.kind};
		return false;
	}
</script>
<script type="text/javascript">			
	function roundXL(n, digits) {
		  if (digits >= 0) return parseFloat(n.toFixed(digits)); // 소수부 반올림

		  digits = Math.pow(10, digits); // 정수부 반올림
		  var t = Math.round(n * digits) / digits;

		  return parseFloat(t.toFixed(0));
		}
</script>
</head>
<body>

<div>
	<div style="float:left; display:inliine;">
	<h4>이용 후기 <strong><em>${listcount}</em>개</strong><span></span>
						평균 평점 <strong><em>
						<script>
						document.write(roundXL(${avgScore},1))
						</script></em></strong>
	</h4></div>
	<span style="float:right">
	</span>
</div>
<!--  리뷰 목록부분 -->
<table border="1" style="margin-top:30px" width="80%" align="center">

<c:if test="${listcount > 0}">
<c:forEach var="board" items="${boardlist}" varStatus="i">
	<tr>
		<td colspan="2" width="50%">${board.subject}</td>
		<td>${board.id}</td>
		<td align="right">${board.score}</td>
	</tr>
	<tr>
		<td colspan="4">${board.content}</td></tr>
	<tr><td><img src="${path }/picture/${board.img1}" style="width:25%; height:300px"></td>
		<td><img src="${path }/picture/${board.img2}" style="width:25%; height:300px"></td>
		<td><img src="${path }/picture/${board.img3}" style="width:25%; height:300px"></td>
		<td><img src="${path }/picture/${board.img4}" style="width:25%; height:300px"></td>
	</tr>
	<tr>
		<td colspan="4"><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd H:mm:ss"/></td>
	<tr>
  		<td colspan="4" align="center" class="content">
  		<c:if test="${sessionScope.loginMember.id == building.id && board.sNo == building.sNo }">
  		<a href="../review/reply.sms?bNo=${board.bNo}&pageNum=${pageNum}">[답변]</a></c:if>
  		<c:if test="${sessionScope.loginMember.id == board.id }">
		<a href="../review/Rupdate.sms?bNo=${board.bNo}&pageNum=${pageNum}">[수정]</a>
		<a href="../review/delete.sms?bNo=${board.bNo}&pageNum=${pageNum}">[삭제]</a></c:if><br>
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
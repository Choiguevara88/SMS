<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	function list(pageNum) {
		location.href="Rlist.sms?pageNum=" + pageNum +"&sNo="+${param.sNo};
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
<script type="text/javascript">
	function win_openR(num)	{
		var op = "width=800,height=700,scrollbars=yes,left=50,top=150";
			window.open("../building/Rreply.sms?bNo="+num,"reply",op);
	}
</script>
<div>
	<div style="float:left; display:inliine;">
	<h4>이용 후기 <strong><em>${listcount}</em>개</strong><span></span>
						평균 평점 <strong><em>
	<div id="roundavg" style="display:inline"></div>					
	<script>
	roundavg.innerHTML =roundXL(${avgScore},1)
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
		<td>${board.id}</td>
		<td align="right">${board.score}</td>
	</tr>
	<tr>
		<td colspan="4">내용 : ${board.content}</td></tr>
	<c:if test="${board.refLevel == 0 }">
	<tr><td><img src="${path }/picture/${board.img1}" style="width:25%; height:300px"></td>
		<td><img src="${path }/picture/${board.img2}" style="width:25%; height:300px"></td>
		<td><img src="${path }/picture/${board.img3}" style="width:25%; height:300px"></td>
		<td><img src="${path }/picture/${board.img4}" style="width:25%; height:300px"></td>
	</tr></c:if>
	<tr>
		<td colspan="3"><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd H:mm:ss"/></td>
  		<td align="center" >
  		<c:if test="${sessionScope.loginMember.id == building.id && board.sNo == building.sNo }">
  		<c:if test="${board.refLevel == 0  }">
  		<c:if test="${board.qType == 0 }">
  		<a href="javascript:win_openR('${board.bNo}')">[답변]</a></c:if></c:if></c:if>
</tr>
<!--  글 밑 부분 -->
</c:forEach>
<tr align="center" height="26"><td colspan="5">
		<c:if test="${pageNum >1 }">
			<a href="javascript:listQlist(${pageNum -1 })"> [이전]</a>
		</c:if>&nbsp;
		<c:if test="${pageNum <= 1}">[이전]</c:if>&nbsp;
		<c:forEach var="a" begin="${startpage }" end="${endpage}"> 
			<c:if test="${a == pageNum}">[${a}]</c:if>
			<c:if test="${a != pageNum}">
				<a href="javascript:listRlist()">[${a}]</a></c:if>
		</c:forEach>
			<c:if test="${pageNum < maxpage}">
				<a href="javascript:listRlist(${pageNum+1 })">[다음]</a>
			</c:if>&nbsp;
			<c:if test="${pageNum >= maxpage}">[다음]</c:if>&nbsp;
			</td></tr>
	</c:if>
<c:if test="${listcount == 0}">
	 <tr><td colspan="5">등록된 게시물이 없습니다.</td></tr>
	 </c:if>
</table>

<%@page import="logic.Board"%>
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
	<h4>이용 후기 <strong class="w3-text-deep-purple"><em>${listcount}</em>개</strong><span></span>
						평균 평점 <strong><em>
	<div id="roundavg" style="display:inline" class="w3-text-deep-purple"></div>					
	<script>
	roundavg.innerHTML =roundXL(${avgScore},1)
	</script></em></strong>
	</h4></div>
	<span style="float:right" >
	</span>
</div>
<!--  리뷰 목록부분 -->
<br>
<table style="margin-top:30px" width="80%" align="center">

<c:if test="${listcount > 0}">
<c:forEach var="board" items="${boardlist}" varStatus="i">
<hr size="1" style="color:red">
	<div><b>
	<c:if test="${board.refLevel == 0 }">
		<span class="w3-xlarge w3-text-darkgrey">${board.id}</span></c:if>
		<c:if test="${board.refLevel != 0 }">
		<div class="w3-xlarge">${building.id}님의 답글입니다.</div>
		</c:if></b>
		<c:if test="${board.refLevel == 0 }">
		<span style="float:right;">
		<c:forEach begin="1" end="${board.score}"><i class="fa fa-thumbs-up" style="font-size:24px;"></i></c:forEach>
		<c:forEach begin="${board.score+1}" end="5"><i class="fa fa-thumbs-o-up" style="font-size:24px;"></i></c:forEach>
		 </span>
		</c:if>
	</div>
	<div>
		<div class="w3-large w3-text-deepgray"> ${board.content}</div></div>
	<c:if test="${board.refLevel == 0 }">
	<div><span><img src="${path }/picture/${board.img1}" style="width:150px; height:150px"></span>
		<span><img src="${path }/picture/${board.img2}" style="width:150px; height:150px"></span>
		<span><img src="${path }/picture/${board.img3}" style="width:150px; height:150px"></span>
		<span><img src="${path }/picture/${board.img4}" style="width:150px; height:150px"></span>
	</div></c:if>
	<div>
		<div class="w3-medium w3-text-grey"><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd H:mm:ss"/></div>
  		<div align="center" >
  		<c:if test="${sessionScope.loginMember.id == building.id && board.sNo == building.sNo }">
  		<c:if test="${board.refLevel == 0  }">
  		<c:if test="${board.qType == 0 }">
  		<a href="javascript:win_openR('${board.bNo}')">[답변]</a></c:if></c:if></c:if>
</div>
</div>
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

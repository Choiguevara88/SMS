<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	function win_openR(num)	{
		var op = "width=800,height=850,scrollbars=yes,left=50";
			window.open("../building/Rreply.sms?bNo="+num,"reply",op);
	}
</script>

<div class="w3-container">
	<div class="w3-leftbar w3-border-deep-purple w3-left"><h3>&nbsp;&nbsp;이용 후기 <strong class="w3-text-deep-purple"><em>${listcount}</em>개</strong><span></span>
	</h3>
	</div>
	
	<div class="w3-right"><h3>
		평균 평점 <strong><em style="display:inline" class="w3-text-deep-purple">
	${avgScore}</em></strong>
	&nbsp;&nbsp;</h3></div>
</div>
<hr>
<!--  리뷰 목록부분 -->
<div class="w3-container">
<table width="80%" align="center">
<c:if test="${listcount > 0}">
<c:forEach var="board" items="${boardlist}" varStatus="i">
	<div><b>
	<c:if test="${board.refLevel == 0 }">
		<span class="w3-xlarge w3-text-darkgrey">${board.id}</span></c:if>
		<c:if test="${board.refLevel != 0 }">
		<div class="w3-xlarge">└─${building.id}님의 답글입니다.</div>
		</c:if></b>
		<c:if test="${board.refLevel == 0 }">
		<span style="float:right;">
		<c:forEach begin="1" end="${board.score}"><i class="fa fa-thumbs-up w3-text-deep-purple" style="font-size:24px;"></i>&nbsp;</c:forEach>
		<c:forEach begin="${board.score+1}" end="5"><i class="fa fa-thumbs-o-up w3-text-deep-purple" style="font-size:24px;"></i>&nbsp;</c:forEach>
		 </span>
		</c:if>
	</div>
	<div>
		<div class="w3-large w3-text-deepgray"> ${board.content}</div></div>
	<c:if test="${board.refLevel == 0 }">
	<div>
	<c:if test="${board.img1 != null }">
	<span><img src="${path }/picture/${board.img1}" style="width:150px; height:150px"></span></c:if>
	<c:if test="${board.img2 != null }">
	<span><img src="${path }/picture/${board.img2}" style="width:150px; height:150px"></span></c:if>
	<c:if test="${board.img3 != null }">
	<span><img src="${path }/picture/${board.img3}" style="width:150px; height:150px"></span></c:if>
	<c:if test="${board.img4 != null }">
	<span><img src="${path }/picture/${board.img4}" style="width:150px; height:150px"></span></c:if>
	</div></c:if>
	<div>
		<div class="w3-medium w3-text-grey"><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd H:mm:ss"/></div>
  		<span style="float:right; display: inline;">
  		<c:if test="${sessionScope.loginMember.id == building.id && board.sNo == building.sNo }">
  		<c:if test="${board.refLevel == 0  }">
  		<c:if test="${board.qType == 0 || board.qType == null}">
  		<a href="javascript:win_openR('${board.bNo}')">[답변]</a></c:if></c:if></c:if>
</span>
</div>
<hr>
<!--  글 밑 부분 -->
</c:forEach>
<tr align="center" height="26"><td colspan="5">
		<c:if test="${pageNum >1 }">
			<a href="javascript:listRlist(${pageNum -1 })"> [이전]</a>
		</c:if>&nbsp;
		<c:if test="${pageNum <= 1}">[이전]</c:if>&nbsp;
		<c:forEach var="a" begin="${startpage }" end="${endpage}"> 
			<c:if test="${a == pageNum}">[${a}]</c:if>
			<c:if test="${a != pageNum}">
				<a href="javascript:listRlist(${a})">[${a}]</a></c:if>
		</c:forEach>
			<c:if test="${pageNum < maxpage}">
				<a href="javascript:listRlist(${pageNum+1 })">[다음]</a>
			</c:if>&nbsp;
			<c:if test="${pageNum >= maxpage}">[다음]</c:if>&nbsp;
			</td></tr>
	</c:if>
<c:if test="${listcount == 0}">
<tr><td>&nbsp;</td></tr>
등록된 게시물이 없습니다.
</c:if>
</table>
</div>
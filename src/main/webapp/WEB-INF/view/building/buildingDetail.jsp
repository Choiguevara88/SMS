<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공간 정보 보기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	/*태그 관련 스크립트*/
	var tagih = "";
	var tagstag = document.getElementById("tags");
	var tag = "";
	<c:forEach items="${building.sTagList}" var="item">
	tag= ("${item}");
	tagih+="<span class='w3-tag w3-white w3-round-xlarge w3-border w3-border-gray w3-center'>#"
	+tag+"</span>&nbsp;&nbsp;"
	</c:forEach>
	tagstag.innerHTML = tagih;
	
	/*시설안내 스크립트*/
	var infoSubih="";
	var infoSubstag = document.getElementById("infoSubs");
	var infoSub = "";
	var infoSubidx = 1;
	<c:forEach items="${building.sInfoSubList}" var="item">
	infoSub = ("${item}");
	infoSubih+="<div>"+infoSubidx+". "+infoSub+"</div>&nbsp;&nbsp;"
	infoSubidx++;
	</c:forEach>
	infoSubstag.innerHTML = infoSubih;
	
	/*이용규칙 스크립트*/
	var ruleih="";
	var ruletag = document.getElementById("rules");
	var rule = "";
	var ruleidx = 1;
	<c:forEach items="${building.sRuleList}" var="item">
	rule = ("${item}");
	ruleih+="<div>"+ruleidx+". "+rule+"</div>&nbsp;&nbsp;"
	ruleidx++;
	</c:forEach>
	ruletag.innerHTML = ruleih;
	
});
</script>
</head>
<body>
<!-- 공간(Building)헤더정보 -->
<img src="../picture/${building.sImg1}" style="width:100% ; height:300px">
<div><h1>${building.sName}</h1></div>
<div><h4>${building.sPreview}</h4></div>
<div id="tags">
<!-- 태그가 보여질 곳 -->
</div>
<hr>

<!-- 공간상세, Q&A, 댓글(리뷰) start -->
<div class="w3-row">
<div class="w3-col s7">

<!-- 공간(Building)상세정보 -->
<div>
<div class="w3-panel w3-leftbar w3-border-purple">
<h3>공간소개</h3>
</div>
<div>${building.sContent}</div>
<hr>

<%-- <div>${building.sTypeList}</div> --%>
<div class="w3-panel w3-leftbar w3-border-purple">
<h3>시설안내</h3>
</div>
<div id="infoSubs">
<!-- 시설안내가 보여질 곳 -->
</div>
<hr>

<div class="w3-panel w3-leftbar w3-border-purple">
<h3>이용규칙</h3>
</div>
<div id = "rules">
<!-- 이용안내가 보여질 곳 -->
</div>
<hr>

<div class="w3-panel w3-leftbar w3-border-purple">
<h3>이용시간</h3>
</div>
<div>${building.sHDay}</div>
<div>${building.sBHourList}</div>
<hr>

<div class="w3-panel w3-leftbar w3-border-purple">
<h3>주소</h3>
</div>
<div>${building.sAddress}</div>
</div>
<hr size="1">

<!-- 이용후기, Q&A -->
<div>
	<div style="float:left; display:inliine;">
	<h4 class="h_intro">이용 후기 <strong><em>${listcount}</em>개</strong><span></span>
						평균 평점 <strong><em>${avgScore}</em></strong>
	</h4></div>
	<span style="float:right">
	</span>
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
</div>
</div>

<!-- 세부공간(Room)정보 -->
<div class="w3-col s5">
<h1>룸정보가 들어올 공간</h1>
</div>
</div>
</body>
</html>
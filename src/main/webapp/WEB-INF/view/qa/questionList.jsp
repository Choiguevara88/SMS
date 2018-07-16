<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1:1 문의</title>
<style type="text/css">
#main{
background: #ADD8E6; 
}
.balloon {
    display: inline-block;
    position: relative;
    background: #F8F8FF;
    width: 500px;
    margin: 0 auto 10px;
    border-radius: 10px;
    border: 1px solid gray;
    padding: 10px;
}
.balloon1 {
    display: inline-block;
    position: relative;
    background: #EEE8AA;
    width: 500px;
    margin: 0 auto 10px;
    border-radius: 10px;
    border: 1px solid gray;
    padding: 10px;
    text-vertical-align:middle;
}
 .writer {
    display: inline-block;
    position: relative;
    height: 30px;
    width: 280px;
    margin: 0 auto 10px;
}
</style>
</head> 
<body>


<div class="w3-container w3-margin">
<h1 align="center" style="font-family:'Hanna';">${loginMember.id}님 반갑습니다! 무엇을 도와드릴까요?</h1>

<div class="w3-container w3-margin w3-padding w3-border w3-round w3-row w3-text-dark-gray" style="text-align:center; font-family:'Hanna'; font-size:x-large;">
	<div class="w3-col s6">	※사이트에 관한 건의 및 불만사항	</div>
	<div class="w3-col s6">	※허위 공간이나 사기 거래 등 신고	</div> 
</div>
</div>
<hr size="1">
<div id="main" class="w3-container w3-margin w3-padding-24 w3-round">

<% pageContext.setAttribute("newLineChar","\n"); %>
<c:forEach var="board" items="${list}">

<c:if test="${board.refLevel != 0 }">
<div align="left">
<c:set var="str" value="${fn:replace(board.content,'  ','&nbsp;&nbsp;') }"/>
<c:set var="str" value="${fn:replace(board.content,newLineChar,'<br>') }"/>
				
	<div class="balloon" style="vertical-align:middle; font-family:'Hanna'; font-size:large;" class="w3-container">${str}</div><br>
	<div class="writer" style="font-family:'Hanna';">
		<font style="font-size:large">관리자</font>
		<font class="w3-text-gray">
		(<fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd a hh:mm:ss"/>)
		</font>
	</div>
	</div>
</c:if>


<c:if test="${board.refLevel == 0 }">
<div align="right">
<c:set var="str" value="${fn:replace(board.content,'  ','&nbsp;&nbsp;') }"/>
<c:set var="str" value="${fn:replace(board.content,newLineChar,'<br>') }"/>
	<div>
		<div class="balloon1" style="vertical-align:middle; font-family:'Hanna'; font-size:large;" class="w3-container">${str}</div>
		<br>
		<div class="writer" style="font-family:'Hanna';">
			<font style="font-size:large">${board.id}</font>
			<font class="w3-text-gray">
			(<fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd a hh:mm:ss"/>)
			</font>
			<c:if test="${board.qType == 0 }">
			<font class="w3-text-red">
				(미답변)
			</font>
			</c:if>
			
			<c:if test="${board.qType == 1 }">
			<font class="w3-text-blue">
				(답변완료)
			</font>
			</c:if>
		</div>
		</div>
</div>
	</c:if>
	</c:forEach>
</div>

</body>
</html>

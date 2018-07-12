<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    height: 70px;
    width: 600px;
    margin: 0 auto 10px;
    border-radius: 10px;
    border: 1px solid gray;
    padding: 10px;
}
.balloon1 {
    display: inline-block;
    position: relative;
    background: #EEE8AA;
    height: 70px;
    width: 600px;
    margin: 0 auto 10px;
    border-radius: 10px;
    border: 1px solid gray;
    padding: 10px;
}
 .writer {
    display: inline-block;
    position: relative;
    height: 30px;
    width: 280px;
    margin: 0 auto 10px;
}
h1 {
	align: center;
    display: block;
    position: relative;
    height: 60px;
    width: 650px;
    margin: 0 auto 10px;
    
}
h3 {
	align: center;
    display: block;
    position: relative;
    height: 70px;
    width: 400px;
    margin: 0 auto 10px;
} 
</style>
</head> 
<body>
<h1>Guest,Host님을 위한 문의 게시판입니다.</h1>
<h3>※사이트에 관한 건의 및 불만사항<br>
	※허위 공간이나 사기 거래등 신고 </h3>
<hr size="1">
<div id="main">
<br>
<% pageContext.setAttribute("newLineChar","\n"); %>
	<c:forEach var="board" items="${list}">
	<c:if test="${board.refLevel != 0 }">
<div align="left">
	<c:set var="str" value="${fn:replace(board.content,'  ','&nbsp;&nbsp;') }"/>
	<c:set var="str" value="${fn:replace(board.content,newLineChar,'<br>') }"/>
		<div>
			<div class="writer">관리자 
			(<fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd a hh:mm:ss"/>)<br>
		</div>
		<div class="balloon">
		${str}
		</div>
		</div>
</div>
	</c:if>
	<c:if test="${board.refLevel == 0 }">
<div align="right">
	<c:set var="str" value="${fn:replace(board.content,'  ','&nbsp;&nbsp;') }"/>
	<c:set var="str" value="${fn:replace(board.content,newLineChar,'<br>') }"/>
		<div>
		<div class="balloon1">
		${str}
		</div>
			<div class="writer">${board.id} 
			(<fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd a hh:mm:ss"/>)<br>
		</div>
		</div>
</div>
	</c:if>
	</c:forEach>
</div>	
</body>
</html>

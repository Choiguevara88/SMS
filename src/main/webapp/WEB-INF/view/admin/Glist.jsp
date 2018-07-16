<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Guest 문의 목록</title>
<script type="text/javascript">
	function list(pageNum) {
		if(searchType == null || searchType.length == 0) {
			document.searchform.searchContent.value = "";
			document.searchform.pageNum.value = "1";
			location.href="Glist.sms?pageNum=" + pageNum+"&kind="+ 1;
		}else{
			document.searchform.pageNum.value = pageNum;
			document.searchform.submit();
			return true;
		}
		return false;
	}
</script>
<style type="text/css">
#main{
background: #ADD8E6; 
}
.balloon {
    display: inline-block;
    position: relative;
    background: #EEE8AA;
    height: 70px;
    width: 550px;
    margin: 0 auto 10px;
    border-radius: 10px;
    border: 1px solid gray;
    padding: 10px;
}
.balloon1 {
    display: inline-block;
    position: relative;
    background: #F8F8FF;
    height: 70px;
    width: 550px;
    margin: 0 auto 10px;
    border-radius: 10px;
    border: 1px solid gray;
    padding: 10px;
}
 .writer {
    display: inline-block;
    position: relative;
    height: 30px;
    width: 400px;
    margin: 0 auto 10px;
}
</style>
</head>
<body>
<br>
<table width="80%" align="center" border="1" style="margin-top:10px">
	<tr><td colspan="5" align="center">
		<form action="Glist.sms" method="post" name="searchform" onsubmit="return list(1)" >
			<label for="notice_txt">Guest Q&A 검색</label>
			<input type="hidden" name="pageNum" value="1">
			<select name="searchType" id="searchType">
				<option value="">선택하세요</option>
				<option value="id">아이디</option>
				<option value="content">내용</option>
			</select>&nbsp;
		<script type="text/javascript">
			if('${param.searchType}' != '') {
				document.getElememntById("searchType").value='${param.searchType}'
			}
		</script>
<input type="text" name="searchContent" value="${param.searchContent}">
<input type="submit" value="검색">
		</form></td></tr></table>
		
<% pageContext.setAttribute("newLineChar","\n"); %>
	<c:forEach var="board" items="${gList}">
	<c:if test="${board.refLevel != 0 }">
<div align="left">
	<c:set var="str" value="${fn:replace(board.content,'  ','&nbsp;&nbsp;') }"/>
	<c:set var="str" value="${fn:replace(board.content,newLineChar,'<br>') }"/>
		<div>
			<div class="writer">관리자 (->${board.id})
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
			(<fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd a hh:mm:ss"/>)
			<c:if test="${board.qType != 1 }">
			<a href="adminAnswerQuestion.sms?bNo=${board.bNo}">답변하기</a><br></c:if>
		</div>
		</div>
</div>
	</c:if>
	<hr size="1">
	</c:forEach>
	
</body>
</html>
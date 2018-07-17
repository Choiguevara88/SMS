<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언-->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Host 문의 목록</title>

<script type="text/javascript">
	function list(pageNum) {
		if(searchType == null || searchType.length == 0) {
			document.searchform.searchContent.value = "";
			document.searchform.pageNum.value = "1";
			location.href="Hlist.sms?pageNum=" + pageNum+"&kind="+ 1;
		}else{
			document.searchform.pageNum.value = pageNum;
			document.searchform.submit();
			return true;
		}
		return false;
	}
	
	function win_open(num)	{
		var op = "width=800,height=850,scrollbars=yes,left=50";
			window.open("adminAnswerQuestion.sms?bNo=" + num, "HostAnswer", op);
	}
	
</script>

<style type="text/css">
#main{
	background: #ADD8E6;
	font-family:'Hanna';
}
.balloon {
    display: inline-block;
    position: relative;
    background: #EEE8AA;
    width: 500px;
    margin: 0 auto 10px;
    border-radius: 10px;
    border: 1px solid gray;
    padding: 10px;
    font-size:large;
}
.balloon1 {
    display: inline-block;
    position: relative;
    background: #F8F8FF;
    width: 500px;
    margin: 0 auto 10px;
    border-radius: 10px;
    border: 1px solid gray;
    padding: 10px;
    font-size:large;
}
 .writer {
    display: inline-block;
    position: relative;
    height: 30px;
    width: 500px;
    margin: 0 auto 10px;
}
</style>
</head>
<body>
<div class="w3-container w3-margin w3-row">
<div class="w3-col s2"><p>&nbsp;</p></div>
<div class="w3-col s8">





<div class="w3-container w3-margin">
<h2 style="font-family:'Hanna';">Host 계정 문의 내역</h2>
<form action="Hlist.sms" method="post" name="searchform" onsubmit="return list(1)" >
<div class="w3-container w3-margin w3-border w3-round" style="font-family:'Hanna';">
<div class="w3-col s2 w3-margin" align="center">
	<input type="hidden" name="pageNum" value="1">
	
	<select name="searchType" id="searchType" class="w3-select">
	<option value="">Q&#38;A검색</option>
	<option value="id">아이디</option>
	<option value="content">내용</option>
	</select>
	
	<script type="text/javascript">
		if('${param.searchType}' != '') {
			document.getElememntById("searchType").value='${param.searchType}'
		}
	</script>
</div>
<div class="w3-col s6 w3-margin">
	<input type="text" name="searchContent" value="${param.searchContent}" class="w3-input">
</div>
<div class="w3-col s2 w3-margin">
	<input type="submit" value="검 색" class="btn btn-outline-primary btn-block">
</div>

</div>
</form>


</div>
<div id="main" class="w3-container w3-margin w3-padding w3-round">
<% pageContext.setAttribute("newLineChar","\n"); %>
	<c:forEach var="board" items="${hList}">
	<c:if test="${board.refLevel != 0 }">
<div align="left">
	<c:set var="str" value="${fn:replace(board.content,'  ','&nbsp;&nbsp;') }"/>
	<c:set var="str" value="${fn:replace(board.content,newLineChar,'<br>') }"/>
		<div class="balloon">
		${str}
		</div>
		<div>
			<div class="writer">${board.id}님께 답변
			<font class="w3-text-gray">
			(<fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd a hh:mm:ss"/>)<br>
			</font>
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
			<div class="writer">
			<font style="font-size:large">
			${board.id}
			</font>
			<font class="w3-text-gray"> 
			(<fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd a hh:mm:ss"/>)
			</font>
			
			<c:if test="${board.qType == 0 }">
			<font class="w3-text-red">
				(미답변)
			</font>
			<a href="javascript:win_open('${board.bNo}')" style="font-family:'Hanna';">[답변달기]</a>
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



</div>
<div class="w3-col s2"><p>&nbsp;</p></div>
</div>
</body>
</html>
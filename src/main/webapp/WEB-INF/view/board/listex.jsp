<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 목록</title>
<script type="text/javascript">
	function list(pageNum) {
		if(searchType == null || searchType.length == 0) {
			document.searchform.searchContent.value = "";
			document.searchform.pageNum.value = "1";
			location.href="list.sms?pageNum=" + pageNum;
		}else{
			document.searchform.pageNum.value = pageNum;
			documetn.searchform.submit();
			return true;
		}
		return false;
	}
</script>

</head>
<body>
<table width="100%" border="1" cellpadding="0" cellspacing="0">
<!--  글 위 검색부분 -->
	<tr><td colspan="5" align="center">
		<form action="list.sms" method="post" name="searchform" onsubmit="return list(1)">
			<input type="hidden" name="pageNum" value="1">
			<select name="searchType" id="searchType">
				<option value="">선택하세요</option>
				<option value="subject">제목</option>
				<option value="name">글쓴이</option>
				<option value="content">내용</option>
			</select>&nbsp;
		<script type="text/javascript">
			if('${param.searchType}' != ''){
				document.getElememntById("searchType").value='${param.searchType}'
			}
		</script>
<input type="text" name="searchContent" value="${param.searchContent}">
<input type="submit" value="검색">
		</form></td></tr>
		
	<!--  글 본문 -->
	<c:if test="${listcount > 0}">
		<tr align="center" valign="middle">
			<td colspan="4"> 기본 게시판 </td><td>글 갯수:${listcount}</td>
		</tr>
		<tr align="center" valign="middle" bordercolor="#212121">
			<th width="8%" height="26">번호</th>
			<th width="50%" height="26">제목</th>
			<th width="14%" height="26">글쓴이</th>
			<th width="17%" height="26">날짜</th>
			<th width="11%" height="26">조회수</th>
		</tr>
		<!--  forEach 문안에서 boardlist -> board로 변수 설정  -->
		<c:forEach var="board" items="${boardlist}">
			<tr align="center" valign="middle">
			<td height="23">${boardcnt}</td>
			<c:set var="boardcnt" value="${boardcnt -1 }"/>
			<td align="left">
			<!-- 답글 표시 -->
			<c:forEach begin="1" end="${board.refLevel}">&nbsp;&nbsp;&nbsp;</c:forEach>
			<c:if test="${board.refLevel>0 }">└	</c:if>
			<a href="detail.sms?bNo=${board.bNo}&pageNum=${pageNum}">TESTBOARD</a></td>
			<td align="left">${board.id}</td>
			<td align="center">${board.regDate}</td>
			<td align="left">${board.score}</td></tr>
			</c:forEach>
		<!--  글 밑부분  -->
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
				<a href="javascript:list(${pageNum+1})">[다음]</a>
			</c:if>&nbsp;
			<c:if test="${pageNum >= maxpage}">[다음]</c:if>&nbsp;
			</td></tr>
	</c:if>
	<c:if test="${listcount == 0}">
	 <tr><td colspan="5">등록된 게시물이 없습니다.</td></tr>
	 </c:if>
	 <tr><td align="right" colspan="5">
	 	<a href="write.sms">[글쓰기]</a></td></tr>
</table>
</body>
</html>
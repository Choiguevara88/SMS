<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>목록보기</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	function list(pageNum) {
		if(searchType == null || searchType.length == 0) {
			document.searchform.searchContent.value = "";
			document.searchform.pageNum.value = "1";
			location.href="list.sms?pageNum=" + pageNum+"&kind="+ 1;
		}else{
			document.searchform.pageNum.value = pageNum;
			document.searchform.submit();
			return true;
		}
		return false;
	}
</script>
<style>
.accordion {
    background-color: #eee;
    color: #444;
    cursor: pointer;
    padding: 18px;
    width: 100%;
    border: none;
    text-align: left;
    outline: none;
    font-size: 15px;
    transition: 0.4s;
}
.active, .accordion:hover {
    background-color: #ccc;
}
.accordion:after {
    content: '\002B';
    color: #777;
    font-weight: bold;
    float: right;
    margin-left: 5px;
}
.active:after {
    content: "\2212";
}
.panel {
    padding: 0 18px;
    background-color: white;
    max-height: 0;
    overflow: hidden;
    transition: max-height 0.2s ease-out;
}
</style>
</head>
<body>
<c:if test="${sessionScope.loginMember.id == 'admin' }">
<div align="center"><a href="../notice/write.sms">[작성]</a></div></c:if>
<table width="80%" align="center" border="1" style="margin-top:10px">
	<tr><td colspan="5" align="center">
		<form action="list.sms" method="post" name="searchform" onsubmit="return list(1)" >
			<label for="notice_txt">공지사항 검색</label>
			<input type="hidden" name="pageNum" value="1">
			<select name="searchType" id="searchType">
				<option value="">선택하세요</option>
				<option value="subject">제목</option>
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
		
		
<table border="1" style="margin-top:30px" width="80%" align="center">
<c:if test="${listcount > 0}">
<c:forEach var="board" items="${boardlist}" varStatus="i">
	<button class="accordion">
		<span>공지사항</span>
		<span>
		<span style="float:center; display: inline;">${board.subject}</span>
		<span style="float:right"><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd"/></span></span>
	</button>
	<div class="panel">
  		<div align="center" class="content">
  		<c:if test="${sessionScope.loginMember.id == 'admin' }">
		<a href="../notice/update.sms?bNo=${board.bNo}&pageNum=${pageNum}">[수정]</a>
		<a href="../notice/delete.sms?bNo=${board.bNo}&pageNum=${pageNum}">[삭제]</a><br></c:if>
   		 ${board.content}
 	 </div>
</div>
</c:forEach>
<script>
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
  acc[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var panel = this.nextElementSibling;
    if (panel.style.maxHeight){
      panel.style.maxHeight = null;
    } else {
      panel.style.maxHeight = panel.scrollHeight + "px";
    } 
  });
}
</script>
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
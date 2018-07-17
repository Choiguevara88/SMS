<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언 -->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

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
<div class="w3-row">
<div class="w3-col s2"><p>&nbsp;</p></div><!-- 좌우 공간 확보 용  -->

<div class="w3-col s8">
<form action="list.sms" method="post" name="searchform" onsubmit="return list(1)" >
<input type="hidden" name="pageNum" value="1">
<div class="w3-container w3-margin">
	<table class="w3-table w3-border w3-round">
	<tr>
	<td style="text-align:center; vertical-align:middle;"><font style="font-size:large; color:gray;">공지사항 검색</font></td>
	
	<td style="text-align:center; vertical-align:middle;">
	<select name="searchType" id="searchType" class="w3-select">
			<option value="">선택하세요</option>
			<option value="subject">제목</option>
			<option value="content">내용</option>
		</select>
	<script type="text/javascript">
			if('${param.searchType}' != '') {
				document.getElememntById("searchType").value='${param.searchType}'
			}
	</script>
	</td>
	<td style="text-align:center; vertical-align:middle;">
		<input type="text" name="searchContent" value="${param.searchContent}" class="w3-input">
	</td>
	<td style="text-align:center; vertical-align:middle;">
		<input type="submit" value="검색" class="btn btn-outline-primary btn-block">
	</td>
	</tr>
	</table>	
</div>

</form>
		
<c:if test="${listcount > 0}">		
<c:forEach var="board" items="${boardlist}" varStatus="i">
	<button class="accordion">
		<span style="font-family:'Hanna'; color:slateblue;">공지사항</span>
		<span>
		<span style="float:center; display: inline;">&nbsp;&nbsp;&nbsp;<b>${board.subject}</b></span>
		<span style="float:right"><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd"/></span></span>
	</button>
	<div class="panel">
  		<div align="center" class="content">
  		<c:if test="${sessionScope.loginMember.id == 'admin' }">
  		<div class="w3-container w3-padding w3-margin">
		<a href="../notice/update.sms?bNo=${board.bNo}&pageNum=${pageNum}" class="btn btn-outline-primary btn-sm" style="font-family:'Hanna';">수정</a>&nbsp;&nbsp;
		<a href="../notice/delete.sms?bNo=${board.bNo}&pageNum=${pageNum}" class="btn btn-outline-danger btn-sm" style="font-family:'Hanna';">삭제</a>
		</div>
		</c:if>
		<div class="w3-container w3-margin">
   		 ${board.content}
   		 </div>
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

<div class="w3-container w3-margin">
	<c:if test="${sessionScope.loginMember.id == 'admin' }">
		<a href="../notice/write.sms" class="btn btn-outline-danger btn-block" style="font-family:'Hanna';">공지사항 작성</a>
	</c:if>
</div>

<div class="w3-container w3-margin">
<table class="w3-table">
<tr>
	<td style="text-align:center; font-size:large;">
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

</table>
</div>
</c:if>

<c:if test="${listcount == 0}">
	 <div class="w3-container w3-margin">
	<c:if test="${sessionScope.loginMember.id == 'admin' }">
		<a href="../notice/write.sms" class="btn btn-outline-danger btn-block" style="font-family:'Hanna';">공지사항 작성</a>
	</c:if>
</div>
	<div class="w3-container w3-gray w3-margin" style="font-size:large; font-family:'Hanna';">
	 	등록된 게시물이 없습니다.
	 </div>
</c:if>


</div>
<div class="w3-col s2"><p>&nbsp;</p></div><!-- 좌우 공간 확보 용  -->
</div>
</body>

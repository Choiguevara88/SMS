<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

<title>:::회원정보 보기:::</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

	function allchkbox(chk) {
		var chks = document.getElementsByName("idchks")
		for(var i=0; i < chks.length; i++) {
			chks[i].checked = chk.checked;
		}
	}

	function pageGo(pageNum) {
		document.sf.pageNum.value = pageNum;
		document.sf.submit();
	}
</script>

<script type="text/javascript">
	$(document).ready(function() {
		
		$("#searchType").click(function(){
			 
			var searchSelect = document.getElementById("searchType")
			var search = searchSelect.options[searchSelect.selectedIndex].value
			var searchContent = document.getElementById("searchContent")
			var cont = "";
			
			if(search == "id" || search == "name" || search == "email" || search == "mob" || search == "hostName" || search == "address" || search == "accountNo") {
				cont += "<input type='text' class='inputText' name='searchContent' value='${find}' placeholder='찾고자 하는 내용'>"
				+ "&nbsp;&nbsp;<input type='submit' class='inputButton' value='검색'>"
			}
			
			if(search == "reDate" || search == "regDate") {
				cont += "<input type='date' name='startDate'>부터 <input type='date' name='endDate'>까지"
				+ "&nbsp;&nbsp;<input type='submit' class='inputButton' value='검색'>"
			}
			
			if(search == "memType") {
				cont += "<select name='searchContent'><option value=''>선택하세요.</option><option value='0'>Guest</option><option value='1'>Host</option>"
				+ "<option value='2'>Host승인취소</option></select>"
				+ "&nbsp;&nbsp;<input type='submit' class='inputButton' value='검색'>"
			}
			searchContent.innerHTML = cont
		 })
	})
</script>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<style type="text/css">
	select {width: 140px; /* 원하는 너비설정 */ 
			padding: .2em .2em; /* 여백으로 높이 설정 */ 
			font-family: inherit; /* 폰트 상속 */ 
			background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%; /* 네이티브 화살표 대체 */ 
			-webkit-appearance: none; /* 네이티브 외형 감추기 */ 
			-moz-appearance: none; 
			appearance: none; }
	.inputText {
			width: 500px; /* 원하는 너비설정 */ 
			padding: .1em .1em; /* 여백으로 높이 설정 */}
	.inputButton {
			width: 100px; /* 원하는 너비설정 */ 
			padding: .2em .2em; /* 여백으로 높이 설정 */}
</style>
</head>

<body>
<div class="w3-container w3-margin">
	<h2>회원목록조회</h2>
</div>
	
<div class="w3-container w3-margin">
	<form action="adminMemberList.sms" method="GET" name="sf">
	<span>
		<font style="color:gray;">Category</font>&nbsp;
		<select name="searchType" id="searchType">
			<option value="">선택하세요.</option>
			<option value="id">ID</option>
			<option value="name">이름</option>
			<option value="email">이메일</option>
			<option value="mob">연락처</option>
			<option value="regDate">가입일자</option>
			<option value="memType">회원유형</option>
		</select>
		
		<script type="text/javascript">
			document.sf.searchType.value = "${searchType}"
		</script>
	</span>
	<input type="hidden" name="pageNum" value="1">
	<span id="searchContent">&nbsp;&nbsp;</span>
	<span>
	<select name="limit">
		<option value="30">30개씩 보기</option>
		<option value="50">50개씩 보기</option>
		<option value="100">100개씩 보기</option>
	</select>
	</span>
	</form>
</div>


<form action="mailForm.shop" method="post">
<div class="w3-container w3-margin">
<table class="w3-table w3-striped w3-border">

	<tr>
		<th style="text-align:center;">ID</th>
		<th style="text-align:center;">이름</th>
		<th style="text-align:center;">Email</th>
		<th style="text-align:center;">Mob</th>
		<th style="text-align:center;">가입일자</th>
		<th style="text-align:center;">회원상태</th>
		<th style="text-align:center;">전체선택&nbsp;&nbsp;<input type="checkbox" name="allchk" onchange="allchkbox(this)"></th>
	</tr>
	
	<c:if test="${listCnt == 0 }">
		<tr><td colspan="9" rowspan="4" style="text-align:center;">검색 결과가 없습니다.</td></tr>
	</c:if>
	
	<c:if test="${listCnt != 0 }">
	<c:forEach var="mem" items="${list}" >
	<tr>
		<td style="text-align:center;" class="w3-small">${mem.id}</td>
		<td style="text-align:center;" class="w3-small">${mem.name}</td>
		<td style="text-align:center;" class="w3-small">${mem.email}</td>
		<td style="text-align:center;" class="w3-small">${mem.mob}</td>
		<td style="text-align:center;" class="w3-small"><fmt:formatDate value="${mem.regDate}" pattern="yyyy-MM-dd"/></td>
		<td style="text-align:center;" class="w3-small">${mem.memType == 0 ? 'Guest' : mem.memType == 1 ? 'Host' : 'Host Cancel'}</td>
		<td style="text-align:center;" class="w3-small"><input type="checkbox" name="idchks" value="${mem.id}"></td>
	</tr>
	</c:forEach>
	</c:if>
</table>
</div>

<div class="w3-container">
	<table class="w3-table">
	<tr>
		<td colspan="8" style="text-align:center;"><c:if test="${pageNum <= 1}">&nbsp;</c:if> 
		<c:if test="${pageNum > 1}">
			<a href="javascript:pageGo(${pageNum-1})">[이전]</a>&nbsp;</c:if> 
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a == pageNum}">[${a}]	</c:if>
					
				<c:if test="${a != 0}">	
				<c:if test="${a != pageNum }">
					<a href="javascript:pageGo(${a})">[${a}]</a>
				</c:if>
				</c:if>
			</c:forEach> 
				<c:if test="${pageNum >= maxpage }">&nbsp;</c:if> 
				<c:if test="${pageNum < maxpage }">
					<a href="javascript:pageGo(${pageNum+1})">[다음]</a>
				</c:if>
		</td>
	</tr>
	</table>
</div>

<div class="w3-container w3-margin" style="text-align:center;">
	<input type="submit" value="Send Email" class="w3-button w3-black" style="width:40%;">
</div>

</form>

</body>
</html>
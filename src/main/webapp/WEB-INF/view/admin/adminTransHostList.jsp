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

<title>세부 선언</title>

<script type="text/javascript">
	function allchkbox(chk) {
		
		var chks = document.getElementsByName("idchks")
		
		for(var i=0; i < chks.length; i++) {
			chks[i].checked = chk.checked;
		}
	}
	
	function graph_open(url) {
		var op = "width=700, height=500, scrollbars=yes, left=50, top=150"
		var searchSelect = document.getElementById("graphSearchType")
		var search = searchSelect.options[searchSelect.selectedIndex].value
		console.log(search)
		window.open(url+".sms?searchType="+search ,"graph",op)
	}
</script>

</head>
<body>
<div class="w3-container w3-margin">
<form action="adminTransHostList.sms" method="post">
<input type="date" name="startDate">부터 <input type="date" name="endDate">까지
	<select name="searchType">
		<option value="">선택하세요.</option>
		<option value="host">판매자ID</option>
		<option value="guest">구매자ID</option>
		<option value="sName">상호명</option>
		<option value="srName">건물명</option>
	</select>
검색내용 <input type="text" name="searchContent"/> <input type="submit" value="검색">
	</form>
</div>
<div class="w3-container w3-margin">
<table class="w3-table w3-striped">
		<tr><th>호스트ID</th><th>상호명</th><th>Room이름</th><th>예약일자</th><th>발생수익</th><th>구매자</th></tr>
	<c:forEach var="th" items="${thList}">
		<tr><td>${th.host}</td><td>${th.hostName}</td><td>${th.sRName}</td><td><fmt:formatDate value="${th.reDate}" pattern="yyyy-MM-dd"/></td>
			<td>${th.totPrice}</td><td>${th.guest}</td></tr>
	</c:forEach>
</table>
</div>

<div class="w3-container w3-margin">
<table class="w3-table">
	<tr>
		<td><select id="graphSearchType">
			<option class="searchType" value="host">Host별</option>
			<option class="searchType" value="guest">Guest별</option>
			<option class="searchType" value="sName">상호별</option>
			<option class="searchType" value="sRName">공간별</option>
		</select></td>
		<td><input type="button" value="Graph(Bar)" onclick="graph_open('../trans/graphTransHistory')"></td>
	</tr>	
</table>
</div>

</body>
</html>
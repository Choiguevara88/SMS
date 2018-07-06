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

<table class="w3-table w3-striped">
		<tr><th>호스트ID</th><th>상호명</th><th>Room이름</th><th>예약일자</th><th>발생수익</th><th>구매자</th></tr>
	<c:forEach var="th" items="${thList}">
		<tr><td>${th.host}</td><td>${th.hostName}</td><td>${th.sRName}</td><td><fmt:formatDate value="${th.reDate}" pattern="yyyy-MM-dd"/></td>
			<td>${th.totPrice}</td><td>${th.guest}</td></tr>
	</c:forEach>
</table>

</body>
</html>
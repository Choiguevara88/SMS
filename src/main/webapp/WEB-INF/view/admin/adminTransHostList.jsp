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

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언 -->
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
	<div class="row">
		<div class="col-5">
		<div class="row">
		 	<div class="col-6">
			<input type="date" name="startDate" class="w3-input w3-border" placeHolder="부터">
			</div>
			<div class="col-6">
			<input type="date" name="endDate" class="w3-input w3-border" placeHolder="까지">
			</div>
		</div>
		</div>
		<div class="col-2">
			<select name="searchType" class="w3-select">
				<option value="">선택하세요.</option>
				<option value="host">판매자ID</option>
				<option value="guest">구매자ID</option>
				<option value="sName">상호명</option>
				<option value="srName">건물명</option>
			</select>
		</div>
		<div class="col-5">
		<div class="row">
			<div class="col-8">
			<input type="text" name="searchContent" class="w3-input" placeholder="검색 내용">
			</div>
			<div class="col-4">
			<input type="submit" value="검색" class="w3-btn w3-light-gray" style="width:100%;">
			</div>
		</div>
		</div>
	</div>
	</form>
</div>

<div class="w3-container w3-margin">
	<table class="w3-table w3-striped w3-border">
		<tr>
			<th style="text-align:center;">호스트ID</th>
			<th style="text-align:center;">상호명</th>
			<th style="text-align:center;">Room이름</th>
			<th style="text-align:center;">예약일자</th>
			<th style="text-align:center;">발생수익</th>
			<th style="text-align:center;">구매자</th>
		</tr>
	<c:forEach var="th" items="${thList}">
		<tr>
			<td style="text-align:center;">${th.host}</td>
			<td style="text-align:center;">${th.hostName}</td>
			<td style="text-align:center;">${th.sRName}</td>
			<td style="text-align:center;"><fmt:formatDate value="${th.reDate}" pattern="yyyy-MM-dd"/></td>
			<td style="text-align:center;"><fmt:formatNumber value="${th.totPrice}" pattern="###,###"/></td>
			<td style="text-align:center;">${th.guest}
			<c:set var="tAmount" value="${tAmount + th.totPrice}"/>
			<c:set var="tCnt" value="${tCnt + 1}"/>
			</td>
		</tr>
	</c:forEach>
		<tr>
			<th colspan="5" style="text-align:right; font-family:'Hanna';" >기간 별 총 거래금액</th>
			<th><fmt:formatNumber value="${tAmount}" pattern="###,###"/></th>
		</tr>
		
		<tr>
			<th colspan="5" style="text-align:right; font-family:'Hanna';">기간 별 총 거래건수</th>
			<th>${tCnt}</th>
		</tr>
	</table>
</div>

<div class="w3-container w3-margin">
<table class="w3-table">
	<tr>
		<td style="text-align:center;">
		<div class="row">
		<div class="col-sm-4">
		<select id="graphSearchType" class="w3-select">
			<option class="searchType" value="host">Host별</option>
			<option class="searchType" value="guest">Guest별</option>
			<option class="searchType" value="sName">상호별</option>
			<option class="searchType" value="sRName">공간별</option>
		</select>
		</div>
		<div class="col-sm-8"><b>
		<input type="button" value="Graph(Bar)" onclick="graph_open('../trans/graphTransHistory')" class="w3-button w3-gray" style="width:70%">
		</b></div>
		
		</div>
		</td>
	</tr>	
</table>
</div>

</body>
</html>
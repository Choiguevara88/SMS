<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<fmt:requestEncoding value="UTF-8" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Host 계정용 예약 목록 조회</title>

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

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#searchType").click(function(){
			 
			var searchSelect = document.getElementById("searchType")
			var search = searchSelect.options[searchSelect.selectedIndex].value
			var searchContent = document.getElementById("searchContent")
			var cont = "";
			
			if(search == "id") {
				cont += "<input type='text' class='inputText' name='searchContent' value='${find}' placeholder='찾고자 하는 내용'>"
				+ "&nbsp;&nbsp;<input type='submit' class='inputButton' value='검색'>"
			}
			
			if(search == "reDate" || search == "regDate") {
				cont += "<input type='date' name='startDate'>부터 <input type='date' name='endDate'>까지"
				+ "&nbsp;&nbsp;<input type='submit' class='inputButton' value='검색'>"
			}
			
			if(search == "reStat") {
				cont += "<select name='searchContent'><option value=''>선택하세요.</option><option value='0'>신규예약</option><option value='1'>이용/결제완료</option>"
				+ "<option value='2'>예약취소</option>	<option value='3'>환불완료</option></select>"
				+ "&nbsp;&nbsp;<input type='submit' class='inputButton' value='검색'>"
			}
			searchContent.innerHTML = cont
		 })
	})
	
		function pageGo(pageNum) {
			document.sf.pageNum.value = pageNum;
			document.sf.submit();
		}
	
</script>
</head>
<body>
	<c:set var="path" value="${pageContext.request.contextPath}" />
	<div class="w3-container w3-margin">
		<h2>예약목록조회</h2>
	</div>
	<div class="w3-container w3-margin">
		<form action="hostResList.sms" method="GET" name="sf">
		<input type="hidden" name="sNo" value="${param.sNo}">
		<span>
			<font style="color:gray;">Category</font>&nbsp;
			<select name="searchType" id="searchType">
				<option value="">선택하세요.</option>
				<option value="id">예약인</option>
				<option value="reDate">예약일자</option>
				<option value="regDate">등록일자</option>
				<option value="reStat">이용상태</option>
			</select>
			
			<script type="text/javascript">
				document.sf.searchType.value = "${searchType}"
			</script>
		</span>
			<input type="hidden" name="pageNum" value="1">
			<input type="hidden" name="sNo" value="${sNo}">
			<span id="searchContent">&nbsp;&nbsp;</span>
		</form>
		<br>
	</div>


	<div class="w3-container w3-margin">
		<table class="w3-table w3-striped w3-border">
			
			<c:if test="${reservecnt == 0}">
				<tr><td>등록된 예약이 없습니다.<a href="${path}/main.sms">[메인페이지로 가기]</a></td></tr>
			</c:if>
			
			<c:if test="${reservecnt != 0}">
				<tr>
					<th style="text-align:center;">주문번호</th>
					<th style="text-align:center;">예약인</th>
					<th style="text-align:center;">예약일자</th>
					<th style="text-align:center;">등록일자</th>
					<th style="text-align:center;">이용금액</th>
					<th style="text-align:center;">비고</th>
				</tr>

				<c:forEach var="res" items="${list}">
					<tr>

						<td class="td_1" style="text-align:center;">${res.reNo}</td>
						<td class="td_2" style="text-align:center;">${res.id}</td>
						
						<jsp:useBean id="today" class="java.util.Date" />
						<fmt:formatDate var="today1" value="${today}" pattern="yyyyMMddhhmm" type="date" />
						<fmt:formatDate var="redate" value="${res.reDate}" pattern="yyyyMMddhhmm" type="date" /> 
						<fmt:formatDate var="regdate" value="${res.regDate}" pattern="yyyyMMddhhmm" type="date" />

 						<c:if test="${today1 == redate}">
							<td style="text-align:center;"><fmt:formatDate value="${res.reDate}" pattern="hh:mm:ss" /></td>
						</c:if>

						<c:if test="${today1 != redate}">
							<td style="text-align:center;"><fmt:formatDate value="${res.reDate}"
									pattern="yyyy-MM-dd hh시" /></td>
						</c:if>
						
						<td style="text-align:center;"><fmt:formatDate value="${res.regDate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
						<td style="text-align:center;"><fmt:formatNumber value="${res.totPrice}" pattern="###,###"/></td>
						<td style="text-align:center;">
							<c:if test="${res.reStat == 0}">예약요청 : <a href="hostResConfirm.sms?reNo=${res.reNo}">[결제확인하기]</a></c:if>
							<c:if test="${res.reStat == 1}">
								
								<c:if test="${today1 >= redate}">
									[이용완료]
								</c:if>
								
								<c:if test="${today1 < redate}">
									[결제완료:이용대기 중]
								</c:if>
								
							</c:if>
							<c:if test="${res.reStat == 2}">[예약취소요청:환불확인중]</c:if>
							<c:if test="${res.reStat == 3}">[환불완료]</c:if>
							<c:if test="${res.reStat == 4}">[예약취소]</c:if>
						</td>
					</tr>
				</c:forEach>

				<%-- 페이지 부분 출력하기 --%>
				
				<tr align="center">
				<td colspan="6">
				
				<c:if test="${pageNum <= 1}">&nbsp;&nbsp;</c:if> 
				<c:if test="${pageNum > 1}">
					<a href="javascript:pageGo(${pageNum-1})">[이전]</a>&nbsp;
				</c:if>
					
					<c:forEach var="a" begin="${startpage}" end="${endpage}">
					<c:if test="${a == pageNum}">
						[${a}]
					</c:if>
					
					<c:if test="${a != 0}">	
					<c:if test="${a != pageNum }">
						<a href="javascript:pageGo(${a})">[${a}]</a>
					</c:if>
					</c:if>
						</c:forEach>
						
						<c:if test="${pageNum >= maxpage}">&nbsp;&nbsp;</c:if>
						<c:if test="${pageNum < maxpage}"><a href="javascript:pageGo(${pageNum+1})">[다음]</a></c:if>
				</td>
				</tr>
			</c:if>
		</table>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<fmt:requestEncoding value="UTF-8" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>예약 목록 조회</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript">
	function pageGo(pageNum) {
		document.sf.pageNum.value = pageNum;
		document.sf.submit();
	}
</script>

<style type="text/css">
select {width: 200px; /* 원하는 너비설정 */ 
		padding: .5em .5em; /* 여백으로 높이 설정 */ 
		font-family: inherit; /* 폰트 상속 */ 
		background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%; /* 네이티브 화살표 대체 */ 
		border: 1px solid #999; border-radius: 0px; /* iOS 둥근모서리 제거 */ 
		-webkit-appearance: none; /* 네이티브 외형 감추기 */ 
		-moz-appearance: none; 
		appearance: none; }
.inputText {
		width: 800px; /* 원하는 너비설정 */ 
		padding: .5em .5em; /* 여백으로 높이 설정 */}
.inputButton {
		width: 200px; /* 원하는 너비설정 */ 
		padding: .5em .5em; /* 여백으로 높이 설정 */}
</style>
</head>
<body>
	<c:set var="path" value="${pageContext.request.contextPath}" />
	<div class="w3-container">
		<h2>예약목록조회</h2>
	</div>
	<div>
		<form action="resList.sms" method="post" name="sf">
		<input type="hidden" name="set_num" value="${param.set_num}">
			<font style="color:gray;">Category</font><select name="c2">
				<option value="">선택하세요.</option>
				<option value="menu1">보기1</option>
				<option value="menu2">보기2</option>
				<option value="menu3">보기3</option>
				<option value="menu4">보기4</option>
			</select>
			
			<script type="text/javascript">
				document.sf.c2.value = "${param.c2}"
			</script>

			<input type="hidden" name="pageNum" value="1"> <font style="color:gray;">&nbsp;SearchRange</font>
			<select name="column">
				<option value="">선택하세요</option>
				<option value="subject">제목</option>
				<option value="content">내용</option>
				<option value="id">작성자</option>
			</select>
			<script type="text/javascript">
				document.sf.column.value = "${param.column}"
			</script>
						
			<input type="text" class="inputText" name="find" value="${find}" placeholder="찾고자 하는 내용">
			<input type="submit" class="inputButton" value="검색">
		</form>
		<br>
	</div>



		<table class="w3-table w3-striped w3-hoverable">
			
			<c:if test="${reservecnt == 0 }">
				<td>등록된 예약이 없습니다.<a href="main.sms">[메인페이지로 가기]</a></td>
			</c:if>
			
			<c:if test="${reservecnt != 0 }">
				<tr>
					<th style="text-align:center;">주문번호</th>
					<th style="text-align:center;">건물번호</th>
					<th style="text-align:center;">예약일자</th>
					<th style="text-align:center;">등록일자</th>
					<th style="text-align:center;">가격</th>
					<th style="text-align:center;">수량</th>
					<th style="text-align:center;">비고</th>
				</tr>

				<c:forEach var="res" items="${list}">
					<tr>

						<td class="td_1" style="text-align:center;"><a href="resDetail.sms?reNo=${res.reNo}">${res.reNo}</a></td>
						<td class="td_2" style="text-align:center;">[${res.srNo}]</td>
						
						<jsp:useBean id="today" class="java.util.Date" />
						<fmt:formatDate var="today1" value="${today}" type="date" />
						<fmt:formatDate var="redate" value="${res.reDate}" type="date" /> 
						<fmt:formatDate var="regdate" value="${res.regDate}" type="date" />

 						<c:if test="${today1 == redate}">
							<td style="text-align:center;"><fmt:formatDate value="${res.reDate}" pattern="hh:mm:ss" /></td>
						</c:if>

						<c:if test="${today1 != redate}">
							<td style="text-align:center;"><fmt:formatDate value="${res.reDate}"
									pattern="yyyy-MM-dd hh시" /></td>
						</c:if>
						
						<td style="text-align:center;"><fmt:formatDate value="${res.regDate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>

						<td style="text-align:center;">${res.totPrice}</td>

						<td style="text-align:center;">${res.reCnt}</td> 
						<td style="text-align:center;">
							<c:if test="${res.reStat == 0}">[결제대기]</c:if>
							
							<c:if test="${res.reStat == 1}">
								<c:if test="${today1 <= redate}">
										[결제완료]
								</c:if>

								<c:if test="${today1 > redate}">
									[이용완료] <a href="resCancel.sms?sNo=${res.sNo}&kind=2">[리뷰 작성]</a>
								</c:if>
							</c:if>
							<c:if test="${res.reStat == 2}">환불 예정 : <a href="resCancel.sms?reNo=${res.reNo}&reStat=${res.reStat}">[환불 확인]</a></c:if>
							<c:if test="${res.reStat == 3}">[환불완료]	</c:if>
							<c:if test="${res.reStat == 4}">[예약취소]	</c:if>
						</td>
					</tr>
				</c:forEach>

				<%-- 페이지 부분 출력하기 --%>
				
				<tr align="center">
				<td colspan="8">
				
				<c:if test="${pageNum <= 1}">&nbsp;</c:if> 
				<c:if test="${pageNum > 1}">
					<a href="javascript:pageGo(${pageNum-1})">[이전]</a>&nbsp;
				</c:if>
					
					<c:forEach var="a" begin="${requestScope.startpage}" end="${requestScope.endpage}">
					<c:if test="${a == pageNum}">
						[${a}]
					</c:if>
					
					<c:if test="${a != 0}">	
					<c:if test="${a != pageNum }">
						<a href="javascript:pageGo(${a})">[${a}]</a>
					</c:if>
					</c:if>
						</c:forEach>
						
						<c:if test="${pageNum >= maxpage }">&nbsp;</c:if>
						
						<c:if test="${pageNum < maxpage }"><a href="javascript:pageGo(${pageNum+1})">[다음]</a></c:if>
				</td>
				</tr>
			</c:if>
			<tr align="right">
				<td colspan="8"><a href="">[글쓰기]</a></td>
			</tr>
		</table>
</body>
</html>
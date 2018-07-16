<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언-->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언 -->

<link href='http://fonts.googleapis.com/earlyaccess/hanna.css' rel='stylesheet'>

<title>예약 미리 확인하기</title>

<script type="text/javascript">
	function win_close() {
		window.close();
	}
</script>

</head>
<body>


<div class="w3-container w3-margin">

<c:if test="${chkCnt == 0 }">
<div class="w3-container w3-margin w3-border w3-round">
	<h1 style="font-family:'Hanna';">등록된 예약이 없습니다.</h1>
</div>
</c:if>

<table class="w3-table w3-border" style="font-family:'Hanna';">
	<c:if test="${chkCnt != 0 }">
		<tr>
		<td style="text-align:center;">예약인</td>
		<td style="text-align:center;">예약일자</td>
		<td style="text-align:center;">수량</td>
		</tr>
	
	<c:forEach var="res" items="${chkList}">
		<tr>
		
		<td style="text-align:center;">
			${fn:substring(res.id,0,2)}&#42;&#42;&#42;	
		</td>
						
		<jsp:useBean id="today" class="java.util.Date"/>

		<fmt:formatDate var="today1" value="${today}" type="date" pattern="yyyyMMdd"/>
		<fmt:formatDate var="redate" value="${res.reDate}" type="date" pattern="yyyyMMdd"/> 

		<c:if test="${today1 == redate}">
		<c:if test="${sResType == 0}">
			<td style="text-align:center;"><fmt:formatDate value="${res.reDate}" pattern="오늘 aa hh:mm:ss"/></td>
		</c:if>
		
		<c:if test="${sResType == 1}">
			<td style="text-align:center;"><fmt:formatDate value="${res.reDate}" pattern="오늘"/></td>
		</c:if>
		</c:if>

		<c:if test="${today1 != redate}">
		<c:if test="${sResType == 0}">
			<td style="text-align:center;"><fmt:formatDate value="${res.reDate}" pattern="yyyy-MM-dd aa hh시" /></td>
		</c:if>
			
		<c:if test="${sResType == 1}">
			<td style="text-align:center;"><fmt:formatDate value="${res.reDate}" pattern="yyyy-MM-dd" /></td>
		</c:if>
		</c:if>
						
		

		<td style="text-align:center;">${res.reCnt}
		<c:if test="${sResType == 0}">시간</c:if>
		<c:if test="${sResType == 1}">일</c:if>
		</td> 
		</tr>
		</c:forEach>
		</c:if>
	</table>
	
	<div class="w3-container w3-margin" style="text-align:center">
		<input type="button" onclick="javascript:win_close()" value="닫기" class="btn btn-outline-danger" 
		style="font-family:'Hanna';">
	</div>
	
	</div>
</body>
</html>
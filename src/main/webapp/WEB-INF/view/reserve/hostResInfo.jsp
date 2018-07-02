<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<fmt:requestEncoding value="UTF-8" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Host 계정용 예약 정보 조회</title>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>

<body>
	<table class="w3-table w3-striped w3-hoverable">
		<c:if test="${buildingcnt == 0}">
			<td>등록된 건물이 없습니다.<a href="main.sms">[등록하러 가기]</a></td>
		</c:if>
			
		<c:if test="${reservecnt != 0 }">
			<tr>
				<th style="text-align:center;">건물관리번호</th>
				<th style="text-align:center;">건물명</th>
				<th style="text-align:center;">신규예약</th>
				<th style="text-align:center;">신규문의</th>
				<th style="text-align:center;"></th>
			</tr>

			<c:forEach var="build" items="${list}">
				<tr>
					<td>${build.sNo}</td>
					<td>${build.sName}</td>
					<td>[${build.boCnt}]</td>
					<td>[${build.reCnt}]</td>
					<td><a href="hostResList.sms?sNo=${build.sNo}">[확인하러가기]</a></td>			
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>
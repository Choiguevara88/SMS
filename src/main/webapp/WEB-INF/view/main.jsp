<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./jspHeader.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ProjectMain</title>
</head>
<body>
<%--forEach 통해서 메인에 뿌리기 --%>
<section align="center">
<h2 align="center"><strong>오늘의 추천 공간! &gt;&lt;</strong></h2>
<br/>
<%-- 첫번째 row시작 --%>
<div class="w3-row w3-container" id="row_row">
  <div class="w3-col w3-container" style="width:14%">빈공간</div>
  <c:forEach var="building" items="${today_buildings }" varStatus="status">
    <div class="w3-col w3-container" style="width:22%">
  	<div class="w3-card-4" id="preview">
  		<a href="building/buildingDetail.sms?sNo=${building.sNo }" style="text-decoration:none"><img id="picturee" src="./picture/${building.sImg1 }" style="width:100%; height:200px">
  		<div class="w3-container w3-light-gray w3-center">
      		<p align="left" style="margin-bottom: 0; margin-top: 0"><strong>${building.sPreview }</strong></p>
      		<p align="left" style="margin-bottom: 0; margin-top: 0">
      			<c:forEach items="${building.sTagList }" var="taglist">
      				<span class='w3-tag w3-tiny w3-pale-yellow w3-round-xlarge w3-border w3-border-pale-yellow w3-center'>#${taglist }</span></c:forEach><br/>
      			<c:forEach items="${building.sTypeList }" var="typelist">
      				<span class='w3-tag w3-tiny w3-pale-red w3-round-xlarge w3-border w3-border-pale-red w3-center'>#${typelist }</span></c:forEach></p>
      		<p align="left" style="margin-bottom: 1; margin-top: 0"><i class="fa fa-krw"></i><fmt:formatNumber value="${today_buildings_price[status.index].sPrice}" pattern="#,###"></fmt:formatNumber>~ /
      			<c:if test="${today_buildings_price[status.index].sResType==0 }">시간</c:if>
      			<c:if test="${today_buildings_price[status.index].sResType==1 }">일</c:if></p></div></a></div></div>
    <c:if test="${status.index == 0 || status.index == 1 ||status.index == 3 ||status.index == 4}"><div class="w3-col w3-container" style="width:3%">빈공간</div></c:if>
    <c:if test="${status.index == 2 }"><div class="w3-col w3-container" style="width:14%">빈공간</div></div>
  <br>
  <br>
	<%-- row끝 --%>
	<%-- 두번째 row시작 --%>
  <div class="w3-row w3-container" id="row_row">
  <div class="w3-col w3-container" style="width:14%">빈공간</div></c:if>
    
   <c:if test="${status.index == 5 }"><div class="w3-col w3-container" style="width:14%">빈공간</div>
  			</div><br/></section></c:if></c:forEach>
<%--forEach 통해서 메인에 뿌리기 끝 --%>

<hr size="1">
<!--  91-96 -->
<section align="center">
<h2 align="center"><strong>평점 좋은 공간! &gt;&lt;</strong></h2>
<br/>
<%-- 첫번째 row시작 --%>
<div class="w3-row w3-container" id="row_row">
  <div class="w3-col w3-container" style="width:14%">빈공간</div>
  <c:forEach var="building" items="${today_buildings }" varStatus="status">
    <div class="w3-col w3-container" style="width:22%">
  	<div class="w3-card-4" id="preview">
  		<a href="building/buildingDetail.sms?sNo=${building.sNo }" style="text-decoration:none"><img id="picturee" src="./picture/${building.sImg1 }" style="width:100%; height:200px">
  		<div class="w3-container w3-light-gray w3-center">
      		<p align="left" style="margin-bottom: 0; margin-top: 0"><strong>${building.sPreview }</strong></p>
      		<p align="left" style="margin-bottom: 0; margin-top: 0">
      			<c:forEach items="${building.sTagList }" var="taglist">
      				<span class='w3-tag w3-tiny w3-pale-yellow w3-round-xlarge w3-border w3-border-pale-yellow w3-center'>#${taglist }</span></c:forEach><br/>
      			<c:forEach items="${building.sTypeList }" var="typelist">
      				<span class='w3-tag w3-tiny w3-pale-red w3-round-xlarge w3-border w3-border-pale-red w3-center'>#${typelist }</span></c:forEach></p>
      		<p align="left" style="margin-bottom: 1; margin-top: 0"><i class="fa fa-krw"></i><fmt:formatNumber value="${today_buildings_price[status.index].sPrice}" pattern="#,###"></fmt:formatNumber>~ /
      			<c:if test="${today_buildings_price[status.index].sResType==0 }">시간</c:if>
      			<c:if test="${today_buildings_price[status.index].sResType==1 }">일</c:if></p></div></a></div></div>
    <c:if test="${status.index == 0 || status.index == 1 ||status.index == 3 ||status.index == 4}"><div class="w3-col w3-container" style="width:3%">빈공간</div></c:if>
    <c:if test="${status.index == 2 }"><div class="w3-col w3-container" style="width:14%">빈공간</div></div>
  <br>
  <br>
	<%-- row끝 --%>
	<%-- 두번째 row시작 --%>
  <div class="w3-row w3-container" id="row_row">
  <div class="w3-col w3-container" style="width:14%">빈공간</div></c:if>
    
   <c:if test="${status.index == 5 }"><div class="w3-col w3-container" style="width:14%">빈공간</div>
  			</div><br/></section></c:if></c:forEach>
<hr size="1">
</body>
</html>
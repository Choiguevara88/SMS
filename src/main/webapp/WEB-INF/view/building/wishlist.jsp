<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언-->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언 -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>:::나의위시리스트:::</title>

</head>

<body>

<div class="w3-container w3-margin w3-padding" style="text-align:center;">
	<h1 style="font-family:'Hanna'">${loginMember.id } 님께서 찜한 공간!! &#42;&gt;&nbsp;&lt;&#42;</h1>
</div>

<div class="w3-row w3-container w3-margin" id="context">
<div class="w3-row">
<div class="w3-col s2"><p>&nbsp;</p></div>

<div class="w3-col s8">
<c:set var="cnt" value="0"/>
<c:forEach var="building" items="${list}">
	
	<c:if test="${cnt % 3 == 0}">
		<div class="w3-row">
	</c:if>
	
	<div class="w3-col s4">
		<div class="w3-card-2 w3-margin" id="preview">
			<!-- 건물의 메인 사진을 보여주는 구간 -->
			<a href="buildingDetail.sms?sNo=${building.sNo}" style="text-decoration:none">
				<img id="picturee" src="../picture/${building.sImg1}" style="width:100%; height:200px" class="w3-hover-opacity">
			</a>
			<!-- 건물의 메인 사진을 보여주는 구간 -->
			
			<!-- 건물의 정보를 보여주는 구간 -->
			<div class="w3-container w3-center w3-light-gray">
				
				<!-- 건물 한줄 평 -->
				<p align="left" style="margin-bottom: 0; margin-top: 0;"><font style="font-family:'Hanna'; font-size:large;'">${building.sPreview}</font></p>
				<!-- 건물 한줄 평 -->
				
				<p align="left" style="margin-bottom: 0; margin-top: 0">
				
				<!-- 건물의 태그의 정보를 뿌리는 구간 -->
				<c:forEach items="${building.sTagList}" var="taglist">
					<span class='w3-tag w3-tiny w3-pale-yellow w3-round-xlarge w3-border w3-border-pale-yellow w3-center'>#${taglist}</span>
				</c:forEach><br/>
				<!-- 건물의 태그의 정보를 뿌리는 구간 -->
				
				<!-- 건물 유형의 정보를 뿌리는 구간 -->
      			<c:forEach items="${building.sTypeList}" var="typelist">
      				<span class='w3-tag w3-tiny w3-pale-red w3-round-xlarge w3-border w3-border-pale-red w3-center'>#${typelist}</span>
      			</c:forEach>
      			<!-- 건물 유형의 정보를 뿌리는 구간 -->
      			
      			</p>
      			
      			<p align="left" style="margin-bottom: 1; margin-top: 2;">
      			<i class="fa fa-krw"></i>
      			<fmt:formatNumber value="${building.room[0].sPrice}" pattern="###,###"></fmt:formatNumber>~ /
      			<c:if test="${building.room[0].sResType == 0}">시간</c:if>
      			<c:if test="${building.room[0].sResType == 1}">일</c:if>
	      		</p>
			</div>
			<!-- 건물의 정보를 보여주는 구간 -->
		</div>
		${cnt = cnt + 1;""}
	</div>
</c:forEach>


</div>
<div class="w3-col s2"><p>&nbsp;</p></div>
</div>
</div>


</body>
</html>
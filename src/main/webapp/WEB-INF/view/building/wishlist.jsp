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

<!-- fontAwesome Icon 사용 선언 -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
<!-- fontAwesome Icon 사용 선언 -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>:::나의위시리스트:::</title>

</head>

<body>

<div class="w3-container w3-margin w3-padding" style="text-align:center;">
	<h1 style="font-family:'Hanna'">${loginMember.id } 님께서 찜한 공간!! &#42;&gt;&nbsp;&lt;&#42;</h1>
</div>

<div class="w3-row w3-container w3-margin" id="context">
<div class="w3-row">
<div class="w3-col s1"><p>&nbsp;</p></div>

<div class="w3-col s10">
<c:if test="${chk == false }">
	<div class="w3-container w3-margin w3-padding" style="text-align:center;">
		<div class="w3-container w3-margin">
		<h1 style="font-family:'Hanna';">저런~! 아직 찜한 공간이 없으시네요!</h1>
		</div>
		<div class="w3-container w3-center w3-padding w3-margin" style="text-align:center;">
		<a href="buildingList.sms" class="btn btn-outline-primary btn-lg" style="font-family:'Hanna'; font-size:large;">공간보러가기</a>
		</div>
	</div>
</c:if>

<c:forEach var="building" items="${list}">
	
	<div class="w3-col s4">
		<div class="w3-card-2 w3-margin">
			<!-- 건물의 메인 사진을 보여주는 구간 -->

			<a href="buildingDetail.sms?sNo=${building.sNo}" style="text-decoration:none">
				<img src="../picture/${building.sImg1}" style="width:100%; height:200px" class="w3-hover-opacity">
			</a>
			<!-- 건물의 메인 사진을 보여주는 구간 -->
			
			<!-- 건물의 정보를 보여주는 구간 -->
			<div class="w3-container w3-center w3-light-gray">
				
				<!-- 건물 이름 / 지역 -->
				<p align="left" style="margin-bottom: 0; margin-top: 0; padding-top: 5px;">
				<font style="font-family:'Hanna'; font-size:large;'">${building.sName}</font>
				</p>
				<!-- 건물 이름 -->
				
				<p align="left" style="margin-bottom: 0; margin-top: 0">
				<span class="w3-medium w3-center"><i class="fas fa-map-marker-alt" style="font-color:purple;"></i>
				<font style="font-family:'Hanna';">${fn:substring(building.sAddress,6,11)}</font>
				</span><br>
				
				<!-- 건물의 태그의 정보를 뿌리는 구간 -->
				<c:forEach items="${building.sTagList}" var="taglist">
					<span class='w3-tag w3-tiny w3-pale-yellow w3-round-xlarge w3-border w3-border-pale-yellow w3-center'>#&nbsp;${taglist}</span>
				</c:forEach><br/>
				<!-- 건물의 태그의 정보를 뿌리는 구간 -->
				
				<!-- 건물 유형의 정보를 뿌리는 구간 -->
      			<c:forEach items="${building.sTypeList}" var="typelist">
      				<span class='w3-tag w3-tiny w3-pale-red w3-round-xlarge w3-border w3-border-pale-red w3-center'><i class="fa fa-tag"></i>&nbsp;${typelist}</span>
      			</c:forEach>
      			<!-- 건물 유형의 정보를 뿌리는 구간 -->
      			
      			</p>
      			
      			<p align="left" style="margin-top: 5px;">
      			<i class="fa fa-krw"></i>
      			<font style="font-family:'Hanna'">
      			<fmt:formatNumber value="${building.room[0].sPrice}" pattern="###,###"></fmt:formatNumber>~ /
      			<c:if test="${building.room[0].sResType == 0}">시간</c:if>
      			<c:if test="${building.room[0].sResType == 1}">일</c:if>
      			</font>
	      		</p>
			</div>
			<!-- 건물의 정보를 보여주는 구간 -->
		</div>
		
	</div>
	
</c:forEach>



</div>
<div class="w3-col s1"><p>&nbsp;</p></div>
</div>
</div>


</body>
</html>
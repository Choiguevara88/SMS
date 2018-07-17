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

<title>:::건물리스트:::</title>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	
	function list(pageNum) {
		
		if(searchType == null || searchType.length == 0) {
			document.searchform.searchContent.value = "";
			document.searchform.pageNum.value = "1";
			location.href="buildingList.sms";
		} else {
			document.searchform.submit();
			return true;
		}
		
		return false;
	}
	
</script>
</head>

<body>

<div class="w3-row w3-container w3-margin" id="context">
<div class="w3-row">
<div class="w3-col s1"><p>&nbsp;</p></div>

<div class="w3-col s10">

<form action="buildingList.sms" method="get" name="searchform" onsubmit="return list(1)" >
<input type="hidden" name="pageNum" value="${pageNum}">
<div class="w3-container w3-margin w3-padding-24" style="text-align:center;">
	<table class="w3-table w3-border">
	<tr>
	<td style="text-align:center; vertical-align:middle;"><font style="font-size:large; color:gray;">검 색</font></td>
	
	<td style="text-align:center; vertical-align:middle;">
	<select name="searchType" id="searchType" class="w3-select">
			<option value="">선택하세요</option>
			<option value="sName">건물명</option>
			<option value="sType">유형</option>
			<option value="sTag">태그</option>
			<option value="sAddress">지역</option>
		</select>
	
	<script type="text/javascript">
		if('${param.searchType}' != '') {
			document.getElementById("searchType").value='${param.searchType}'
		}
	</script>
	
	</td>
	<td style="text-align:center; vertical-align:middle;">
		<input type="text" name="searchContent" value="${param.searchContent}" class="w3-input">
	</td>
	<td style="text-align:center; vertical-align:middle;">
		<input type="submit" value="검색" class="btn btn-outline-primary btn-block">
	</td>
	</tr>
	</table>	
</div>


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

<div class="w3-container w3-margin w3-padding-24">

<table class="w3-table" style="font-family:'Hanna';">
<tr>
	<td style="text-align:center; font-size:large;">
		
		<c:if test="${pageNum > 1}">
			<a href="javascript:list(${pageNum -1})"  style="font-family:'Hanna';">[이전]</a>
		</c:if>&nbsp;
		
		<c:if test="${pageNum <= 1}">&nbsp;&nbsp;&nbsp;</c:if>&nbsp;
		
		
		<c:forEach var="a" begin="${startpage + 1}" end="${endpage}"> 
			
		<c:if test="${a == 0}">&nbsp;</c:if>
			
		<c:if test="${a == pageNum}"><font class="w3-text-blue">[${a}]</font></c:if>
			
		<c:if test="${a != pageNum}">
			<a href="javascript:list(${a})" style="font-family:'Hanna';">[${a}]</a></c:if>
		</c:forEach>
		
		<c:if test="${pageNum < maxpage}">
			<a href="javascript:list(${pageNum + 1})"  style="font-family:'Hanna';">[다음]</a>
		</c:if>&nbsp;
		<c:if test="${pageNum >= maxpage}">&nbsp;&nbsp;&nbsp;</c:if>&nbsp;
	</td>
</tr>

</table>
</div>

</form>
</div>
<div class="w3-col s1"><p>&nbsp;</p></div>
</div>
</div>


</body>
</html>
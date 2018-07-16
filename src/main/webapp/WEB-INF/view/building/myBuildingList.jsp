<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공간 리스트</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
function budelete(sNo) {
	console.log(sNo)
	var result = confirm("삭제하시면 공간정보, 세부공간정보, 공간에 달린 댓글, 리뷰정보가 모두 삭제됩니다. 정말로 삭제하시겠습니까?")
	if(result == true){
	location.href="buildingDelete.sms?sNo="+sNo
	}
}
</script>
</head>
<body>
<br>
<div class="w3-left">
<h1>${id}님의 공간 정보 관리</h1>
<c:if test="${buildCnt == 0}">
<br>
<br>
<div class="w3-center">
<h3>
등록된 공간이 없습니다. 공간 추가하기를 눌러 공간을 추가해주세요.
</h3>
</div>
<br>
<br>
</c:if>
</div>
<div class="w3-right w3-margin-top w3-margin-right">
<a href="../building/buildingForm.sms?id=${sessionScope.loginMember.id}">
<input type="button" class="w3-btn w3-deep-purple" value="공간추가하기">
</a>
</div>

<div class="w3-row-padding">
<c:forEach var="build" items="${myBuildingList}">
<div class = "w3-third">
  <div class="w3-card-4 w3-margin-top w3-margin-bottom">
    <img src="../picture/${build.sImg1}" style="width:100%; height:250px">
      <div class="w3-container w3-margin-left w3-margin-top">
      <a href="buildingDetail.sms?sNo=${build.sNo}" class="w3-btn w3-white">
      <font class="w3-large">${build.sName}</font>
      </a>
      </div>
      <div class="w3-container w3-center w3-margin-top">
      <a href="buildingUpdate.sms?sNo=${build.sNo}" class="w3-btn w3-black">공간수정</a>
      <a href="roomList.sms?sNo=${build.sNo}" class="w3-btn w3-black">세부공간추가/수정</a>
      <a href="javascript:budelete(${build.sNo})" class="w3-btn w3-black">삭제</a>
      </div>
    <br>
  </div>
  </div>
</c:forEach>
</div>

</body>
</html>
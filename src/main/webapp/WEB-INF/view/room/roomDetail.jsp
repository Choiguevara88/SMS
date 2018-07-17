<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>세부 공간 리스트</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
function roomDelete(sNo,sRNo) {
	confirm("정말로 삭제하시겠습니까?")
	location.href="roomDeleteSuccess.sms?sNo="+sNo+"&sRNo="+sRNo
}
</script>
	<style>
		.srinfo{
			border:2px black solid;
		}
	</style>
</head>




<body>
<h1>세부공간 정보</h1>

<div class="w3-container">
  <div class="w3-card-4" style="width:30%">
    <div class="w3-container w3-center">
     <c:forEach var="roomImg" items="${room.sRImgNameList}">
      <div >
      <img src="../picture/${roomImg}" style="width:100%; height:250px"><br>
      </div>
      </c:forEach>
      <p>세부 공간 이름 : ${room.sRName}</p><br>	
      <p>설명 : ${room.sRContent}</p><br>
      <p>공간 유형 : ${room.sRType}</p><br>
      <div class="srinfo">시설 안내
      <c:forEach var="roomInfo" items="${room.sRInfoList}">
      <div >
    	 ${roomInfo}
      </div>
     	</c:forEach>
     	</div>
     	
      <p>예약 유형 : ${room.sResType}</p><br>
	  <p>최소 인원 : ${room.sRPersonLimit}</p><br>
	  <p>시간/일 가격 : ${room.sPrice}</p><br>
    </div>
  </div>
</div>
<a href="roomList.sms?sNo=${room.sNo}" class="w3-button w3-black">리스트로 가기</a>
<a href="/TestProject/room/roomUpdateForm.sms?sRNo=${room.sRNo }&sNo=${room.sNo}" class="w3-button w3-black">수정</a>
<a href="javascript:roomDelete(${room.sNo}, ${room.sRNo })" class="w3-btn w3-black">삭제</a>


</body>
</html>
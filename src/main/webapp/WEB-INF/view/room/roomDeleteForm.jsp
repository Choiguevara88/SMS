<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언 -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>지울거야</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<p>패스워드를 입력하세요</p>
<form method="post" action="roomDeleteSuccess.sms" name="f">
<div class="w3-container">
  <div class="w3-card-4" style="width:30%">
    <div class="w3-container w3-center">
  		<input type="hidden" name="sRNo" value="${room.sRNo}"/>
		<input type="hidden" name="sNo" value="${room.sNo}"/>
		<label for="pass">
		<input type="text" name="pass"/>
		</label>
     <c:forEach var="roomImg" items="${room.sRImgNameList}">
      <div >
      <img src="../picture/${roomImg}" style="width:100%; height:250px">
      </div>
      </c:forEach>
      <p>세부 공간 이름 : ${room.sRName}</p><br>	
      <p>설명 : ${room.sRContent}</p><br>
      <p>공간 유형 : ${room.sRType}</p><br>
      <p>시설 안내 : ${room.sRInfo}</p><br>
      <p>예약 유형 : ${room.sResType}</p><br>
	  <p>최소 인원 : ${room.sRPersonLimit}</p><br>
	  <p>시간/일 가격 : ${room.sPrice}</p><br>
    </div>
  </div>
</div>
<a href="javascript:document.f.submit()" class="w3-button w3-black">삭제 확정</a>
</form>
</body>
</html>
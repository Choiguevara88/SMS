<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script>
//Get the modal
var modal = document.getElementById('id01');

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>
</head>
<body>
<br/>
<br/>
<c:if test="${!empty member }">
	<div class="w3-container w3-center w3-text-tawny">
		<h1>비밀번호가 나왔습니다 !</h1><br>
		<h1>결과를 확인하세욥 ! </h1>
	</div>
  <div class="w3-container w3-center">
  <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-black">내가 맞추지 못한 나의 비밀번호는!? &gt;&lt;</button>

  <div id="id01" class="w3-modal">
    <div class="w3-modal-content w3-card-4 w3-center">
    		<img src="${path }/picture/applause.png" style="width:40%;">
 		 <span onclick="document.getElementById('id01').style.display='none'" 
    		    class="w3-button w3-display-topright">&times;</span>     
      <div class="w3-container">
        <h1 align="center">결과 : ${member.pw }</h1><br>
        <a href="main.sms">메인페이지로 가기! &gt;&lt;</a>
      </div>
    </div>
  </div>
</div>
</c:if>
<c:if test="${empty member}">
	<div class="w3-container w3-center">
		<h1>조건을 만족하는 비밀번호가 없네여...ㅜ</h1><br>
		<h1>다시 한번 검색 해보시겠어엽?</h1>
		<a href="findmypassword.sms">다시 찾기<i class="fa fa-arrow-circle-right"></i></a>
	</div>
</c:if>
</body>
</html>
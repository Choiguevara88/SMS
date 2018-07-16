<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="path" value="${pageContext.request.contextPath}"/>
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<br/>
<br/>
<c:if test="${!empty id }">
	<div class="w3-container w3-center">
		<h1 style="font-family:'Hanna';">아이디가 나왔습니다 !</h1><br>
		<h1 style="font-family:'Hanna';">결과를 확인하세욥 ! </h1>
		<br>
	</div>
	<div class="w3-center">
  <button style="font-family:'Hanna';" onclick="document.getElementById('id012').style.display='block'" class="w3-button w3-deep-purple">두근두근 나의 아이디 ! &gt;&lt;</button>
	</div>
  <div id="id012" class="w3-modal">
    <div class="w3-modal-content w3-center">
    		<img src="${path }/picture/applause.png" style="width:40%;">
 		 <span onclick="document.getElementById('id012').style.display='none'" 
    		    class="w3-button w3-display-topright">&times;</span>     
      <div class="w3-container">
        <h1 style="font-family:'Hanna';" align="center">결과 : ${id }</h1><br>
        <a style="font-family:'Hanna';text-decoration:none" href="findmypassword.sms">비밀번호 찾기는 여기 클릭! &gt;&lt;</a>
      	<br>
      </div>
    </div>
  </div>
</c:if>
<c:if test="${empty id}">
	<div class="w3-container w3-center">
		<h1 style="font-family:'Hanna';">조건을 만족하는 아이디가 없네여...ㅜ</h1><br>
		<h1 style="font-family:'Hanna';">다시 한번 검색 해보시겠어엽?</h1>
		<a style="font-family:'Hanna';text-decoration:none" href="findmyID.sms">다시 찾기<i class="fa fa-arrow-circle-right"></i></a>
		<br>
	</div>
</c:if>
<br>
</body>
</html>
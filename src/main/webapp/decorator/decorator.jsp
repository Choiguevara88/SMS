<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<c:set var="path" value="${pageContext.request.contextPath}"/> <!-- 어느 곳에서나 login.sms할수 있도록 할려고 경로를 만들어 준 것-->
<%-- /WebContent/decorator/decorator_test_bar.jsp --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link href="https://fonts.googleapis.com/css?family=Gothic+A1" rel="stylesheet">
<link href='http://fonts.googleapis.com/earlyaccess/hanna.css' rel='stylesheet'>
<style>
.mySlides {display:none;}

.header {
  padding: 10px 16px;
  color: #f1f1f1;
}
.sticky {
  position: fixed;
  top: 0;
  width: 100%;
}
#preview {
	transition: width 2s, height 2s;
	}
#preview {transition-timing-function: ease-in;}
#preview:hover {
    width: 265px;
    height: 265px;
}
h2{
	font-family: 'Hanna';
}
a {
	font-family: 'Hanna';
}
input {
	font-family: 'Gothic A1', sans-serif;
}
td {
	font-family: 'Hanna';
}
</style>

<title>
<decorator:title/>
</title>
<decorator:head/>
</head>
<body>
<div class="w3-sidebar w3-bar-block w3-card w3-animate-right" style="display:none;right:0;" id="rightMenu">
  <button onclick="closeRightMenu()" class="w3-bar-item w3-button w3-large">Close &times;</button>
  <c:if test="${empty sessionScope.loginMember.id}"><a href="${path }/login.sms" class="w3-bar-item w3-button">
  	<i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;로그인/회원가입!</a></c:if>
   <c:if test="${!empty sessionScope.loginMember.id }">
  	<hr size="1">
  	<a href="#" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;${sessionScope.loginMember.name }님 환영해여~&gt;&lt;</a></c:if>
  <c:if test="${!empty sessionScope.loginMember.id && sessionScope.loginMember.id != 'admin' }">
  	<hr size="1">
  	<a href="${path }/personal_info.sms?id=${sessionScope.loginMember.id }" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;나의 소중한 정보 !</a></c:if>
  <c:if test="${sessionScope.loginMember.id == 'admin' }">
  	<hr size="1">
  	<a href="${path }/admin/adminManagement.sms?id=${sessionScope.loginMember.id }" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;관리자 페이지 !</a></c:if>
  <c:if test="${!empty sessionScope.loginMember.id && sessionScope.loginMember.id != 'admin' }">
  	<hr size="1">
  	<a href="${path }/reserve/resList.sms?id=${sessionScope.loginMember.id}" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;나의 예약정보 !</a></c:if>
  <c:if test="${sessionScope.loginMember.memType == 1 }">
  	<hr size="1">
  	<a href="${path }/building/myBuildingList.sms?id=${sessionScope.loginMember.id}" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;공간 관리하기 !</a></c:if>
  <c:if test="${sessionScope.loginMember.memType == 1 && sessionScope.loginMember.id != 'admin'}">
  	<hr size="1">
  	<a href="${path }/reserve/hostResInfo.sms" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;공간 예약관리 하기 !</a></c:if>
  <c:if test="${!empty sessionScope.loginMember.id && sessionScope.loginMember.id != 'admin' }">
  	<hr size="1">
  	<a href="wishlist.sms" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;내가 찜한 공간 !</a></c:if>
  <c:if test="${!empty sessionScope.loginMember.id && sessionScope.loginMember.memType == '0'}">
  	<hr size="1">
  	<a href="${path }/becomeaHost.sms" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;호스트 등록 !</a></c:if>
  <c:if test="${!empty sessionScope.loginMember}">
  	<hr size="1">
  	<a href="${path}/qa/questionAdmin.sms?id=${sessionScope.loginMember.id}" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;1 : 1 문의 !</a></c:if>
  <hr size="1">
  <a href="${path }/notice/list.sms" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;공지사항 !</a>
  <hr size="1">
  <a href="${path }/terms_and_condition.sms" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;이용약관 !</a>
  <hr size="1">
  <c:if test="${!empty sessionScope.loginMember.id }"><a href="${path }/logout.sms" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;로그아웃..ㅜ</a></c:if>
</div>


<div class="w3-purple" style="height:450px" >
   <div class="header w3-purple" id="myHeader" style="height: 90px;">
   		<h1><strong><a href="${path }/main.sms" style="text-decoration:none">Share My Space</a></strong>
   			<button class="w3-button w3-purple w3-xlarge w3-right" onclick="openRightMenu()">&#9776;</button></h1></div>
<script>
window.onscroll = function() {myFunction()};

var header = document.getElementById("myHeader");
var sticky = header.offsetTop;

function myFunction() {
  if (window.pageYOffset > sticky) {
    header.classList.add("sticky");
  } else {
    header.classList.remove("sticky");
  }
}
</script>  			 		
  <div class="w3-content w3-center w3-section" style="max-width:500px">
	  <img class="mySlides" src="${path}/picture/mainimage.jpg" style="width:100%">
	  <img class="mySlides" src="${path}/picture/mainimage2.jpg" style="width:100%">
	  <img class="mySlides" src="${path}/picture/mainimage3.jpg" style="width:100%">
  	  <img class="mySlides" src="${path}/picture/mainimage4.jpg" style="width:100%">
  	  <img class="mySlides" src="${path}/picture/mainimage5.jpg" style="width:100%">
      </div></div>

<script>
//사진 이동
var myIndex = 0;
carousel();

function carousel() {
    var i;
    var x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";  
    }
    myIndex++;
    if (myIndex > x.length) {myIndex = 1}    
    x[myIndex-1].style.display = "block";  
    setTimeout(carousel, 4000); // Change image every 2 seconds
}
</script>

<script>
//메뉴바 열고 닫기
function openRightMenu() {
    document.getElementById("rightMenu").style.display = "block";
}
function closeRightMenu() {
    document.getElementById("rightMenu").style.display = "none";
}
</script>
<div class="w3-content" style="max-width:1400px">
<decorator:body/>
</div>
<div class="w3-container w3-light-gray" align="right"><p>	
<h2>
This is Sparta!!!!!
</h2>
</div>

</body>
</html>


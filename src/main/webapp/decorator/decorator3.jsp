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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Gothic+A1" rel="stylesheet">
<link href='http://fonts.googleapis.com/earlyaccess/hanna.css' rel='stylesheet'>
<style>
.mySlides {display:none;}
#myHeader{
	z-index: 101;
}
#rightMenu{
    z-index: 103;
}
.header {
  padding: 10px 16px;
  color: #f1f1f1;
}
.sticky {
  position: fixed;
  top: 0;
  width: 100%;
}
#picturee {
	transition: width 2s, height 2s;
	}
#picturee {transition-timing-function: ease-in;}
#picturee:hover {
    width: 180px;
    height: 180px;
}
input,h2,a,td {
	font-family: 'Gothic A1', sans-serif;
}

input {
	font-family: 'Hanna';
	color: 'black'
}
td, h1 {
	font-family: 'Hanna';
	color: 'black'
}
div {
	font-family: 'Hanna';
	color: 'black'
}
.mainImg { position: relative; padding-top: 500px; /* 1:1 ratio */ overflow: hidden; } 
.mainImg .centered { position: absolute; top: 0; left: 0; right: 0; bottom: 0; -webkit-transform: translate(50%,50%); -ms-transform: translate(50%,50%); transform: translate(50%,50%); } 
.mainImg .centered #mainImg { position: absolute; top: 0; left: 0; max-width: 100%; height: auto; -webkit-transform: translate(-50%,-50%); -ms-transform: translate(-50%,-50%); transform: translate(-50%,-50%); opacity: 0.9
</style>
<title>
<decorator:title/>
</title>
<decorator:head/>
</head>
<body>
<div class="w3-sidebar w3-bar-block w3-card w3-animate-right" style="display:none;right:0;" id="rightMenu">
  <button onclick="closeRightMenu()" class="w3-bar-item w3-button w3-large w3-hover-light-grey">닫기 &times;</button>
  	<hr size="1">
  <c:if test="${empty sessionScope.loginMember.id}"><a href="${path }/login.sms" class="w3-bar-item w3-button w3-hover-light-grey">
  	<i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;로그인/회원가입!</a><hr></c:if>
   <c:if test="${!empty sessionScope.loginMember.id }">
  	<a href="#" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;${sessionScope.loginMember.name }님 환영해여 !</a><hr size="1"></c:if>
  	
  <c:if test="${!empty sessionScope.loginMember.id && sessionScope.loginMember.id != 'admin' }">
  	<a href="${path }/personal_info.sms?id=${sessionScope.loginMember.id }" class="w3-bar-item w3-button w3-hover-light-grey"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;나의 소중한 정보 !</a><hr size="1"></c:if>
  	
  <c:if test="${sessionScope.loginMember.id == 'admin' }">
  	<a href="${path }/admin/adminManagement.sms?id=${sessionScope.loginMember.id }" class="w3-bar-item w3-button w3-hover-light-grey"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;관리자 페이지 !</a><hr size="1"></c:if>
  	
  <c:if test="${!empty sessionScope.loginMember.id && sessionScope.loginMember.id != 'admin' }">
  	<a href="${path }/reserve/resList.sms?id=${sessionScope.loginMember.id}" class="w3-bar-item w3-button w3-hover-light-grey"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;나의 예약정보 !</a><hr size="1"></c:if>
  <c:if test="${!empty sessionScope.loginMember.id && sessionScope.loginMember.id != 'admin' }">
  	<a href="${path }/building/wishlist.sms" class="w3-bar-item w3-button w3-hover-light-grey"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;내가 찜한 공간 !</a><hr size="1"></c:if>
  <c:if test="${sessionScope.loginMember.memType == 1 }">
  	<a href="${path }/building/myBuildingList.sms?id=${sessionScope.loginMember.id}" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;공간 관리하기 !</a><hr size="1"></c:if>
  <c:if test="${sessionScope.loginMember.memType == 1 && sessionScope.loginMember.id != 'admin'}">
  	<a href="${path }/reserve/hostResInfo.sms" class="w3-bar-item w3-button w3-hover-light-grey" ><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;공간 예약관리 하기 !</a><hr size="1"></c:if>
  <c:if test="${!empty sessionScope.loginMember.id && sessionScope.loginMember.memType == '0' && sessionScope.loginMember.regStatus != '0' && sessionScope.loginMember.id != 'admin'}">
  	<a href="${path }/becomeaHost.sms" class="w3-bar-item w3-button w3-hover-light-grey"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;호스트 등록 !</a><hr size="1"></c:if>
  <c:if test="${!empty sessionScope.loginMember && sessionScope.loginMember.id != 'admin'}">
  	<a href="${path}/qa/questionAdmin.sms?id=${sessionScope.loginMember.id}" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;1 : 1 문의 !</a><hr size="1"></c:if>
  	<a href="${path }/notice/list.sms" class="w3-bar-item w3-button w3-hover-light-grey"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;공지사항 !</a>
  	<hr size="1">
  <button onclick="document.getElementById('id01').style.display='block'" class="w3-bar-item w3-white w3-hover-light-grey"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;이용약관 !</button>
 	<hr size="1">
  <div id="id01" class="w3-modal w3-animate-opacity">
    <div class="w3-modal-content">
      <div class="w3-container">
        <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
          <script>
        window.onclick = function(event) {
        	var modal = document.getElementById('id01');
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
        </script>
        <div class="w3-container w3-margin w3-padding">
        
<p><h1 style="font-family:'Hanna';">[쉐어 마이 스페이스] 에 오신 것을 환영합니다.</h1></p>
<div class="w3-container" style="font-size:small;">
<p><font>[쉐어 마이 스페이스] 제품 및 서비스(‘서비스’)를 이용해 주셔서 감사합니다. 서비스는 1600 Amphitheatre Parkway, Mountain View, CA 94043, United States에 소재한 '쉐어 마이 스페이스' LLC(‘'쉐어 마이 스페이스'’)에서 제공합니다.</font></p>
<p><b>서비스를 이용함으로써 귀하는 본 약관에 동의하게 되므로 본 약관을 주의 깊게 읽어보시기 바랍니다.</b></p>
<p>'쉐어 마이 스페이스' 서비스는 매우 다양하므로 때로 추가약관 또는 제품 요구사항(연령 요건 포함)이 적용될 수 있습니다. 추가약관은 관련 서비스에 대하여 제공되며, 귀하가 해당 서비스를 이용하는 경우 이 추가약관은 귀하와 '쉐어 마이 스페이스' 간 계약의 일부가 됩니다.</p>

<p><h5 style="font-family:'Hanna';">[쉐어 마이 스페이스] 서비스 이용<h5></p>

<p><font style="font-size:small;">귀하는 서비스 내에서 적용되는 모든 정책을 준수해야 합니다.</font></p>

<p><font style="font-size:small;">'쉐어 마이 스페이스' 서비스의 오용을 삼가시기 바랍니다. 예를 들어 서비스를 방해하거나 '쉐어 마이 스페이스'이 제공하는 인터페이스 및 안내사항 이외의 다른 방법을 사용하여 액세스를 시도하지 않아야 합니다. 귀하는 관련 수출 및 재수출 통제 법규 및 규정 등 오직 법률상 허용되는 범위에서만 '쉐어 마이 스페이스' 서비스를 이용할 수 있습니다. 귀하가 '쉐어 마이 스페이스' 약관이나 정책을 준수하지 않거나 '쉐어 마이 스페이스'이 부정행위 혐의를 조사하고 있는 경우, '쉐어 마이 스페이스' 서비스 제공이 일시 중지 또는 중단될 수 있습니다.</font></p>

<p><font style="font-size:small;">'쉐어 마이 스페이스' 서비스를 사용한다고 해서 '쉐어 마이 스페이스' 서비스 또는 액세스하는 콘텐츠의 지적재산권을 소유하게 되는 것은 아닙니다. 콘텐츠 소유자로부터 허가를 받거나 달리 법률에 따라 허용되는 경우를 제외하고, '쉐어 마이 스페이스' 서비스의 콘텐츠를 사용할 수 없습니다.본 약관은 귀하에게 '쉐어 마이 스페이스' 서비스에 사용된 브랜드나 로고를 사용할 권리를 부여하지 않습니다. '쉐어 마이 스페이스' 서비스에 또는 '쉐어 마이 스페이스' 서비스와 함께 게재된 어떠한 법적 고지도 삭제하거나 감추거나 변경하지 마십시오.</font></p>

<p><font style="font-size:small;">'쉐어 마이 스페이스' 서비스는 '쉐어 마이 스페이스'이 소유하지 않은 일부 콘텐츠를 표시할 수 있습니다. 그러한 콘텐츠에 대해서는 제공한 주체가 단독으로 책임지게 됩니다. '쉐어 마이 스페이스'는 콘텐츠의 위법성 여부 또는 '쉐어 마이 스페이스' 정책 위반 여부를 결정하기 위해 검토할 수 있으며, 콘텐츠가 '쉐어 마이 스페이스' 정책 또는 법률에 위반된다고 합리적으로 판단하는 경우 이를 삭제하거나 게시를 거부할 수 있습니다. 그렇다고 반드시 콘텐츠를 검토한다는 의미는 아니므로, 콘텐츠를 검토할 것이라고 간주하지 마십시오.</font></p>

<p><font style="font-size:small;">서비스 이용과 관련하여 '쉐어 마이 스페이스'는 귀하에게 서비스 고지, 관리 메시지 및 기타 정보를 발송할 수 있습니다. 귀하는 메시지 수신을 거부할 수 있습니다.</font></p>

<p><font style="font-size:small;">일부 '쉐어 마이 스페이스' 서비스는 휴대기기에서 사용할 수 있습니다. 트래픽 또는 보안 관련 법규 준수를 방해하거나 막는 방식으로 서비스를 사용해서는 안 됩니다.</font></p>

<p><h5 style="font-family:'Hanna';">귀하의 [쉐어 마이 스페이스] 계정</h5></p>
<p>귀하가 '쉐어 마이 스페이스' 서비스를 이용하기 위해서는 '쉐어 마이 스페이스' 계정이 필요할 수 있습니다.
귀하가 '쉐어 마이 스페이스' 계정을 직접 만들 수도 있고, 고용주 또는 교육기관과 같은 관리자가 귀하에게 '쉐어 마이 스페이스' 계정을 배정할 수도 있습니다. 
관리자가 배정한 '쉐어 마이 스페이스' 계정을 사용하고 있는 경우, 별도의 약관 또는 추가약관이 적용될 수 있으며 관리자가 귀하의 계정에 액세스하거나 계정을 해지할 수 있습니다.</p>

<p>'쉐어 마이 스페이스' 계정을 보호하려면 비밀번호를 비공개로 유지하십시오. 
귀하는 '쉐어 마이 스페이스' 계정에서 또는 '쉐어 마이 스페이스' 계정을 통해 이루어지는 활동에 대한 책임이 있습니다.
타사 애플리케이션에서 '쉐어 마이 스페이스' 계정 비밀번호를 재사용하지 마십시오. 
귀하의 비밀번호나 '쉐어 마이 스페이스' 계정이 무단으로 사용되고 있음을 알게 되는 경우 다음 도움말을 참조하시기 바랍니다.</p>

<p><h5 style="font-family:'Hanna';">개인정보 보호 및 저작권 보호</h5></p>
<p>'쉐어 마이 스페이스' 개인정보처리방침은 귀하가 '쉐어 마이 스페이스' 서비스를 사용할 때 '쉐어 마이 스페이스'이 개인정보를 어떻게 취급하고 보호하는지에 대해 설명합니다.
'쉐어 마이 스페이스' 서비스를 사용함으로써 귀하는 '쉐어 마이 스페이스'이 개인정보처리방침에 따라 귀하의 개인정보를 사용할 수 있음에 동의하게 됩니다.<p>

<p>'쉐어 마이 스페이스'는 미국 디지털 밀레니엄 저작권법(US Digital Millennium Copyright Act)에 규정된 절차에 따라 저작권침해를 주장하는 신고에 대응하고, 반복 침해자의 계정을 해지합니다.<p>

<p>'쉐어 마이 스페이스'는 저작권자가 온라인상에서 자신의 지적 재산을 관리할 수 있도록 정보를 제공합니다. 
누군가 귀하의 저작권을 침해하고 있다고 생각되어 '쉐어 마이 스페이스'에 통지하고자 하는 경우, '쉐어 마이 스페이스' 도움말 센터에서 신고서 제출 방법 및 저작권 침해 신고에 대한 '쉐어 마이 스페이스' 대응 정책 관련 정보를 확인하실 수 있습니다.</p>

<p><h5 style="font-family:'Hanna';">[쉐어 마이 스페이스] 서비스에 포함된 귀하의 콘텐츠</h5></p>
<p>귀하는 일부 '쉐어 마이 스페이스' 서비스에서 콘텐츠를 업로드, 제출, 저장, 전송, 수신할 수 있으며 이에 대해 귀하가 보유한 지적재산권은 귀하의 소유입니다. 즉, 귀하가 보유한 권리는 귀하에게 존속됩니다.</p>

<p>귀하가 콘텐츠를 '쉐어 마이 스페이스' 서비스로 또는 이를 통해 업로드, 제출, 저장, 전송 또는 수신하는 경우 귀하는 '쉐어 마이 스페이스'(및 '쉐어 마이 스페이스'의 협력사)이 이러한 콘텐츠를 사용, 저장, 복제, 수정, 이차적 저작물(귀하의 콘텐츠가 '쉐어 마이 스페이스' 서비스와 더 잘 작동하도록 '쉐어 마이 스페이스'이 생성하는 번역본, 개작본, 또는 수정본으로 인해 발생되는 것) 제작, 전달, 공개, 공개적으로 실연, 공개적으로 게시 및 배포할 수 있는 전 세계적인 라이선스를 제공하게 됩니다. 
본 라이선스에서 귀하가 부여하는 권리는 '쉐어 마이 스페이스' 서비스를 운영, 홍보 및 개선하고 새로운 서비스를 개발하기 위한 제한적인 목적으로 사용됩니다. 
본 라이선스는 귀하가 '쉐어 마이 스페이스' 서비스의 사용을 중지한 후에도 존속됩니다(예: '쉐어 마이 스페이스' 지도에 추가한 업체 정보). 일부 서비스에서는 제공한 콘텐츠에 액세스하고 이를 삭제하는 방법을 제공할 수 있습니다. 
또한 서비스 중 일부에는 제출된 콘텐츠에 대한 '쉐어 마이 스페이스'의 사용 범위를 제한하는 약관 또는 설정이 있습니다. 
귀하는 '쉐어 마이 스페이스' 서비스에 제출한 콘텐츠에 대해 '쉐어 마이 스페이스'에 라이선스를 부여하기 위해 필요한 권리를 보유해야 합니다.</p>

<p>'쉐어 마이 스페이스'의 자동 시스템은 맞춤 검색결과, 맞춤 광고, 스팸/멀웨어 감지 등 귀하에게 유용한 제품 기능을 제공할 목적으로 귀하의 콘텐츠(이메일 포함)를 분석합니다. 이러한 분석은 콘텐츠 전송, 수신, 저장 시에 수행됩니다.</p>

<p>귀하가 '쉐어 마이 스페이스' 계정을 사용하는 경우 '쉐어 마이 스페이스'는 귀하의 프로필 이름, 프로필 사진 및 '쉐어 마이 스페이스' 또는 '쉐어 마이 스페이스' 계정에 연결된 타사 애플리케이션에서 수행하는 작업(예: +1, 리뷰, 댓글 등)을 '쉐어 마이 스페이스' 서비스에 표시할 수 있습니다(광고 및 기타 상업적 용도로 표시하는 경우 포함). 
'쉐어 마이 스페이스'는 '쉐어 마이 스페이스' 계정에서 공유 또는 공개 설정을 제한하는 귀하의 선택을 존중합니다. 
예를 들어, 귀하의 이름과 사진이 광고에 표시되지 않도록 설정할 수 있습니다.</p>

<p>'쉐어 마이 스페이스'이 콘텐츠를 사용 및 저장하는 방법에 대해 좀 더 자세한 정보가 필요한 경우, '쉐어 마이 스페이스' 개인정보처리방침 또는 특정 서비스에 대한 추가약관을 참조하시기 바랍니다. 
귀하가 '쉐어 마이 스페이스' 서비스에 대한 의견이나 제안을 제출하는 경우 '쉐어 마이 스페이스'는 귀하에 대한 의무 없이 귀하의 의견이나 제안을 사용할 수 있습니다.</p>

<p><h5 style="font-family:'Hanna';">[쉐어 마이 스페이스] 서비스에 포함된 소프트웨어에 대하여</h5></p>
<p>서비스에 다운로드 가능한 소프트웨어가 필요하거나 포함되는 경우, 소프트웨어에 대한 새로운 버전이나 기능이 제공되는 즉시 기기에서 소프트웨어가 자동으로 업데이트될 수 있습니다.
 일부 서비스에서는 자동 업데이트 설정을 사용자가 직접 조정할 수도 있습니다.</p>

<p>'쉐어 마이 스페이스'는 귀하에게 서비스의 일부로 제공하는 소프트웨어를 사용할 수 있도록 개인적이고 전 세계적이며 양도불가능하고 비독점적인 무상 라이선스를 제공합니다. 
이 라이선스는 본 약관에 따라 귀하가 '쉐어 마이 스페이스'이 제공한 대로 서비스를 사용하고 혜택을 누릴 수 있도록 하기 위한 목적으로만 제공됩니다. 
귀하는 법률상 이와 같은 제한이 금지되거나 '쉐어 마이 스페이스'의 서면허가를 받은 경우를 제외하고, '쉐어 마이 스페이스' 서비스 또는 이에 포함된 소프트웨어의 일부를 복사, 수정, 배포, 판매 또는 대여할 수 없으며, 소프트웨어를 역설계하거나 소스 코드의 추출을 시도할 수 없습니다.</p>

<p>'쉐어 마이 스페이스'에서 오픈 소스 소프트웨어는 매우 중요한 의미를 가집니다. 
'쉐어 마이 스페이스' 서비스에서 사용되는 일부 소프트웨어는 귀하에게 제공하는 오픈 소스 라이선스에 따라 제공될 수 있습니다. 
오픈 소스 라이선스에는 명시적으로 본 약관의 일부 규정에 우선하는 규정이 있을 수 있습니다.<p>

<p><h5 style="font-family:'Hanna';">[쉐어 마이 스페이스] 서비스의 수정 및 해지</strong></h5></p>
<p>'쉐어 마이 스페이스'는 서비스를 지속적으로 변경 및 개선하고 있습니다. '쉐어 마이 스페이스'는 기능을 추가 또는 제거할 수 있으며, 서비스를 일시 중지하거나 완전히 중단할 수 있습니다.<p>

<p>'쉐어 마이 스페이스'로서는 매우 안타까운 일이지만, 귀하는 언제라도 '쉐어 마이 스페이스' 서비스 이용을 중지할 수 있습니다. 
'쉐어 마이 스페이스' 또한 언제든지 서비스 제공을 중단하거나 '쉐어 마이 스페이스' 서비스에 대해 새로운 제한을 추가하거나 만들 수 있습니다.</p>

<p>귀하의 데이터는 귀하 소유이며, 이러한 데이터에 계속 액세스하도록 하는 것이 매우 중요하다고 '쉐어 마이 스페이스'는 믿고 있습니다. 
'쉐어 마이 스페이스'는 서비스를 중지할 때 합리적으로 가능한 경우 귀하에게 합당한 사전 통지를 제공하고 귀하가 해당 서비스에서 정보를 반출할 기회를 제공할 것입니다.</p>

<p><h5 style="font-family:'Hanna';">[쉐어 마이 스페이스]의 보증 및 부인</strong></h5></p>
<p>'쉐어 마이 스페이스'는 상업적으로 합리적인 수준의 기술을 사용하고 주의를 기울여 서비스를 제공하며, 귀하가 서비스를 누리게 되길 바랍니다. 단, 서비스에 대하여 약속할 수 없는 몇 가지 사항이 있습니다.</p>

<p>본 약관 또는 추가약관에 명시되지 않은 한 '쉐어 마이 스페이스', 또는 '쉐어 마이 스페이스'의 공급자나 판매자는 서비스와 관련하여 어떠한 구체적인 약정도 하지 않습니다. 
예를 들어, '쉐어 마이 스페이스'는 서비스에 속한 콘텐츠, 서비스의 특정 기능, 서비스의 신뢰성, 이용가능성 또는 귀하의 요구를 충족할 능력에 대하여 어떠한 약정도 하지 않습니다. 
'쉐어 마이 스페이스'는 서비스를 ‘있는 그대로’ 제공합니다.</p>

<p>일부 사법관할권 내에서는 상품성, 특정목적에의 적합성 및 비침해성에 대한 묵시적 보증과 같은 특정 보증이 인정됩니다. '쉐어 마이 스페이스'는 법률상 허용되는 한도 내에서 모든 보증을 배제합니다.</p>

<p><h5 style="font-family:'Hanna';">[쉐어 마이 스페이스] 서비스에 대한 책임</h5></p>
<p>법률상 허용되는 경우, '쉐어 마이 스페이스', '쉐어 마이 스페이스'의 공급자 및 판매자는 일실이익, 일실수입, 망실자료, 재무적 손실, 간접 손해, 특별 손해, 결과적 손해, 징계적 손해, 또는 징벌적 손해에 대한 책임을 부담하지 않습니다.</p>

<p>법률상 허용되는 한도 내에서, 묵시적 보증을 포함하여 본 약관에 따른 청구에 대한 '쉐어 마이 스페이스', '쉐어 마이 스페이스'의 공급자 및 판매자의 총 책임은 귀하가 서비스 사용을 위해 '쉐어 마이 스페이스'에 
지급한 금원(또는 '쉐어 마이 스페이스'의 선택에 따라 귀하에게 서비스를 다시 제공하는 것)으로 제한됩니다.</p>

<p>어떤 경우에도 '쉐어 마이 스페이스', '쉐어 마이 스페이스'의 공급자 및 판매자는 합리적으로 예측 불가능한 손실이나 손해에 대해 책임지지 않습니다.</p>

<p><h5 style="font-family:'Hanna';">업체의 [쉐어 마이 스페이스] 서비스 사용</h5></p>
<p>업체를 대신하여 '쉐어 마이 스페이스' 서비스를 사용하는 경우 다음 약관이 적용됩니다. 
서비스 이용 또는 해당 약관 위반으로 인해 발생하거나 이와 관련된 모든 소송, 고소 또는 조치로부터 '쉐어 마이 스페이스'과 '쉐어 마이 스페이스'의 제휴사, 임원, 
대행사 및 직원을 보호하고 면책해야 하며 여기에는 각종 책임과 소송, 손실, 피해, 고소, 판결, 소송 비용 및 변호사 수임료 등으로 인한 비용도 포함됩니다.</p>

<p><h5 style="font-family:'Hanna';">[본 약관에 대하여]</h5></p>
<p>'쉐어 마이 스페이스'는 법률의 변경이나 서비스의 변경사항을 반영하기 위한 목적 등으로 본 약관이나 서비스에 적용되는 추가약관을 수정할 수 있습니다. 
귀하는 정기적으로 약관을 확인해야 합니다. 
본 페이지의 약관이 변경되는 경우 '쉐어 마이 스페이스'는 이에 대한 공지를 게시합니다. 
'쉐어 마이 스페이스'는 추가약관의 변경 공지를 해당 서비스에 게재할 것입니다. 
당해 변경은 소급되어 적용되지 않으며 게시하고 14일 이후에 발효됩니다. 
단, 서비스에 대한 새로운 기능과 관련된 변경이나 법률적인 사유로 인한 변경은 즉시 발효됩니다. 
서비스에 대해 변경된 약관에 동의하지 않는 경우 해당 서비스의 사용을 중지해야 합니다.</p>

<p>본 약관과 추가약관이 상충하는 경우, 상충하는 사항에 대하여 추가약관이 적용됩니다.

본 약관은 '쉐어 마이 스페이스'과 귀하와의 관계를 규율하며, 어떠한 제3자의 수익권도 발생시키지 않습니다.

귀하가 본 약관을 준수하지 않은 경우, 당사가 즉시 조치를 취하지 않더라도 이는 당사가 가지고 있는 권리(향후 조치를 취하는 것 등)를 포기함을 의미하지 않습니다.

특정 조항이 집행 불가능한 것으로 판명되는 경우, 이는 다른 조항에 영향을 미치지 않습니다.

본 약관 또는 서비스와 관련하여 발생하는 분쟁에 대해서는 미국 캘리포니아주 법률이 적용되며, 캘리포니아 주 국제사법의 적용은 배제됩니다. 본 약관 또는 서비스와 관련하여 발생되는 모든 소송은 독점적으로 미국 캘리포니아주 산타클라라 
카운티의 연방 또는 주 법원에서 다루어지며 귀하와 '쉐어 마이 스페이스'는 이러한 법원이 인적 관할을 갖는 것에 동의합니다.</p>
</div>
</div>
      </div>
    </div>
  </div>
  <c:if test="${!empty sessionScope.loginMember.id }"><a href="${path }/logout.sms" class="w3-bar-item w3-button"><i class="fa fa-arrow-circle-right"></i>&nbsp;&nbsp;로그아웃..ㅜ</a></c:if>
  <hr size="1">
</div>


<div class="w3-deep-purple" style="height:90px" >
   <div class="header w3-deep-purple" id="myHeader" style="height:90px;">
   		<h1><strong><a href="${path }/main.sms" style="text-decoration:none">Share My Space</a></strong>
   			<button class="w3-button w3-deep-purple w3-xlarge w3-right" onclick="openRightMenu()">&#9776;</button></h1></div>
      </div>
<div class="w3-content" style="max-width:1400px">
<decorator:body/>
</div>
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
<div class="w3-container w3-light-gray">	
<div class="w3-container w3-margin w3-padding w3-row">
<div class="w3-col s2"><p>&nbsp;</p></div>

<div class="w3-col s8 w3-margin">
	<div class="w3-margin w3-container">
		<font style="font-family:'Hanna'; font-size:xx-large;">Share My Space</font>
	</div>
	<div class="w3-container w3-margin w3-text-gray" style="font-size:small;">
	<p>상호명 : 쉐어마이스페이스 | 사업자등록번호 : 100-10-10000</p>
	<p>동신판매업신고번호:2018-서울금천구-1000 | 대표 : 박선민, 신진호, 이승환, 정인선, 최재원</p>
	<p>영업소재지 : 서울특별시 금천구 가산디지털2로 115 509호 구디아카데미, 대륭테크노타운 3차</p>
	<p>이메일 : shareMySpace@Andromeda.or.kr</p>
	<p>대표전화 : 02-0000-1111 </p>
	<c:if test="${loginMember != null }">
		<p><a href="${path}/qa/questionAdmin.sms?id=${loginMember.id}">온라인 1:1 문의 바로가기</a> (평일 오전 10시 ~ 오후 6시 30분)</p>
	</c:if>
	<p>*공간에 대한 문의사항은 해당 공간 호스트에게 직접 문의해주세요.</p>
	</div> 
</div>
</div>		
		
<div class="w3-col s2"><p>&nbsp;</p></div>
</div>

</body>
</html>


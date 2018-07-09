<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
var reg1 =  /^[가-힣\s]+$/;
var reg2 =  /^[0-9]+$/;
function check() {
	if($('#hostName').val()==''){
		alert("상호명/호스트이름을 입력하세요~><");
		document.getElementById('hostName').focus();
	} 
	else if($('#hostRegNo').val() =='' || !reg2.test($('#hostRegNo').val()) ){
		alert("사업자 등록번호를 확인하세요~><");
		document.getElementById('hostRegNo').focus();
	} else if($('#picture').val()==''){
		alert("사업자 등록증을 업로드하세요~><");
		document.getElementById('picture').focus();
	} else if($('#zipcode').val()=='' || !reg2.test($('#zipcode').val())){
		alert("우편번호를 확인하세요~><");
		document.getElementById('zipcode').focus();
	} else if($('#asdf').val()==''){
		alert("주소를 입력하세요~><");
		document.getElementById('asdf').focus();
	} else if($('#accountNo1').val()=='' || !reg1.test($('#accountNo1').val())){
		alert("은행명을 확인하세요~><");
		document.getElementById('accountNo1').focus();
	} else if($('#accountNo2').val()=='' || !reg2.test($('#accountNo2').val())){
		alert("계좌번호를 확인하세요~><");
		document.getElementById('accountNo2').focus();
	}else if($('#accountNo3').val()=='' ||!reg1.test($('#accountNo3').val())){
		alert("입금자명을 확인하세요~><");
		document.getElementById('accountNo3').focus();
	}else if($('#tel').val()==''|| reg2.test($('#tel').val())){
		alert("전화번호를 입력하세요~><");
		document.getElementById('tel').focus();
	}else{
		confirm("정보는 제대로 다 적으셨나여? ><");
		document.f.submit();
	}
}
</script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script>
function autohypen(){
	var x = document.getElementById("tel"); //tel을 선택해서
	x.value = x.value.replace(/[^0-9]/g, ''); //0-9를 ''으로 바꾼다 x.value는 뭐 암것도 없음. 그냥 변수
	console.log(x.value); //11111111111 이렇게 나옴
	var tmp = "";
	if (x.value.length > 3 && x.value.length <= 7) { //length구해서 -필요한 곳마다 넣기
		tmp += x.value.substr(0, 3);
		tmp += '-';
		tmp += x.value.substr(3);
		x.value = tmp;
		return x.value;
	} else if (x.value.length > 7) {
		tmp += x.value.substr(0, 3);
		tmp += '-';
		tmp += x.value.substr(3, 4);
		tmp += '-';
		tmp += x.value.substr(7);
		x.value = tmp;
		return x.value;
	}
}
function find_address(){
	var op = "width=500, height=200, scrollbars=yes, left=70, top=150";
	window.open("find_address.sms","",op); //하나밖에 안뜸. picture는 생략가능
}
</script>
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=2bA8v55yYLf1omsHnKFk&submodules=geocoder"></script>
</head>
<body>
<form:form modelAttribute="member" action="addhostdata.sms" name="f" method="POST" enctype="multipart/form-data">
	<input type="hidden" id="regStatus" name="regStatus" value="0"/>
	<input type="hidden" id="id" name="id" value="${sessionScope.loginMember.id }">
	
		<table align="center">
		<tr>
			<td align="left">상호명/호스트이름</td><td style="width:auto;"><input type="text" name="hostName" id="hostName" placeholder="상호명/호스트이름"></td></tr>
		<tr>
			<td align="left">사업자 등록번호</td><td><input type="text" name="hostRegNo" id="hostRegNo" placeholder="하이픈(-) 없이 숫자만!"></td></tr>
		<tr>
			<td align="left"> 사업자 등록증</td><td><input type="file" name="picture" id="picture"></tr></table>
			
	<hr size="1" style="margin-top:1px; margin-bottom:1px">
		<table align="center">
		<tr>
			<td><strong>사업자 주소지</strong></td></tr>
		<tr>
			<td>우편번호</td><td><input type="text" name="address" id="zipcode" class="postcodify_postcode5" value="" />
				<input type="button" id="postcodify_search_button" value="검색"></td></tr>
		<tr>
			<td>도로명 주소</td><td><input type="text" id="asdf" name="address" class="postcodify_address" value=""/></td></tr>
		<tr>
			<td>상세 주소</td><td><input type="text" name="address" id="details" class="postcodify_details" value="" onfocus="drawmap()"/></td></tr>
		<tr><td colspan="2"><div id="map" style="width:100%;height:200px;"></div></td></tr>
			</table>
<script>
function drawmap(){ 
	var map = new naver.maps.Map('map');
    var myaddress = $('#asdf').val();// 도로명 주소나 지번 주소만 가능 (건물명 불가!!!!)
    console.log(myaddress);
    naver.maps.Service.geocode({address: myaddress}, function(status, response) {
        if (status !== naver.maps.Service.Status.OK) {
            return alert(myaddress + '의 검색 결과가 없거나 기타 네트워크 에러');
        }
        var result = response.result;
        // 검색 결과 갯수: result.total
        // 첫번째 결과 결과 주소: result.items[0].address
        // 첫번째 검색 결과 좌표: result.items[0].point.y, result.items[0].point.x
        var myaddr = new naver.maps.Point(result.items[0].point.x, result.items[0].point.y);
        map.setCenter(myaddr); // 검색된 좌표로 지도 이동
        // 마커 표시
        var marker = new naver.maps.Marker({
          position: myaddr,
          map: map
        });
        // 마커 클릭 이벤트 처리
        naver.maps.Event.addListener(marker, "click", function(e) {
          if (infowindow.getMap()) {
              infowindow.close();
          } else {
              infowindow.open(map, marker);
          }
        });
    });
  }
</script>
<script>
var map = new naver.maps.Map('map');
var myaddress = "한강대로 405";// 도로명 주소나 지번 주소만 가능 (건물명 불가!!!!)
console.log(myaddress);
naver.maps.Service.geocode({address: myaddress}, function(status, response) {
    if (status !== naver.maps.Service.Status.OK) {
        return alert(myaddress + '의 검색 결과가 없거나 기타 네트워크 에러');
    }
    var result = response.result;
    // 검색 결과 갯수: result.total
    // 첫번째 결과 결과 주소: result.items[0].address
    // 첫번째 검색 결과 좌표: result.items[0].point.y, result.items[0].point.x
    var myaddr = new naver.maps.Point(result.items[0].point.x, result.items[0].point.y);
    map.setCenter(myaddr); // 검색된 좌표로 지도 이동
    // 마커 표시
    var marker = new naver.maps.Marker({
      position: myaddr,
      map: map
    });
    // 마커 클릭 이벤트 처리
    naver.maps.Event.addListener(marker, "click", function(e) {
      if (infowindow.getMap()) {
          infowindow.close();
      } else {
          infowindow.open(map, marker);
      }
    });
});
</script>
	<hr size="1" style="margin-top:1px; margin-bottom:1px">
		<table align="center">			
		<tr><td rowspan="3" align="left">계좌정보</td>
			<td><input type="text" name="accountNo" id="accountNo1"  placeholder="은행명 !"/></td></tr>
		<tr>
			<td><input type="text" name="accountNo" id="accountNo2" placeholder="계좌번호: 하이픈(-) 제외 !"/></td></tr>
		<tr>
			<td><input type="text" name="accountNo" id="accountNo3" placeholder="예금주 : 이름 !"/></td></tr>
		<tr>
			<td align="left">사업자 연락처</td>
				<td><input type="text" name="tel" id="tel" placeholder="하이픈(-) 없이 !" onkeyup="autohypen()" maxlength="13"></td></tr></table>
	<hr size="1" style="margin-top:1px; margin-bottom:1px">
		<table align="center">
		<tr><td></td><td align="left">
				<input type="button" value="호스트 승인 요청" onclick="check()" id="request">
				<input type="button" onclick="javascript:history.go(-1)" value="뒤로가기"></td></tr></table>
</form:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공간 등록</title>
<style type="text/css">
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
/*건물유형 관련 스크립트*/
function chkboxcheck() {
	var sTypeList = document.getElementsByName("sTypeList")
	var cnt = 0;
	for(var i=0;i<sTypeList.length;i++) {
		if (sTypeList[i].checked) cnt++;
		if(cnt > 3)	{
			alert("공간 유형은 3개만 선택 가능합니다.");
			sTypeList[i].checked=false;
			return
		}
	}
}

/*태그관련 스크립트*/
var tagarr = new Array(5);
var idx=0;
var t;
$(document).ready(function() {
	$("#addtag").click(function(){
		 tl = document.getElementById("tagList");
		 ti = "";
		 t = document.getElementById("tag"); //입력하는 곳
		 tagarr[idx++] = t.value;
		 for (var i=0;i<idx;i++) {
			 ti += "<span name='sTag' id='tags" + i +"'>#"+tagarr[i] +
			 "<input type='button' onclick=\"tagdel("+i +")\" value='X'></input>&nbsp;</span>"+
			 "<input type='hidden' name='sTagList' value='"+tagarr[i]+"'>";
		 }
		 tl.innerHTML=ti;
		 size = idx;
		 if(size>=5) {
			 $("#addtag" ).attr( 'disabled', true );
		 }
		 $("#tag").val("");
	 });
});
function tagdel(di) {

    tagarr.splice(di, 1)

     ti=""
     idx--
	 for (var i=0;i<idx;i++) {
		 ti += "<span name='sTag" + i +"'>#"+tagarr[i] +
		 "<input type='button' onclick=\"tagdel("+i+")\" value='X'></input>&nbsp;</span>" +
		 "<input type='hidden' name='sTagList' value='"+tagarr[i]+"'>";
	 }
	 tl = document.getElementById("tagList");
	 tl.innerHTML=ti;
	 $("#addtag" ).attr( 'disabled', false );
}

/*시설안내관련 스크립트*/
var infoarr = new Array(10);
var infoidx=0;
var info;
$(document).ready(function() {
	$("#addinfo").click(function(){
		 il = document.getElementById("infoList");
		 ii = "";
		 info = document.getElementById("info"); //입력하는 곳
		 infoarr[infoidx++] = info.value;
		 for (var i=0;i<infoidx;i++) {
			 ii += "<span name='sInfoSub' id='infos" + i +"'>"+(i+1)+". "+infoarr[i] +
			 "<input type='button' onclick=\"infodel("+i +")\" value='X'></input>&nbsp;</span><br>" +
			 "<input type='hidden' name='sInfoSubList' value='"+infoarr[i]+"'>";
		 }
		 il.innerHTML=ii;
		 size = infoidx;
		 if(size>=10) {
			 $("#addinfo" ).attr('disabled', true );
		 }
		 $("#info").val("");
	 });
});
function infodel(di) {

    infoarr.splice(di, 1)

     ii=""
     infoidx--
	 for (var i=0;i<infoidx;i++) {
		 ii += "<span id='infos" + i +"'>"+(i+1)+". "+infoarr[i] +
		 "<input type='button' onclick=\"infodel("+i+")\" value='X'></input>&nbsp;</span><br>" +
		 "<input type='hidden' name='sInfoSubList' value='"+infoarr[i]+"'>";
	 }
	 il = document.getElementById("infoList");
	 il.innerHTML=ii;
	 $("#addinfo" ).attr( 'disabled', false );
}

/*시설안내관련 스크립트*/
var rulearr = new Array(10);
var ruleidx=0;
var rule;
$(document).ready(function() {
	$("#addrule").click(function(){
		 rl = document.getElementById("ruleList");
		 ri = "";
		 rule = document.getElementById("rule"); //입력하는 곳
		 rulearr[ruleidx++] = rule.value;
		 for (var i=0;i<ruleidx;i++) {
			 ri += "<span name='sRule' id='rules" + i +"'>"+(i+1)+". "+rulearr[i] +
			 "<input type='button' onclick=\"ruledel("+i +")\" value='X'></input>&nbsp;</span><br>"+
			 "<input type='hidden' name='sRuleList' value='"+rulearr[i]+"'>";
		 }
		 rl.innerHTML=ri;
		 size = ruleidx;
		 if(size>=10) {
			 $("#addrule" ).attr('disabled', true );
		 }
		 $("#rule").val("");
	 });
});
function ruledel(di) {

    rulearr.splice(di, 1)

     ri=""
     ruleidx--
	 for (var i=0;i<ruleidx;i++) {
		 ri += "<span name='sRule' id='rules" + i +"'>"+(i+1)+". "+rulearr[i] +
		 "<input type='button' onclick=\"ruledel("+i+")\" value='X'></input>&nbsp;</span><br>"+
		 "<input type='hidden' name='sRuleList' value='"+rulearr[i]+"'>";
	 }
	 rl = document.getElementById("ruleList");
	 rl.innerHTML=ri;
	 $("#addrule" ).attr( 'disabled', false );
}

/*휴무일 직접입력 관련 스크립트*/
$(document).ready(function() {
	$("#direct").click(function(){
		var chk = $("input:checkbox[id='direct']").is(":checked")
		var dir = document.getElementById("directsHDay")
		var dirTag = "<tr><td></td><td><input id='sHDaydir' name='sHDay'/></td></tr>";
		if(chk) {
			$("#sHDayList" ).attr('disabled', true );
			dir.innerHTML = dirTag
		} else {
			$("#sHDayList" ).attr('disabled', false );
			$("#directsHDay" ).empty();
		}
	});
});

/*전화번호 관련 스크립트*/
function chk_tel(){ 
  var str = $('#sTel').val(); 
  console.log(str);
  var field = document.getElementById('sTel');
  str = checkDigit(str); 
  len = str.length; 
  
  if(len==8){ 
  if(str.substring(0,2)==02){ 
    error_numbr(str, field); 
  }else{ 
    field.value  = phone_format(1,str); 
  }  
  }else if(len==9){ 
  if(str.substring(0,2)==02){ 
    field.value = phone_format(2,str); 
  }else{ 
    error_numbr(str, field); 
  } 
  }else if(len==10){ 
  if(str.substring(0,2)==02){ 
    field.value = phone_format(2,str); 
  }else{ 
    field.value = phone_format(3,str); 
  } 
  }else if(len==11){ 
  if(str.substring(0,2)==02){ 
    error_numbr(str, field); 
  }else{ 
    field.value  = phone_format(3,str); 
  } 
  }else{ 
  error_numbr(str, field); 
  } 
 } 
 function checkDigit(num){ 
  var Digit = "1234567890"; 
  var string = num; 
  var len = string.length 
  var retVal = ""; 
  for (i = 0; i < len; i++){ 
  if (Digit.indexOf(string.substring(i, i+1)) >= 0){ 
    retVal = retVal + string.substring(i, i+1); 
  } 
  } 
  return retVal; 
 } 
 function phone_format(type, num){ 
  if(type==1){ 
  return num.replace(/([0-9]{4})([0-9]{4})/,"$1-$2"); 
  }else if(type==2){ 
  return num.replace(/([0-9]{2})([0-9]+)([0-9]{4})/,"$1-$2-$3"); 
  }else{ 
  return num.replace(/(^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3"); 
  } 
 } 
 function error_numbr(str, field){ 
  alert("정상적인 번호가 아닙니다."); 
  field.value = ""; 
  field.focus(); 
 } 
	 
/*유효성 관련 스크립트*/
var reg1 =  /^[가-힣\s]+$/;
      var reg2 =  /^[0-9]+$/;
   function checkb() {
      var sType_chk = false;
      var sTypeList = document.getElementsByName("sTypeList")
      var sTag = document.getElementsByName("sTag")
      var sInfo = document.getElementsByName("sInfoSub")
      var sRule = document.getElementsByName("sRule")
   
   for(var i=0;i<sTypeList.length;i++) {
      if (sTypeList[i].checked==true){
         sType_chk = true;
         break;
         }
      }
      if(!sType_chk){
         alert("하나 이상 체크하세요.")
      return false;
   }
   if($('#sName').val()=='' ){
      alert("공간 이름을 작성하세요~><");
      document.getElementById('sName').focus();
   } else if($('#sPreview').val()=='' ){
      alert("한줄 소개를 작성하세요~><");
      document.getElementById('sPreview').focus();
   } else if($('#sContent').val()=='' ){
      alert("공간 소개를 작성하세요~><");
      document.getElementById('sContent').focus();
   }   else if(sTag.length == 0 ){
      alert("태그 설정을 입력하세요~><");
      document.getElementById('tag').focus();
   } else if(sInfo.length==0){
      alert("시설안내를 입력하세요~><");
      document.getElementById('info').focus();
   } else if($('#sImg1').val()=='' ){
      alert("대표이미지를 확인하세요~><");
      document.getElementById('info').focus();
    } else if($('#zipcode').val()==''){
      alert("우편번호를 확인하세요~><");
      document.getElementById('details').focus();
   }else if($('#sTel').val()==''|| reg2.test($('#sTel').val())){
      alert("전화번호를 입력하세요~><");
      document.getElementById('sTel').focus();
   } else if(sRule.length==0){
      alert("이용시 주의사항을 입력하세요~><");
      document.getElementById('rule').focus();
   }else{ 
      var res = confirm("정보는 제대로 다 적으셨나여? ><");
      if(res == true){
      document.bf.submit();
      }
   }
}

</script>

<!-- 지도관련 스크립트 -->
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=2bA8v55yYLf1omsHnKFk&submodules=geocoder"></script>

</head>
<body>
<div>
<form:form id="sss" modelAttribute="building" method="post" action="buildingReg.sms" enctype="multipart/form-data" name="bf">
<input type="hidden" name="id" value="${param.id}" />
  <table cellpadding="0" cellspacing="1" align="center">
  <tr><td colspan="2" align="center">공간정보</td></tr>
    <tr><td>공간 유형(최대 3개)</td>
        <td>
        <form:checkboxes path="sTypeList" items="${sTypeNames}" onchange="chkboxcheck()" style="margin-left:10px;"/>  
        </td></tr>
           
    <tr><td>공간 이름(최대 20자)</td><td><form:input path="sName" /></td></tr>
    <tr><td>공간 한줄 소개(최대 30자)</td><td><form:input path="sPreview" /></td></tr>
    <tr><td>공간 소개(최대 500자)</td><td><form:textarea cols="50px" rows="10px" path="sContent" /></td></tr>
    
    <tr><td>태그 설정(최대 5개)</td>
        <td><input id="tag" />
            <input type="button" id="addtag" value="추가" />
            </td></tr>
    <tr><td></td><td> <div id="tagList">
              <!-- 태그 추가시 웹상 보여지는 부분 -->
            </div></td></tr>

        
    <tr><td>시설안내(최대 10개)</td>
        <td><input id="info" />
            <input type="button" id="addinfo" value="추가" /></td></tr>
    <tr><td></td><td> <div id="infoList">
              <!-- 시설안내 추가시 웹상에서 보여지는 부분 -->
            </div></td></tr>
    
    <tr><td>대표이미지</td>
        <td><input type="file" id="sImg1" name="sImg1File" accept="image/*"/></td></tr>
    
    <tr><td>이미지</td>
        <td>
        <input multiple="multiple" type="file" id="sImg2" name="sImg2Files" accept="image/*" /></td></tr>
    
    <tr>
			<td><strong>사업자 주소지</strong></td></tr>
		<tr>
			<td>우편번호</td><td><input type="text" name="sAddress" id="zipcode" class="postcodify_postcode5" value="" readonly/>
				<input type="button" id="postcodify_search_button" value="검색"></td></tr>
		<tr>
			<td>도로명 주소</td><td><input type="text" id="asdf" name="sAddress" class="postcodify_address" value="" readonly/></td></tr>
		<tr>
			<td>상세 주소</td><td><input type="text" name="sAddress" id="details" class="postcodify_details" value="" onfocus="drawmap()"/></td></tr>
		<tr><td colspan="2"><div id="map" style="width:100%;height:200px;"></div></td></tr>
                
     <tr><td colspan="2" align="center">이용정보</td></tr>
     <tr><td>이용시간</td>
         <td><select name="sBHourList">
         <c:forEach var="i" begin="0" end="23" >
         <option>${i}시</option></c:forEach></select>부터
         <select name="sBHourList">
         <c:forEach var="i" begin="1" end="24" >
         <option>${i}시</option></c:forEach></select>까지</td></tr>
     
     <tr><td>정기휴무</td>
         <td>
         <select id="sHDayList" name="sHDay">
         <option>휴무없음</option>
         <option>공휴일휴무</option>
         <option>명절연휴휴무</option>
         <option>명절당일휴무</option>
         <option>주말휴무</option>
         <option>토요일휴무</option>
         <option>일요일휴무</option>
         </select>
         <input type="checkbox" id="direct" /> 직접입력
         <div id="directsHDay"><!-- 직접입력을 누르면 input태그가 생기는 부분 -->
         <!-- <tr><td></td><td><input id="directInput" /></td></tr> -->
         </div></td></tr>
    <tr><td align="left">공간 연락처</td>
		<td>
		<input type="text" name="sTel" id="sTel"> 
		</td></tr>
 
    <tr><td>이용시 주의사항(최대 10개)</td>
        <td><input id="rule"  onfocus="chk_tel()"/>
            <input type="button" id="addrule" value="추가" /></td></tr>
    <tr><td></td><td> <div id="ruleList">
              <!-- 이용시 주의사항 추가시 웹상에서 보여지는 부분 -->
            </div>
     
     <tr><td colspan="2" align="center"><input type="button" value="공간등록" onclick="checkb()" id="request"></td></tr>
  </table>

<!-- 지도관련 스크립트 -->
  <script>
function drawmap(){ 
	var map = new naver.maps.Map('map');
    var myaddress = $('#asdf').val();// 도로명 주소나 지번 주소만 가능 (건물명 불가!!!!)
    console.log(myaddress);
    naver.maps.Service.geocode({address: myaddress}, function(status, response) {
        if (status !== naver.maps.Service.Status.OK) {
        	alert(myaddress + '의 지도검색 결과가 없거나 기타 네트워크 에러입니다. 우편번호를 다시 검색해주세요');
        	$("#zipcode").val(null);
        	$("#asdf").val(null);
        	$("#details").focus();
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
</form:form>
</div>
</body>
</html>
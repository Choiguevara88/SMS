<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공간정보 수정</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
	<c:forEach items="${building.sTagList}" var="item">
	tagarr[idx++] = ("${item}");
	</c:forEach>
	ti = "";
	for (var i=0;i<idx;i++) {
		 ti += "<span name='sTag' id='tags" + i +"'>#"+tagarr[i] +
		 "<input type='button' onclick=\"tagdel("+i +")\" value='X'></input>&nbsp;</span>"+
		 "<input type='hidden' name='sTagList' value='"+tagarr[i]+"'>";
	}
	 tl = document.getElementById("tagList");
	 tl.innerHTML=ti;
	 size = idx;
	 if(size>=5) {
		 $("#addtag" ).attr( 'disabled', true );
	 } else {
		 $("#addtag" ).attr('disabled', false );
	 }
	$("#addtag").click(function(){
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
	<c:forEach items="${building.sInfoSubList}" var="item">
	infoarr[infoidx++] = ("${item}");
	</c:forEach>
	ii = "";
	for (var i=0;i<infoidx;i++) {
		ii += "<span name='sInfoSub' id='infos" + i +"'>"+(i+1)+". "+infoarr[i] +
		 "<input type='button' onclick=\"infodel("+i +")\" value='X'></input>&nbsp;</span><br>" +
		 "<input type='hidden' name='sInfoSubList' value='"+infoarr[i]+"'>";
	}
	 il = document.getElementById("infoList");
	 il.innerHTML=ii;
	 size = infoidx;
	 if(size>=10) {
		 $("#addinfo" ).attr('disabled', true );
	 } else {
		 $("#addinfo" ).attr('disabled', false );
	 }
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
	<c:forEach items="${building.sRuleList}" var="item">
	rulearr[ruleidx++] = ("${item}");
	</c:forEach>
	ri = "";
	for (var i=0;i<ruleidx;i++) {
		ri += "<span name='sRule' id='rules" + i +"'>"+(i+1)+". "+rulearr[i] +
		 "<input type='button' onclick=\"ruledel("+i +")\" value='X'></input>&nbsp;</span><br>"+
		 "<input type='hidden' name='sRuleList' value='"+rulearr[i]+"'>";
	}
	 rl = document.getElementById("ruleList");
	 rl.innerHTML=ri;
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
		 } else {
			 $("#addrule" ).attr('disabled', false );
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


$(document).ready(function() {
	
	/*이용시간 관련 스크립트*/
	var hourarr = new Array(2);
	houridx = 0;
	<c:forEach items="${building.sBHourList}" var="item">
	hourarr[houridx++] = ("${item}");
	</c:forEach>
	$('#hour1').val(hourarr[0])
	$('#hour2').val(hourarr[1])
	
	/*휴무일 관련 스크립트*/
	var sHDay = "${building.sHDay}";
	var sHDayOptions = ["휴무없음","공휴일휴무","명절연휴휴무","명절당일휴무","주말휴무","토요일휴무","일요일휴무"];
	for(i = 0 ; i < sHDayOptions.length ; i++ ) {
		if(sHDayOptions[i] == sHDay) {
			$("#sHDayList").val(sHDay);
			break;
		}
	}
	if(sHDayOptions.length <= i) {
		$("#sHDayList").attr( 'disabled', true );
		$("#direct").attr("checked", true)
		var dirTag = "<tr><td></td><td><input id='sHDaydir' name='sHDay' value='${building.sHDay}'/></td></tr>";
		$("#directsHDay").html(dirTag)		
	}
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
	/*이미지 관련 스크립트*/
	$("#sImg1File").attr('disabled', true)
	$("#sImg2File").attr('disabled', true)
	
	$("#img1_btn").click(function() {
	       $("#sImg1").val("");
	       $("#file1_desc").empty();
	       $("#sImg1File").attr('disabled', false)
        });
	
	img2idx = 0;
	img2arr = new Array();
	<c:forEach items="${building.sImg2Name}" var="item">
	img2arr[img2idx++] = ("${item}");
    </c:forEach>
    console.log(img2arr)
    $("#sImg2Name").val(img2arr);
	$("#img2_btn").click(function() {
		img2idx = 0;
		<c:forEach items="${building.sImg2Name}">
			img2arr[img2idx++] = "";
		</c:forEach>
		console.log(img2arr)
		   $("#file2_desc").empty();
		   $("#file2_desc2").empty();
		   $("#sImg2Name").val(img2arr);
		   $("#sImg2File").attr('disabled', false)
	    });
	
});

/*전화번호 관련 스크립트*/
function chk_tel(str, field){ 
	  var str; 
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
</script>
<!-- 지도관련 스크립트 -->
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=2bA8v55yYLf1omsHnKFk&submodules=geocoder"></script>
</head>
<body>
<div>
<form:form name="f" modelAttribute="building" method="post" action="buildingUpdateReg.sms" enctype="multipart/form-data">
<input type="hidden" name="sNo" value="${building.sNo}">
<input type="hidden" name="id" value="${building.id}">
<input type="hidden" id="sImg1" name="sImg1" value="${building.sImg1}">
<input type="hidden" id="sImg2Name" name="sImg2Name" value="${building.sImg2Name}">
  <table cellpadding="0" cellspacing="1" align="center">
  <tr><td colspan="2" align="center">공간정보 수정</td></tr>
    <tr><td>공간 유형(최대 3개)</td>
        <td>
        <form:checkboxes path="sTypeList" items="${sTypeNames}" onchange="chkboxcheck()" />  
        </td></tr>
           
    <tr><td>공간 이름(최대 20자)</td><td><form:input path="sName"/></td></tr>
    <tr><td>공간 한줄 소개(최대 30자)</td><td><form:input path="sPreview" /></td></tr>
    <tr><td>공간 소개(최대 500자)</td><td><form:textarea cols="50px" rows="10px" path="sContent" /></td></tr>
    
    <tr><td>태그 설정(최대 5개)</td>
        <td><input id="tag" />
            <input type="button" id="addtag" value="추가" />
            </td></tr>
    <tr><td></td>
        <td><div id="tagList">
              <!-- 태그 추가시 웹상 보여지는 부분 -->
            </div>
         </td></tr>

        
    <tr><td>시설안내(최대 10개)</td>
        <td><input id="info" />
            <input type="button" id="addinfo" value="추가" /></td></tr>
    <tr><td></td><td> <div id="infoList">
              <!-- 시설안내 추가시 웹상에서 보여지는 부분 -->
            </div></td></tr>
    
    <tr><td>대표이미지</td>
        <td>
          <c:if test="${!empty building.sImg1 }">
            <div id="file1_desc">
              <div>
              <a href="../picture/${building.sImg1}">${building.sImg1}</a>
              </div>
              <div>
              <input type="button" id="img1_btn" value="기존이미지삭제" /> 
              </div>
            </div> 
          </c:if>
       <input type="file" id="sImg1File" name="sImg1File" accept="image/*"/>
       </td></tr>
   
    <%-- <div class="w3-card-4" style="width:10%">
    <img src="../picture/${building.sImg1}" style="width:100%">
    </div> --%>
              
    
    <tr><td>이미지</td>
        <td>
        <div id="file2_desc">
        <c:forEach items="${building.sImg2Name}" var="item">
        <c:if test="${item != null}">
              <a href="../picture/${item}">${item}</a><br>
        </c:if>
        </c:forEach>
        </div>
        <div id="file2_desc2">
        
        <input type="button" id="img2_btn" value="기존이미지삭제" />
        
        </div>
        <input multiple="multiple" type="file" id="sImg2File" name="sImg2Files" accept="image/*" />
        
        </td></tr>
    <%-- <c:forEach items="${building.sImg2Name}" var="item">
        <div class="w3-card-4" style="width:10%">
           <img src="../picture/${item}" style="width:100%">
        </div>
        </c:forEach> --%>
    
    <!-- 주소API 추후 사용 -->
    <tr>
			<td><strong>사업자 주소지</strong></td></tr>
		<tr>
			<td>우편번호</td><td><input type="text" name="sAddress" id="zipcode" class="postcodify_postcode5" value="${address1}" />
				<input type="button" id="postcodify_search_button" value="검색"></td></tr>
		<tr>
			<td>도로명 주소</td><td><input type="text" id="asdf" name="sAddress" class="postcodify_address" value="${address2}"/></td></tr>
		<tr>
			<td>상세 주소</td><td><input type="text" name="sAddress" id="details" class="postcodify_details" value="${address3}" onfocus="drawmap()"/></td></tr>
		<tr><td colspan="2"><div id="map" style="width:100%;height:200px;"></div></td></tr>
                     
     <tr><td colspan="2" align="center">이용정보</td></tr>
     <tr><td>이용시간</td>
         <td><select id="hour1" name="sBHourList">
         <c:forEach var="i" begin="0" end="23" >
         <option>${i}시</option></c:forEach></select>부터
         <select id="hour2" name="sBHourList">
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
         </div>
         
    <tr><td align="left">공간 연락처</td>
		<td>
		<input type="text" name="sTel" onkeyup="chk_tel(this.value,this);" value="${building.sTel}" > 
		</td></tr>
 
    <tr><td>이용시 주의사항(최대 10개)</td>
        <td><input id="rule" />
            <input type="button" id="addrule" value="추가" /></td></tr>
    <tr><td></td><td> <div id="ruleList">
              <!-- 이용시 주의사항 추가시 웹상에서 보여지는 부분 -->
            </div></td></tr>
     
    <tr><td colspan="2" align="center"><input type="submit" value="공간수정"></td></tr>
  </table>
<!-- 지도관련 스크립트 -->
<script>
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
</form:form>
</div>
</body>
</html>
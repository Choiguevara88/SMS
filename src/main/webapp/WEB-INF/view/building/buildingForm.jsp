<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
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
function imgprc() {
	ip = document.getElementById("sImg2")
}
$(document).ready(function() {
	$("#direct").click(function(){
		var chk = $("input:checkbox[id='direct']").is(":checked")
		var dir = document.getElementById("directsHDay")
		var dirTag = "<tr><td></td><td><input id='sHDaydir' name='sHDayList'/></td></tr>";
		if(chk) {
			$("#sHDayList" ).attr('disabled', true );
			dir.innerHTML = dirTag
		} else {
			$("#sHDayList" ).attr('disabled', false );
			$("#directsHDay" ).empty();
		}
	});
});

</script>
</head>
<body>
<div>
<form:form id="sss" modelAttribute="building" method="post" action="buildingReg.sms" enctype="multipart/form-data">

  <table cellpadding="0" cellspacing="1" align="center">
  <tr><td colspan="2" align="center">공간정보</td></tr>
    <tr><td>공간 유형(최대 3개)</td>
        <td>
        <form:checkboxes path="sTypeList" items="${sTypeNames}" onchange="chkboxcheck()" />  
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
        <input multiple="multiple" type="file" id="sImg2" name="sImg2Files" accept="image/*" onchange="imgprc()"/></td></tr>
    
    <!-- 주소API 추후 사용 -->
    <tr><td>주소</td><td><form:input path="sAddress" /> </td></tr>
                     
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
         </div>
 
    <tr><td>이용시 주의사항(최대 10개)</td>
        <td><input id="rule" />
            <input type="button" id="addrule" value="추가" /></td></tr>
    <tr><td></td><td> <div id="ruleList">
              <!-- 이용시 주의사항 추가시 웹상에서 보여지는 부분 -->
            </div></td></tr>
     
     <tr><td colspan="2" align="center"><input type="submit" value="공간등록"></td></tr>
  </table>
</form:form>
</div>
</body>
</html>
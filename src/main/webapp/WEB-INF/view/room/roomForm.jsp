<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언 -->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>세부 공간 등록</title>
<script type="text/javascript">
function power5() {
	var sRType_chk = false;
	var sRTypeList = document.getElementsByName("sRType")
	for(var i=0;i<sRTypeList.length;i++) {
	      if (sRTypeList[i].checked==true){
	         sRType_chk = true;
	         break;
	         }
	      }
	      if(!sRType_chk){
	         alert("세부 공간 유형을 하나 이상 체크하세요.")
	      return false;
	   }
	      
	      var sResType_chk = false;
	  	var sResTypeList = document.getElementsByName("sResType")
	  	for(var i=0;i<sResTypeList.length;i++) {
	  	      if (sResTypeList[i].checked==true){
	  	         sResType_chk = true;
	  	         break;
	  	         }
	  	      }
	  	      if(!sResType_chk){
	  	         alert("예약 유형을 하나 이상 체크하세요.")
	  	      return false;
	  	   }      
	
	if($('#sRName').val()=='' ){
	      alert("세부 공간 이름을 작성하세요~><");
	      document.getElementById('sRName').focus();
	  
	}else if($('#sRImg').val()==''){
			alert("이미지를 올려주세요~><");
			document.getElementById('sRImg').focus();
		
	}else if($('#sRContent').val()=='' ){
	      alert("세부 공간 소개를 작성하세요~><");
	      document.getElementById('sRContent').focus();
	   }
	
	else if($('#sPrice').val()=='' ){
	      alert("가격을 설정하세요~><");
	      document.getElementById('sPrice').focus();
	   }
	
	else{
			var res=confirm("정보는 제대로 다 적으셨나여? ><");
			if(res == true){
				document.sp.submit();
			}
		}
}
function chkboxcheck() {
	var sRType = document.getElementsByName("sRType")
	var cnt = 0;
	for(var i=0;i<sRType.length;i++) {
		if (sRType[i].checked) cnt++;
		if(cnt > 1)	{
			alert("공간 유형은 1개만 선택 가능합니다.");
			sRType[i].checked=false;
			return
		}
	}
}

function chkboxcheck2() {
	var sResType = document.getElementsByName("sResType")
	var cnt = 0;
	for(var i=0;i<sResType.length;i++) {
		if (sResType[i].checked) cnt++;
		if(cnt > 1)	{
			alert("예약 유형은 1개만 선택 가능합니다.");
			sResType[i].checked=false;
			return
		}
	}
}

</script>
</head>
<body>
<div class="w3-row">
<div class="w3-col s2"><p>&nbsp;</p></div>
<div class="w3-col s8">
<form:form modelAttribute="room" action="roomSuccess.sms" method="post" commandName="room" enctype="multipart/form-data" name="sp" >

<spring:hasBindErrors name="room"> <!-- ? -->
		<font color="tomato">
			<c:forEach items="${errors.globalErrors }" var="error">
				<spring:message code="${error.code }"/>
			</c:forEach>
		</font>
</spring:hasBindErrors>

<div class="w3-container w3-margin w3-padding">
	<h1 style="font-family:'Hanna'">세부 공간 등록</h1>
</div>

<div class="w3-container">
<div class="w3-container w3-margin w3-card w3-padding-16">
<p>
    <div class="w3-container w3-margin w3-row">
    	<div class="w3-col s8"><label style="font-family:'Hanna'">세부 공간 이름</label><form:input path="sRName" class="w3-input" placeholder="공간의 이름을 작성해주세요."/></div>
    	<div class="w3-cols s4"><font color="red"><form:errors path="sRName"/></font>
    </div>
	</div>
	<div class="w3-row w3-container w3-margin">
    	<div class="w3-col s8"><label style="font-family:'Hanna'">세부 공간 내용</label><form:input path="sRContent" class="w3-input" placeholder="공간에 대한 설명을 작성해주세요."/></div>
		<div class="w3-col s4"><font color="red"><form:errors path="sRContent"/></font></div>
	</div>
	
	<div class="w3-container w3-margin w3-row">
		<div class="w3-col s5">
		<label style="font-family:'Hanna'">세부 공간 유형 (1개만 체크 가능)</label>
		<div>
		<form:checkboxes path="sRType" items="${building.sTypeList}" onchange="chkboxcheck()" delimiter="&nbsp;&nbsp;&nbsp;&nbsp;" class="w3-checkbox"/>
		</div>
		<div class="w3-col s4"><font color="red"><form:errors path="sRType"/></font></div>
		</div>
		<div class="w3-col s2"><p>&nbsp;</p></div>
		<div class="w3-col s5">
		<label style="font-family:'Hanna'">예약 유형 (1개만 체크 가능)</label>
		<div>
		<form:checkbox path="sResType" value="0" label="시간 단위" onchange="chkboxcheck2()"/>&nbsp; &nbsp;
		<form:checkbox path="sResType" value="1" label="일 단위" onchange="chkboxcheck2()"/>
		</div>
		<div class="w3-col s4"><font color="red"><form:errors path="sResType"/></font></div>
		</div>
	</div>
	
	<div class="w3-container w3-margin">
		<label style="font-family:'Hanna'">사진 업로드(여러 장 가능)</label>
		<div>
		<input type="file" multiple="multiple" id="sRImg" name="sRImgList" class="w3-button">
				</div>
	</div>
	
	<div class="w3-container w3-margin">
	<p style="font-family:'Hanna'">편의 시설</p>
	<table class="w3-table w3-border">
	<tr>
		<td><form:checkbox path="sRInfo" value="<span><i class='material-icons'>devices</i></span><span>TV/프로젝터</span>" label="TV/프로젝터" /></td>
    	<td><form:checkbox path="sRInfo" value="<i class='material-icons'>local_printshop</i>복사기/인쇄기" label="복사기/인쇄기" /></td>
    	<td><form:checkbox path="sRInfo" value="<i class='material-icons'>local_bar</i>주류반입가능" label="주류반입가능" /></td>
        <td><form:checkbox path="sRInfo" value="<i class='material-icons'>hot_tub</i>샤워시설" label="샤워시설" /></td>
    </tr>
    <tr>
        <td><form:checkbox path="sRInfo" value="<i class='material-icons'>wifi</i>인터넷/WIFI" label="인터넷/WIFI"/></td>
        <td><form:checkbox path="sRInfo" value="<i class='material-icons'>airplay</i>화이트보드" label="화이트보드"/></td>
        <td><form:checkbox path="sRInfo" value="<i class='material-icons'>settings_voice</i>음향/마이크" label="음향/마이크"/></td>
        <td><form:checkbox path="sRInfo" value="<i class='material-icons'>kitchen</i>취사시설" label="취사시설"/></td>
    </tr>
    <tr>
        <td><form:checkbox path="sRInfo" value="<i class='material-icons'>cake</i>음식물반입가능" label="음식물반입가능"/></td>
        <td><form:checkbox path="sRInfo" value="<i class='material-icons'>time_to_leave</i>주차" label="주차"/></td>
        <td><form:checkbox path="sRInfo" value="<i class='material-icons'>smoke_free</i>금연" label="금연"/></td>
        <td><form:checkbox path="sRInfo" value="<i class='material-icons'>desktop_windows</i>PC/노트북" label="PC/노트북"/></td>
	</tr>
	<tr>
     	<td><form:checkbox path="sRInfo" value="<i class='material-icons'>event_seat</i>의자/테이블" label="의자/테이블"/></td>
     	<td><form:checkbox path="sRInfo" value="<i class='material-icons'>wc</i>내부화장실" label="내부화장실"/></td>
     	<td><form:checkbox path="sRInfo" value="<i class='material-icons'>accessibility</i>탈의실" label="탈의실"/></td>
     	<td><form:checkbox path="sRInfo" value="<i class='material-icons'>beach_access</i>테라스/루프탑" label="테라스/루프탑"/></td>
   	</tr>
   	<tr>
     	<td><form:checkbox path="sRInfo" value="<i class='material-icons'>weekend</i>공용라운지" label="공용라운지"/></td>
     	<td><form:checkbox path="sRInfo" value="<i class='material-icons'>nature_people</i>전신거울" label="전신거울"/></td>
     	<td><form:checkbox path="sRInfo" value="<i class='material-icons'>restaurant</i>바베큐시설" label="바베큐시설"/></td>
   		<td><form:checkbox path="sRInfo" value="<i class='material-icons'>dialpad</i>도어락" label="도어락"/></td>
	</tr>
	</table>
    </div>

	<div class="w3-container w3-margin w3-row">
    	<div class="w3-col s5" style="vertical-align:middle;">
    		<label style="font-family:'Hanna'">최소 인원</label>
    		<form:select path="sRPersonLimit" class="w3-select">
    			<form:option value="1"> 1명 </form:option>
    			<form:option value="2"> 2명 </form:option>
    			<form:option value="3"> 3명 </form:option>
    			<form:option value="4"> 4명 </form:option>
    			<form:option value="5"> 5명 </form:option>
    			<form:option value="10"> 10명 </form:option>
    			<form:option value="20"> 20명 </form:option>
    			<form:option value="50"> 50명 </form:option>
    		</form:select><font color="red"><form:errors path="sRPersonLimit"/></font>
    	</div>
    	<div class="w3-col s2"><p>&nbsp;</p></div>
    	<div class="w3-col s5" style="vertical-align:middle;">
    		<label style="font-family:'Hanna'">예약 단위당 가격</label>
    		<form:input path="sPrice" class="w3-input" placeholder="숫자만 입력하세요."/>
    		<font color="red"><form:errors path="sPrice"/></font>
    	</div>
	</div>
	
	<div class="w3-container w3-section w3-padding" style="text-align:center;">
		<input type="button" value="등록하기" class="w3-btn w3-black" style="font-family:'Hanna'; width:30%;" onclick="power5()">
	</div>
</div>
<div>
<form:hidden path="sNo" value="${room.sNo}"/>
</div>
</div>
</form:form>
</div>
<div class="w3-col s2"><p>&nbsp;</p></div> 
</div>
</body>
</html>
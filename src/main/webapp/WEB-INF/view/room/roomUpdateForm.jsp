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
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<!-- w3 css 사용 선언-->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>세부 공간 수정</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
function power5(){
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



$(document).ready(function() {
	$("#sRImg").attr('disabled', true)

	img2idx = 0;
	img2arr = new Array();
	<c:forEach items="${room.sRImgNameList}" var="item">
	img2arr[img2idx++] = ("${item}");
    </c:forEach>
    console.log(img2arr)
    $("#sRImgNameList").val(img2arr);
	
	
	
	$("#img1_btn").click(function() {
       $("#sImg1").val("");
       $("#file1_desc").empty();
       $("#sRImg").attr('disabled', false)
    });
	
});

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
//$(document).ready(function() {
	//$(".sRInfo").att("checked",true)
//});
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
<form:form modelAttribute="room" action="roomUpdateSuccess.sms" method="post" commandName="room" enctype="multipart/form-data" name="sp">
<input type="hidden" name="sNo" value="${building.sNo}">
<input type="hidden" name="sRNo" value="${room.sRNo}">
<input type="hidden" id="sRImgNameList" name="sRImgNameList">


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
    	<div class="w3-col s8"><label style="font-family:'Hanna'">세부 공간 이름</label><form:input path="sRName" class="w3-input" placeholder="공간의 이름을 작성해주세요." value="${room.sRName }"/></div>
    	<div class="w3-cols s4"><font color="red"><form:errors path="sRName"/></font>
    </div>
	</div>
	<div class="w3-row w3-container w3-margin">
    	<div class="w3-col s8"><label style="font-family:'Hanna'">세부 공간 내용</label><form:input path="sRContent" class="w3-input" placeholder="공간에 대한 설명을 작성해주세요." value="${room.sRContent }"/></div>
		<div class="w3-col s4"><font color="red"><form:errors path="sRContent"/></font></div>
	</div>
	
	<div class="w3-container w3-margin w3-row">
		<div class="w3-col s5">
		<label style="font-family:'Hanna'">세부 공간 유형 (1개만 체크 가능)</label>
		<div>
		<form:checkboxes path="sRType" items="${building.sTypeList}" onchange="chkboxcheck()" delimiter="&nbsp;&nbsp;&nbsp;&nbsp;" class="w3-checkbox"/>
		<div class="w3-col s4"><font color="red"><form:errors path="sRType"/></font></div>
		</div>
		</div>
		<div class="w3-col s2"><p>&nbsp;</p></div>
		<div class="w3-col s5">
		<label style="font-family:'Hanna'">예약 유형 (1개만 체크 가능)</label>
		<div>
		<form:checkbox path="sResType" value="0" label="시간 단위" onchange="chkboxcheck2()"/>&nbsp; &nbsp;
		<form:checkbox path="sResType" value="1" label="일 단위" onchange="chkboxcheck2()"/>
		<div class="w3-col s4"><font color="red"><form:errors path="sResType"/></font></div>
		</div>
		</div>
	</div>
	
	<div class="w3-container w3-margin">
		<label style="font-family:'Hanna'">사진 업로드(여러 장 가능)</label>
	<c:if test="${!empty room.sRImg }">
            <div id="file1_desc">
      <div>
      </div>
    
             
            </div> 
          </c:if>
             <div>
          <input type="button" id="img1_btn" value="기존이미지삭제" /> 
        </div>
          <input type="file" id="sRImg" name="sRImgList" multiple="multiple"/>
 			</div>
	<div class="w3-container w3-margin">
	<p style="font-family:'Hanna'">편의 시설</p>
	<table class="w3-table w3-border">
	
	<tr>
     <c:forEach items="${sRInfoNames1}" var="na">
     <td style="width:25%;"><form:checkbox  path="sRInfoList" value="${na}"/>${na}</td>
     </c:forEach>
 	</tr>
	<tr>
     <c:forEach items="${sRInfoNames2}" var="na">
     <td style="width:25%;"><form:checkbox  path="sRInfoList" value="${na}"/>${na}</td>
     </c:forEach>
 	</tr>
	<tr>
     <c:forEach items="${sRInfoNames3}" var="na">
     <td style="width:25%;"><form:checkbox  path="sRInfoList" value="${na}"/>${na}</td>
     </c:forEach>
 	</tr>
	<tr>
     <c:forEach items="${sRInfoNames4}" var="na">
     <td style="width:25%;"><form:checkbox  path="sRInfoList" value="${na}"/>${na}</td>
     </c:forEach>
 	</tr>
	<tr>
     <c:forEach items="${sRInfoNames5}" var="na">
     <td style="width:25%;"><form:checkbox  path="sRInfoList" value="${na}"/>${na}</td>
     </c:forEach>
 	</tr>
	
<%-- <form:checkboxes path="sRInfoList" items="${sRInfoNames1}" delimiter="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" class="w3-checkbox"/>--%>
	<%-- <tr><td>
		<form:checkboxes path="sRInfoList" items="${sRInfoNames2}" delimiter="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" class="w3-checkbox"/>
	<tr><td>
	
	<tr><td>
		<form:checkboxes path="sRInfoList" items="${sRInfoNames3}" delimiter="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" class="w3-checkbox"/>
	<tr><td>
	
	<tr><td>
		<form:checkboxes path="sRInfoList" items="${sRInfoNames4}" delimiter="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" class="w3-checkbox"/>
	<tr><td>
	
	<tr><td>
		<form:checkboxes path="sRInfoList" items="${sRInfoNames5}" delimiter="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" class="w3-checkbox"/>
	<tr><td> --%>
	
	
	
	
  
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
    		<form:input path="sPrice" class="w3-input" placeholder="숫자만 입력하세요." value="${room.sPrice }"/>
    		<font color="red"><form:errors path="sPrice"/></font>
    	</div>
	</div>
	<div class="w3-container w3-section w3-padding" style="text-align:center;">
		<input type="button" value="수정하기" class="w3-btn w3-black" style="font-family:'Hanna'; width:30%;" onclick="power5()">
	</div>
</div>
<div>
</div>
</div>
</form:form>
</div>
<div class="w3-col s2"><p>&nbsp;</p></div> 
</div>
</body>
</html>
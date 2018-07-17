<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>세부 공간 정보</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">



function roomDelete(sNo,sRNo) {
	var result = confirm("정말로 삭제하시겠습니까?")
	if(result == true){
	location.href="roomDeleteSuccess.sms?sNo="+sNo+"&sRNo="+sRNo
	}
}




</script>
	<style>
		.srinfo{
			border:2px black solid;
		}
		#imgBox{
			border:2px black solid;
		}
		
.max-small {
    width: auto; height: auto;
    max-width: 200px;
    max-height: 200px;
}

	</style>
</head>
<body>
<form:form modelAttribute="room" commandName="room" name="sp">
<div class="w3-container">
<<<<<<< HEAD
<div class="w3-container w3-margin w3-card w3-padding-16">
	<h1 style="font-family:'Hanna'">세부 공간 정보</h1>

<p>
    <div class="w3-container w3-margin w3-row">
    	<div class="w3-col s8"><label style="font-family:'Hanna'">세부 공간 이름</label><div style="border-bottom:1px solid black;" class="w3-sepia">${room.sRName }</div></div>
=======
  <div class="w3-card-4" style="width:30%">
    <div class="w3-container w3-center">
     <c:forEach var="roomImg" items="${room.sRImgNameList}">
      <div >
      <img src="../picture/${roomImg}" style="width:100%; height:250px"><br>
      </div>
      </c:forEach>
      <p>세부 공간 이름 : ${room.sRName}</p><br>	
      <p>설명 : ${room.sRContent}</p><br>
      <p>공간 유형 : ${room.sRType}</p><br>
      <div class="srinfo">시설 안내
      <c:forEach var="roomInfo" items="${room.sRInfoList}">
      <div >
    	 ${roomInfo}
      </div>
     	</c:forEach>
     	</div>
     	
      <p>예약 유형 : ${room.sResType}</p><br>
	  <p>최소 인원 : ${room.sRPersonLimit}</p><br>
	  <p>시간/일 가격 : ${room.sPrice}</p><br>
>>>>>>> branch 'master' of https://github.com/Choiguevara88/SMS.git
    </div>
	
	<div class="w3-row w3-container w3-margin">
    	<div class="w3-col s8"><label style="font-family:'Hanna'">세부 공간 내용</label><div style="border-bottom:1px solid black;" class="w3-sepia">${room.sRContent }</div></div>
	</div>
	
	<div class="w3-container w3-margin w3-row">
		<div class="w3-col s5">
		<label style="font-family:'Hanna'">세부 공간 유형</label>&nbsp;&nbsp;&nbsp;&nbsp;
		<form:checkbox path="sRType" value="${room.sRType }" label="${room.sRType }" class="w3-checkbox" disabled="true"/>
		</div>
		<div class="w3-col s5">
		<label style="font-family:'Hanna'">예약 유형</label>&nbsp;&nbsp;&nbsp;&nbsp;	
		<form:checkbox path="sResType" value="0" label="시간 단위" disabled="true"/>&nbsp; &nbsp;
		<form:checkbox path="sResType" value="1" label="일 단위" disabled="true"/>
		</div>
	</div>	
	<div class="w3-container w3-margin w3-row">
		<div style="font-family:'Hanna'">세부 공간 사진</div>
	</div>	
	<div id="imgBox">
           <c:forEach var="roomImg" items="${room.sRImgNameList }">
        <img class="max-small" src="../picture/${roomImg}"/>&nbsp; &nbsp; &nbsp; &nbsp;
	</c:forEach>
         </div> 
    
	<div class="w3-container w3-margin">
	<label style="font-family:'Hanna'">편의 시설</label><div class="w3-sepia">${room.sRInfo }
	</div>
	</div>
	<div class="w3-container w3-margin w3-row">
    	<div class="w3-col s5" style="vertical-align:middle;">
    		<label style="font-family:'Hanna'">최소 인원</label>
    		<form:select path="sRPersonLimit" class="w3-select" disabled="true">
    			<form:option value="1"> 1명 </form:option>
    			<form:option value="2"> 2명 </form:option>
    			<form:option value="3"> 3명 </form:option>
    			<form:option value="4"> 4명 </form:option>
    			<form:option value="5"> 5명 </form:option>
    			<form:option value="10"> 10명 </form:option>
    			<form:option value="20"> 20명 </form:option>
    			<form:option value="50"> 50명 </form:option>
    			</form:select>
    	</div>
    	<div class="w3-col s2"><p>&nbsp;</p></div>
    	<div class="w3-col s5">
    	<label style="font-family:'Hanna'">예약 단위당 가격</label><div class="w3-sepia">${room.sPrice }</div></div>
	
</div>
	<div class="w3-container w3-section w3-padding" style="text-align:center;">
		<a href="roomList.sms?sNo=${room.sNo}" class="w3-button w3-black" style="font-family:'Hanna';">리스트로 가기</a>
		<a href="roomUpdateForm.sms?sRNo=${room.sRNo }&sNo=${room.sNo}" class="w3-btn w3-black" style="font-family:'Hanna';">수정</a>
		<a href="javascript:roomDelete(${room.sNo},${room.sRNo })" class="w3-btn w3-black" style="font-family:'Hanna';">삭제</a>
	</div>
</div>
</div>

</form:form>








</body>
</html>
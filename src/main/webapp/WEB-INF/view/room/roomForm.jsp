<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언 -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>room/roomForm.jsp</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

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

<form:form modelAttribute="room" action="roomSuccess.sms" method="post" commandName="room" enctype="multipart/form-data">

<spring:hasBindErrors name="room"> <!-- ? -->
		<font color="tomato">
			<c:forEach items="${errors.globalErrors }" var="error">
				<spring:message code="${error.code }"/>
			</c:forEach>
		</font>
</spring:hasBindErrors>

<div class="w3-container w3-margin">
	<h1>세부 공간 등록</h1>
</div>
<div class="w3-container" style="">
<div class="w3-container w3-margin w3-card w3-padding-16" style="width:80%;">
<p>
    <div class="rows w3-container w3-margin">
    	<div class="cols-8"><label class="w3-small">세부 공간 이름</label><form:input path="sRName" class="w3-input" placeholder="공간의 이름을 작성해주세요."/></div>
    	<div class="cols-4"><font color="red"><form:errors path="sRName"/></font>
    </div>
	</div>
	<div class="rows w3-container w3-margin">
    	<div class="cols-8"><label class="w3-small">세부 공간 내용</label><form:input path="sRContent" class="w3-input" placeholder="공간에 대한 설명을 작성해주세요."/></div>
		<div class="cols-4"><font color="red"><form:errors path="sRContent"/></font></div>
	</div>
	
	<div class="w3-container w3-margin">
	<div class="rows">
		<div class="cols-4"><label class="w3-small">세부 공간 유형 (1개만 체크 가능)</label></div>
		<div class="cols-8 w3-margin"><form:checkboxes path="sRType" items="${building.sTypeList}" onchange="chkboxcheck()" delimiter="&nbsp;&nbsp;&nbsp;&nbsp;" class="w3-checkbox"/></div>
	</div>
	</div>
	
	
	<table class="w3-table">
	<tr>
		<td>예약 유형</td>
        <td>
      	<form:checkbox path="sResType" value="0" label="시간 단위" onchange="chkboxcheck2()"/>
       	<form:checkbox path="sResType" value="1" label="일 단위" onchange="chkboxcheck2()"/>
        </td>
    </tr>
	
	<tr><td>편의시설</td>
        <td>
        <form:checkbox path="sRInfo" value="TV/프로젝터" label="TV/프로젝터" />
        <form:checkbox path="sRInfo" value="복사기/인쇄기" label="복사기/인쇄기" />
        <form:checkbox path="sRInfo" value="주류반입가능" label="주류반입가능" />
        <form:checkbox path="sRInfo" value="샤워시설" label="샤워시설" />
        <form:checkbox path="sRInfo" value="인터넷/WIFI" label="인터넷/WIFI"/>
        <form:checkbox path="sRInfo" value="화이트보드" label="화이트보드"/>
        <form:checkbox path="sRInfo" value="음향/마이크" label="음향/마이크"/>
        <form:checkbox path="sRInfo" value="취사시설" label="취사시설"/>
        <form:checkbox path="sRInfo" value="음식물반입가능" label="음식물반입가능"/>
        <form:checkbox path="sRInfo" value="주차" label="주차"/>
        <form:checkbox path="sRInfo" value="금연" label="금연"/>
        <form:checkbox path="sRInfo" value="PC/노트북" label="PC/노트북"/>
     	<form:checkbox path="sRInfo" value="의자/테이블" label="의자/테이블"/>
     	<form:checkbox path="sRInfo" value="내부화장실" label="내부화장실"/>
     	<form:checkbox path="sRInfo" value="탈의실" label="탈의실"/>
     	<form:checkbox path="sRInfo" value="테라스/루프탑" label="테라스/루프탑"/>
     	<form:checkbox path="sRInfo" value="공용라운지" label="공용라운지"/>
     	<form:checkbox path="sRInfo" value="전신거울" label="전신거울"/>
     	<form:checkbox path="sRInfo" value="바베큐시설" label="바베큐시설"/>
   		<form:checkbox path="sRInfo" value="도어락" label="도어락"/>
        </td>
      </tr>
	<form:hidden path="sNo" value="${room.sNo}"/>
	
	<tr>
		<td>최소 인원</td>
		<td><form:input path="sRPersonLimit"/><font color="red"><form:errors path="sRPersonLimit"/></font></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><form:input path="sPrice"/><font color="red"><form:errors path="sPrice"/></font></td>
	</tr>
</table>
</div>
<div>
	<input type="submit" value="룸 등록하기">
</div>
</div>
</form:form>
 

</body>
</html>
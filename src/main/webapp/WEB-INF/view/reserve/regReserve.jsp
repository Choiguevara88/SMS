<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!-- /WebContent/reserve/regReserve.jsp -->    
<!DOCTYPE html>
<html>
<head>
<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언-->

<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언 -->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
$(document).ready(function() {
	var selDate = ""
	var selTime = ""
	var selcnt = 0
	
	var chkDate
	var chkTime
	var cnt
	var totPrice
	var reserveDate
	
	$("#selectReCnt").change(function(){
		selcnt = document.getElementById("selectReCnt").value
		selDate = document.getElementById("selectReDate").value
		selTime = document.getElementById("selectReTime").value
		cnt = document.getElementById("chkReCnt")
		cnt.innerHTML = selcnt
		
		if(selDate != "") {
			totPrice = document.getElementById("totPrice1")
			totalPrice = document.getElementById("totalPrice1")
			var pri = ${room.sPrice} * selcnt
			totPrice.innerHTML = pri
			totalPrice.value = pri
		}

	})
	
	$("#selectReDate").change(function(){
		selcnt = document.getElementById("selectReCnt").value
		selDate = document.getElementById("selectReDate").value
		selTime = document.getElementById("selectReTime").value
		chkDate = document.getElementById("chkReDate")
		chkDate.innerHTML = selDate
		var sres = ${room.sResType}
		
		if(selcnt != 0) {
			totPrice = document.getElementById("totPrice1")
			totalPrice = document.getElementById("totalPrice1")
			var pri = ${room.sPrice} * selcnt
			totPrice.innerHTML = pri
			totalPrice.value = pri
		}
		
		if(selTime != "") {
			reserveDate1 = document.getElementById("reserveDate1")
			var dateTime = selDate.replace(/-/gi, "") + selTime.replace(":","")
			console.log(typeof dateTime)
			console.log(dateTime)
			reserveDate1.value = dateTime
			
		} 
		
		if (sres == 1) {
			reserveDate = document.getElementById("reserveDate1")
			var dateTime = selDate.replace(/-/gi, "") + "000000"
			console.log(typeof dateTime)
			console.log(dateTime)
			reserveDate.value = dateTime
		}
	})
	
	$("#selectReTime").change(function(){
		selcnt = document.getElementById("selectReCnt").value
		selDate = document.getElementById("selectReDate").value
		selTime = document.getElementById("selectReTime").value
		chkTime = document.getElementById("chkReTime")
		chkTime.innerHTML = selTime
		
		if(selcnt != 0) {
			totPrice = document.getElementById("totPrice1")
			totalPrice = document.getElementById("totalPrice1")
			var pri = ${room.sPrice} * selcnt
			totPrice.innerHTML = pri
			totalPrice.value = pri
		}
		
		if(selDate != "") {
			reserveDate = document.getElementById("reserveDate1")
			var dateTime = selDate.replace(/-/gi, "") + selTime.replace(":","")
			console.log(typeof dateTime)
			console.log(dateTime)
			reserveDate.value = dateTime 
		}
	})
	
	function check(f) {
		if(f.reDate.value == '') {
			alert('날짜 입력 해주세요.');
			f.reDate.focus();
			return false;
		}
		
		if(f.reTime.value == '') {
			alert('시간을 체크 해주세요.');
			f.reTime.focus();
			return false;
		}		
		
		if(f.reCnt.value =='') {
			alert('수량 확인 해주세요.');
			f.reCnt.focus();
			return false;
		}
		
		return f.submit();
	}

})
</script>

<title>예약 등록 화면</title>
</head>
<body>

<div class="w3-row">
<div class="w3-col s1"><p>&nbsp;</p></div><!-- 좌우공간확보용 -->
<form:form modelAttribute="reserve" action="regReserve.sms" method="POST" name="f" onsubmit="return check(this)">

<div class="w3-col s7 w3-margin">
<div class="w3-container w3-margin">
	<h2 style="font-family:'Hanna';">공간 예약하기</h2>
</div>

<c:forEach items="${room.sRImgNameList}" varStatus="i" var="sRImg">
</c:forEach>

<div class="w3-container w3-margin w3-border w3-round">
	
	<div class="w3-container w3-margin">
		<h1 style="font-family:'Hanna'" class="w3-text-gray">${room.sRName}</h1>
	</div>
	
	<div class="w3-container w3-margin w3-padding">
		<table class="w3-table w3-bordered">
		<tr>
			<td style="width:30%; vertical-align:middle; font-size:large;">한 줄 설명</td>
			<td style="width:60%; vertical-align:middle; font-size:large;" class="w3-text-gray">${room.sRContent}</td>
		</tr>
		<tr>
			<td style="width:30%; vertical-align:middle; font-size:large;">공간유형</td>
			<td style="width:60%; vertical-align:middle; font-size:large;" class="w3-text-gray">${room.sRType}</td>
		</tr>
		<tr>
			<td style="width:30%; vertical-align:middle; font-size:large;">최대 인원</td>
			<td style="width:60%; vertical-align:middle; font-size:large;" class="w3-text-gray">${room.sRPersonLimit}명</td>
		</tr>
		<tr>
			<td style="width:30%; vertical-align:middle; font-size:large;">이용 시간</td>
			<td style="width:60%; vertical-align:middle; font-size:large;" class="w3-text-gray">
			<c:forEach items="${building.sBHourList}" var="bu" varStatus="i">
				${bu}<c:if test="${!(i.last)}"> ~ </c:if></c:forEach>&nbsp;까지
			</td>
		</tr>
		
		<tr>
		<c:if test="${room.sResType == 0 }">
			<td style="width:30%; vertical-align:middle; font-size:large;">예약 일시</td>
			<td style="width:30%; vertical-align:middle;" class="w3-text-gray">
				<input type="date" name="reDate1" class="w3-border-0 w3-hover-light-gray" placeholder="예약일자" id="selectReDate">
			</td>
			<td style="width:30%; vertical-align:middle;" class="w3-text-gray">
				<input type="time" name="reTime" class="w3-border-0 w3-hover-light-gray" placeholder="예약시간" id="selectReTime">
			</td>
		</c:if>
		
		<c:if test="${room.sResType == 1 }">
			<td style="width:30%; vertical-align:middle; font-size:large;">예약 일자</td>
			<td style="width:60%; vertical-align:middle;" class="w3-text-gray">
				<input type="date" name="reDate" class="w3-border-0 w3-hover-light-gray" placeholder="예약일자" id="selectReDate">
				<input type="hidden" id="selectReTime">
			</td>
		</c:if>
		</tr>
		
		<tr>
			<td style="width:30%; vertical-align:middle; font-size:large;">예약 수량</td>
			<td style="width:60%; vertical-align:middle;" class="w3-text-gray">
				<form:select path="reCnt" class="w3-select" id="selectReCnt">
				<c:if test="${room.sResType == 0 }">
					<option value="" style="text-color:gray;">선택하세요.</option>
					<option value="1">1시간</option>
					<option value="2">2시간</option>
					<option value="3">3시간</option>
					<option value="4">4시간</option>
					<option value="5">5시간</option>
					<option value="6">6시간</option>
				</c:if>
				<c:if test="${room.sResType == 1 }">
					<option value="" style="text-color:gray;">선택하세요.</option>
					<option value="1">1일</option>
					<option value="2">2일</option>
					<option value="3">3일</option>
					<option value="4">4일</option>
					<option value="5">5일</option>
					<option value="6">6일</option>
				</c:if>
				</form:select>
			</td>
		</tr>
		</table>
		
	<div class="w3-row w3-margin">
		<c:forEach items="${room.sRInfoList}" var="srInfo" varStatus="cnt">
			<div class="w3-col s3 w3-margin">● <font style="font-family:'Hanna'; text-align:center; color:gray;">${srInfo}</font></div>
		</c:forEach>	
	</div>
</div>
 <div class="w3-container w3-margin w3-row">
	<c:forEach items="${room.sRImgNameList}" var="sRImg">
		<div class="w3-col s6" style="text-align:center;">
		<img src="../picture/${sRImg}" class="w3-image w3-hover-opacity" width="200px" height="100px">
		</div>
	</c:forEach>
</div>
</div>

<div class="w3-container w3-margin w3-border w3-round">
	<div class="w3-container w3-margin w3-light-gray">
		<h3 style="font-family:'Hanna'; text-align:center;">호스트 정보</h3>
	</div>
	
	
	<div class="w3-row w3-margin">
	<div class="w3-col s4">
		<font style="font-family:'Hanna'; font-size:large;">상호명</font>
	</div>
	<div class="w3-col s8">
		<font style="font-family:'Hanna'; font-size:large; color:gray;">${building.sName}</font>
	</div>
	</div>
	
	<div class="w3-row w3-margin">
	<div class="w3-col s4">
		<font style="font-family:'Hanna'; font-size:large;">사업자명</font>
	</div>
	<div class="w3-col s8">
		<font style="font-family:'Hanna'; font-size:large; color:gray;">${hostMember.hostName}</font>
	</div>
	</div>

	<div class="w3-row w3-margin">
	<div class="w3-col s4">
		<font style="font-family:'Hanna'; font-size:large;">소재지</font>
	</div>
	<div class="w3-col s8">
		<font style="font-family:'Hanna'; font-size:large; color:gray;">${hostMember.address}</font>
	</div>
	</div>
	
	<div class="w3-row w3-margin">
	<div class="w3-col s4">
		<font style="font-family:'Hanna'; font-size:large;">사업자등록번호</font>
	</div>
	<div class="w3-col s8">
		<font style="font-family:'Hanna'; font-size:large; color:gray;">${hostMember.hostRegNo}</font>
	</div>
	</div>
	
	<div class="w3-row w3-margin">
	<div class="w3-col s4">
		<font style="font-family:'Hanna'; font-size:large;">연락처</font>
	</div>
	<div class="w3-col s8">
		<font style="font-family:'Hanna'; font-size:large; color:gray;">
		${hostMember.tel}&nbsp;&nbsp;&nbsp;&nbsp;${hostMember.email}</font>
	</div>
	</div>
</div>


<div class="w3-container w3-margin w3-border w3-round">
	<div class="w3-container w3-margin w3-light-gray">
		<h3 style="font-family:'Hanna'; text-align:center;">이용 시 주의사항</h3>
	</div>


<c:forEach items="${building.sRuleList}" var="sRule" varStatus="cnt">
<div class="w3-row w3-margin">
	<div class="w3-col s2" style="vertical-align:middle; text-align:center;">
		<font style="font-family:'Hanna'; font-size:large;">${cnt.count}.</font>
	</div>
	<div class="w3-col s10" style="vertical-align:middle;">
		<font style="font-family:'Hanna'; font-size:large;">${sRule}</font>
	</div>
</div>
</c:forEach>

</div>

</div>

<div class="w3-col s3 w3-margin" id="floatMenu">
	<div class="w3-container w3-margin">
		<h2 style="font-family:'Hanna'" class="w3-text-gray">결제 예정 금액</h2>
	</div>
	<div class="w3-container w3-padding w3-border w3-round">
	<table class="w3-table">
	<tr>
		<td>
		<c:if test="${room.sResType == 0}">시간당</c:if>
		<c:if test="${room.sResType == 1}">하루당</c:if>금액
		</td>
		<td><fmt:formatNumber value="${room.sPrice}" pattern="###,###" />원</td>
	</tr>
	<tr>
		<td>예약일자</td>
		<td><font id='chkReDate'></font>&nbsp;<font id='chkReTime'></font></td>
	</tr>
	<tr>
		<td>예약수량</td>
		<td><font id="chkReCnt"></font>&nbsp;
		<c:if test="${room.sResType == 0}">시간</c:if>
		<c:if test="${room.sResType == 1}">일</c:if>
		</td>
	</tr>
	</table>
	<hr>
	<table class="w3-table w3-large">
	<tr>
		<td>결제금액</td>
		<td><font id="totPrice1"></font>원</td>
	</tr>
	</table>
	</div>
	
	<div class="w3-container w3-margin" style="text-align:center;">
	<input type="submit" value="예약하기" class="btn btn-outline-primary btn-block" style="font-family:'Hanna';"></div>
</div>

<form:hidden path="totPrice" id="totalPrice1" value=""/>
<form:hidden path="reDate" id="reserveDate1" value=""/>
<form:hidden path="id" value="${loginMember.id}"/>		<!-- 예약자 ID -->
<form:hidden path="sNo" value="${param.sNo}"/>			<!-- 예약하는 건물번호 -->
<form:hidden path="srNo" value="${param.sRNo}"/>		<!-- 예약하는 공간번호 -->
</form:form>

<div class="w3-col s1"><p>&nbsp;</p></div><!-- 좌우공간확보용 -->
</div>
</body>


<!-- 달력 스크립트 ::: 일단 봉인함. ㅠㅠ --> 
<!-- <script type="text/javascript">
	
	var today = new Date();	// 오늘 날짜, 내 컴퓨터 로컬을 기준으로 today에 Date객체를 넣어줌
	var date = new Date();	// today의 Date를 세어주는 역할
	
	function prevCalendar() {			// 이전 달
										// 이전 달을 today에 값을 저장하고 달력에 today를 넣어줌
										// today.getFullYear() 현재 년도//today.getMonth() 월  //today.getDate()일 
										// getMonth()는 현재 달을 받아 오므로 이전달을 출력하려면 -1을 해줘야함
		
		today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
		buildCalendar(); //달력 cell 만들어 출력
	}
 
	function nextCalendar() {			// 다음 달
            							// 다음 달을 today에 값을 저장하고 달력에 today 넣어줌
            							// today.getFullYear() 현재 년도 //today.getMonth() 월  //today.getDate() 일 
            							// getMonth()는 현재 달을 받아 오므로 다음 달을 출력하려면 +1을 해줘야함
		today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
		buildCalendar();//달력 cell 만들어 출력
	}
	
	function buildCalendar() {			//현재 달 달력 만들기
		var doMonth = new Date(today.getFullYear(), today.getMonth(), 1); //이번 달의 첫째 날
            // new를 쓰는 이유 : new를 쓰면 이번달의 로컬 월을 정확하게 받아온다.     
            // new를 쓰지 않았을 때 이번 달을 받아오려면 +1을 해줘야한다. 왜냐면 getMonth()는 0~11을 반환하기 때문 
		
		var lastDate = new Date(today.getFullYear(), today.getMonth()+1, 0); //이번 달의 마지막 날
            // new를 써주면 정확한 월을 가져옴, getMonth()+1을 해주면 다음달로 넘어가는데
            // day를 1부터 시작하는게 아니라 0부터 시작하기 때문에 제대로 된 다음달 시작일(1일)은 못가져오고 1 전인 0, 즉 전달 마지막일 을 가져오게 된다 
		
		var tbCalendar = document.getElementById("calendar");
            // 날짜를 찍을 테이블 변수 만듬, 일 까지 다 찍힘
		
		var tbCalendarYM = document.getElementById("tbCalendarYM");
            // 테이블에 정확한 날짜 찍는 변수
            // innerHTML : js언어를 HTML의 권장 표준 언어로 바꾼다
            // new를 찍지 않아서 month는 +1을 더해줘야 한다. 
		
		tbCalendarYM.innerHTML = today.getFullYear() + "년 " + (today.getMonth() + 1) + "월"; 
 
		
		/* while은 이번달이 끝나면 다음달로 넘겨주는 역할 */
		while (tbCalendar.rows.length > 2) {	// 열을 지워줌	// 기본 열 크기는 body 부분에서 2로 고정되어 있다.
			tbCalendar.deleteRow(tbCalendar.rows.length - 1);
				// 테이블의 tr 갯수 만큼의 열 묶음은 -1칸 해줘야지 
				// 30일 이후로 담을달에 순서대로 열이 계속 이어진다.
			}
		
		var row = null;
		row = tbCalendar.insertRow(); // 테이블에 새로운 열 삽입 //즉, 초기화 
		var cnt = 0;	// count, 셀의 갯수를 세어주는 역할 //1일이 시작되는 칸을 맞추어 줌
             
		/* 이번달의 day만큼 돌림 */
		for (i=0; i < doMonth.getDay(); i++) {
			cell = row.insertCell();	//	열 한칸 한칸 계속 만들어주는 역할
			cnt = cnt + 1;	//	열의 갯수를 계속 다음으로 위치하게 해주는 역할
		}
            
		/* 달력 출력 */
		for (i=1; i<=lastDate.getDate(); i++) { // 1일부터 마지막 일까지 돌림 
			cell = row.insertCell();			// 열 한칸한칸 계속 만들어주는 역할
			cell.innerHTML = "<p style='text-align:center; vertical-align:middle;'>" + i + "</p>";					// 셀을 1부터 마지막 day까지 HTML 문법에 넣어줌
			cnt = cnt + 1;						// 열의 갯수를 계속 다음으로 위치하게 해주는 역할
			
			/*일요일 계산*/
			if (cnt % 7 == 1) { // 1주일이 7일 이므로 일요일 구하기 // 월화수목금토일을 7로 나눴을때 나머지가 1이면 cnt가 1번째에 위치함을 의미한다
				cell.innerHTML = "<p style='text-align:center; vertical-align:middle;'><font color=#F79DC2>" + i + "</font></p>"//1번째의 cell에만 색칠
            }    
			
			/* 1주일이 7일 이므로 토요일 구하기*/
			if (cnt%7 == 0)	{ // 월화수목금토일을 7로 나눴을때 나머지가 0이면 cnt가 7번째에 위치함을 의미한다
				cell.innerHTML = "<p style='text-align:center; vertical-align:middle;'><font color=skyblue>" + i + "</font></p>"//7번째의 cell에만 색칠
				row = calendar.insertRow(); //토요일 다음에 올 셀을 추가
              }
              
			/* 오늘의 날짜에 노란색 칠하기 */
			if (today.getFullYear() == date.getFullYear() && today.getMonth() == date.getMonth() && i == date.getDate()) { 
            	//달력에 있는 연, 달과 내 컴퓨터의 로컬 연월이 같고, 일이 오늘의 일과 같으면
                cell.bgColor = "#FAF58C"; //셀의 배경색을 노랑으로 
               }
             }
        }
</script> -->
<!-- 달력 스크립트 -->

<!-- 달력 HTML 페이지 부분 ::: 눈물을 머금고 봉인 -->
<!-- <div>
	<table id="calendar" class="w3-table w3-bordered">
    <tr>
    label은 마우스로 클릭을 편하게 해줌
        <td style="text-align:center;"><label onclick="prevCalendar()" >&#60;</label></td>
        <td id="tbCalendarYM" colspan="5" style="text-align:center;">
        yyyy년 m월</td>
        <td style="text-align:center;"><label onclick="nextCalendar()"style="text-align:center;">&#62;</label></td>
    </tr>
    <tr>
        <td style="text-align:center;"><font color ="#F79DC2">일</font></td>
        <td style="text-align:center;">월</td>
        <td style="text-align:center;">화</td>
        <td style="text-align:center;">수</td>
        <td style="text-align:center;">목</td>
        <td style="text-align:center;">금</td>
        <td style="text-align:center;"><font color ="skyblue">토</font></td>
    </tr> 
</table>
<script type="text/javascript">
    buildCalendar();
</script>
</div>
 -->
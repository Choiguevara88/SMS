<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언-->
<!-- w3 css 사용 선언 -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- w3 css 사용 선언-->
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	function win_openQ(num)	{
		var op = "width=800,height=700,scrollbars=yes,left=50,top=150";
			window.open("../building/Qreply.sms?bNo="+num,"reply",op);
	}
</script>

<style>

body {font-family: Arial, Helvetica, sans-serif;}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 101; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 50%;
}

/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}
#table2{
	display:block;
	align:center;
}
</style>

<!-- Trigger/Open The Modal -->
<div>
<div class="w3-leftbar w3-border-deep-purple w3-left">
<h2>&nbsp;&nbsp;Q&A</h2>
</div>
<div class="w3-padding" align="right"><button class="w3-btn w3-deep-purple w3-round-large" id="myBtn"><b>질문 작성하기</b></button></div></div>
<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
<div class="modal-content" style="text-align:right;">
    <span class="close">&times;</span>
<p>
<script type="text/javascript">
function check() {
	if($('#content').val()==''){
		alert("내용을 입력하세요~><");
		document.getElementById('content').focus();
	} else{
		document.wf.submit();
	}
}
</script>
<form:form modelAttribute="board" action="Qwrite.sms" method="post" name="wf">
<form:hidden path="kind" value="3"/>
<input type="hidden" name="pageNum" value="1"/>
<form:hidden path="sNo" value="${param.sNo }"/>
<form:hidden path="id" value="${sessionScope.loginMember.id}"/>


<div align="center" id="table2">

<table class="w3-table">
        <tr><td>
        <label style="font-family:'Hanna'; font-size:x-large;">공간문의하기</label>
        <form:textarea rows="15" cols="80" path="content" class="w3-input w3-border" id="content"/></td></tr>
        <tr><td><font color="red"><form:errors path="content"/></font></td></tr>
        <tr><td style="text-align:center;">
            <input type="button" value="질문등록" onclick="check()" class="btn btn-outline-primary btn-lg" style="font-family:'Hanna'; font-size:x-large;">
			<a href="javascript:document.f.reset()" class="btn btn-outline-danger btn-lg" style="font-family:'Hanna'; font-size:x-large;">다시작성</a>
        </td></tr>
</table>	
</div>
</form:form>
</p>
</div>
</div>
<script>
// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
function check5(bno,sno,kind) {
	var result = confirm("정말 삭제 하시겠습니까?");
	if(result){
	f = document.df;
	f.bNo.value=bno;
	f.sNo.value=sno;
	f.kind.value=kind;	
	f.submit();
	}
//	return false;
}

</script>
<!--  리뷰 목록부분 -->
 	<form name="df" method="post" action="delete.sms">
  		<input type="hidden" name="bNo"	value="">
		<input type="hidden" name="sNo"	value="">
		<input type="hidden" name="kind" value="">
	</form>


<c:if test="${listcount > 0}">
<c:forEach var="board" items="${boardlist}">
<hr size="1">
	<div><b>
	<c:if test="${empty board.id }">
		<span class="w3-xlarge w3-text-darkgrey">비회원의 질문입니다.</span></c:if>
	<c:if test="${!empty board.id && board.refLevel == 0 }">
		<span class="w3-xlarge w3-text-darkgrey">${board.id}</span></c:if>
	<c:if test="${!empty board.id && board.refLevel != 0 }">
		<div class="w3-xlarge">└─${building.id}님의 답글입니다.</div></c:if>
		</b>
	</div>

	<div>
		<div class="w3-large w3-text-deepgray"> ${board.content}</div></div>
	<div>
		<div class="w3-medium w3-text-grey"><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd H:mm:ss"/></div>
  	<div align="right" style="display:inline;">
  		<c:if test="${sessionScope.loginMember.id == building.id}">
  		<c:if test="${board.refLevel == 0  }">
  		<c:if test="${board.qType == 0 }">
  	<a href="javascript:win_openQ('${board.bNo}')">[답변]</a></c:if></c:if></c:if>
		<c:if test="${sessionScope.loginMember.id == board.id && !empty board.id}">
		<input type="button" value="삭제" onclick="check5('${board.bNo}','${board.sNo}','${board.kind}')">
	</c:if>
	<br>
	</div>
</div>
<!--  글 밑 부분 -->
</c:forEach>
<table style="margin-top:30px" width="80%" align="center">
<tr align="center" height="26"><td colspan="5">
		<c:if test="${pageNum >1 }">
			<a href="javascript:listQlist(${pageNum -1 })"> [이전]</a>
		</c:if>&nbsp;
		<c:if test="${pageNum <= 1}">[이전]</c:if>&nbsp;
		<c:forEach var="a" begin="${startpage }" end="${endpage}"> 
			<c:if test="${a == pageNum}">[${a}]</c:if>
			<c:if test="${a != pageNum}">
				<a href="javascript:listQlist(${a})">[${a}]</a></c:if>
		</c:forEach>
			<c:if test="${pageNum < maxpage}">
				<a href="javascript:listQlist(${pageNum+1 })">[다음]</a>
			</c:if>&nbsp;
			<c:if test="${pageNum >= maxpage}">[다음]</c:if>&nbsp;
			</td></tr>
	</c:if>
<c:if test="${listcount == 0}">
	 <tr><td colspan="5">등록된 게시물이 없습니다.</td></tr>
	 </c:if>
</table>

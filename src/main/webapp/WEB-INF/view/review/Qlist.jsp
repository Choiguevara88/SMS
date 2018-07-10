<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	function list(pageNum) {
		location.href="Qlist.sms?pageNum=" + pageNum +"&sNo="+${param.sNo};
		return false;
	}
</script>
<script type="text/javascript">
	function check() {
		confirm("정말 삭제 하시겠습니까?")
		document.d.submit();
	}

</script>
<style>
body {font-family: Arial, Helvetica, sans-serif;}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
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
    width: 80%;
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
<div align="right"><button id="myBtn">질문하기</button></div>

<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>
    <p>
<form:form modelAttribute="board" action="Qwrite.sms" method="post" name="f">
<form:hidden path="kind" value="3"/>
<input type="hidden" name="pageNum" value="1"/>
<form:hidden path="sNo" value="1"/>
<form:hidden path="id" value="${sessionScope.loginMember.id}"/>
	<div align="center" id="table2"><table cellpadding="0" cellspacing="0" align="center" >
		<caption><h3>질문 글 등록하기</h3></caption>
		<tr><td align="center">제목</td>
			<td><form:input path="subject"/>
			<font color="red">
			<form:errors path="subject"/>
			</font></td>
		</tr>
		
		<tr><td align="center" colspan="2">내용</td></tr>
        <tr><td colspan="2"><form:textarea rows="15" cols="80" path="content"/>
        <font color="red"><form:errors path="content"/></font></td></tr>
        <tr><td colspan="2" align="center">
        <a href="javascript:document.f.submit()">[질문등록]</a>
		<a href="javascript:document.f.reset()">[다시작성]</a>
		<a href="javascript:history.go(-1)">[뒤로가기]</a>
		</td></tr>
	</table></div>
</form:form></p>
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
</script>
<!--  리뷰 목록부분 -->
<table border="1" style="margin-top:30px" width="80%" align="center">

<c:if test="${listcount > 0}">
<c:forEach var="board" items="${boardlist}">
	<tr>
		<td width="50%" colspan="2">${board.subject}</td></tr>
	<tr><td colspan="2">${board.content}</td> 
	</tr>
	<tr>
		<td><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd H:mm:ss"/></td>
  	<td align="right">
  		<c:if test="${sessionScope.loginMember.id == building.id && board.sNo == building.sNo }">
  	<a href="../review/reply.sms?bNo=${board.bNo}&pageNum=${pageNum}">[답변]</a></c:if>
  		<c:if test="${sessionScope.loginMember.id == board.id }">
  	<form name="d" method="post" action="delete.sms">
  		<input type="hidden" name="bNo"	value="${board.bNo}">
		<input type="hidden" name="sNo"	value="${board.sNo}">
		<input type="hidden" name="kind" value="${board.kind}">
		<input type="hidden" name="pageNum"	value="${param.pageNum}">
		<input type="button" value="삭제" onclick="check()" ></form></c:if><br></td>
<!--  글 밑 부분 -->
</c:forEach>
<tr align="center" height="26"><td colspan="2">
		<c:if test="${pageNum >1 }">
			<a href="javascript:list(${pageNum -1 })"> [이전]</a>
		</c:if>&nbsp;
		<c:if test="${pageNum <= 1}">[이전]</c:if>&nbsp;
		<c:forEach var="a" begin="${startpage }" end="${endpage}"> 
			<c:if test="${a == pageNum}">[${a}]</c:if>
			<c:if test="${a != pageNum}">
				<a href="javascript:list(${a})">[${a}]</a></c:if>
		</c:forEach>
			<c:if test="${pageNum < maxpage}">
				<a href="javascript:list(${pageNum+1 })">[다음]</a>
			</c:if>&nbsp;
			<c:if test="${pageNum >= maxpage}">[다음]</c:if>&nbsp;
			</td></tr>
	</c:if>
<c:if test="${listcount == 0}">
	 <tr><td colspan="5">등록된 게시물이 없습니다.</td></tr>
	 </c:if>
</table>

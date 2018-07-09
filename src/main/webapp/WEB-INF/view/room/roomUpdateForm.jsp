<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>room/roomForm.jsp</title>
</head>
<body>
<form:form modelAttribute="room" action="roomUpdateSuccess.sms" method="post" commandName="room">
<spring:hasBindErrors name="room"> <!-- ? -->
		<font color="tomato">
			<c:forEach items="${errors.globalErrors }" var="error">
				<spring:message code="${error.code }"/>
			</c:forEach>
		</font>
	</spring:hasBindErrors>

<table align="center" cellpadding="1" cellspacing="1" border = "1">
		
	<tr><td>sRNo = ${myRoom.sRNo }</td><td>수정해라 수정!</td></tr>
	<form:hidden path="sRNo" value="${myRoom.sRNo }" />
	
	<tr><td>SRName</td><td><form:input path="sRName" value="${myRoom.sRName}"  />
		<font color="red"><form:errors path="sRName"/></font></td></tr>
	<tr>
		<td>SRType</td><td><form:input path="sRType" value="${myRoom.sRType }" />
		 <font color="red"><form:errors path="sRType"/></font></td></tr>
	<tr>
		<td>SRContent</td><td><form:input path="sRContent" value="${myRoom.sRContent }" />
		<font color="red"><form:errors path="sRContent"/></font></td>
		</tr>
	<tr>
		<td>SRInfo</td><td><form:input path="sRInfo" value="${myRoom.sRInfo }" />
		<font color="red"><form:errors path="sRInfo"/></font></td>
	</tr>
	<tr>
		<td>SResType</td><td><form:input path="sResType" value="${myRoom.sResType }"/>
		<font color="red"><form:errors path="sResType"/></font></td></tr>
	<tr>
		<td>SRPersonLimit</td><td><form:input path="sRPersonLimit" value="${myRoom.sRPersonLimit }" />
		<font color="red"><form:errors path="sRPersonLimit"/></font></td>
	</tr>
	<tr>
		<td>SPrice</td><td><form:input path="sPrice" value="${myRoom.sPrice }" />
		<font color="red"><form:errors path="sPrice"/></font></td>
	</tr>
</table>
 <input type="submit" value="수정 완료">
</form:form> 
</body>
</html>
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
<form:form modelAttribute="room" action="roomSuccess.sms" method="post" commandName="room">
<spring:hasBindErrors name="room"> <!-- ? -->
		<font color="tomato">
			<c:forEach items="${errors.globalErrors }" var="error">
				<spring:message code="${error.code }"/>
			</c:forEach>
		</font>
	</spring:hasBindErrors>


<table align="center" cellpadding="1" cellspacing="1" border = "1">
	
	<tr><td>SNo </td><td><form:input path="sNo" placeholder="primarykey"/>
		<font color="red"><form:errors path="sNo"/></font></td></tr>
	
	<tr><td>SRNo </td><td><form:input path="sRNo" placeholder="primarykey" />
		<font color="red"><form:errors path="sRNo"/></font></td></tr>
	
	<tr><td>SRName </td><td><form:input path="sRName" value="1" />
		<font color="red"><form:errors path="sRName"/></font></td></tr>
	<tr>
		<td>SRType </td><td><form:input path="sRType" value="1" />
		 <font color="red"><form:errors path="sRType"/></font></td></tr>
	<tr>
		<td>SRContent</td><td><form:input path="sRContent" value="1" />
		<font color="red"><form:errors path="sRContent"/></font></td>
		</tr>
	<tr>
		<td>SRInfo</td><td><form:input path="sRInfo" value="1" />
		<font color="red"><form:errors path="sRInfo"/></font></td>
	</tr>
	<tr>
		<td>SResType</td><td><form:input path="sResType" value="1"/>
		<font color="red"><form:errors path="sResType"/></font></td></tr>
	<tr>
		<td>SRPersonLimit</td><td><form:input path="sRPersonLimit" value="1" />
		<font color="red"><form:errors path="sRPersonLimit"/></font></td>
	</tr>
	<tr>
		<td>SPrice</td><td><form:input path="sPrice" value="1" />
		<font color="red"><form:errors path="sPrice"/></font></td>
	</tr>
	<tr>
		<td>SRImg</td><td><form:input path="sRImg" value="1" />
		<font color="red"><form:errors path="sRImg"/></font></td>
	</tr>
</table>
 <input type="submit" value="룸 등록하기">
</form:form> 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>    
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
opener.location.href="${path}/building/buildingDetail.sms?sNo=${sNo}";
self.close(); 
</script>
    
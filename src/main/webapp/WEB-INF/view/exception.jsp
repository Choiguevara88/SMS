<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" isErrorPage="true"%>
<%-- /WebContent/WEB-INF/view/exception.jsp --%>
<%-- isErrorPage="true" : ���� ���������� ���� �� ���� �� --%>
<%-- exception ��ü�� ���� ���� �� �������� �̵��Ǹ�, �ش� exception ��ü�� ���� ��. --%>
<script type="text/javascript">
	alert('${exception.message}');
	location.href="${exception.url}";
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!-- /WEB-INF/view/graphCanvas.jsp -->
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Graph</title>
<!-- Chart.JS 사용 선언 -->
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.3.0/Chart.bundle.js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<!-- Chart.JS 사용 선언 -->

<!-- 부트스트랩 사용 선언 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<!-- 부트스트랩 사용 선언-->

</head>
<body>

<div class="row">
<div class="col-lg-2"></div>

<div id="canvas-holder-cnt" style="width:40%" class="col-lg-4">
	<canvas id="cnt-chart-area" width="100" height="100"></canvas>
</div>

<div id="canvas-holder-sum" style="width:40%" class="col-lg-4">
	<canvas id="sum-chart-area" width="100" height="100"></canvas>
</div>

<div class="col-lg-2"></div>

</div>
<script type="text/javascript">
	
	var randomColorFactor = function() {
		return Math.round(Math.random() * 255);
	}
	
	var randomColor = function(opacity) {
		return "rgba("  + randomColorFactor() + ","
						+ randomColorFactor() + ","
						+ randomColorFactor() + ","
						+ (opacity || '.3') + ")";
	}

	// ${map} : Map 객체
	// m : Map.Entry (키, 값) 쌍 객체
	var config1 = {
			type : 'bar',
			data : {
				labels:[<c:forEach items="${cntMap}" var="m" varStatus="i">"${m.key}"<c:if test="${i.last != true}">,</c:if></c:forEach>],
				datasets : [{
					label : '거래량',
					data:[<c:forEach items="${cntMap}" var="m" varStatus="i">"${m.value}"<c:if test="${i.last != true}">,</c:if></c:forEach>],
					backgroundColor:[<c:forEach items="${cntMap}" var="m">randomColor(1),</c:forEach>]
				}]
			},
			options:{responsive:true}
	}
	
	var config2 = {
			type : 'bar',
			data : {
				labels:[<c:forEach items="${sumMap}" var="m" varStatus="i">"${m.key}"<c:if test="${i.last != true}">,</c:if></c:forEach>],
				datasets : [{
					label : '거래금액',
					data:[<c:forEach items="${sumMap}" var="m" varStatus="i">"${m.value}"<c:if test="${i.last != true}">,</c:if></c:forEach>],
					backgroundColor:[<c:forEach items="${sumMap}" var="m">randomColor(1),</c:forEach>]
				}]
			},
			options:{responsive:true}
	}
	
	window.onload = function() {
		var ctx1 = document.getElementById("cnt-chart-area").getContext("2d");
		var ctx2 = document.getElementById("sum-chart-area").getContext("2d");
		window.myPid = new Chart(ctx1, config1);
		window.myPid = new Chart(ctx2, config2);
	}
</script>

</body>
</html>
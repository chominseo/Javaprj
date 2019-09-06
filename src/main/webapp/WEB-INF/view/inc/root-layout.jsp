<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Document</title>

<link href="../../css/style2.css" type="text/css" rel="stylesheet">
<style>
#footer{width:100px;}
</style>
</head>



<body>
	<!--header block ----------------------------------------------------------------------------------------------------------------- -->
	<%-- <jsp:include page="../../inc/header.jsp" /> --%>
	<tiles:insertAttribute name="header"/>
	<!--visual block ----------------------------------------------------------------------------------------------------------------- -->

	<div id="visual">
		<div class="content-box">
			<div></div>
		</div>
	</div>
	<!--body block ----------------------------------------------------------------------------------------------------------------- -->
	<!-- flexible box layout: 반응형 사용을 위해 존재.수직이든 수평이든 뭐를 둬도 알아서 배치. static은 수직으로 알아서 배치하지만 수평은 안해쥼 axis(중심선) -->
	<div id="body">
		<div class="content-box">


		<!-- main 부분 -->
		<tiles:insertAttribute name="main"/>
		
		</div>
	</div>




	<!--footer      block ----------------------------------------------------------------------------------------------------------------- -->

	<%-- <jsp:include page="../../inc/footer.jsp" /> --%>
	<tiles:insertAttribute name="footer"/>





</body>

</html>
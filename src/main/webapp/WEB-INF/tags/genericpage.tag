<%@ tag description="Generic Page" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>
<html>
	<head>
		<spring:url value="/ressources/css/lib/bootstrap.min.css" var="bootstrapCss" />
		<spring:url value="/ressources/css/lib/bootstrap-theme.min.css" var="bootstrapThemeCss" />
		<spring:url value="/ressources/css/lib/bootstrap-datetimepicker.min.css" var="bootstrapDateTimePickerCss" />
		<spring:url value="/ressources/css/style.css" var="styleCss" />

		<!-- Bootstrap -->
		<link rel="stylesheet" type="text/css" href="${bootstrapCss}"/>
		<link rel="stylesheet" type="text/css" href="${bootstrapThemeCss}"/>
		<link rel="stylesheet" type="text/css" href="${bootstrapDateTimePickerCss}"/>
		<link rel="stylesheet" type="text/css" href="${styleCss}"/>

		<!-- JS Dependencies -->
		<script type="text/javascript" src="/ressources/js/lib/jquery-3.1.1.js"></script>
		<script type="text/javascript" src="/ressources/js/lib/bootstrap.min.js"></script>
		<script type="text/javascript" src="/ressources/js/lib/moment.min.js"></script>
		<script type="text/javascript" src="/ressources/js/lib/bootstrap-datetimepicker.js"></script>
		<script type="text/javascript" src="/ressources/js/layout.js"></script>
	</head>
  	<body>
	    <header>
	      	<jsp:invoke fragment="header"/>
	    </header>
	    <div id="main" class="container-fluid">
	      	<jsp:doBody/>
	    </div>
	    <footer>
	      	<jsp:invoke fragment="footer"/>
		</footer>
	</body>
</html>

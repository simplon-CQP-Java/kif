<%@ tag description="Generic Page" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>
<html>
	<head>
		<spring:url value="/ressources/css/bootstrap.min.css" var="bootstrapCss" />
		<spring:url value="/ressources/css/bootstrap-theme.min.css" var="bootstrapThemeCss" />
		<spring:url value="/ressources/css/style.css" var="styleCss" />
		<!-- Bootstrap -->
		<link rel="stylesheet" type="text/css" href="${bootstrapCss}"/>
		<link rel="stylesheet" type="text/css" href="${bootstrapThemeCss}"/>
		<link rel="stylesheet" type="text/css" href="${styleCss}"/>
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
		<script type="text/javascript" src="/ressources/js/jquery-3.1.1.js"></script>
		<script type="text/javascript" src="/ressources/js/layout.js"></script>
	</body>
</html>
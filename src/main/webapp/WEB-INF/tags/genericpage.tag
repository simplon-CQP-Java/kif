<%@ tag description="Generic Page" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>
<html>
	<head>
		<spring:url value="/ressources/css/bootstrap.min.css" var="bootstrapCss" />
		<spring:url value="/ressources/css/bootstrap-theme.min.css" var="bootstrapThemeCss" />
		<!-- Bootstrap -->
		<link rel="stylesheet" type="text/css" href="${bootstrapCss}"/>
		<link rel="stylesheet" type="text/css" href="${bootstrapThemeCss}"/>
	</head>
  	<body>
	    <header>
	      	<jsp:invoke fragment="header"/>
	    </header>
	    <div id="main">
	      	<jsp:doBody/>
	    </div>
	    <footer>
	      	<jsp:invoke fragment="footer"/>
		</footer>
	</body>
</html>
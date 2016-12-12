<%@ tag description="Generic Page" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>
<html >
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<spring:url value="/ressources/css/lib/bootstrap.min.css" var="bootstrapCss" />
		<spring:url value="/ressources/css/lib/bootstrap-theme.min.css" var="bootstrapThemeCss" />
		<spring:url value="/ressources/css/lib/bootstrap-datetimepicker.min.css" var="bootstrapDateTimePickerCss" />
		<spring:url value="/ressources/css/lib/fullcalendar.min.css" var="fullCalendarPickerCss" />
		<spring:url value="/ressources/css/style.css" var="styleCss" />

		<!-- Bootstrap -->
		<link rel="stylesheet" type="text/css" href="${bootstrapCss}"/>
		<link rel="stylesheet" type="text/css" href="${bootstrapThemeCss}"/>
		<link rel="stylesheet" type="text/css" href="${bootstrapDateTimePickerCss}"/>
		<link rel="stylesheet" type="text/css" href="${fullCalendarPickerCss}"/>
		<link rel="stylesheet" type="text/css" href="${styleCss}"/>

		<!-- JS Dependencies -->
		<script type="text/javascript" src="/ressources/js/lib/jquery-3.1.1.js"></script>
		<script type="text/javascript" src="/ressources/js/lib/bootstrap.min.js"></script>
		<script type="text/javascript" src="/ressources/js/lib/moment.min.js"></script>
		<script type="text/javascript" src="/ressources/js/lib/bootstrap-datetimepicker.js"></script>
		<script type="text/javascript" src="/ressources/js/layout.js"></script>
			<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.js"></script>
<script type="text/javascript" src="webjars/angularjs/1.3.8/angular.min.js"></script>
<script type="text/javascript" src="webjars/angularjs/1.3.8/angular-resource.min.js"></script>


    <script src="lib/angular.min.js"></script>
    
    <script src="lib/ng-grid.min.js"></script>

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
			<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.js"></script>
<script type="text/javascript" src="webjars/angularjs/1.3.8/angular.min.js"></script>
<script type="text/javascript" src="webjars/angularjs/1.3.8/angular-resource.min.js"></script>
	</body>
</html>

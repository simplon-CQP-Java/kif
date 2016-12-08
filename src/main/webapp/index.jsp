<%@ page contentType="text/html;" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
    	<div class="row">
			<div class="jumbotron">
				<h1>KIF Projet</h1>
				<p>Gestion de salles et ordinateurs</p>
			</div>
			<div class="container-fluid">
				<%@ include file="/WEB-INF/jsp/bookings/bookingsCalendar.jsp" %>
			</div>
		</div>
    <script src="./webjars/angularjs/1.4.8/angular.js"></script>
  <script src="./webjars/angularjs/1.4.8/angular-resource.js"></script>
  <script src="./webjars/angularjs/1.4.8/angular-route.js"></script>
  <script src="../resources/static/js/app.js"></script>
  <script src="../resources/static/js/controller.js"></script>
    </jsp:body>
</t:genericpage>

<<<<<<< HEAD
<%@ page contentType="text/html;" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"   %>
<html ng-app="myApp">
<head >
	<title>{{headingTitle}}</title>
</head>

<t:genericpage>
<jsp:attribute name="header">
<%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
</jsp:attribute>

<jsp:attribute name="footer">
<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
</jsp:attribute>
<jsp:body>
<div id="admin" class="row"  >
	<div >
    <p ><a href="../users">Gerer un utilisateur</a></p>
    <p><a href="../rooms">Gérer une salle</a></p>
    <p><a href="../bookings">Voir les reservations</a></p>
</div>

</jsp:body>
</t:genericpage>
</html>


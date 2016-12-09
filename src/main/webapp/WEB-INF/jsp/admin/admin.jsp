<%@ page contentType="text/html;" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"   %>
<html ng-app="myApp">
<head >
	<title>Admin</title>
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
	<div class="col-xs-10">
    <a class="col-xs-2" href="../users">Gerer un utilisateur</a>
        <a class="col-xs-2" href="../rooms">Gérer une salle</a>
        <a class="col-xs-2" href="../bookings">Voir les reservations</a>
    </div>
    
    <div class="row">
    	
    	<div class="col-md-5">
            <div class="jumbotron">
              <h3>Utilisateurs</h3>
            </div>
            <ul id="users-list" class="list-group">
                <c:forEach items="${users}" var="user">
                <li class="list-group-item"><a href="/users/userById?id=${user.id}">${user.username}</a></li>
            </c:forEach>
        </ul>
    </div>
    

    <!-- <div class="row"> -->

    <div class="col-md-5">
       <div class="jumbotron">
              <h3> Les Réservations</h3>
        </div>
        <div id="booking-list" class="col-md-12">
        <c:forEach items="${bookings}" var="booking">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">${booking.id}</h3>
                </div>
                <div class="panel-body">
                    <ul class="list-group">
                        <li class="list-group-item">Titre : ${booking.title}</li>
                        <li class="list-group-item">Réservé par : ${booking.user.username}</li>
                        <li class="list-group-item">Nom de la salle : ${booking.room.name}</li>
                        <li class="list-group-item">Ordinateur : ${booking.computer.brand} -  ${booking.computer.model}</li>
                        <li class="list-group-item">Date de début : ${booking.start}</li>
                        <li class="list-group-item">Date de fin : ${booking.end}</li>
                    </ul>
                </div>
            </div>
        </c:forEach>
        </div>
</div>
</div>
<!-- </div> -->
</div>



</jsp:body>
</t:genericpage>
</html>


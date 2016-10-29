<%@ page contentType="text/html;" pageEncoding="UTF-8"%><html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<head>
	<title>Réservations</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>
    
    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
    	<div id="bookings" class="row">
        	<div class="jumbotron">
			  <h3>Réservations</h3>
			</div>
        	<div id="list" class="col-md-6">
				<c:forEach items="${bookings}" var="booking">
					<div class="panel panel-default">
						<div class="panel-heading">
					   		<h3 class="panel-title">${booking.id}</h3>
					  	</div>
						<div class="panel-body">
						    <ul class="list-group">
							   	<li class="list-group-item">Salle : ${booking.roomName}</li>
							   	<li class="list-group-item">Identifiant de l'ordinateur : ${booking.computerId}</li>
							   	<li class="list-group-item">Date de début : ${booking.start}</li>
							   	<li class="list-group-item">Date de fin : ${booking.end}</li>
							</ul>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-md-6">
				<div class="page-header">
					<h5>Réserver</h5>
				</div>
				<form method="get" action="/bookings/book">
					<div class="form-group">
					    <label for="book-room-input">Identidiant de la salle</label>
						<input name="roomId" placeholder="Salle" type="text" class="form-control" id="book-room-input">
					</div>
					<div class="form-group">
					    <label for="book-computer-input">Identifiant de l'ordinateur</label>
						<input name="computerId" placeholder="Identifiant de l'ordinateur" type="text" class="form-control" id="book-computer-input">
					</div>
					<div class="form-group">
					    <label for="book-start-input">Date de début</label>
						<input name="start" placeholder="Date de début" type="datetime-local" class="form-control" id="book-start-input">
					</div>
					<div class="form-group">
					    <label for="book-end-input">Date de fin</label>
						<input name="end" placeholder="Date de fin" type="datetime-local" class="form-control" id="book-end-input">
					</div>
					<button type="submit" class="btn btn-primary">Réserver</button>
				</form>
			</div>
		</div>
	</jsp:body>
</t:genericpage>
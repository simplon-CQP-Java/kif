<%@ page contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<head>
<title>Réservations</title>
</head>

<t:genericpage>
	<jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp"%>
    </jsp:attribute>

	<jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
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
								<li class="list-group-item">Titre : ${booking.title}</li>
						    	<li class="list-group-item">Réservé par : ${booking.user.username}</li>
							   	<li class="list-group-item">Nom de la salle : ${booking.room.name}</li>
							   	<li class="list-group-item">Ordinateur : ${booking.computer.brand} -  ${booking.computer.model}</li>
							   	<li class="list-group-item">Date de début : ${booking.start}</li>
							   	<li class="list-group-item">Date de fin : ${booking.end}</li>
							</ul>
						</div>
						<div class="panel-footer">
							<form action="/bookings/delete">
								<input name="id" value="${booking.id}" type="hidden" />
								<button type="submit" class="btn btn-danger">Supprimer</button>
							</form>
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
					    <label for="add-room-select">Sélectionner une salle</label>
						<select name="roomId" class="form-control" id="add-room-select">
							<c:forEach items="${rooms}" var="room">
								<option value="${room.id}">${room.name}</option>
							</c:forEach>
							<option value="">Aucune</option>
						</select>
					</div>
					<div class="form-group">
					    <label for="add-computer-select">Sélectionner un ordinateur</label>
						<select name="computerId" class="form-control"
							id="add-computer-select">
							<c:forEach items="${computers}" var="computer">
								<option value="${computer.id}">${computer.brand} - ${computer.model}</option>
							</c:forEach>
							<option value="">Aucun</option>
						</select>
					</div>
					<div class="form-group">
					    <label for="book-start-input">Date de début</label>
						<div class="input-group date" id="book-start">
			                <input type='text' name="start" class="form-control"
								placeholder="Date de début" id="book-start-input" />
			                <span class="input-group-addon">
			                    <span class="glyphicon glyphicon-calendar"></span>
			                </span>
			            </div>
					</div>
					<div class="form-group">
					    <label for="book-end-input">Date de fin</label>
						<div class="input-group date" id="book-end">
			                <input type='text' name="end" class="form-control"
								placeholder="Date de fin" id="book-end-input" />
			                <span class="input-group-addon">
			                    <span class="glyphicon glyphicon-calendar"></span>
			                </span>
			            </div>
					</div>
					<button type="submit" class="btn btn-primary">Réserver</button>
				</form>
			</div>
		</div>
		<script type="text/javascript" src="/ressources/js/bookings.js"></script>
	</jsp:body>
</t:genericpage>

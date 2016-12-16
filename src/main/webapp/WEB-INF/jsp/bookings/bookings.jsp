<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
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
			<div class="col-md-12">
	       		<c:if test="${error != null}">
	       			<div class="alert alert-danger alert-dismissible" role="alert">
	     				<button type="button" class="close" data-dismiss="alert" aria-label="Fermer"><span aria-hidden="true">&times;</span></button>
	     				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
						<span class="sr-only">Error:</span>
						${error}
					</div>
	       		</c:if>
	       		<c:if test="${success != null}">
	       			<div class="alert alert-success alert-dismissible" role="alert">
	       				<button type="button" class="close" data-dismiss="alert" aria-label="Fermer"><span aria-hidden="true">&times;</span></button>
	       				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
						${success}
					</div>
	       		</c:if>
			</div>
        	<div id="list" class="col-md-6">
        		<c:if test="${empty bookings}">
        			<div class="alert alert-warning" role="alert">
        				<button type="button" class="close" data-dismiss="alert" aria-label="Fermer"><span aria-hidden="true">&times;</span></button>
        				Aucune réservation n'a été trouvée.
        			</div>
        		</c:if>
				<c:forEach items="${bookings}" var="booking">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title"><span class="label label-primary">ID</span> ${booking.id}</h3>
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
							<a href="${pageContext.request.contextPath}/bookings/bookingById?id=${booking.id}" class="btn btn-primary">Modifier</a>
							<form method="post" action="${pageContext.request.contextPath}/bookings/delete">
								<input name="id" value="${booking.id}" type="hidden" />
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
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
				<form method="post" action="${pageContext.request.contextPath}/bookings/book">
					<security:authorize access="hasAuthority('ADMIN')">
						<div class="form-group">
						    <label for="add-user-select">Réserver pour un utilisateur</label>
							<select name="userId" class="form-control" id="add-room-select">
								<c:forEach items="${users}" var="user">
									<option value="${user.id}">${user.username}</option>
								</c:forEach>
								<option value="-1">Aucun</option>
							</select>
						</div>
					</security:authorize>
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
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-primary">Réserver</button>
				</form>
			</div>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/ressources/js/bookings.js"></script>
	</jsp:body>
</t:genericpage>

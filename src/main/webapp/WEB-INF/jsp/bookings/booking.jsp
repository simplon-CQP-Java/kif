<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<head>
<title>Rechercher un ordinateur</title>
</head>

<t:genericpage>
	<jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp"%>
    </jsp:attribute>

	<jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
    </jsp:attribute>
	<jsp:body>
    	<div id="booking" class="row">
    		<div class="col-md-8 col-md-offset-2">
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
		    	<c:choose>
					<c:when test="${booking != null}">
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
						  		<button class="btn btn-primary" type="button"
									data-toggle="collapse" data-target="#update-booking"
									aria-expanded="false" aria-controls="update-booking">
							  		Modifier
								</button>
								<form method="post" action="${pageContext.request.contextPath}/bookings/delete">
									<input name="id" value="${booking.id}" type="hidden" />
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<button type="submit" class="btn btn-danger">Supprimer</button>
								</form>
								<div class="collapse" id="update-booking">
							  		<form method="post" action="${pageContext.request.contextPath}/bookings/edit">
										<security:authorize access="hasAuthority('ADMIN')">
											<div class="form-group">
											    <label for="edit-user-select">Réserver pour un utilisateur</label>
												<select name="userId" class="form-control"
													id="edit-room-select">
													<c:forEach items="${users}" var="user">
														<option value="${user.id}" <c:if test="${booking.user.id == user.id}">selected</c:if>>
															${user.username}
														</option>
													</c:forEach>
													<option value="-1">Aucune</option>
												</select>
											</div>
										</security:authorize>
										<div class="form-group">
										    <label for="edit-room-select">Sélectionner une salle</label>
											<select name="roomId" class="form-control"
												id="edit-room-select">
												<option value="">Aucune</option>
												<c:forEach items="${rooms}" var="room">
													<option value="${room.id}" <c:if test="${booking.room.id == room.id}">selected</c:if>>
														${room.name}
													</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
										    <label for="edit-computer-select">Sélectionner un ordinateur</label>
											<select name="computerId" class="form-control"
												id="edit-computer-select">
												<option value="">Aucun</option>
												<c:forEach items="${computers}" var="computer">
													<option value="${computer.id}" <c:if test="${booking.room.id == room.id}">selected</c:if>>
														${computer.brand} - ${computer.model}
													</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
										    <label for="book-start-input">Date de début</label>
											<div class="input-group date" id="book-start">
								                <input type='text' name="start"
													class="form-control" placeholder="Date de début"
													id="book-start-input" />
								                <span class="input-group-addon">
								                    <span class="glyphicon glyphicon-calendar"></span>
								                </span>
								            </div>
										</div>
										<div class="form-group">
										    <label for="book-end-input">Date de fin</label>
											<div class="input-group date" id="book-end">
								                <input type='text' name="end"
													class="form-control" placeholder="Date de fin"
													id="book-end-input" />
								                <span class="input-group-addon">
								                    <span class="glyphicon glyphicon-calendar"></span>
								                </span>
								            </div>
										</div>
								  		<input name="id" value="${booking.id}" type="hidden" />
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
										<button type="submit" class="btn btn-success">Modifier</button>
							  		</form>
							  	</div>
						  	</div>
						</div>
					</c:when>
				    <c:otherwise>
				        <div class="alert alert-danger">
				        	<span class="glyphicon glyphicon-exclamation-sign"
								aria-hidden="true"></span>
		  					<span class="sr-only">Error:</span>
				        	Aucune réservation trouvée avec l'identifiant ${id}
				        </div>
				    </c:otherwise>
				</c:choose>
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/bookings">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					Liste des réservations
				</a>
			</div>
	    </div>
	    <script type="text/javascript">
			$(document).ready(function() {
				$('#book-start').datetimepicker({
					format : "YYYY-MM-DD HH:mm",
					defaultDate: moment("${booking.start}").format("YYYY-MM-DD HH:mm")
					//sideBySide: true,
				});
				$('#book-end').datetimepicker({
					format : "YYYY-MM-DD HH:mm",
					useCurrent : false,
					defaultDate: moment("${booking.end}").format("YYYY-MM-DD HH:mm")
					//Important! See issue #1075
				});
				$("#book-start").on("dp.change", function(e) {
					$('#book-end').data("DateTimePicker").minDate(e.date);
				});
				$("#book-end").on("dp.change", function(e) {
					$('#book-start').data("DateTimePicker").maxDate(e.date);
				});
			});
		</script>
    </jsp:body>
</t:genericpage>
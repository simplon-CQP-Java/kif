<%@ page contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<head>
	<title>Rechercher une salle</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
    	<div id="room" class="row">
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
				    <c:when test="${room != null}">
				        <div class="panel panel-default">
							<div class="panel-heading">
						   		<h3 class="panel-title"><span class="label label-primary">ID</span> ${room.id}</h3>
						  	</div>
						  	<div class="panel-body">
						  	<ul class="list-group">
								<li class="list-group-item">Nom : ${room.name}</li>
							   	<li class="list-group-item">Places : ${room.places}</li>
							   	<li class="list-group-item">Description : ${room.description}</li>
						    </ul>
						  	</div>
						  	<div class="panel-footer">
						  		<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#update-room" aria-expanded="false" aria-controls="update-room">
							  		Modifier
								</button>
								<form method="post" action="${pageContext.request.contextPath}/rooms/delete">
									<input name="id" value="${room.id}" type="hidden" />
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<button type="submit" class="btn btn-danger">Supprimer</button>
								</form>
								<div class="collapse" id="update-room">
							  		<form method="post" action="${pageContext.request.contextPath}/rooms/edit">
								  		<div class="form-group">
										    <label for="name-input">Nom</label>
											<input name="name" placeholder="Nom" type="text" class="form-control" id="name-input" value="${room.name}">
										</div>
										<div class="form-group">
										    <label for="places-input">Places</label>
											<input name="places" placeholder="Places" type="number" class="form-control" id="places-input" value="${room.places}">
										</div>
										<div class="form-group">
										    <label for="description-input">Description</label>
											<input name="description" placeholder="Description" type="text" class="form-control" id="description-input" value="${room.description}">
										</div>
								  		<input name="id" value="${room.id}" type="hidden" />
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										<button type="submit" class="btn btn-success">Modifier</button>
							  		</form>
							  	</div>
						  	</div>
						</div>
				    </c:when>
				    <c:otherwise>
				        <div class="alert alert-danger">
				        	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		  					<span class="sr-only">Error:</span>
				        	Aucune salle trouvé avec l'identifiant ${id}
				        </div>
				    </c:otherwise>
				</c:choose>
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/rooms">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					Liste des salles
				</a>
			</div>
		</div>
    </jsp:body>
</t:genericpage>

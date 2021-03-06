<%@ page contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<head>
	<title>Salles</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
    	<div id="salles" class="row">
        	<div class="jumbotron">
			  <h3>Salles</h3>
			  <p>Liste et gestion des salles</p>
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
        		<c:if test="${empty rooms}">
        			<div class="alert alert-warning" role="alert">
        				<button type="button" class="close" data-dismiss="alert" aria-label="Fermer"><span aria-hidden="true">&times;</span></button>
        				Aucune salle n'a été trouvée.
        			</div>
        		</c:if>
        		<c:forEach items="${rooms}" var="room">
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
							<a href="${pageContext.request.contextPath}/rooms/roomById?id=${room.id}" class="btn btn-primary">Voir</a>
							<form method="post" action="${pageContext.request.contextPath}/rooms/delete">
								<input name="id" value="${room.id}" type="hidden" />
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<button type="submit" class="btn btn-danger">Supprimer</button>
							</form>
					  	</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-md-6">
				<div class="page-header">
					<h5>Ajouter une salle</h5>
				</div>
				<form method="post" action="${pageContext.request.contextPath}/rooms/add">
					<div class="form-group">
					    <label for="add-name-input">Nom</label>
						<input name="name" placeholder="Nom" type="text" class="form-control" id="add-name-input">
					</div>
					<div class="form-group">
					    <label for="add-places-input">Places</label>
						<input name="places" placeholder="Places" type="number" class="form-control" id="add-places-input">
					</div>
					<div class="form-group">
					    <label for="add-description-input">Description</label>
						<input name="description" placeholder="Description" type="text" class="form-control" id="add-description-input">
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-success">Ajouter</button>
				</form>

				<div class="page-header">
					<h5>Rechercher une salle par son identifiant</h5>
				</div>
				<form method="post" action="${pageContext.request.contextPath}/rooms/roomById">
					<div class="form-group">
					    <label for="room-by-id-input">Id</label>
						<input name="id" placeholder="Id" type="number" class="form-control" id="room-by-id-input">
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-primary">Rechercher</button>
				</form>
			</div>
    	</div>
    </jsp:body>
</t:genericpage>

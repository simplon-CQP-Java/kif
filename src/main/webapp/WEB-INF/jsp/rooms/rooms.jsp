<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
			</div>
        	<div id="list" class="col-md-6">
        		<c:forEach items="${rooms}" var="room">
		        	<div class="panel panel-default">
						<div class="panel-heading">
					   		<h3 class="panel-title">${room.id}</h3>
					  	</div>
					  	<div class="panel-body">
					  	<ul class="list-group">
  							<li class="list-group-item">Marque : ${room.name}</li>
						   	<li class="list-group-item">Modèle : ${room.places}</li>
						   	<li class="list-group-item">description : ${room.description}</li>
					    </ul>
					  	</div>
					  	<div class="panel-footer">
						  	<form action="/rooms/delete">
								<input name="id" value="${room.id}" type="hidden" />
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
				<form method="get" action="/rooms/add">
					<div class="form-group">
					    <label for="add-name-input">Nom</label>
						<input name="name" placeholder="Nom" type="text" class="form-control" id="add-name-input">
					</div>
					<div class="form-group">
					    <label for="add-places-input">Places</label>
						<input name="places" placeholder="Places" type="text" class="form-control" id="add-places-input">
					</div>
					<div class="form-group">
					    <label for="add-description-input">Description</label>
						<input name="description" placeholder="Description" type="text" class="form-control" id="add-description-input">
					</div>
					<button type="submit" class="btn btn-success">Ajouter</button>
				</form>

				<div class="page-header">
					<h5>Rechercher une salle par son identifiant</h5>
				</div>
				<form method="get" action="/rooms/roomById">
					<div class="form-group">
					    <label for="room-by-id-input">Id</label>
						<input name="id" placeholder="Id" type="number" class="form-control" id="room-by-id-input">
					</div>
					<button type="submit" class="btn btn-primary">Rechercher</button>
				</form>
			</div>
    	</div>
    </jsp:body>
</t:genericpage>

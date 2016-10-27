<%@ page contentType="text/html;" pageEncoding="UTF-8"%><html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<head>
	<title>Ordinateurs</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>
    
    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
    	<div id="computers" class="row">
        	<div class="jumbotron">
			  <h3>Ordinateurs</h3>
			</div>
        	<div id="list" class="col-md-6">
        		<c:forEach items="${computers}" var="computer">
		        	<div class="panel panel-default">
						<div class="panel-heading">
					   		<h3 class="panel-title">${computer.id}</h3>
					  	</div>
					  	<div class="panel-body">
					    <ul class="list-group">
					    	<li class="list-group-item">Marque : ${computer.brand}</li>
						   	<li class="list-group-item">Modèle : ${computer.model}</li>
						   	<li class="list-group-item">Serial : ${computer.serial}</li>
					    </ul>
					  	</div>
					  	<div class="panel-footer">
						  	<form action="/computers/delete">
								<input name="id" value="${computer.id}" type="hidden" />
								<button type="submit" class="btn btn-danger">Supprimer</button>
							</form>
					  	</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-md-6">
				<div class="page-header">
					<h5>Ajouter un ordinateur</h5>
				</div>
				<form method="get" action="/computers/add">
					<div class="form-group">
					    <label for="add-brand-input">Marque</label>
						<input name="brand" placeholder="Marque" type="text" class="form-control" id="add-brand-input">
					</div>
					<div class="form-group">
					    <label for="add-model-input">Modèle</label>
						<input name="model" placeholder="Modèle" type="text" class="form-control" id="add-model-input">
					</div>
					<div class="form-group">
					    <label for="add-serial-input">Serial</label>
						<input name="serial" placeholder="Serial" type="number" class="form-control" id="add-serial-input">
					</div>
					<button type="submit" class="btn btn-success">Ajouter</button>
				</form>
			
				<form method="get" action="/computers/computerById">
					<div class="page-header">
						<h5>Rechercher un ordinateur par son identifiant</h5>
					</div>
					<div>
						<div class="form-group">
						    <label for="computeur-by-id-input">Id</label>
							<input name="id" placeholder="Id" type="number" class="form-control" id="computeur-by-id-input">
						</div>
						<button type="submit" class="btn btn-primary">Rechercher</button>
					</div>
				</form>
			</div>
    	</div>
    </jsp:body>
</t:genericpage>
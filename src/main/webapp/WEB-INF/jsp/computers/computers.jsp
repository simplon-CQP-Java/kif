<%@ page contentType="text/html;" pageEncoding="UTF-8"%>
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
        		<c:forEach items="${computers}" var="computer">
		        	<div class="panel panel-default">
						<div class="panel-heading">
					   		<h3 class="panel-title"><span class="label label-primary">ID</span> ${computer.id}</h3>
					  	</div>
					  	<div class="panel-body">
					    <ul class="list-group">
					    	<li class="list-group-item">Marque : ${computer.brand}</li>
						   	<li class="list-group-item">Modèle : ${computer.model}</li>
					    </ul>
					  	</div>
					  	<div class="panel-footer">
					  		<a href="/computers/computerById?id=${computer.id}" class="btn btn-primary">Voir</a>
							<form method="post" action="/computers/delete">
								<input name="id" value="${computer.id}" type="hidden" />
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
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
				<form method="post" action="/computers/add">
					<div class="form-group">
					    <label for="add-brand-input">Marque</label>
						<input name="brand" placeholder="Marque" type="text" class="form-control" id="add-brand-input">
					</div>
					<div class="form-group">
					    <label for="add-model-input">Modèle</label>
						<input name="model" placeholder="Modèle" type="text" class="form-control" id="add-model-input">
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-success">Ajouter</button>
				</form>

				<div class="page-header">
					<h5>Rechercher un ordinateur par son identifiant</h5>
				</div>
				<form method="post" action="/computers/computerById">
					<div class="form-group">
					    <label for="computer-by-id-input">Id</label>
						<input name="id" placeholder="Id" type="number" class="form-control" id="computer-by-id-input">
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-primary">Rechercher</button>
				</form>
			</div>
    	</div>
    </jsp:body>
</t:genericpage>

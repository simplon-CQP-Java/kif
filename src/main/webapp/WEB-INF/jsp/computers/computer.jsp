<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<head>
	<title>Rechercher un ordinateur</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
    	<div id="computer" class="row">
    		<div class="col-md-8 col-md-offset-2">
		    	<c:choose>
					<c:when test="${computer != null}">
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
						  		<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#update-computer" aria-expanded="false" aria-controls="update-computer">
							  		Modifier
								</button>
								<form method="post" action="${pageContext.request.contextPath}/computers/delete">
									<input name="id" value="${computer.id}" type="hidden" />
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<button type="submit" class="btn btn-danger">Supprimer</button>
								</form>
								<div class="collapse" id="update-computer">
							  		<form method="post" action="${pageContext.request.contextPath}/computers/edit">
								  		<div class="form-group">
								    		<label for="brand-input">Marque</label>
											<input name="brand" placeholder="Marque" type="text" class="form-control" id="brand-input" value="${computer.brand}">
										</div>
										<div class="form-group">
								    		<label for="model-input">Modèle</label>
											<input name="model" placeholder="Modèle" type="text" class="form-control" id="model-input" value="${computer.model}">
										</div>
								  		<input name="id" value="${computer.id}" type="hidden" />
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
				        	Aucun ordinateur trouvé avec l'identifiant ${id}
				        </div>
				    </c:otherwise>
				</c:choose>
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/computers">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					Liste des ordinateurs
				</a>
			</div>
	    </div>
    </jsp:body>
</t:genericpage>

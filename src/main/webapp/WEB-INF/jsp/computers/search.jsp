<%@ page contentType="text/html;" pageEncoding="UTF-8"%>
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
    	<c:choose>
			<c:when test="${computer != null}">
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
			</c:when>
		    <c:otherwise>
		        <div class="alert alert-danger">
		        	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
  					<span class="sr-only">Error:</span>
		        	Aucun ordinateur trouvé avec l'identifiant ${id}
		        </div>
		    </c:otherwise>
		</c:choose>
		<a class="btn btn-primary" href="/computers">
			<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
			Liste des ordinateurs
		</a>
    </jsp:body>
</t:genericpage>

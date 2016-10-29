<%@ page contentType="text/html;" pageEncoding="UTF-8"%><html>
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
	    <c:choose>
		    <c:when test="${room != null}">
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
		    </c:when>    
		    <c:otherwise>
		        <div class="alert alert-danger">
		        	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
  					<span class="sr-only">Error:</span>
		        	Aucune salle trouvé avec l'identifiant ${id}
		        </div>
		    </c:otherwise>
		</c:choose>
		<a class="btn btn-primary" href="/rooms">
			<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
			Liste des salles
		</a>
    </jsp:body>
</t:genericpage>
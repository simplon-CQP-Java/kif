<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<head>
	<title>Rechercher un utilisateur</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
    	<div id="users-search" class="row">
    		<div class="col-md-8 col-md-offset-2">
		    	<c:choose>
					<c:when test="${user != null}">
				    	<div class="panel panel-default">
							<div class="panel-heading">
						   		<h3 class="panel-title">${user.id}</h3>
						  	</div>
						  	<div class="panel-body">
						    <ul class="list-group">
						    	<li class="list-group-item">Nom : ${user.username}</li>
							   	<li class="list-group-item">Email : ${user.email}</li>
							   	<li class="list-group-item">Rôle : ${user.role}</li>
						    </ul>
						  	</div>
						  	<div class="panel-footer">
								<form method="post" action="${pageContext.request.contextPath}/users/delete">
									<input name="id" value="${user.id}" type="hidden" />
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<button type="submit" class="btn btn-danger">Supprimer</button>
								</form>
						  	</div>
						</div>
					</c:when>
				    <c:otherwise>
				        <div class="alert alert-danger">
				        	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		  					<span class="sr-only">Error:</span>
				        	Aucun utilisateur trouvé avec le nom d'utilisateur suivant : ${username}
				        </div>
				    </c:otherwise>
				</c:choose>
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/users">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					Liste des utilisateurs
				</a>
    		</div>
    	</div>
    </jsp:body>
</t:genericpage>

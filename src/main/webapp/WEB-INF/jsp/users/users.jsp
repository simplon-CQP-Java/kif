<%@ page contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<head>
  <title>Utilisateurs</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
   		<div id="users" class="row">
        	<div class="jumbotron">
			  <h3>Utilisateurs</h3>
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
        		<c:if test="${empty users}">
        			<div class="alert alert-warning" role="alert">
        				<button type="button" class="close" data-dismiss="alert" aria-label="Fermer"><span aria-hidden="true">&times;</span></button>
        				Aucun utilisateur n'a été trouvée.
        			</div>
        		</c:if>
        		<c:forEach items="${users}" var="user">
		        	<div class="panel panel-default">
						<div class="panel-heading">
					   		<h3 class="panel-title"><span class="label label-primary">ID</span> ${user.id}</h3>
					  	</div>
					  	<div class="panel-body">
					    <ul class="list-group">
					    	<li class="list-group-item">Nom : ${user.username}</li>
						   	<li class="list-group-item">Rôle : ${user.role}</li>
					    </ul>
					  	</div>
					  	<div class="panel-footer">
					  		<a href="${pageContext.request.contextPath}/users/userById?id=${user.id}" class="btn btn-primary">
					  			Modifier
					  		</a>
						    <c:if test="${user.enabled == 'true'}">
						       <form method="post" action="${pageContext.request.contextPath}/users/delete">
									<input name="id" value="${user.id}" type="hidden" />
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<button type="submit" class="btn btn-danger">Supprimer</button>
								</form>
						    </c:if>
						    <c:if test="${user.enabled == 'false'}">
						        <form method="post" action="${pageContext.request.contextPath}/users/active">
									<input name="id" value="${user.id}" type="hidden" />
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<button type="submit" class="btn btn-success">Activer</button>
								</form>
						    </c:if>
					  	</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-md-6">
				<div class="page-header">
					<h5>Ajouter un utilisateur</h5>
				</div>
				<form method="post" action="${pageContext.request.contextPath}/users/add">
					<div class="form-group">
					    <label for="add-username-input">Nom</label>
						<input name="username" placeholder="Nom" type="text" class="form-control" id="add-username-input">
					</div>
					<div class="form-group">
					    <label for="add-password-input">Mot de passe</label>
						<input name="password" placeholder="Mot de passe" type="password" class="form-control" id="add-password-input">
					</div>
					<div class="form-group">
					    <label for="add-confirm-password-input">Confirmer votre mot de passe</label>
						<input name="confirmPassword" placeholder="Mot de passe" type="password" class="form-control" id="add-confirm-password-input">
					</div>
					<div class="form-group">
					    <label for="add-role-input">Rôle</label>
						<select name="role" class="form-control" id="add-role-input">
							<option value="USER" selected>Utilisateur</option>
							<option value="ADMIN">Administrateur</option>
						</select>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-success">Ajouter</button>
				</form>
				<div class="page-header">
					<h5>Rechercher un utilisateur par son identifiant</h5>
				</div>
				<form method="post" action="${pageContext.request.contextPath}/users/userByUsername">
					<div class="form-group">
					    <label for="user-by-username-input">Nom d'utilisateur</label>
						<input name="username" placeholder="Nom d'utilisateur" type="text" class="form-control" id="user-by-username-input">
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-primary">Rechercher</button>
				</form>
			</div>
		</div>
    </jsp:body>
</t:genericpage>

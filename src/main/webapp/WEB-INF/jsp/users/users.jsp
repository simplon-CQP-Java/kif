<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
        	<div id="list" class="col-md-6">
        		<c:forEach items="${users}" var="user">
		        	<div class="panel panel-default">
						<div class="panel-heading">
					   		<h3 class="panel-title">${user.id}</h3>
					  	</div>
					  	<div class="panel-body">
					    <ul class="list-group">
					    	<li class="list-group-item">Nom : ${user.username}</li>
						   	<li class="list-group-item">Rôle : ${user.role}</li>
					    </ul>
					  	</div>
					  	<div class="panel-footer">
						  	<form action="/users/delete">
								<input name="id" value="${user.id}" type="hidden" />
								<button type="submit" class="btn btn-danger">Supprimer</button>
							</form>
					  	</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-md-6">
				<div class="page-header">
					<h5>Ajouter un utilisateur</h5>
				</div>
				<form method="get" action="/users/add">
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
					<button type="submit" class="btn btn-success">Ajouter</button>
				</form>

				<div class="page-header">
					<h5>Rechercher un utilisateur par son identifiant</h5>
				</div>
				<form method="get" action="/users/userByUsername">
					<div class="form-group">
					    <label for="user-by-username-input">Nom d'utilisateur</label>
						<input name="username" placeholder="Nom d'utilisateur" type="text" class="form-control" id="user-by-username-input">
					</div>
					<button type="submit" class="btn btn-primary">Rechercher</button>
				</form>
			</div>
		</div>
    </jsp:body>
</t:genericpage>

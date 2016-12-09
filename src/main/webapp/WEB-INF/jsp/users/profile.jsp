<%@ page contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<head>
	<title>
		<security:authorize access="isAuthenticated()">
			Profil - <security:authentication property="name" />
		</security:authorize>
	</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
   		<div id="profile" class="row">
        	<div class="jumbotron">
			  	<h3>
			  		<security:authorize access="isAuthenticated()">
						<security:authentication property="name" />
					</security:authorize>
			  	</h3>
			  	<p>Mettre à jour les informations de votre compte</p>
			</div>
			<div class="col-md-6">
				<div class="page-header">
					<h5>Modifier mon nom d'utilisateur</h5>
				</div>
				<form method="get" action="/users/edit/username">
					<input name="id" value="${user.id}" type="hidden" >
					<div class="form-group">
					    <label for="new-username-input">Nom</label>
						<input name="username" placeholder="Nom" type="text" class="form-control" id="new-username-input">
					</div>
					<button type="submit" class="btn btn-success">Valider</button>
				</form>
				<hr />
				<button type="submit" class="btn btn-danger" data-toggle="modal" data-target="#confirmDelete">
					Supprimer mon compte
				</button>
			</div>
			<div class="col-md-6">
				<div class="page-header">
					<h5>Modifier mon mot de passe</h5>
				</div>
				<form method="get" action="/users/edit/password">
					<input name="id" value="${user.id}" type="hidden" >
					<div class="form-group">
					    <label for="password-input">Mot de passe actuel</label>
						<input name="password" placeholder="Mot de passe" type="password" class="form-control" id="password-input">
					</div>
					<div class="form-group">
					    <label for="new-password-input">Nouveau mot de passe</label>
						<input name="newPassword" placeholder="Mot de passe" type="password" class="form-control" id="new-password-input">
					</div>
					<div class="form-group">
					    <label for="new-confirm-password-input">Confirmer votre mot de passe</label>
						<input name="confirmNewPassword" placeholder="Mot de passe" type="password" class="form-control" id="new-confirm-password-input">
					</div>
					<button type="submit" class="btn btn-success">Valider</button>
				</form>
			</div>
		</div>
		<div class="modal fade" id="confirmDelete" tabindex="-1" role="dialog" aria-labelledby="confirmDelete">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title">Supprimer votre compte</h4>
		      </div>
		      <div class="modal-body">
  				<div class="alert alert-danger" role="alert">
  					<h4>
	  					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	  					<span class="sr-only">Error:</span>
	  					Êtes-vous complètement sûr de supprimer votre compte ?
  					</h4>
  					<p>Toutes vos données seront perdues.</p>
  				</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <form method="get" action="/users/edit/delete">
					<input name="id" value="${user.id}" type="hidden" >
					<button type="submit" class="btn btn-danger" data-toggle="modal" data-target="#confirmDelete">
						Oui, supprimer mon compte
					</button>
				</form>
		      </div>
		    </div>
		  </div>
		</div>
    </jsp:body>
</t:genericpage>

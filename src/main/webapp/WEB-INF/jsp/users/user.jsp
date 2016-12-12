<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<head>
	<title>${user.username}</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
	    <div id="user" class="row">
        	<div class="jumbotron">
			  	<h3>
			  		${user.username}
			  		<c:if test="${user.enabled == 'true'}">
				       <button type="submit" class="pull-right btn btn-danger" data-toggle="modal" data-target="#confirmDelete">
							Supprimer ce compte
						</button>
				    </c:if>
				    <c:if test="${user.enabled == 'false'}">
				        <form method="post" action="/users/active" class="inline pull-right">
							<input name="id" value="${user.id}" type="hidden" />
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<button type="submit" class="btn btn-success">Activer</button>
						</form>
				    </c:if>
			  	</h3>
			</div>
			<div class="col-md-8 col-md-offset-2">
				<form method="post" action="/users/edit" class="panel panel-primary">
					<div class="panel-heading">Modifier l'utilisateur</div>
					<div class="panel-body">
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
						<input name="id" value="${user.id}" type="hidden" />
						<div class="form-group">
						    <label for="username-input">Nom</label>
							<input name="username" placeholder="Nom" type="text" class="form-control" id="username-input" value="${user.username}">
						</div>
						<div class="form-group">
						    <label for="password-input">Ancien mot de passe</label>
							<input name="password" placeholder="Ancien mot de passe" type="password" class="form-control" id="password-input">
						</div>
						<div class="form-group">
						    <label for="new-password-input">Nouveau mot de passe</label>
							<input name="newPassword" placeholder="Nouveau mot de passe" type="password" class="form-control" id="new-password-input">
						</div>
						<div class="form-group">
						    <label for="confirm-new-password-input">Confirmer votre nouveau mot de passe</label>
							<input name="confirmNewPassword" placeholder="Confirmer le mot de passe" type="password" class="form-control" id="confirm-new-password-input">
						</div>
						<div class="form-group">
						    <label for="role-input">Rôle</label>
							<select name="role" class="form-control" id="role-input">
								<option <c:if test="${user.role == 'USER'}">selected</c:if> value="USER" selected>Utilisateur</option>
								<option <c:if test="${user.role == 'ADMIN'}">selected</c:if> value="ADMIN">Administrateur</option>
							</select>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<div class="panel-footer">
						<button type="submit" class="btn btn-success">Enregistrer</button>
					</div>
				</form>
			</div>
		</div>
		<div class="modal fade" id="confirmDelete" tabindex="-1" role="dialog" aria-labelledby="confirmDelete">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Fermer"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title">Supprimer l'utilisateur ${user.username}</h4>
		      </div>
		      <div class="modal-body">
  				<div class="alert alert-danger" role="alert">
  					<h4>
	  					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	  					<span class="sr-only">Error:</span>
	  					Êtes-vous complètement sûr de supprimer cet utilisateur ?
  					</h4>
  					<p>Toutes les données de ce compte seront perdues.</p>
  				</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        <form method="post" action="/users/delete">
					<input name="id" value="${user.id}" type="hidden" >
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
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
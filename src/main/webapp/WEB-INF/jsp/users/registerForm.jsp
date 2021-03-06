<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<head>
	<title>Register</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
      <div id="register" class="row">
        <div class="col-md-6 col-md-offset-3">
           <c:if test="${error != null}">
	      	<div class="alert alert-danger alert-dismissible" role="alert">
	      	  <button type="button" class="close" data-dismiss="alert" aria-label="Fermer"><span aria-hidden="true">&times;</span></button>
	      	  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			  <span class="sr-only">Error:</span>
			  ${error}
			</div>
	      </c:if>
          <form method="post" action="${pageContext.request.contextPath}/users/register">
            <div class="form-group">
			  <label for="register-username-input">Nom</label>
			  <input name="username" placeholder="Nom" type="text" class="form-control" id="register-username-input">
			</div>
            <div class="form-group">
              <label for="register-password">Mot de passe</label>
              <input name="password" type="password" id="register-password" class="form-control" placeholder="Mot de passe" />
            </div>
            <div class="form-group">
              <label for="register-confirm-password">Confirmer le mot de passe</label>
              <input name="confirmPassword" type="password" id="register-confirm-password" class="form-control" placeholder="Mot de passe" />
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <button type="submit" class="btn btn-success">Inscription</button>
            <a href="${pageContext.request.contextPath}/login" class="pull-right">Connexion</a>
          </form>
        </div>
      </div>
    </jsp:body>
</t:genericpage>

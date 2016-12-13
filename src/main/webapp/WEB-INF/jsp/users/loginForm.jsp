<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<head>
	<title>Connexion</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
      <div id="login" class="row">
        <div class="col-md-6 col-md-offset-3">
	      <c:if test="${error != null}">
	      	<div class="alert alert-danger alert-dismissible" role="alert">
	      	  <button type="button" class="close" data-dismiss="alert" aria-label="Fermer"><span aria-hidden="true">&times;</span></button>
	      	  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			  <span class="sr-only">Error:</span>
			  ${error}
			</div>
	      </c:if>
          <form accept-charset="utf-8" action="${pageContext.request.contextPath}/login" method="POST">
            <div class="form-group">
              <label for="username-email">Nom d'utilisateur</label>
              <input type="text" name="username" id="login-username" class="form-control" placeholder="Username" />
            </div>
            <div class="form-group">
              <label for="login-password">Mot de passe</label>
              <input type="password" name="password" id="login-password" class="form-control" placeholder="Mot de passe" />
            </div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <button type="submit" class="btn btn-success">Connexion</button>
            <a href="${pageContext.request.contextPath}/register" class="pull-right">Inscription</a>
          </form>
        </div>
      </div>
    </jsp:body>
</t:genericpage>

<%@ page contentType="text/html" contentType="text/html"pageEncoding="UTF-8"%>
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
          <form method="get">
            <div class="form-group">
              <label for="register-email">Email</label>
              <input type="text" id="register-email" class="form-control" placeholder="Email" />
            </div>
            <div class="form-group">
              <label for="register-password">Mot de passe</label>
              <input type="password" id="register-password" class="form-control" placeholder="Mot de passe" />
            </div>
            <div class="form-group">
              <label for="register-confirm-password">Confirmer le mot de passe</label>
              <input type="password" id="register-confirm-password" class="form-control" placeholder="Mot de passe" />
            </div>
            <button type="submit" class="btn btn-success">Inscription</button>
          </form>
        </div>
      </div>
    </jsp:body>
</t:genericpage>

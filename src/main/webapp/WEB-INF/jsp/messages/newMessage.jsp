<%@ page contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<head>
	<title>Contact - KIF</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
    	<div id="new-message" class="row">
    		<div class="col-md-8 col-md-offset-2">
				<div class="page-header">
					<h5>Demande d'information</h5>
				</div>
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
				<form method="post" action="${pageContext.request.contextPath}/contactSubmit">
					<div class="form-group">
					    <label for="add-title-input">Titre</label>
						<input name="title" placeholder="Titre" type="text" class="form-control" id="add-title-input">
					</div>
					<div class="form-group">
					    <label for="email-input">Email</label>
						<input name="email" placeholder="Adresse email" type="text" class="form-control" id="email-input">
					</div>
					<div class="form-group">
					    <label for="add-content">Message</label>
						<textarea class="form-control" name="content" id="add-content" rows="3"></textarea>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-success">Envoyer</button>
				</form>
    		</div>
    	</div>
    </jsp:body>
</t:genericpage>
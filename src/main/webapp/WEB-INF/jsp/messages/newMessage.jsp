<%@ page contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<head>
	<title>Messages</title>
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
				<form method="get" action="/messages/add">
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
					<button type="submit" class="btn btn-success">Envoyer</button>
				</form>
    		</div>
    	</div>
    </jsp:body>
</t:genericpage>
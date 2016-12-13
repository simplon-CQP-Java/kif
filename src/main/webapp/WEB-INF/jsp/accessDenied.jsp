<%@ page contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<head>
	<title>Accès refusé - KIF</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
    	<div class="page-header">
	    	<h2>Accès refusé</h2>
    	</div>
    	<a href="/login" class="btn btn-danger">Connexion</a>
    </jsp:body>
</t:genericpage>
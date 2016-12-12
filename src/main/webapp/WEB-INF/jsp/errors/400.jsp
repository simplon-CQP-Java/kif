<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<head>
	<title>Bad Request - KIF</title>
</head>

<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>

    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
		<div class="error-page row" id="400">
	    	<div class="jumbotron">
				<h2>Erreur 400</h2>
				<p>Bad request</p>
				<p>
					<a class="btn btn-primary btn-lg" href="/" role="button">
						<span class="glyphicon glyphicon-home"></span>&nbsp;
						Accueil
					</a>
				</p>
			</div>
    	</div>
    </jsp:body>
</t:genericpage>
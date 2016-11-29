<%@ page contentType="text/html;" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:genericpage>
    <jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
    </jsp:attribute>
    
    <jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
    </jsp:attribute>
    <jsp:body>
    	<div class="row">
			<div class="jumbotron">
				<h1>KIF Projet</h1>
				<p>Gestion de salles et ordinateurs</p>
			</div>
		</div>
    </jsp:body>
</t:genericpage>
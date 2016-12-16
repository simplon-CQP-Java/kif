<%@ page contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<title>Historique des réservations</title>
</head>

<t:genericpage>
	<jsp:attribute name="header">
      <%@ include file="/WEB-INF/jsp/include/menu.jsp"%>
    </jsp:attribute>

	<jsp:attribute name="footer">
      <%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
    </jsp:attribute>

	<jsp:body>
    	<div id="history" class="row">
	    	<div class="jumbotron">
				<h3>Historique des réservations</h3>
			</div>
			<div class="col-md-12">
		    	<table class="table table-hover table-responsive">
		    		<tr class="info">
		    			<th>Utilisateur</th>
		    			<th>Date de début</th>
		    			<th>Date de fin</th>
		    			<th>Salle</th>
		    			<th>Ordinateur</th>
		    		</tr>
		        	<c:forEach items="${history}" var="booking">
		            	<tr>
		                    <td><p>${booking.user.username}</p></td>
		                    <td><p>
		                    	<fmt:formatDate value="${booking.start}"
										pattern="dd-MM-yyyy HH:mm:ss" />
		                    </p></td>
		                    <td><p>
								<fmt:formatDate value="${booking.end}"
										pattern="dd-MM-yyyy HH:mm:ss" />  
							</p></td>
							<td><p>${booking.room.name}</p></td>
		                	<td><p>${booking.computer.brand}</p></td>
		            	</tr>
		        	</c:forEach>
		    	</table>
			</div>
    	</div>
    </jsp:body>
</t:genericpage>
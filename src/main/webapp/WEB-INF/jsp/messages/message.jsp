<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
    	<div id="message" class="row">
    		<div class="col-md-8 col-md-offset-2">
    			<c:if test="${message != null}">
			       	<div class="panel panel-primary">
	    				<div class="panel-heading">${message.title}</div>
	    				<div class="panel-body">
	    				<p>${message.email}</p>
						<p>${message.content}</p>
						<h3>Réponses</h3>
						<ul class="list-group">
							<c:forEach items="${replys}" var="reply">
								<li class="list-group-item">${reply.userId} - ${reply.content}</li>
							</c:forEach>
						</ul>
					</div>
	    				<div class="panel-footer">
	    					<h3>Réponse rapide</h3>
	    					<form action="/messages/${message.id}/reply" method="get">
	    						<input type="hidden" value="${message.id}" name="id">
	    						<div class="form-group">
								    <label for="add-reply">Message</label>
									<textarea class="form-control" name="reply" id="add-reply" rows="3"></textarea>
								</div>
								<button type="submit" class="btn btn-success">Envoyer</button>
	    					</form>
	    				</div>
    				</div>
			    </c:if>
			    <c:if test="${message == null}">
			        <div class="alert alert-danger" role="alert">
				        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			        	Aucun message trouvé.
			        </div>
			    </c:if>
    		</div>
    	</div>
    </jsp:body>
</t:genericpage>

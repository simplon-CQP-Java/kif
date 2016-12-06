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
    	<div id="messages" class="row">
    		<div class="jumbotron">
			  <h3>Messages</h3>
			</div>
        	<div id="list" class="col-md-8 col-md-offset-2">
        		<c:forEach items="${messages}" var="message">
		        	<div class="panel panel-default">
						<div class="panel-heading">
					   		<h3 class="panel-title">${message.id}</h3>
					  	</div>
					  	<div class="panel-body">
					  	<ul class="list-group">
  							<li class="list-group-item">Titre : ${message.title}</li>
						   	<li class="list-group-item">Message : ${message.content}</li>
						   	<li class="list-group-item">Email : ${message.email}</li>
					    </ul>
					  	</div>
					  	<div class="panel-footer">
							<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#quick-reply" aria-expanded="false" aria-controls="quick-reply">
							  Réponse rapide
							</button>
					  		<a href="/messages/messageById/${message.id}" class="btn btn-primary">Voir</a>
						  	<form action="/messages/delete">
								<input name="id" value="${message.id}" type="hidden" />
								<button type="submit" class="btn btn-danger">Supprimer</button>
							</form>
							<div class="collapse" id="quick-reply">
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
					</div>
				</c:forEach>
			</div>
		</div>
    </jsp:body>
</t:genericpage>

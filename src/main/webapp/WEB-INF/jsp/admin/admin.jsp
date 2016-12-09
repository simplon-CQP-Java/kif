<%@ page contentType="text/html;" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"   %>
<html ng-app="myApp">
<head >
	<title>Admin</title>
</head>

<t:genericpage>
<jsp:attribute name="header">
<%@ include file="/WEB-INF/jsp/include/menu.jsp" %>
</jsp:attribute>

<jsp:attribute name="footer">
<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
</jsp:attribute>
<jsp:body>
<div id="admin" class="row"  >
	<div class="col-xs-10">
        <p ><a href="../users">Gerer un utilisateur</a></p>
        <p><a href="../rooms">Gérer une salle</a></p>
        <p><a href="../bookings">Voir les reservations</a></p>
    </div>
    
    <div class="row">
    	Utilisateurs
    	<div class="col-xs-10">
    		<ul id="users-list" >
             <c:forEach items="${users}" var="user">
             <li><a href="/users/userById?id=${user.id}">${user.username}</a></li>
         </c:forEach>
     </ul>
 </div>
</div>

<div class="row">
   Les Réservations
   <div class="col-xs-10">
      <ul>
         <c:forEach items="${bookings}" var="booking">
         <li><a href="/bookings/">${booking.id} créée le: {booking.createdAt}</a></li>
     </c:forEach>
 </ul>
</div>
</div>
</div>



</jsp:body>
</t:genericpage>
</html>


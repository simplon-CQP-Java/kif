<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="bookings-calendar" class="row">
	<div class="col-md-12">
		<h2>
			Calendrier des réservations de salles et ordinateurs
			<a href="${pageContext.request.contextPath}/bookings" class="btn btn-primary pull-right">Nouvelle réservation</a>
		</h2>
		<div class="legend">
			<h4>Légende</h4>
			<span class="label label-room-computer">Salle et ordinateur</span>
			<span class="label label-room">Salle</span>
			<span class="label label-computer">Ordinateur</span>
		</div>
	</div>
	<div id="calendar" class="col-md-12"></div>
</div>
<c:set var="user" value="${pageContext.request.userPrincipal}" />

<script type="text/javascript" src="${pageContext.request.contextPath}/ressources/js/lib/fullcalendar.min.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/ressources/js/lib/fc-locale-all.js'></script>
<script type="text/javascript">
	$(document).ready(function() {
		var user = "${user}";
		var userName = "${user.name}";
		var userRole = "${user.authorities}";
		var calendar = $('#calendar').fullCalendar({
			locale: 'fr',
			events: '${pageContext.request.contextPath}/bookings/getCalendarBookings',
			dayClick: function() {
		        console.log('a day has been clicked!');
		    },
		    eventClick: function(e, element) {
		    	var popoverOptions = {
	            	title: e.title,
	            	container: 'body',
	            	placement: 'top',
	            	trigger: 'focus',
	            	html: true,
	            	content: '<div><span class="bold">Réservé de</span> ' + moment(e.start).format('LLLL') + ' à ' + moment(e.end).format('LLLL') + ' par <span class="bold">' + e.user.username + '</span></div>'
		    	};
				if (userRole == "[ADMIN]" || userName == e.user.username) {
					popoverOptions.content += '<a class="btn btn-block btn-primary" href="${pageContext.request.contextPath}/bookings/bookingById?id=' + e._id + '">Modifier</a>';
					popoverOptions.content += '<a class="btn btn-block btn-danger" href="${pageContext.request.contextPath}/bookings/delete?id=' + e._id + '">Supprimer</a>';
				}
		    	$(element.currentTarget).popover(popoverOptions);
	            $(element.currentTarget).popover('toggle');
		    }
		});
	});
</script>

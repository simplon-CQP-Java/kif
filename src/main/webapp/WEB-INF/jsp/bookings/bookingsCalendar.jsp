<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<div id="bookings-calendar" class="row">
	<div class="col-md-12">
		<h2>Calendrier des réservations de salles et ordinateurs</h2>
	</div>
	<div id="calendar" class="col-md-12"></div>
</div>
<security:authorize access="isAuthenticated()">
	<security:authentication var="user" property="principal" />
</security:authorize>
<script type="text/javascript" src="${pageContext.request.contextPath}/ressources/js/lib/fullcalendar.min.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/ressources/js/lib/fc-locale-all.js'></script>
<script type="text/javascript">
	$(document).ready(function() {
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
				if (userRole == "[ADMIN]") {
					popoverOptions.content += '<a class="btn btn-block btn-danger" href="${pageContext.request.contextPath}/bookings/delete?id=' + e._id + '">Supprimer</a>';
				}
		    	$(element.currentTarget).popover(popoverOptions);
	            $(element.currentTarget).popover('toggle');
		    }
		});
	});
</script>

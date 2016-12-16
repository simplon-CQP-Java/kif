<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<nav id="menu" class="nav">
	<div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar-menu"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/">KIF</a>
		</div>
		<div class="collapse navbar-collapse" id="navbar-menu">
		<ul class="nav navbar-nav">
			<li><a href="${pageContext.request.contextPath}/">Calendrier</a></li>
			<security:authorize access="isAuthenticated()">
				<li><a href="${pageContext.request.contextPath}/bookings">Réservations</a></li>
			</security:authorize>
			<security:authorize access="hasAuthority('ADMIN')">
				<li><a href="${pageContext.request.contextPath}/computers">Ordinateurs</a></li>
				<li><a href="${pageContext.request.contextPath}/rooms">Salles</a></li>
				<li><a href="${pageContext.request.contextPath}/users">Utilisateurs</a></li>
				<li><a href="${pageContext.request.contextPath}/messages">Messages</a></li>
			</security:authorize>
		</ul>
		<ul class="nav navbar-nav navbar-right">
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
	          	<security:authorize access="!isAuthenticated()">
	          	  Login
	          	</security:authorize>
				<security:authorize access="isAuthenticated()">
					<security:authentication property="name" />
				</security:authorize>
	          	<span class="caret"></span>
	          </a>
	          <ul class="dropdown-menu">
	          	<li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
				<security:authorize access="!isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/login">Connexion</a></li>
					<li><a href="${pageContext.request.contextPath}/register">Inscription</a></li>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<% String adminName = System.getenv().get("ADMIN_NAME"); %>
					<c:set var="username"><security:authentication property="name" /></c:set>
					<c:set var="adminName"><%= adminName %></c:set>
					<c:if test="${adminName != username}">
						<li><a href="${pageContext.request.contextPath}/profil">Profil</a></li>
					</c:if>
					<li><a href="${pageContext.request.contextPath}/logout">Déconnexion</a></li>
				</security:authorize>
	          </ul>
	        </li>
	      </ul>
	  </div>
	</div>
</nav>

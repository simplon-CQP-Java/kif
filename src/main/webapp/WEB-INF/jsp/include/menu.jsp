<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<nav id="menu" class="nav navbar-inverse">
	<div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-menu" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    	<a class="navbar-brand" href="/">KIF</a>
    </div>
    <div class="collapse navbar-collapse" id="navbar-menu">
		<ul class="nav navbar-nav">
	    	<li><a href="/computers">Ordinateurs</a></li>
		  	<li><a href="/rooms">Salles</a></li>
		  	<li><a href="/bookings">Réservations</a></li>
		  	<li><a href="/users">Utilisateurs</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
	          	<security:authorize access="!isAuthenticated()">
	          	  Login
	          	</security:authorize>
				<security:authorize access="isAuthenticated()">

				</security:authorize>
	          	<span class="caret"></span>
	          </a>
	          <ul class="dropdown-menu">
				<security:authorize access="!isAuthenticated()">
					<li><a href="/login">Connexion</a></li>
					<li><a href="/register">Inscription</a></li>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li><a href="/logout">Déconnexion</a></li>
				</security:authorize>
	          </ul>
	        </li>
	      </ul>
	  </div>
	</div>
</nav>

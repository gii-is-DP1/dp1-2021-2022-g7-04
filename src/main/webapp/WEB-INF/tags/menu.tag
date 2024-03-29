<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: players, tutorial, new game"%>
	
<sec:authentication var="principal" property="principal" />

<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#main-navbar">
				<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				<sec:authorize access="isAuthenticated()">
					<c:if test = "${fn:contains(principal.authorities, 'player')}">
						<minesweeper:menuItem active="${name eq 'new game'}"
							url="/selectGame" title="new-game">
							<span class="glyphicon glyphicon-play-circle" aria-hidden="true"></span>
							<span>New Game</span>
						</minesweeper:menuItem>
						<minesweeper:menuItem active="${name eq 'game stats'}"
							url="/gameStats" title="game-stats">
							<span class="glyphicon glyphicon-stats" aria-hidden="true"></span>
							<span>Game stats</span>
						</minesweeper:menuItem>
					</c:if>
				</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<c:if test = "${fn:contains(principal.authorities, 'admin')}">
						<minesweeper:menuItem active="${name eq 'players'}"
							url="/players/find" title="find players">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							<span>Players</span>
						</minesweeper:menuItem>
						<minesweeper:menuItem active="${name eq 'game stats'}"
							url="/gameStats" title="game-stats">
							<span class="glyphicon glyphicon-stats" aria-hidden="true"></span>
							<span>Game stats</span>
						</minesweeper:menuItem>
						<minesweeper:menuItem active="${name eq 'config achievements'}"
							url="/configAchievements" title="config-achievements">
							<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
							<span>Config achievements</span>
						</minesweeper:menuItem>
						<minesweeper:menuItem active="${name eq 'audits'}"
							url="/audits" title="audits">
							<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
							<span>Audits</span>
						</minesweeper:menuItem>
					</c:if>
				</sec:authorize>

				<minesweeper:menuItem active="${name eq 'tutorial'}"
					url="/tutorial" title="tutorial">
					<span class="glyphicon glyphicon-education" aria-hidden="true"></span>
					<span>Tutorial</span>
				</minesweeper:menuItem>
				
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
					<li><a href="<c:url value="/players/new" />">Register</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span> 
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
									<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user"></span>
											</p>
										</div>
										<div class="col-lg-8">
										<c:if test = "${fn:contains(principal.authorities, 'player')}">
											<p class="text-center">
											
												<a href="<c:url value="/players/list/${principal.username}" />"
													class="btn btn-primary btn-block btn-sm">My profile</a>
											
											</p>
											</c:if>
											<c:if test = "${fn:contains(principal.authorities, 'admin')}">
											<p class="text-center">
											
												<a href="<c:url value="/admin" />"
													class="btn btn-primary btn-block btn-sm">My profile</a>
											
											</p>
											</c:if>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-log-out"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							
							<!-- 							
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="#" class="btn btn-primary btn-block">My Profile</a>
												<a href="#" class="btn btn-danger btn-block">Change
													Password</a>
											</p>
										</div>
									</div>
								</div>
							</li>
-->
						</ul></li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>
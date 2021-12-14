<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="buscaminas" tagdir="/WEB-INF/tags"%>

<buscaminas:layout pageName="players">
	<h2>
		<c:if test="${player['new']}">New </c:if>
		player
	</h2>
	<form:form modelAttribute="player" class="form-horizontal"
		id="add-player-form">
		<div class="form-group has-feedback">
			<buscaminas:inputField label="First Name" name="firstName" />
			<buscaminas:inputField label="Last Name" name="lastName" />
			<c:choose>
				<c:when test="${player['new']}">
					<buscaminas:inputField label="Username"  name="user.username" />
				</c:when>
				<c:otherwise>
					<form:hidden path="user.username" />
				</c:otherwise>
			</c:choose>

			<buscaminas:inputField label="Password" name="user.password" />
			<buscaminas:inputField label="City" name="city" />
			<buscaminas:inputField label="Address" name="address" />
			<buscaminas:inputField label="Telephone" name="telephone" />
			<buscaminas:inputField label="Email" name="email" />
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${player['new']}">
						<button class="btn btn-default" type="submit">Add player</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" type="submit">Update
							player</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
</buscaminas:layout>
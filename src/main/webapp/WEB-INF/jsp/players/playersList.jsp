<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="buscaminas" tagdir="/WEB-INF/tags"%>

<buscaminas:layout pageName="users">
	<h2>Jugadores</h2>
	<spring:url value="/users/new" var="newUrl"></spring:url>
	<a href="${fn:escapeXml(newUrl)}" class="btn btn-default">Add
		Player</a>
	<table id="jugadoresTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Username</th>
				<th style="width: 150px;">Password</th>
				<th style="width: 150px;"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selections}" var="user">
				<tr>
					<td><spring:url value="/users/{username}" var="userUrl">
							<spring:param name="username" value="${user.username}" />
						</spring:url> <a href="${fn:escapeXml(userUrl)}"><c:out
								value="${user.username}" /></a></td>
					<td><c:out value="${user.password}" /></td>

					<td><spring:url value="/{username}/delete" var="deleteUrl">
							<spring:param name="username" value="${user.username}" />
						</spring:url> <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete
							User</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</buscaminas:layout>

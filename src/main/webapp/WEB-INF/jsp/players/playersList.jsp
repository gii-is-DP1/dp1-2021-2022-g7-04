<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>

<minesweeper:layout pageName="players">
	<h2>Players</h2>
	<spring:url value="/players/new" var="newUrl"></spring:url>
	<a href="${fn:escapeXml(newUrl)}" class="btn btn-default">Add
		Player</a>
	<table id="playersTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Username</th>
				<th style="width: 150px;">First name</th>
				<th style="width: 150px;">Last name</th>
				<th style="width: 150px;">City</th>
				<th style="width: 150px;">Address</th>
				<th style="width: 150px;">Telephone</th>
				<th style="width: 150px;">Email</th>
				<th style="width: 150px;"> </th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selections}" var="player">
				<tr>
				<c:if test = "${player.user.enabled}">
					<td><spring:url value="/players/{playerId}" var="playerUrl">
							<spring:param name="playerId" value="${player.id}" />
						</spring:url> <a href="${fn:escapeXml(playerUrl)}"><c:out
								value="${player.user.username}" /></a></td>
					<td><c:out value="${player.firstName}" /></td>
					<td><c:out value="${player.lastName}" /></td>
					<td><c:out value="${player.city}" /></td>
					<td><c:out value="${player.address}" /></td>
					<td><c:out value="${player.telephone}" /></td>
					<td><c:out value="${player.email}" /></td>

					<td><spring:url value="/{username}/delete" var="deleteUrl">
							<spring:param name="username" value="${player.user.username}" />
						</spring:url> <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete Player</a></td>
				</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</minesweeper:layout>

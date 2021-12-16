<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authentication var="principal" property="principal" />

<minesweeper:layout pageName="players">

	<c:if test="${player.user.username eq principal.username}">
		<h2>Player information</h2>


		<table class="table table-striped">

			<tr>
				<th>City</th>
				<td><c:out value="${player.city}" /></td>
			</tr>


		</table>
		<spring:url value="{playerId}/edit" var="editUrl">
			<spring:param name="playerId" value="${player.id}" />
		</spring:url>
		<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit
			Player</a>
	</c:if>
</minesweeper:layout>
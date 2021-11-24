<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="buscaminas" tagdir="/WEB-INF/tags"%>

<buscaminas:layout pageName="users">

	<h2>Información Jugador</h2>


	<table class="table table-striped">
		<tr>
			<th>Nombre</th>
			<td><b><c:out value="${jugador.nombre}" /></b></td>
		</tr>
		<tr>
			<th>Last Name</th>
			<td><c:out value="${jugador.lastName}" /></td>
		</tr>
		<tr>
			<th>Password</th>
			<td><c:out value="${jugador.password}" /></td>
		</tr>

	</table>

	<spring:url value="{jugadorId}/edit" var="editUrl">
		<spring:param name="jugadorId" value="${jugador.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Jugador</a>

</buscaminas:layout>
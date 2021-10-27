<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="usuarios">

	<h2>Usuario Information</h2>


	<table class="table table-striped">
		<tr>
			<th>Nombre</th>
			<td><b><c:out value="${usuario.nombre}" /></b></td>
		</tr>
		<tr>
			<th>Email</th>
			<td><c:out value="${usuario.email}" /></td>
		</tr>
		<tr>
			<th>Contraseña</th>
			<td><c:out value="${usuario.contraseña}" /></td>
		</tr>

	</table>

	<spring:url value="{usuarioId}/edit" var="editUrl">
		<spring:param name="usuarioId" value="${usuario.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Usuario</a>



</petclinic:layout>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="buscaminas" tagdir="/WEB-INF/tags"%>

<buscaminas:layout pageName="users">

	<h2>Informaci�n User</h2>


	<table class="table table-striped">
		
		<tr>
			<th>Password</th>
			<td><c:out value="${user.password}" /></td>
		</tr>
		

	</table>

	<spring:url value="{username}/edit" var="editUrl">
		<spring:param name="username" value="${user.username}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit User</a>

</buscaminas:layout>
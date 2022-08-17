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

<minesweeper:layout pageName="admin">

	<c:if
		test="${admin.user.username eq principal.username or fn:contains(principal.authorities, 'admin')}">

		<h2>Admin information</h2>
		<table class="table table-striped">

			<tr>
				<th>First name</th>
				<td><c:out value="${admin.firstName}" /></td>
			</tr>
			<tr>
				<th>Last name</th>
				<td><c:out value="${admin.lastName}" /></td>
			</tr>
			<tr>
				<th>Address</th>
				<td><c:out value="${admin.address}" /></td>
			</tr>
			<tr>
				<th>City</th>
				<td><c:out value="${admin.city}" /></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><c:out value="${admin.email}" /></td>
			</tr>
			<tr>
				<th>Telephone</th>
				<td><c:out value="${admin.telephone}" /></td>
			</tr>
			<tr>
				<th>Username</th>
				<td><c:out value="${admin.user.username}" /></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><c:out value="${admin.user.password}" /></td>
			</tr>


		</table>
		<spring:url value="admin/{adminId}/edit" var="editUrl">
			<spring:param name="adminId" value="${admin.id}" />
		</spring:url>
	 <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Admin</a>
	</c:if>
</minesweeper:layout>
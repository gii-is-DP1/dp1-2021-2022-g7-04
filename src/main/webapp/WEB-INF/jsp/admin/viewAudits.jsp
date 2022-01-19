<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>

<minesweeper:layout pageName="audits">
	<h2>Audits</h2>

	<table id="auditsTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Start Date</th>
				<th style="width: 150px;">End Date</th>
				<th style="width: 150px;">Game level</th>
				<th style="width: 150px;">Player</th>
				<th style="width: 150px;">Game Status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${audits}" var="audit">
				<tr>
					<td><c:out value="${audit.startDate}" /></td>
					
					<c:choose>
					<c:when test="${not empty audit.endDate}">
						<td><c:out value="${audit.endDate}" /></td>
					</c:when>
					<c:otherwise>
						<td><c:out value="NOT FINISHED" /></td>
					</c:otherwise>
					</c:choose>
					
					<td><c:out value="${audit.difficulty}" /></td>
					<td><c:out value="${audit.player}" /></td>
					<c:choose>
						<c:when test="${audit.gameStatus eq 'CANCELLED'}">
							<td style="font-weight:bold;background-color:black;color:gray"><c:out value="${audit.gameStatus}" /></td>
						</c:when>
						<c:when test="${audit.gameStatus eq 'WON'}">
							<td style="font-weight:bold;background-color:black;color:green"><c:out value="${audit.gameStatus}" /></td>
						</c:when>
						<c:when test="${audit.gameStatus eq 'LOST'}">
							<td style="font-weight:bold;background-color:black;color:red"><c:out value="${audit.gameStatus}" /></td>
						</c:when>
						<c:when test="${audit.gameStatus eq 'STARTED'}">
							<td style="font-weight:bold;background-color:black;color:yellow"><c:out value="${audit.gameStatus}" /></td>
						</c:when>
					</c:choose>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
</minesweeper:layout>
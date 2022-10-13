<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>

<minesweeper:layout pageName="error">
	<spring:url value="/resources/images/mine.png" var="mine" />
	<img width="150px" src="${mine}" />

	<h2>Internal server error</h2>
	<p>There was an error, the server is unable to handle this request</p>

</minesweeper:layout>
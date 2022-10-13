<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>

<minesweeper:layout pageName="error">
	<spring:url value="/resources/images/mine.png" var="mine" />
	<img width="150px" src="${mine}" />

	<h2>Error</h2>
	<p>We're sorry, an error has ocurred, please return to the main page</p>

</minesweeper:layout>
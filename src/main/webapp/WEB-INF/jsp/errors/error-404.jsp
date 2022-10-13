<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>

<minesweeper:layout pageName="error">
	<spring:url value="/resources/images/mine.png" var="mine" />
	<img width="150px" src="${mine}" />

	<h2>There is no page here!</h2>
	<p>We're sorry, but the page you requested is not available</p>

</minesweeper:layout>
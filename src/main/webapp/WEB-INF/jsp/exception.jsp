<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>

<minesweeper:layout pageName="error">

	<spring:url value="/resources/images/mine.png" var="mine" />
	<img width="150px" src="${mine}" />

	<h2>Something happened...</h2>

	<p>${exception.message}</p>

</minesweeper:layout>
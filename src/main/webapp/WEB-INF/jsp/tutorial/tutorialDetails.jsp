<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>


<minesweeper:layout pageName="tutorial">

	<h1>Tutorial Minesweeper</h1>
	</br>
	<h2>Goal:</h2>
	<p>${goal}</p>
	</br>
	</br>
	<h2>Players:</h2>
	<p>${players}</p>
	</br>
	</br>
	<h2>Levels:</h2>
	<ul>
		<li>${formattedLevels[0]}</li>
		<li>${formattedLevels[1]}</li>
		<li>${formattedLevels[2]}</li>
		<li>${formattedLevels[3]}</li>
		<ul>
			<li>${formattedLevels[4]}</li>
			<li>${formattedLevels[5]}</li>
			<li>${formattedLevels[6]}</li>
		</ul>
	</ul>
	</br>
	</br>
	<h2>Basic concepts:</h2>
	<p>${formattedConcepts[0]}</p> 
		<ul>
			<li>${formattedConcepts[1]}</li>
			<li>${formattedConcepts[2]}</li>
			<li>${formattedConcepts[3]}</li>
			<li>${formattedConcepts[4]}</li>
		</ul>
	</br>
	</br>
	<h2>How to play:</h2>
	<p>${howToPlay}</p>
	
</minesweeper:layout>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>

<minesweeper:layout pageName="config achievements">

	<h2>
		Config game achievements
	</h2>
	
	<span>Set the minimum number of games won required for each achievement</span><br/><br/>
	
	<form method="get" action="/updateAchievements" class="form-horizontal" id="update-achievements-form">
			
		<div class="form-horizontal has-feedback">
			<label for="bronze">BRONZE</label><br>
			<input type="number" value="${bronzeMinimumGames}" type="number" min="1" size="5" name="bronzeGames" /> <br/><br/>
			
			<label for="silver">SILVER</label><br>
			<input type="number" value="${silverMinimumGames}" type="number" min="1" size="5" name="silverGames" /> <br/><br/>
			
			<label for="gold">GOLD</label><br>
			<input type="number" value="${goldMinimumGames}" type="number" min="1" size="5" name="goldGames" /> <br/><br/>

			<button class="btn btn-default" type="submit">Save changes</button>
		</div>
		
	</form>
</minesweeper:layout>
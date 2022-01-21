<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authentication var="principal" property="principal" />

<minesweeper:layout pageName="selectDifficulty">

	<c:choose>
		<c:when test="${gameStarted eq false}">
		
			<h2>Select a difficulty level</h2>

			<form method="get" action="/newGame" class="form-horizontal" id="select-game-form">
			
				<div class="form-group has-feedback">
					<input type="radio" id="beginner" name="difficulty" value="Beginner" checked /> <label for="beginner">Beginner</label><br>
					<input type="radio" id="medium" name="difficulty" value="Medium" /> <label for="medium">Medium</label><br>
					<input type="radio" id="ace" name="difficulty" value="Ace" /> <label for="ace">Ace</label><br>
					<input type="radio" id="custom" name="difficulty" value="Custom" /> <label for="custom">Custom</label><br> <br>
					
					<div style="display: none" id="CustomRequest">
						<form:form modelAttribute="boardRequest" class="form-horizontal">
						
							<div class="form-group has-feedback">
								<form:hidden path="id" />
								<form:hidden path="playerName" value="${principal.username}" />
								<label>Rows: </label>
								<form:input path="rows" value="8" type="number" min="8" max="16" size="5" name="rows" />
								&nbsp;&nbsp;&nbsp; 
								<label>Columns: </label>
								<form:input path="columns" value="8" type="number" min="8" max="30" size="5" name="columns" />
								&nbsp;&nbsp;&nbsp; 
								<label>Mines: </label>
								<form:input path="mines" value="10" type="number" min="10" max="99" size="5" name="mines" />
							</div>
						</form:form>
					</div>
					<button class="btn btn-default" type="submit">Submit</button>
				</div>
			</form>
		</c:when>
		
		<c:otherwise>
			<h2>Continue your current game?</h2>
			<form method="get" action="/continueGame" class="form-horizontal"
				id="continue-game-form">
				<button class="btn btn-default" type="submit">Yes</button>
			</form>
			<form method="get" action="/finishGame" class="form-horizontal"
				id="finish-game-form">
				<button class="btn btn-default" type="submit">No</button>
			</form>
		</c:otherwise>
	</c:choose>

</minesweeper:layout>

<script>
	var div = document.getElementById("CustomRequest");
	
	const beginner = document.querySelector("input[value='Beginner']");
	const medium = document.querySelector("input[value='Medium']");
	const ace = document.querySelector("input[value='Ace']");
	const custom = document.querySelector("input[value='Custom']");
	
	// handle radio clicks
	beginner.onclick = function () {
		div.style.display = "none";
	};
	medium.onclick = function () {
		div.style.display = "none";
	};
	ace.onclick = function () {
		div.style.display = "none";
	};
	custom.onclick = function () {
		div.style.display = "block";
	};
</script>
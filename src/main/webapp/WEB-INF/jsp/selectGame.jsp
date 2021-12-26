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
	<h2>Select a difficulty level</h2>

	<form method="get" action="/newGame" class="form-horizontal"
		id="select-game-form">
		<div class="form-group has-feedback">
			<input type="radio" name="difficulty" value="Beginner" checked /> Beginner<br>
			<input type="radio" name="difficulty" value="Medium" /> Medium<br>
			<input type="radio" name="difficulty" value="Ace" /> Ace<br> <input
				type="radio" name="difficulty" value="Custom" /> Custom<br> <br>
			<div style="display:none" id="CustomRequest">
			<form:form modelAttribute="boardRequest" class="form-horizontal">
				<div class="form-group has-feedback">
					<form:hidden path="id" />
					<form:hidden path="playerName" value="${principal.username}" />
					<label>Rows: </label>
					<form:input path="rows" value="1" type="number" min="1" max="16" size="5" name="rows" />
					&nbsp;&nbsp;&nbsp; <label>Columns: </label>
					<form:input path="columns" value="1" type="number" min="1" max="30" size="5"
						name="columns" />
					&nbsp;&nbsp;&nbsp; <label>Mines: </label>
					<form:input path="mines" value="1" type="number" min="1" max="99" size="5"
						name="mines" />
				</div>
			</form:form>
			</div>
			<button class="btn btn-default" type="submit">Submit</button>
		</div>
	</form>

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

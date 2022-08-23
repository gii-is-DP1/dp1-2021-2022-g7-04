<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->

<minesweeper:layout pageName="new game">
	<style>
#count_up_timer {
	font-weight: bold;
	color: #ab2605;
}

#stop_count_up_timer {
	background-color: black;
	color: white
}
</style>

	<script type="text/javascript">
		document.addEventListener("DOMContentLoaded", function(event) {
			var message = "${loserMessage}";
			var message2 ="${winnerMessage}";
			// When Lose game
			if (message != "") {
				var current_seconds = parseInt(document
						.getElementById("cont_timer").innerHTML);
				var hour = Math.floor(current_seconds / 3600);
				var minute = Math.floor((current_seconds - hour * 3600) / 60);
				var seconds = current_seconds - (hour * 3600 + minute * 60);
				if (hour < 10)
					hour = "0" + hour;
				if (minute < 10)
					minute = "0" + minute;
				if (seconds < 10)
					seconds = "0" + seconds;
				document.getElementById("timer_finish").innerHTML = hour + ":"
						+ minute + ":" + seconds;
				document.getElementById("flagsRemaining").style.display = "none";
				document.getElementById("nextMove").style.display = "none";
			
			// When won game
			} else if(message2 != "") {
				var current_seconds = parseInt(document
						.getElementById("cont_timer").innerHTML);
				var hour = Math.floor(current_seconds / 3600);
				var minute = Math.floor((current_seconds - hour * 3600) / 60);
				var seconds = current_seconds - (hour * 3600 + minute * 60);
				if (hour < 10)
					hour = "0" + hour;
				if (minute < 10)
					minute = "0" + minute;
				if (seconds < 10)
					seconds = "0" + seconds;
				document.getElementById("timer_finish_bis").innerHTML = hour + ":"
						+ minute + ":" + seconds;
				document.getElementById("flagsRemaining").style.display = "none";
				document.getElementById("nextMove").style.display = "none";
				document.getElementById("restartGame").style.display = "none";
				
			} else {
				countUpTimer();
				var timerVariable = setInterval(countUpTimer, 1000);
			}
		});

		function countUpTimer() {
			document.getElementById("cont_timer").innerHTML = parseInt(document
					.getElementById("cont_timer").innerHTML) + 1;
			var current_seconds = parseInt(document
					.getElementById("cont_timer").innerHTML);
			var hour = Math.floor(current_seconds / 3600);
			var minute = Math.floor((current_seconds - hour * 3600) / 60);
			var seconds = current_seconds - (hour * 3600 + minute * 60);
			if (hour < 10)
				hour = "0" + hour;
			if (minute < 10)
				minute = "0" + minute;
			if (seconds < 10)
				seconds = "0" + seconds;
			document.getElementById("count_up_timer").innerHTML = "Time: "
					+ hour + ":" + minute + ":" + seconds;
			document.getElementById("secondsTimer").value = current_seconds;
		}
	</script>

	<c:if test="${loserMessage != null}">
		<div class="row text-center">
			<h1 style="color: red">
				<c:out value="${loserMessage}"></c:out>
			</h1>
			<span style="font-size: 18px"><b>Time: <span
					id="timer_finish"></span></b></span><br />
			<span style="font-size: 18px"><b>Flags
					remaining: ${flagsInMines}</b></span>
		</div>
	</c:if>
	<c:if test="${winnerMessage != null}">
		<div class="row text-center">
			<h1 style="color: green">
				<c:out value="${winnerMessage}"></c:out>
			</h1>
			<span style="font-size: 18px"><b>Time: <span
					id="timer_finish_bis"></span></b></span><br />
			<span style="font-size: 18px"><b>Flags
					remaining: ${flagsInMines}</b></span>
		</div>
	</c:if>
	<div>
		<div style="height: 30px; font-size: 30px; text-align: center"
			id="count_up_timer"></div>
		<div style="display: none" id="cont_timer">${timer}</div>
		<div id="flagsRemaining" style="display: inline">
			<b>Flags remaining: ${flagsInMines}</b>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<minesweeper:board minesweeperBoard="${minesweeperBoard}" />
			<c:set value="${minesweeperBoard.id}" var="boardId" />
			<c:forEach items="${minesweeperBoard.cells}" var="cell">
				<minesweeper:cell size="24" cell="${cell}" />
			</c:forEach>
		</div>
	</div>

	<div id="nextMove">
	<legend>Next move</legend>
	<form action="/cells/update" method="get" class="form-horizontal"
		id="select-cell-form">
		<input id="flagsInMines" name="flagsInMines" type="hidden"
			value="${flagsInMines}"> 
		<input id="secondsTimer" name="timer"
			type="hidden">
		<div class="form-group has-feedback">
			<label class="col-sm-2 ">Rows (1-${boardRequest.rows})</label> <input
				min="1" size="5" max="${boardRequest.rows}" value="1" type="number"
				name="xPosition" id="xPosition" /> <span>Rows are numbered
				from left to right.</span>
		</div>

		<div class="form-group has-feedback">
			<label class="col-sm-2 ">Columns (1-${boardRequest.columns})
			</label> <input min="1" size="5" max="${boardRequest.columns}" value="1"
				type="number" name="yPosition" id="yPosition" /> <span>Columns
				are numbered from top to bottom.</span>
		</div>

		<div class="form-group">
			<div class="text-left col-sm-10">
				<fieldset>
					<input type="radio" id="uncover" name="move" value="uncover"
						checked="checked" /><label for="uncover">Uncover cell</label><br>
					<input type="radio" id="flag" name="move" value="flag" /><label
						for="flag">Toggle flag</label>
				</fieldset>
				<br>
				<button class="btn btn-default" type="submit">Submit</button>
			</div>
		</div>
	</form>
	<br />
	<br />
	</div>

	<legend>Options</legend>
	<form id="restartGame" action="/restartGame" method="get" class="form-horizontal"
		id="finish-game-form">
		<input id="flagsInMines" name="flagsInMines" type="hidden"
			value="${flagsInMines}"> 
		<input id="secondsTimer" name="timer"
			type="hidden">
		<div class="form-group">
			<div class="text-left col-sm-10">
				<button class="btn btn-default" type="submit">Restart game</button>
			</div>
		</div>
	</form>

	<form action="/finishGame" method="get" class="form-horizontal"
		id="finish-game-form">
		<input id="flagsInMines" name="flagsInMines" type="hidden"
			value="${flagsInMines}"> 
		<input id="secondsTimer" name="timer"
			type="hidden">
		<div class="form-group">
			<div class="text-left col-sm-10">
				<button class="btn btn-default" type="submit">Finish game</button>
			</div>
		</div>
	</form>
</minesweeper:layout>
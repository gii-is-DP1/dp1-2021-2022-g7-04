<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->

<minesweeper:layout pageName="newGame">

	<div class="row">
		<div class="col-md-12">
			<minesweeper:board minesweeperBoard="${minesweeperBoard}" />
			<c:set value="${minesweeperBoard.id}" var="boardId" />
			<c:forEach items="${minesweeperBoard.cells}" var="cell">
				<minesweeper:cell size="24" cell="${cell}" />
			</c:forEach>
		</div>
	</div>

	<legend>Next move</legend>
	<form action="/cells/update" method="get" class="form-horizontal" id="select-cell-form">
		
		<div class="form-group has-feedback">
			<label class="col-sm-2 control-label">Rows
				(1-${boardRequest.rows})</label> <input min="1" size="5"
				max="${boardRequest.rows}" value="1" type="number" name="xPosition"
				id="xPosition" />
		</div>

		<div class="form-group has-feedback">
			<label class="col-sm-2 control-label">Columns
				(1-${boardRequest.columns}) </label> <input min="1" size="5"
				max="${boardRequest.columns}" value="1" type="number"
				name="yPosition" id="yPosition" />
		</div>
		
		<div class="form-group">
			<div class="text-left col-sm-10">
				<fieldset>
					<input type="radio" id="uncover" name="move" value="uncover" checked="checked" /><label for="uncover">Uncover cell</label><br> 
					<input type="radio" id="flag" name="move" value="flag" /><label for="flag">Toggle flag</label>
				</fieldset>
				<br>
				<button class="btn btn-default" type="submit">Submit</button>
			</div>
		</div>
	</form>

	<form action="/restartGame" method="get" class="form-horizontal"
		id="finish-game-form">
		<div class="form-group">
			<div class="text-left col-sm-10">
				<button class="btn btn-default" type="submit">Restart game</button>
			</div>
		</div>
	</form>

	<form action="/finishGame" method="get" class="form-horizontal"
		id="finish-game-form">
		<div class="form-group">
			<div class="text-left col-sm-10">
				<button class="btn btn-default" type="submit">Finish game</button>
			</div>
		</div>
	</form>
</minesweeper:layout>
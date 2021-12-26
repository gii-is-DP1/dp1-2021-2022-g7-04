<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>

<minesweeper:layout pageName="selectDifficulty">
    <h2>
        Select a difficulty level
    </h2>

    <form method="get" action="/newGame" class="form-horizontal" id="select-game-form">
        <div class="form-group has-feedback">
        	<input type="radio" name="difficulty" value="Beginner"/> Beginner<br>
        	<input type="radio" name="difficulty" value="Medium" /> Medium<br>
        	<input type="radio" name="difficulty" value="Ace"/> Ace<br>
        	<input type="radio" name="difficulty" value="Custom" /> Custom<br>
        	<br>
        	<button class="btn btn-default" type="submit">Submit</button>
        </div>
    </form>
            
</minesweeper:layout>

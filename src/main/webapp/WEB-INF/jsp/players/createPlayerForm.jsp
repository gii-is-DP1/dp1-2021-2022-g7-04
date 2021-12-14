<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>

<minesweeper:layout pageName="player">
    <h2>
        Player
    </h2>
    <form:form modelAttribute="player" class="form-horizontal" id="add-player-form">
        <div class="form-group has-feedback">
        	<minesweeper:inputField label="First Name" name="firstName" />
			<minesweeper:inputField label="Last Name" name="lastName" />
			<minesweeper:inputField label="City" name="city" />
			<minesweeper:inputField label="Address" name="address" />
			<minesweeper:inputField label="Telephone" name="telephone" />
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<button class="btn btn-default" type="submit">Add Player</button>
            </div>
        </div>
    </form:form>
            
</minesweeper:layout>

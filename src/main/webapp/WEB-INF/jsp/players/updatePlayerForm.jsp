<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="buscaminas" tagdir="/WEB-INF/tags" %>

<buscaminas:layout pageName="player">
    <h2>
        Player
    </h2>
    <form:form modelAttribute="player" class="form-horizontal" id="add-player-form">
        <div class="form-group has-feedback">
        	<buscaminas:inputField label="First Name" name="firstName" />
			<buscaminas:inputField label="Last Name" name="lastName" />
			<buscaminas:inputField label="Username" name="username" />
			<buscaminas:inputField label="City" name="city" />
			<buscaminas:inputField label="Address" name="address" />
			<buscaminas:inputField label="Telephone" name="telephone" />
			<buscaminas:inputField label="Email" name="email" />
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<button class="btn btn-default" type="submit">Edit Player</button>
            </div>
        </div>
    </form:form>
            
</buscaminas:layout>
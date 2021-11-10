<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="buscaminas" tagdir="/WEB-INF/tags"%>

<buscaminas:layout pageName="usuarios">
	<h2>
		<c:if test="${usuario['new']}">New </c:if>
		Usuario
	</h2>
	<form:form modelAttribute="usuario" class="form-horizontal"
		id="add-usuario-form">
		<div class="form-group has-feedback">
			<buscaminas:inputField label="Nombre" name="Nombre" />
			<buscaminas:inputField label="Last Name" name="lastName" />
			<buscaminas:inputField label="Password" name="password" />
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${usuario['new']}">
						<button class="btn btn-default" type="submit">Add Usuario</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" type="submit">Update
							Usuario</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
</buscaminas:layout>

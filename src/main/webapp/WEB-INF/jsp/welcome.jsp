<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="buscaminas" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<buscaminas:layout pageName="home">
	<div class="row text-center">
    	<h2>${title} (${group})</h2>
    </div>
    <div class="row">
    	<div class="col-md-6 text-left">
            <spring:url value="/resources/images/mine.png" htmlEscape="true" var="mine"/>
            <img width="150px" src="${mine}"/>
        </div>    
        <div class="col-md-6 text-right">
            <spring:url value="/resources/images/logoPNG_3.png" htmlEscape="true" var="logoImage"/>
            <img src="${logoImage}"/>
        </div>
    </div>
</buscaminas:layout>
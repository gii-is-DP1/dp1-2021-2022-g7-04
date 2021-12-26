<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="minesweeper" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<minesweeper:layout pageName="home">
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
    
    <h2><fmt:message key="welcome"/></h2>
    
    <p>	
    <h2><c:out value="${now}"/></h2>

    <div class="row">
        <div class="col-md-12">
        <minesweeper:board minesweeperBoard="${minesweeperBoard}"/>
            <c:forEach items="${minesweeperBoard.cells}" var="cell">
            	<minesweeper:cell size="24" cell="${cell}"/>
            </c:forEach> 
        </div>
    </div>


    <form action="/cells/update" method="get" class="form-horizontal" id="select-cell-form">
        <div class="form-group has-feedback">
            <label class="col-sm-2 control-label">Column (1-${boardRequest.columns})</label>
            <input min="1" size="5" max="${boardRequest.columns}" value="1" type="number" name="xPosition" id="xPosition"/>
        </div>

        <div class="form-group has-feedback">
            <label class="col-sm-2 control-label">Row (1-${boardRequest.rows}) </label>
            <input min="1" size="5" max="${boardRequest.rows}" value="1" type="number" name="yPosition" id="yPosition"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                
                        <button class="btn btn-default" type="submit">Select cell</button>
                    
                
            </div>
        </div>
    </form>

    
</minesweeper:layout>
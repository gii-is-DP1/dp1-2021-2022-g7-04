<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="buscaminas" tagdir="/WEB-INF/tags" %>

<buscaminas:layout pageName="jugadores">
    <h2>Jugadores</h2>

    <table id="jugadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">Last Name</th>
            <th style="width: 150px;">Password</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadores}" var="jugador">
            <tr>
                <td>
                    <spring:url value="/jugadores/{jugadorId}" var="jugadorUrl">
                        <spring:param name="jugadorId" value="${jugador.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(jugadorUrl)}"><c:out value="${jugador.nombre}"/></a>
                </td>
                <td>
                    <c:out value="${jugador.lastName}"/>
                </td>
                <td>
                    <c:out value="${jugador.password}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</buscaminas:layout>

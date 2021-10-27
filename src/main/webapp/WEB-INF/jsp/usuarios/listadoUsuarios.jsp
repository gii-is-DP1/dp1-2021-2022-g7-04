<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="usuarios">
    <h2>Usuarios</h2>

    <table id="usuariosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">Email</th>
            <th style="width: 150px;">Contraseña</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usuarios}" var="usuario">
            <tr>
                <td>
                    <spring:url value="/usuarios/{usuarioId}" var="usuarioUrl">
                        <spring:param name="usuarioId" value="${usuario.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(usuarioUrl)}"><c:out value="${usuario.nombre}"/></a>
                </td>
                <td>
                    <c:out value="${usuario.email}"/>
                </td>
                <td>
                    <c:out value="${usuario.contraseña}"/>
                </td>
             
             
               </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>

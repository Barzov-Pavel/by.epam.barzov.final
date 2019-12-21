<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<u:html title="Список купленных туров">
    <h2>Поздравляем вы купили тур</h2>
    <c:url var="boughtTours" value="/user/bought-tours.html">
        <c:param name="userId" value="${currentUser.id}"/>
    </c:url>
    <p><a href="${boughtTours}">Список купленных туров</a></p>
</u:html>

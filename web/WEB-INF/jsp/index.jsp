<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:message var="title" key="index.title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <c:url var="login" value="/login.html"/>
    <c:if test="${currentUser == null}">
        <p><a href="${login}">Вход</a></p>
    </c:if>
    <c:if test="${currentUser.role == \"TOUR_AGENT\"}">
        <c:url var="userList" value="/user/list.html"/>
        <p><a href="${userList}"><fmt:message key="index.user"/></a></p>
    </c:if>
    <c:if test="${currentUser.role == \"CUSTOMER\"}">
        <c:url var="boughtTours" value="/user/bought-tours.html">
            <c:param name="userId" value="${currentUser.id}"/>
        </c:url>
        <p><a href="${boughtTours}">Список купленных туров</a></p>
    </c:if>
    <c:url var="userList" value="/user/tour-list.html"/>
    <p><a href="${userList}"><fmt:message key="index.tour"/></a></p>
</u:html>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${empty tour}">
    <jsp:useBean id="tour" class="domain.Tour"/>
</c:if>
<c:choose>
    <c:when test="${not empty tour.id}">
        <fmt:message var="title" key="tour.edit.edit"/>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="tour.edit.add"/>
    </c:otherwise>
</c:choose>
<u:html title="${title}">
    <h2>${title}</h2>
    <c:url var="urlTourList" value="/user/tour-list.html"/>
    <c:url var="urlTourDelete" value="/user/tour-delete.html"/>
    <c:url var="urlTourSave" value="/user/tour-save.html"/>
    <form action="${urlTourSave}" method="post">
        <c:if test="${not empty tour.id}">
            <input name="id" value="${tour.id}" type="hidden">
        </c:if>
        <label for="title"><fmt:message key="tour.edit.form.name"/></label>
        <input id="title" name="title" value="${tour.title}">
        <label for="description"><fmt:message key="tour.edit.form.description"/></label>
        <input id="description" name="description" value="${tour.description}">

        <label for="type"><fmt:message key="tour.edit.form.type"/></label>
        <select id="type" name="type">
            <c:forEach var="type" items="${tour.typesTour}">
                <c:choose>
                    <c:when test="${type == tour.type}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${type}" ${selected}>${type}</option>
            </c:forEach>
        </select>

        <label for="price"><fmt:message key="tour.edit.form.price"/></label>
        <input id="price" name="price" value="${tour.price}">
        <label for="enabled"><fmt:message key="tour.edit.form.enabled"/></label>
        <input id="enabled" name="enabled" value="${tour.enabled}">
        <label for="rating"><fmt:message key="tour.edit.form.rating"/></label>
        <input id="rating" name="rating" value="${tour.avgRating}">
        <label for="destination"><fmt:message key="tour.edit.form.destination"/></label>
        <input id="destination" name="destination" value="${tour.destination}">

        <button class="save"><fmt:message key="tour.edit.button.save"/></button>
        <c:if test="${not empty tour.id}">
            <c:if test="${not tourCanBeDeleted}">
                <c:set var="disabled" value="disabled"/>
            </c:if>
            <button class="delete" formaction="${urlTourDelete}" formmethod="post" ${disabled}><fmt:message key="tour.edit.button.delete"/></button>
        </c:if>
        <button class="reset" type="reset"><fmt:message key="tour.edit.button.reset"/></button>
        <button class="back" formaction="${urlTourList}" formmethod="get"><fmt:message key="tour.edit.button.cancel"/></button>
    </form>
</u:html>
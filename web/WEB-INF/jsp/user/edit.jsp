<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${empty user}">
    <jsp:useBean id="user" class="domain.User"/>
</c:if>
<c:choose>
    <c:when test="${not empty user.id}">
        <fmt:message var="title" key="user.edit.title.edit"/>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="user.edit.title.add"/>
    </c:otherwise>
</c:choose>
<u:html title="${title}">
    <h2>${title}</h2>
    <c:url var="urlUserList" value="/user/list.html"/>
    <c:url var="urlUserSave" value="/user/save.html"/>
    <c:url var="urlUserDelete" value="/user/delete.html"/>
    <form action="${urlUserSave}" method="post">
        <c:if test="${not empty user.id}">
            <input name="id" value="${user.id}" type="hidden">
        </c:if>
        <label for="login"><fmt:message key="user.edit.form.login"/></label>
        <input id="login" name="login" value="${user.userName}">
        <label for="firstName"><fmt:message key="user.edit.table.firstName"/></label>
        <input id="firstName" name="firstName" value="${user.firstName}">
        <label for="lastName"><fmt:message key="user.edit.table.lastName"/></label>
        <input id="lastName" name="lastName" value="${user.lastName}">
        <label for="password"><fmt:message key="user.edit.table.password"/></label>
        <input id="password" name="password" value="${user.password}">
        <label for="discount"><fmt:message key="user.edit.table.discount"/></label>
        <input id="discount" name="discount" value="${user.discount}">
        <label for="phone"><fmt:message key="user.edit.table.phone"/></label>
        <input id="phone" name="phone" value="${user.telephone}">
        <label for="role"><fmt:message key="user.edit.form.role"/></label>
        <select id="role" name="role">
            <c:forEach var="role" items="${user.roles}">
                <c:choose>
                    <c:when test="${role == user.role}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${role}" ${selected}>${role}</option>
            </c:forEach>
        </select>
        <button class="save"><fmt:message key="user.edit.button.save"/></button>
        <c:if test="${not empty user.id}">
            <c:if test="${not userCanBeDeleted}">
                <c:set var="disabled" value="disabled"/>
            </c:if>
            <button class="delete" formaction="${urlUserDelete}" formmethod="post" ${disabled}><fmt:message key="user.edit.button.delete"/></button>
        </c:if>
        <button class="reset" type="reset"><fmt:message key="user.edit.button.reset"/></button>
        <button class="back" formaction="${urlUserList}" formmethod="get"><fmt:message key="user.edit.button.cancel"/></button>
    </form>
</u:html>
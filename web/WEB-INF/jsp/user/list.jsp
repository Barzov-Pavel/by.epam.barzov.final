<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:message key="user.list.title" var="title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="user.list.table.login"/></th>
            <th><fmt:message key="user.list.table.role"/></th>
            <td>&nbsp;</td>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td class="content">${user.userName}</td>
                <td class="content">${user.role}</td>
                <td class="empty">
                    <c:url var="urlUserEdit" value="/user/edit.html">
                        <c:param name="id" value="${user.id}"/>
                    </c:url>
                    <a href="${urlUserEdit}" class="edit"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:url var="urlUserEdit" value="/user/edit.html"/>
    <a href="${urlUserEdit}" class="add-button"><fmt:message key="user.list.button.add"/></a>
</u:html>
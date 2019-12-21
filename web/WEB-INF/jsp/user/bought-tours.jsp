<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:message key="bought.tours.table.title" var="title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="bought.tours.table.firstName"/></th>
            <th><fmt:message key="bought.tours.table.lastName"/></th>
            <th><fmt:message key="bought.tours.table.title"/></th>
            <th><fmt:message key="bought.tours.table.date"/></th>
            <th><fmt:message key="bought.tours.table.price"/></th>
        </tr>

        <c:forEach var="purchase" items="${purchases}">
            <tr>
                <td class="content">${purchase.user.firstName}</td>
                <td class="content">${purchase.user.lastName}</td>
                <td class="content">${purchase.tour.title}</td>
                <td class="content">${purchase.date}</td>
                <td class="content">${purchase.price}</td>
            </tr>
        </c:forEach>

    </table>
</u:html>

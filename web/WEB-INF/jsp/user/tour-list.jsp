<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<u:html title="Список туров">
    <h2><fmt:message key="tour.list.title"/></h2>
    <table>
        <tr>
            <th><fmt:message key="tour.list.table.name"/></th>
            <th><fmt:message key="tour.list.table.info"/></th>
            <th><fmt:message key="tour.list.table.type"/></th>
            <th><fmt:message key="tour.list.table.price"/></th>
            <th><fmt:message key="tour.list.table.enabled"/></th>
            <th><fmt:message key="tour.list.table.rating"/></th>
            <td>&nbsp;</td>
        </tr>
        <c:forEach var="tour" items="${tours}">
            <tr>
                <td class="content">${tour.title}</td>
                <td class="content">${tour.description}</td>
                <td class="content">${tour.type}</td>
                <td class="content">${tour.price}</td>
                <td class="content">${tour.enabled}</td>
                <td class="content">${tour.avgRating}</td>
                <td class="empty">
                    <c:url var="urlTourEdit" value="/user/tour-edit.html">
                        <c:param name="id" value="${tour.id}"/>
                    </c:url>
                    <a href="${urlTourEdit}" class="edit"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:url var="urlTourEdit" value="/user/tour-edit.html"/>
    <a href="${urlTourEdit}" class="add-button"><fmt:message key="tour.button"/></a>
</u:html>
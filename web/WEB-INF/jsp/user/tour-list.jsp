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
                <c:if test="${currentUser.role == \"TOUR_AGENT\"}">
                    <td class="empty">
                        <c:url var="urlTourEdit" value="/user/tour-edit.html">
                            <c:param name="id" value="${tour.id}"/>
                        </c:url>
                        <a href="${urlTourEdit}" class="edit"></a>
                    </td>
                </c:if>
                <c:if test="${currentUser.role == \"CUSTOMER\"}">
                    <td class="empty">
                        <c:url var="urlTourBuy" value="/user/tour-buy.html">
                            <c:param name="tourId" value="${tour.id}"/>
                            <c:param name="userId" value="${currentUser.id}"/>
                            <c:param name="price" value="${tour.price}"/>
                        </c:url>
                        <a href="${urlTourBuy}" class="buy"></a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>

    </table>
    <c:if test="${currentUser.role == \"TOUR_AGENT\"}">
        <c:url var="urlTourEdit" value="/user/tour-edit.html"/>
        <a href="${urlTourEdit}" class="add-button"><fmt:message key="tour.button"/></a>
    </c:if>
</u:html>
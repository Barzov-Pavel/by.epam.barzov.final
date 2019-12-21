<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<u:html title="Список купленных туров">
    <h2>Список купленных туров</h2>
    <table>
        <tr>
            <th>Имя пользователя</th>
            <th>Фамилия пользователя</th>
            <th>Название тура</th>
            <th>Дата покупки</th>
            <th>Цена</th>
            <td>&nbsp;</td>
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

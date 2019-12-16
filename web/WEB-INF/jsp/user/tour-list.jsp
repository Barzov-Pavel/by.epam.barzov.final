<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список туров</title>
    <c:url var="urlCss" value="../main.css"/>
    <link href="${urlCss}" rel="stylesheet">
</head>
<body>
<h1>Турагенство путь в EPAM</h1>
<h2>Список туров</h2>
<table>
    <tr>
        <th>Наименование тура</th>
        <th>Описание тура</th>
        <th>Тип тура</th>
        <th>Стоимость тура</th>
        <th>Доступность к заказу</th>
        <th>Средний рейтинг</th>
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
<a href="${urlTourEdit}" class="add-button">Добавить</a>
</body>
</html>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty tour}">
    <jsp:useBean id="tour" class="domain.Tour"/>
</c:if>
<c:choose>
    <c:when test="${not empty tour.id}">
        <c:set var="title" value="Редактирование информации о туре"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление нового тура"/>
    </c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <c:url var="urlCss" value="../main.css"/>
    <link href="${urlCss}" rel="stylesheet">
</head>
<body>
<h1>Турагенство путь в EPAM</h1>
<h2>${title}</h2>
<c:url var="urlTourList" value="/user/tour-list.html"/>
<c:url var="urlTourSave" value="/user/tour-save.html"/>
<form action="${urlTourSave}" method="post">
    <c:if test="${not empty tour.id}">
        <input name="id" value="${tour.id}" type="hidden">
    </c:if>
    <label for="title">Наименование тура:</label>
    <input id="title" name="title" value="${tour.title}">
    <label for="description">Описание тура:</label>
    <input id="description" name="description" value="${tour.description}">

    <label for="type">Тип тура:</label>
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

    <label for="price">Стоимость тура:</label>
    <input id="price" name="price" value="${tour.price}">
    <label for="enabled">Доступность к заказу тура:</label>
    <input id="enabled" name="enabled" value="${tour.enabled}">
    <label for="rating">Средний рейтинг тура:</label>
    <input id="rating" name="rating" value="${tour.avgRating}">

    <button class="save">Сохранить</button>
    <button class="reset" type="reset">Сброс</button>
    <button class="back" formaction="${urlTourList}" formmethod="get">Отмена</button>
</form>
</body>
</html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список пользователей</title>
    <c:url var="urlCss" value="../main.css"/>
    <link href="${urlCss}" rel="stylesheet">
</head>
<body>
<h1>Турагентство путь в EPAM</h1>
<h2>Список пользователей</h2>
<table>
    <tr>
        <th>Имя пользователя</th>
        <th>Роль</th>
        <td>&nbsp;</td>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td class="content">${user.userName}</td>
            <td class="content">${user.role}</td>
            <td class="empty">
                <c:url var="urlUserEdit" value="edit.jsp">
                    <c:param name="id" value="${user.id}"/>
                </c:url>
                <a href="${urlUserEdit}" class="edit"></a>
            </td>
        </tr>
    </c:forEach>
</table>
<c:url var="urlUserEdit" value="edit.jsp"/>
<a href="${urlUserEdit}" class="add-button">Добавить</a>
</body>
</html>
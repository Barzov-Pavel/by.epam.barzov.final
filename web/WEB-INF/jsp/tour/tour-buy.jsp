<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:message var="title" key="tour.buy.title"/>
<u:html title="${title}">
    <h2><fmt:message key="tour.buy.congratulation"/></h2>
    <c:url var="boughtTours" value="/tour/bought-tours.html">
        <c:param name="userId" value="${currentUser.id}"/>
    </c:url>
    <p><a href="${boughtTours}"><fmt:message key="tour.buy.list"/></a></p>
</u:html>

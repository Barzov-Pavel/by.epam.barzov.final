<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <c:url var="urlCss" value="/main.css"/>
    <link href="${urlCss}" rel="stylesheet">
</head>
<body>
<h1><fmt:message key="application.title"/></h1>
<form>
    <c:url var="index" value="/index.html"/>
    <button class="back" formaction="${index}" formmethod="get"><fmt:message key="index.title"/></button>
</form>

<c:if test="${not empty currentUser}">
    <c:url var="urlLogout" value="/logout.html"/>
    <p><fmt:message key="application.welcome"/> ${currentUser.userName} (${currentUser.role}).
        <a href="${urlLogout}"><fmt:message key="application.button.logout"/></a></p>
</c:if>
<jsp:doBody/>
</body>
</html>
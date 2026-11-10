<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:if test="${empty requestScope.user and empty errors}">
    <c:redirect url="/email/index.html"/>
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Murach's Java Servlets and JSP - Email List</title>
    <link rel="stylesheet" href="<c:url value='/email/styles/main.css'/>">
</head>
<body>
    <c:choose>
        <c:when test="${not empty errors}">
            <h1>Please check your information</h1>
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li><c:out value="${error}"/></li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <h1>Thanks for joining our email list</h1>
            <p>Here is the information that you entered:</p>
            <label>Email:</label><span><c:out value="${user.email}"/></span><br>
            <label>First Name:</label><span><c:out value="${user.firstName}"/></span><br>
            <label>Last Name:</label><span><c:out value="${user.lastName}"/></span><br>
            <p>To enter another email address, click on the Back button in your browser
                or the Return button shown below.</p>
        </c:otherwise>
    </c:choose>
    <form action="<c:url value='/emailList'/>" method="get">
        <input type="hidden" name="action" value="join">
        <input type="submit" value="Return">
    </form>
    <p><a href="<c:url value='/index.html'/>">Back to Survey</a></p>
</body>
</html>

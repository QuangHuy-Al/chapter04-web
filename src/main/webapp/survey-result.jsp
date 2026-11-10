<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:if test="${empty sessionScope.survey and empty errors}">
    <c:redirect url="/index.html"/>
</c:if>
<c:set var="survey" value="${sessionScope.survey}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Survey Results</title>
    <link rel="stylesheet" href="styles/main.css">
</head>
<body>
    <img src="images/murachlogo.png" alt="Murach" width="120">
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
            <h1>Thanks for taking our survey!</h1>
            <p>Here is the information you entered:</p>
            <table>
                <tr><th scope="row">First Name</th><td><c:out value="${survey.firstName}"/></td></tr>
                <tr><th scope="row">Last Name</th><td><c:out value="${survey.lastName}"/></td></tr>
                <tr><th scope="row">Email</th><td><c:out value="${survey.email}"/></td></tr>
                <tr><th scope="row">Date of Birth</th><td><c:out value="${survey.dateOfBirth}"/></td></tr>
                <tr><th scope="row">How did you hear about us?</th><td><c:out value="${survey.heardFrom}"/></td></tr>
                <tr><th scope="row">New CDs and special offers</th><td>${survey.wantsUpdates ? 'Yes' : 'No'}</td></tr>
                <tr><th scope="row">Email announcements</th><td>${survey.emailAnnouncements ? 'Yes' : 'No'}</td></tr>
                <tr><th scope="row">Please contact me by</th><td><c:out value="${survey.contactVia}"/></td></tr>
            </table>
        </c:otherwise>
    </c:choose>
    <p><a href="index.html">Back to survey</a></p>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
      <link rel="stylesheet" href="Style.css">
</head>
<body>
    <h1>Login</h1>
    <form action="LoginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
         <c:if test="${not empty sessionScope.loginAttempts}">
        <p>Tentativi rimanenti: ${3 - sessionScope.loginAttempts}</p>
    </c:if>
        <input type="submit" value="Login">
    </form>
</body>
</html>

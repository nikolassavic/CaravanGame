<%--
  Created by IntelliJ IDEA.
  User: Frosty
  Date: 11/16/2018
  Time: 10:07 AM
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<form:form action="/login" modelAttribute="user" method="post">
    <p><span>Your email:</span><form:input path="email"/></p>
    <p><span>Your password</span><form:input path="password"/></p>
    <input type="submit" value="Login">
    <a href="/reg"><input type="button" value="Register"/></a>
</form:form>
</body>
</html>

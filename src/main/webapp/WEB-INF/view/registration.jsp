<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%--
  Created by IntelliJ IDEA.
  User: Frosty
  Date: 11/16/2018
  Time: 11:44 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form:form action="/registration" modelAttribute="user" method="post">
    <p><span>Your nick</span><form:input path="displayName"/></p>
    <p><span>Your email</span><form:input path="email"/></p>
    <p><span>Your password</span><form:input pattern=".{3,}" type="password" path="password"/></p>
    <input type="submit" value="Register">
</form:form>
</body>
</html>

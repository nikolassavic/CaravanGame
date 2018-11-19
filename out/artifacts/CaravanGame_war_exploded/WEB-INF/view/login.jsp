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
    <link rel="stylesheet" href="/resources/css/login.css">
    <title>Login</title>
</head>

<header id="header">
    <div class="container">
        <h1>Welcom to this shiti site</h1>
    </div>
</header>

<div class="container">
    <form:form action="/login" modelAttribute="user" method="post" class="form-login">
        <div class="form-block">
            <label>Email</label>
            <form:input type="text" path="email"/>
        </div>
        <div class="form-block">
            <label>Password</label>
            <form:input pattern=".{3,}" type="text" path="password"/>
        </div>
        <div class="form-block">
            <input class="button" type="submit" value="Login">
            <a href="/reg"><input class="button" type="button" value="Register"/></a>
        </div>
    </form:form>
</div>
</body>
</html>

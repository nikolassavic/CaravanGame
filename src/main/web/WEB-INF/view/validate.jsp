<%--
  Created by IntelliJ IDEA.
  User: Frosty
  Date: 11/19/2018
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Validate</title>
</head>
<body>
<form action="/validated" method="POST">
    <lable name="label" id="num"></lable>
    <input type="text" name="user-input" aria-placeholder="Please type number from above">
    <input type="submit" value="Let me in">
</form>
</body>
<footer>
    <script>
        let getRandomNum = function (length) {
            return Math.floor(Math.pow(10, length - 1) + Math.random() * (Math.pow(10, length) - Math.pow(10, length - 1) - 1));
        }
        document.getElementById('num').textContent = getRandomNum(6);
    </script>
</footer>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 08.04.2022
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="../resources/css/input.css" %>
    </style>
</head>
<body>
<div class="data_user">
    <center>
        <h1>Добро Пожаловать</h1>
    </center>
    <img class="photo_user" src="<%=request.getContextPath()%>${photo}">
    <label>Name:${name}</label>
    <label>Sex:${sex}</label>
    <label>Email:${email}</label>
    <label>Data:${data}</label>
    <br>

    <form action="<%=request.getContextPath()%>/home">
        <input type="hidden" name="command" value="logout">
        <input type="submit" name="logout" value="Exit">
    </form>
</div>
</body>
</html>

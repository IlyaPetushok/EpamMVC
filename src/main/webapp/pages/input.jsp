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
    <div class="flex">
        <div class="photo_border">
            <img class="photo_user" src="<%=request.getContextPath()%>${photo}">
        </div>
        <div class="up_right">
            <p>Name</p>
            <p>&nbsp&nbsp&nbsp&nbsp${name}</p>
            <p>Sex</p>
            <p>&nbsp&nbsp&nbsp&nbsp${sex}</p>
            <p>Email</p>
            <p>&nbsp&nbsp&nbsp&nbsp${email}</p>
            <p>Date</p>
            <p>&nbsp&nbsp&nbsp&nbsp${data}</p>
        </div>
        <br>
    </div>
</div>
<div class="footer">
    <form action="<%=request.getContextPath()%>/home">
        <input type="hidden" name="command" value="myfriend">
        <input type="submit" name="friend" value="friend">
    </form>
    <form action="<%=request.getContextPath()%>/home">
        <input type="hidden" name="command" value="logout">
        <input type="submit" name="logout" value="Exit">
    </form>
</div>
</body>
</html>

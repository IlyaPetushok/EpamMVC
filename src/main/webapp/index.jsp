<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Авторизация</title>
    <style>
        <%@include file="resources/css/index.css" %>
    </style>
    <link type="text/css" href="src/main/webapp/pages/css/index.css" rel="stylesheet">
</head>
<body>
<img class=one src="<%=request.getContextPath()%>/resources/logGrsu.png" alt="Эмблема Гргу им.Янки Купалы "
     title="Гргу имени Янки Купалы">
</body>
<form action="welcom" method="post">
    <h1>Авторизация</h1>
    <center>
        <input class=one type=text required=required placeholder="Логин">
        <br>
        <input class=one type=password required=required
               placeholder="Пароль">
        <br>
        <input class=two type="submit" value="Войти">
        <br>
        <a href="pages/reg.jsp">Регистрация</a>
    </center>
</form>
</html>
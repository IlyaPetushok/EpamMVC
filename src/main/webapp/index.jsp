<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Авторизация</title>
    <style>
        <%@include file="resources/css/index.css" %>
    </style>
</head>
<body>
<img class=one src="<%=request.getContextPath()%>/resources/logGrsu.png" alt="Эмблема Гргу им.Янки Купалы "
     title="Гргу имени Янки Купалы">
</body>
<form action="home">
    <h1>Авторизация</h1>
    <center>
        <input type="hidden" name="command" value="input_user">
        <input class=one type=text name="login" required=required placeholder="Логин">
        <br>
        <input class=one type=password name="password" required=required
               placeholder="Пароль">
        <br>
        <label>${error_aut}</label>
        <br>
        <input class=two type="submit" value="Войти">
        <br>
        <a href="pages/reg.jsp">Регистрация</a>
    </center>
</form>
</html>
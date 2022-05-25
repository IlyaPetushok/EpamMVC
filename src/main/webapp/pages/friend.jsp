<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 17.05.2022
  Time: 02:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyFriend</title>
    <style>
        <%@include file="../resources/css/friend.css" %>
    </style>
    <script>
        function divSee() {
            document.getElementById("div1").style.display = "block";
        };
        function divHidden() {
            document.getElementById("div1").style.display = "none";
        };
        function sendMessage() {
            document.getElementById("send").click();
        };
    </script>
</head>
<body>
<div class="back">
    <center>
        <h1>Мои друзья</h1>
        ${table_friend}
    </center>
</div>
<%--<div id="div1" class="div-hidden">--%>
<%--    <center><h3>Сообщение</h3></center>--%>
<%--    <input class="message" type="text" name="mes" value="hi" placeholder="Введите сообщение">--%>
<%--    <center>--%>
<%--        <input class="btn-mes" type="button" value="отмена" onmousedown="divHidden()">--%>
<%--        <input  class="btn-mes" type="button" value="отправить" onmousedown="send()">--%>
<%--    </center>--%>
<%--</div>--%>
<form action="<%=request.getContextPath()%>/home">
    <input type="hidden" name="command" value="search_friend">
    <input type="submit" value="Search">
</form>
</body>
</html>

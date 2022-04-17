<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.04.2022
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%--isErrorPage для того чтобы были ввидны exception--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/resources/css/error_404.css" %>
    </style>
</head>
<body>
    <h1 class="two"><center>ERROR500</center>${error}</h1>
<%--    <p>${error}</p>--%>
</body>
</html>

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
</head>
<body>
    <h1>Hello </h1>
    <label>Name:</label>${name}
    <label>Sex:</label>${sex}
    <label>Email:</label>${email}
    <label>Data:</label>${data}
    <form action="<%=request.getContextPath()%>/home">
        <input type="hidden" name="command" value="logout">
        <input type="submit" name="logout" value="Exit">
    </form>
</body>
</html>

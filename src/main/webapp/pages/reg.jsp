<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 08.04.2022
  Time: 09:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <link type="text/css" href="../resources/css/reg.css" rel="stylesheet">
</head>
<img class="one" src="<%=request.getContextPath()%>/resources/logGrsu.png">
<form action="welcom" method="post">
    <fieldset>
        <legend>Регистрация</legend>


        <div class="image-upload">
            <label for="file-input">
                <img class="two" src="<%=request.getContextPath()%>/resources/logAddPictureUser.png"/>
            </label>
            <input id="file-input" type="file"/>
        </div>


        <input class=one type="text" required=reqired placeholder="Имя">
        <br>
        <input class=three type="text" required=required
               placeholder="Фамилия">
        <br>
        <input class=one type="text" required=required
               placeholder="Отчество">
        <br>
        <input class="two" type="email" required placeholder="email">
        <div class=one>

            <div class="radio_buttons">
                <div>
                    <input type="radio" name="sex" id="man" checked/>
                    <label for="man">Мужской</label>
                </div>
                <div>
                    <input type="radio" name="sex" id="woman"/>
                    <label for="woman">Женский</label>
                </div>
            </div>

        </div>
        <div class=two>
            <select class=one name=day>
                <option value=month>
                    День
                </option>
            </select>

            <select class=two name=month>
                <option>Месяц</option>
            </select>

            <select class=three>
                <option>Год</option>
            </select>
        </div>
        <input class="button" type="submit" value="Регистрация">

    </fieldset>
</form>
</html>

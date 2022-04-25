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
    <style>
        <%@include file="../resources/css/reg.css" %>
    </style>
</head>
<img class="one" src="<%=request.getContextPath()%>/resources/logGrsu.png">
<form action="<%=request.getContextPath()%>/home">
    <fieldset>
        <legend>Регистрация</legend>
        <div class="up">
            <div class="left_up">
                <div class="image-upload">
                    <label for="file-input">
                        <img class="two" src="<%=request.getContextPath()%>/resources/logAddPictureUser.png"/>
                    </label>
                    <input id="file-input" type="file"/>
                </div>
            </div>

            <input type="hidden" name="command" value="add_user">
            <div class="right_up">
                <input class=one type="text" name="name" required=reqired placeholder="ФИО">
                <br>
                <input class=one type="text" name="login" required=required
                       placeholder="login">
                <br>
                <input class=one type="password" name="password" required=required
                       placeholder="password">
                <br>
            </div>
        </div>

        <div class="down">
            <input class="two" type="email" name="email" required placeholder="email">
            <div class=one>

                <div class="radio_buttons">
                    <div>
                        <input type="radio" name="sex" id="man" value="man" checked/>
                        <label for="man">Мужской</label>
                    </div>
                    <div>
                        <input type="radio" name="sex" id="woman" value="woman"/>
                        <label for="woman">Женский</label>
                    </div>
                </div>

            </div>
            <div class=two>
                <select class=one name=data>
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
        </div>
        <div class="g-recaptcha"
             data-sitekey="6LdLfZwfAAAAAOJPnoDGN_rqiNKpNpel2ZMQEm5V"></div>
        <%--        <a href="reg" class="button">Регистрация</a>--%>
        <input class="button" type="submit" value="Регистрация">

    </fieldset>
</form>
</html>

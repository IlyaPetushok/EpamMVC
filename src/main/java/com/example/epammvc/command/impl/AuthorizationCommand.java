package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.entity.User;
import com.example.epammvc.service.UserService;
import com.example.epammvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;


public class AuthorizationCommand implements Command {
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String SEX = "sex";
    private static final String EMAIL = "email";
    private static final String DATA = "data";

    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        UserService userService = UserServiceImpl.getInstance();
        String page;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        User user = userService.authorization(login, password);
        if (user.getId() != 0) {
            request.setAttribute(NAME, user.getName());
            request.setAttribute(SEX, user.getSex());
            request.setAttribute(EMAIL, user.getEmail());
            request.setAttribute(DATA, user.getData());
            page = "pages/input.jsp";
        } else {
            request.setAttribute("error", "Wrong login or password");
            page = "index.jsp";
        }
        return page;
    }
}

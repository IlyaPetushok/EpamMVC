package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.exception.CommandException;
import com.example.epammvc.exception.DaoException;
import com.example.epammvc.exception.ServiceException;
import com.example.epammvc.service.UserService;
import com.example.epammvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

public class RegisterCommand implements Command {
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String SEX = "sex";
    private static final String EMAIL = "email";
    private static final String DATA = "data";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        String name = request.getParameter(NAME);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String sex = request.getParameter(SEX);
        String email = request.getParameter(EMAIL);
        String data = request.getParameter(DATA);
        UserServiceImpl userService = UserServiceImpl.getInstance();
        try {
            if (userService.registration(name, login, password, sex, email, data)) {
                page = "/";
            } else {
                //add error on reg.html
                page = "pages/reg.jsp";
            }
        } catch (ServiceException exception) {
            throw new CommandException("Error in registrationCommand", exception);
        }
        return page;
    }
}

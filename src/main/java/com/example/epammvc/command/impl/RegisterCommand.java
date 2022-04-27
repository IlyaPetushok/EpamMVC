package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.exception.CommandException;
import com.example.epammvc.exception.ServiceException;
import com.example.epammvc.security.CaptchaVerif;
import com.example.epammvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

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
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        Boolean cheake = CaptchaVerif.verify(gRecaptchaResponse);
        if (!cheake) {
            request.setAttribute("error","Your need use captcha");
            return "/pages/reg.jsp";
        }
        UserServiceImpl userService = UserServiceImpl.getInstance();
        try {
            if (userService.registration(name, login, password, sex, email, data)) {
                page = "/";
            } else {
                //add error on reg.html
                request.setAttribute("error","login or email is busy");
                page = "/pages/reg.jsp";
            }
        } catch (ServiceException exception) {
            throw new CommandException("Error in registrationCommand", exception);
        }
        return page;
    }
}

package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.controller.Router;
import com.example.epammvc.entity.User;
import com.example.epammvc.exception.CommandException;
import com.example.epammvc.exception.ServiceException;
import com.example.epammvc.service.UserService;
import com.example.epammvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


public class AuthorizationCommand implements Command {
    private static final String NAME = "name";//отдельный класс
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String SEX = "sex";
    private static final String EMAIL = "email";
    private static final String DATA = "data";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        String page = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        User user = null;
        HttpSession session = request.getSession();
        try {
            user = userService.authorization(login, password);
        } catch (ServiceException exception) {
            throw new CommandException("Error in AuthorizationCommand", exception);
        }
        //для тобо чтобы знать с какой страницы пришли
        session.setAttribute("current_page", page);
        if (user.getId() != 0) {
            session.setAttribute(NAME, user.getName());
            session.setAttribute(SEX, user.getSex());
            session.setAttribute(EMAIL, user.getEmail());
            session.setAttribute(DATA, user.getData());
            page = "/pages/input.jsp";
        } else {
            session.setAttribute("error", "Wrong login or password");
            page = "/index.jsp";
        }
        Router router=new Router();
        router.setRedirect();
        router.setPage(page);
        return router;
    }
}

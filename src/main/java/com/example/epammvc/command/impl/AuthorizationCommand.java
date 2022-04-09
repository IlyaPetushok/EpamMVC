package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.command.CommandType;
import jakarta.servlet.http.HttpServletRequest;

public class AuthorizationCommand implements Command {
    private static final String LOGIN="login";
    private static final String PASSWORD="password";
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login=request.getParameter(LOGIN);
        String password=request.getParameter(PASSWORD);
        System.out.println(login+" "+password);
        System.out.println(login.equals(LOGIN) && password.equals(PASSWORD));
        if(login.equals(LOGIN) && password.equals(PASSWORD)){
            page="pages/input.jsp";
        }else {
            request.setAttribute("error","Wrong login or password");
            page="index.jsp";
        }
        return page;
    }
}

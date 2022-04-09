package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
    private static final String LOGIN="login";
    private static final String PASSWORD="password";
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String name=request.getParameter("name");
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        if(login.equals(LOGIN) && password.equals(PASSWORD)){
            request.setAttribute("name",name);
            request.setAttribute("login",login);
            request.setAttribute("password",password);
            System.out.println("go");
            page="input.jsp";
        }else {
            request.setAttribute("error","Wrong login or password");
            page="index.jsp";
        }
        return page;
    }
}

package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.controller.Router;
import com.example.epammvc.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    private static final String HOMEPAGE = "index.jsp";

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Router router=new Router();
        router.setPage(HOMEPAGE);
        request.getSession().invalidate();
        return router;
    }
}

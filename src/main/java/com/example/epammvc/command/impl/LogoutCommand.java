package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.controller.Router;
import com.example.epammvc.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    private static final String HOMEPAGE = "index.jsp";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router=new Router();
        router.setPage(HOMEPAGE);
        request.getSession().invalidate();
        return router;
    }
}

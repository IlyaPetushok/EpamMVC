package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.controller.Router;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    private static final String HOMEPAGE = "index.jsp";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router=new Router();
        router.setPage(HOMEPAGE);
        return router;
    }
}

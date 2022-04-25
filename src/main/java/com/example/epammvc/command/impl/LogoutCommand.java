package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.getSession().invalidate();
        return "/index.jsp";
    }
}

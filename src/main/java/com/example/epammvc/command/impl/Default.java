package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class Default implements Command {
    private static final String HOMEPAGE="index.jsp";
    @Override
    public String execute(HttpServletRequest request) {
        return HOMEPAGE;
    }
}

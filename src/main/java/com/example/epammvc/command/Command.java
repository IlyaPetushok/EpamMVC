package com.example.epammvc.command;

import com.example.epammvc.controller.Router;
import com.example.epammvc.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}

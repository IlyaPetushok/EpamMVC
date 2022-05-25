package com.example.epammvc.command;

import com.example.epammvc.controller.Router;
import com.example.epammvc.exception.CommandException;
import com.example.epammvc.exception.DaoException;
import com.example.epammvc.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@FunctionalInterface
public interface Command {
    Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException, ServletException, ServiceException, DaoException;
}

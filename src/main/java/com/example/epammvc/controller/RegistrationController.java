package com.example.epammvc.controller;

import java.io.*;
import java.sql.SQLException;

import com.example.epammvc.command.Command;
import com.example.epammvc.command.CommandType;
import com.example.epammvc.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/pages/reg")
public class RegistrationController extends HttpServlet {
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        Command command = CommandType.getCommand(commandStr);
        String page = null;
        try {
            page = command.execute(request);
        }catch (CommandException exception) {
            throw new ServletException(exception);
        }
        request.getRequestDispatcher(page).forward(request, response);
    }

    public void destroy() {
    }
}
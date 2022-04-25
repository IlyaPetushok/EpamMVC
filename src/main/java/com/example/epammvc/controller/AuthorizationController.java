package com.example.epammvc.controller;

import java.io.*;
import java.sql.SQLException;

import com.example.epammvc.command.Command;
import com.example.epammvc.command.CommandType;
import com.example.epammvc.exception.CommandException;
import com.example.epammvc.pool.ConnectionBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/home")
public class AuthorizationController extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        Command command = CommandType.getCommand(commandStr);
        String page = null;
        try {
            page = command.execute(request);
            response.sendRedirect(request.getContextPath()+page);
        } catch (CommandException exception) {
            request.setAttribute("error",exception);
            request.getRequestDispatcher("pages/error/error_500.jsp").forward(request, response);
            throw new ServletException(exception);
        }
        System.out.println("page" + page);
    }

    public void destroy() {
        ConnectionBuilder.getInstance().destroyPool();
    }
}
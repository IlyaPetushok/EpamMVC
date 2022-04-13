package com.example.epammvc.controller;

import java.io.*;
import java.sql.SQLException;

import com.example.epammvc.command.Command;
import com.example.epammvc.command.CommandType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/home")
public class AuthorizationController extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        Command command = CommandType.getCommand(commandStr);
        String page = null;
        try {
            page = command.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("page" + page);
        request.getRequestDispatcher(page).forward(request, response);
    }

    public void destroy() {
    }
}
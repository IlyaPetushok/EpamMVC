package com.example.epammvc.controller;

import java.io.*;

import com.example.epammvc.command.Command;
import com.example.epammvc.command.CommandType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/pages/reg")
public class RegistrationController extends HttpServlet {
//    private String message;

    public void init() {

        System.out.println("mgmfdmg");
//        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

//        request.getRequestDispatcher("pages/reg.jsp").forward(request,response);

        String commandStr = request.getParameter("command");
        Command command = CommandType.getCommand(commandStr);
        String page = command.execute(request);
        System.out.println("page" + page);
        request.getRequestDispatcher(page).forward(request, response);

    }

    public void destroy() {
    }
}
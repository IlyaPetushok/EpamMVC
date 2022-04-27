package com.example.epammvc.controller;

import java.io.*;

import com.example.epammvc.command.Command;
import com.example.epammvc.command.CommandType;
import com.example.epammvc.exception.CommandException;
import com.example.epammvc.pool.ConnectionBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet(name = "helloServlet", value = "/home")
public class Controller extends HttpServlet {
    public static final Logger logger = LogManager.getLogger(Controller.class.getName());

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        Command command = CommandType.getCommand(commandStr);
        String page = null;
        try {
            Router router = new Router();
            router = command.execute(request);
//            String type=router.getType();
            switch (router.getType()) {
                case REDIRECT:
                    response.sendRedirect(request.getContextPath() + router.getPage());
                    break;

                case FORWARD:
                    request.getRequestDispatcher(router.getPage()).forward(request, response);
                    break;

                default:
                    logger.error("Invalid router dispatch type : {}", router.getType());
//                    response.sendError(500);

            }
//            request.getRequestDispatcher(page).forward(request,response);
//            response.sendRedirect(request.getContextPath()+page);
        } catch (CommandException exception) {
            request.setAttribute("error", exception);
            request.getRequestDispatcher("pages/error/error_500.jsp").forward(request, response);
            throw new ServletException(exception);
        }
        System.out.println("page" + page);
    }

    public void destroy() {
        ConnectionBuilder.getInstance().destroyPool();
    }
}
package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.controller.Router;
import com.example.epammvc.exception.CommandException;
import com.example.epammvc.exception.DaoException;
import com.example.epammvc.exception.ServiceException;
import com.example.epammvc.service.UserService;
import com.example.epammvc.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class SendMessageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException, ServletException, ServiceException, DaoException {
        String message=request.getParameter("message");
        System.out.println(message);
        HttpSession session=request.getSession();
        int idUser=(Integer)session.getAttribute("id");
        int idFriend=Integer.parseInt(request.getParameter("user"));
        UserService userService= UserServiceImpl.getInstance();
        userService.sendMessage(idUser,idFriend,message);
        Router router=new Router();
        router.setPage("/pages/input.jsp");
        return router;
    }
}

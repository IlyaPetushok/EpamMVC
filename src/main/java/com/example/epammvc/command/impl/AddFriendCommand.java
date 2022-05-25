package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.controller.Router;
import com.example.epammvc.entity.User;
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

public class AddFriendCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException, ServletException, ServiceException, DaoException {
        Router router=new Router();
        router.setPage("/pages/findusers.jsp");
        System.out.println(request.getParameter("user"));
        HttpSession session;
        session=request.getSession();
        int id=(Integer)session.getAttribute("id");
        System.out.println(id);
        UserService userService =  UserServiceImpl.getInstance();
        userService.addFriend(id,Integer.parseInt(request.getParameter("user")));
        return router;
    }
}

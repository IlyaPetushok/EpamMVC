package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.controller.Router;
import com.example.epammvc.entity.User;
import com.example.epammvc.exception.CommandException;
import com.example.epammvc.exception.ServiceException;
import com.example.epammvc.service.UserService;
import com.example.epammvc.service.impl.UserServiceImpl;
import com.example.epammvc.util.CustomPictureEncoder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.core.util.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class AuthorizationCommand implements Command {
    private static final String NAME = "name";//отдельный класс
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String SEX = "sex";
    private static final String EMAIL = "email";
    private static final String DATA = "data";

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException {
        UserService userService = UserServiceImpl.getInstance();
        String page = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        User user = null;
        String pathPhotoUser="D:\\Java\\Epam\\EpamMvc\\src\\main\\webapp\\resources\\photo\\user\\";
        HttpSession session = request.getSession();
        try {
            user = userService.authorization(login, password);
        } catch (ServiceException exception) {
            throw new CommandException("Error in AuthorizationCommand", exception);
        }
        //для тобо чтобы знать с какой страницы пришли
        session.setAttribute("current_page", page);
        if (user.getId() != 0) {
            session.setAttribute(NAME, user.getName());
            session.setAttribute(SEX, user.getSex());
            session.setAttribute(EMAIL, user.getEmail());
            session.setAttribute(DATA, user.getData());
            byte[] decodedString =CustomPictureEncoder.decodeString(user.getPhoto());
            InputStream in = new ByteArrayInputStream(decodedString);
            BufferedImage bImageFromConvert = ImageIO.read(in);
            ImageIO.write(bImageFromConvert, "png", new File(pathPhotoUser+"photo_user"+user.getId()+".png"));
            session.setAttribute("photo","/resources/photo/user/photo_user"+user.getId()+".png");//"<img src=<%=request.getContextPath()%>"+pathPhotoUser+"photo_user"+user.getId()+".png>");
            page = "/pages/input.jsp";
        } else {
            session.setAttribute("error", "Wrong login or password");
            page = "/index.jsp";
        }
        Router router=new Router();
        router.setRedirect();
        router.setPage(page);
        return router;
    }
}

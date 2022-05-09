package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.controller.Router;
import com.example.epammvc.exception.CommandException;
import com.example.epammvc.exception.ServiceException;
import com.example.epammvc.security.CaptchaVerif;
import com.example.epammvc.service.impl.UserServiceImpl;
import com.example.epammvc.util.CustomPictureEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;


public class RegisterCommand implements Command {
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String SEX = "sex";
    private static final String EMAIL = "email";
    private static final String DATA = "data";

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException, ServletException {
        String page;
        String name = request.getParameter(NAME);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String sex = request.getParameter(SEX);
        String email = request.getParameter(EMAIL);
        String data = request.getParameter(DATA);
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        Boolean cheake = CaptchaVerif.verify(gRecaptchaResponse);
        if (!cheake) {
            request.setAttribute("error", "Your need use captcha");
            page = "/pages/reg.jsp";
        } else {
            Part filePart = request.getPart("photo_user");
            String pictureName = filePart.getSubmittedFileName();
            byte[] pictureBytes = filePart.getInputStream().readAllBytes();
            String bytePicture = CustomPictureEncoder.arrayToBase64(pictureBytes);

            UserServiceImpl userService = UserServiceImpl.getInstance();
            try {
                if (userService.registration(name, login, password, sex, email, data, bytePicture)) {
                    page = "/";
                } else {
                    //add error on reg.html
                    request.setAttribute("error", "login or email is busy");
                    page = "/pages/reg.jsp";
                }
            } catch (ServiceException exception) {
                throw new CommandException("Error in registrationCommand", exception);
            }
        }

        Router router = new Router();
        router.setPage(page);
        return router;
    }
}

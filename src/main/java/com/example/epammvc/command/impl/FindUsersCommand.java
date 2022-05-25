package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.controller.Router;
import com.example.epammvc.entity.User;
import com.example.epammvc.exception.CommandException;
import com.example.epammvc.exception.ServiceException;
import com.example.epammvc.service.UserService;
import com.example.epammvc.service.impl.UserServiceImpl;
import com.example.epammvc.util.CustomPictureEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FindUsersCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException, ServletException, ServiceException {
        User user = null;
        String pathPhotoUser = "D:\\Java\\Epam\\EpamMvc\\src\\main\\webapp\\resources\\photo\\user\\";
        List<User> list_users = null;
        UserService userService = UserServiceImpl.getInstance();
        list_users = userService.searchUsers();
        String text = "<table cellspacing=10><tr>";
        for (int i = 0; i < list_users.size(); i++) {
            user = list_users.get(i);

            byte[] decodedString = CustomPictureEncoder.decodeString(user.getPhoto());
            InputStream in = new ByteArrayInputStream(decodedString);
            BufferedImage bImageFromConvert = ImageIO.read(in);
            ImageIO.write(bImageFromConvert, "png", new File(pathPhotoUser + "photo_user" + user.getId() + ".png"));
            request.setAttribute("photo", "/EpamMvc_war_exploded/resources/photo/user/photo_user" + user.getId() + ".png");


            text += "<td><form action=\"/EpamMvc_war_exploded/home\">" + "<center><img src=" + "/EpamMvc_war_exploded/resources/photo/user/photo_user" +
                    user.getId() + ".png" + "> <br>"  + "<label>"+user.getName() + "</label><br>" +
                    "<input type=\"hidden\" name=\"command\" value=\"add_friend\">"+
                    "<input type=\"hidden\" name=\"user\" value="+user.getId()+">"+
                    "<input type=submit value=Добавить></center>" + "</form></td>";
//            user = list_users.get(i);
//            System.out.println(user.getName());
            if ((i + 1) % 4 == 0) {
                text += "</tr>";
            }
        }
        text += "</table>";
        request.setAttribute("table_user", text);
        Router router = new Router();
        router.setPage("/pages/findusers.jsp");
        return router;
    }
}

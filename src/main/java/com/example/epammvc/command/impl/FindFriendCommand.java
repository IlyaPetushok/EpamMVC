package com.example.epammvc.command.impl;

import com.example.epammvc.command.Command;
import com.example.epammvc.controller.Router;
import com.example.epammvc.entity.User;
import com.example.epammvc.exception.CommandException;
import com.example.epammvc.exception.DaoException;
import com.example.epammvc.exception.ServiceException;
import com.example.epammvc.service.UserService;
import com.example.epammvc.service.impl.UserServiceImpl;
import com.example.epammvc.util.CustomPictureEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FindFriendCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, IOException, ServletException, ServiceException, DaoException {
        Router router=new Router();
        User user;
        String pathPhotoUser = "D:\\Java\\Epam\\EpamMvc\\src\\main\\webapp\\resources\\photo\\user\\";
        List<User> friend=new ArrayList<>();
        UserService userService =UserServiceImpl.getInstance();
        HttpSession session=request.getSession();
        int id=(Integer)session.getAttribute("id");
        friend=userService.searchFriend(id);
        String text = "<table cellspacing=10><tr>";
        for (int i = 0; i < friend.size(); i++) {
            user = friend.get(i);

            byte[] decodedString = CustomPictureEncoder.decodeString(user.getPhoto());
            InputStream in = new ByteArrayInputStream(decodedString);
            BufferedImage bImageFromConvert = ImageIO.read(in);
            ImageIO.write(bImageFromConvert, "png", new File(pathPhotoUser + "photo_user" + user.getId() + ".png"));
            request.setAttribute("photo", "/EpamMvc_war_exploded/resources/photo/user/photo_user" + user.getId() + ".png");


            text += "<td><form action=\"/EpamMvc_war_exploded/home\">" + "<center><img src=" + "/EpamMvc_war_exploded/resources/photo/user/photo_user" +
                    user.getId() + ".png" + "> <br>" + "<label>"+user.getName() + "</label><br>" +
                    "<input type=\"hidden\" name=\"command\" value=\"send_message\">"+
                    "<input type=\"hidden\" name=\"user\" value="+user.getId()+">"+
                    "<input id=\"send\" type=\"submit\" class=\"input_hidden\" value=\"Отправить сообщение\"></center>" +
                    "<div id=\"div1\" class=\"div-hidden\">" +
                    "    <center><h3>Сообщение</h3></center>" +
                    "    <input class=\"message\" type=\"text\" name=\"message\" placeholder=\"Введите сообщение\">" +
                    "    <center>" +
                    "        <input class=\"btn-mes\" type=\"button\" value=\"отмена\" onmousedown=\"divHidden()\">" +
                    "        <input  class=\"btn-mes\" type=\"button\" value=\"отправить\" onmousedown=\"sendMessage()\">" +
                    "    </center>" +
                    "</div>"+
                    "</form>"+
                    "<center><input type=\"button\" value=\"Отправить сообщение\" onmousedown=\"divSee()\"></center>"+
                    "</td>";
//            user = friend.get(i);
//            System.out.println(user.getName());
            if ((i + 1) % 4 == 0) {
                text += "</tr>";
            }
        }
        text += "</table>";
        request.setAttribute("table_friend", text);
        router.setPage("/pages/friend.jsp");
        return router;
    }
}

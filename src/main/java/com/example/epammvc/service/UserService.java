package com.example.epammvc.service;

import com.example.epammvc.entity.User;
import com.example.epammvc.service.impl.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    boolean registration(String name, String login, String password, String sex, String email, String data) throws SQLException;

    User authorization(String login, String password) throws SQLException;

}

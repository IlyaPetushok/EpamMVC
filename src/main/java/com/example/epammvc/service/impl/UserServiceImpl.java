package com.example.epammvc.service.impl;

import com.example.epammvc.dao.UserDao;
import com.example.epammvc.dao.impl.UserDaoImpl;
import com.example.epammvc.entity.User;
import com.example.epammvc.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return instance;
    }

    private UserServiceImpl(){

    }

    @Override
    public User authorization(String login, String password) throws SQLException {
        //validator login password
        UserDaoImpl userDao=UserDaoImpl.getInstance();
        return userDao.authenticate(login, password);
    }

    @Override
    public boolean registration(String name, String login, String password, String sex, String email, String data) throws SQLException {
        UserDaoImpl userDao=UserDaoImpl.getInstance();
        return userDao.insert(new User(name,login,password,sex,email,data));
    }
}

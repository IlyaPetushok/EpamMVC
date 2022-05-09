package com.example.epammvc.service.impl;

import com.example.epammvc.dao.UserDao;
import com.example.epammvc.dao.impl.UserDaoImpl;
import com.example.epammvc.entity.User;
import com.example.epammvc.exception.DaoException;
import com.example.epammvc.exception.ServiceException;
import com.example.epammvc.security.Sha1;
import com.example.epammvc.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    public Sha1 sha1;
    private static UserServiceImpl instance = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return instance;
    }

    private UserServiceImpl() {
        sha1 = new Sha1();
    }

    @Override
    public User authorization(String login, String password) throws ServiceException {
        //validator login password
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        password = sha1.cipher(password);
        try {
            return userDao.authenticate(login, password);
        } catch (DaoException exception) {
            throw new ServiceException("Mistake in authenticate service", exception);
        }
    }

    @Override
    public boolean registration(String name, String login, String password, String sex, String email, String data,String photo) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        password = sha1.cipher(password);
        try {
            return userDao.insert(new User(name, login, password, sex, email, data, photo));
        } catch (DaoException exception) {
            throw new ServiceException("Mistake in authenticate service", exception);
        }
    }
}

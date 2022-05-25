package com.example.epammvc.service;

import com.example.epammvc.entity.User;
import com.example.epammvc.exception.DaoException;
import com.example.epammvc.exception.ServiceException;
import com.example.epammvc.service.impl.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    boolean registration(String name, String login, String password, String sex, String email, String data,String photo) throws DaoException, ServiceException;

    User authorization(String login, String password) throws ServiceException;

    List<User> searchUsers() throws ServiceException;

    boolean addFriend(int idUser,int idFriend) throws ServiceException, DaoException;

    List<User> searchFriend(int idUser) throws ServiceException, DaoException;

    boolean sendMessage(int idUser,int idFriend,String message) throws ServiceException, DaoException;
}

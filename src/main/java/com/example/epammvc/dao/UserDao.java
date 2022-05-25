package com.example.epammvc.dao;

import com.example.epammvc.entity.User;
import com.example.epammvc.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User authenticate(String login, String Password) throws DaoException;
    boolean insert(User user) throws DaoException;
    List<User> findAll() throws DaoException;
    boolean addFriend(int idUser,int idFriend) throws DaoException;
    List<User> findFriend(int idUser) throws DaoException;
    boolean sendMessage(int idUser,int idFriend,String message) throws DaoException;
}

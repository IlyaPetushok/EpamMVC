package com.example.epammvc.dao;

import com.example.epammvc.entity.User;
import com.example.epammvc.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User authenticate(String login, String Password) throws DaoException;
    boolean insert(User user) throws DaoException;
}

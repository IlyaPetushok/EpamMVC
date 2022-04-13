package com.example.epammvc.dao;

import com.example.epammvc.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User authenticate(String login, String Password) throws SQLException;
    boolean insert(User user) throws SQLException;
}

package com.example.epammvc.dao.impl;

import com.example.epammvc.dao.BaseDao;
import com.example.epammvc.dao.UserDao;
import com.example.epammvc.entity.User;
import com.example.epammvc.exception.DaoException;
import com.example.epammvc.pool.ConnectionBuilder;

import java.sql.*;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final String SELECT_LOGIN_AND_PASSWORD = "Select * FROM info_user WHERE login = ? and password=?";
    private static final String INSERT_USER = "INSERT INTO info_user(name,login,password,sex,email,data) VALUES (?,?,?,?,?,?);";
    public static final String CHECK_LOGIN_OR_EMAIL = "Select * FROM info_user WHERE login = ? or email=?";
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User user) throws DaoException {
        try {
            ConnectionBuilder connectionBuilder = ConnectionBuilder.getInstance();
            Connection connection = connectionBuilder.getFreeConnection();

            PreparedStatement statement1 = connection.prepareStatement(CHECK_LOGIN_OR_EMAIL);
            statement1.setString(1, user.getLogin());
            statement1.setString(2, user.getEmail());
            ResultSet resultSet= statement1.executeQuery();
            if (resultSet.next()) {
                return false;
            } else {
                PreparedStatement statement = connection.prepareStatement(INSERT_USER);
                String name = user.getName();
                String login = user.getLogin();
                String password = user.getPassword();
                String sex = user.getSex();
                String email = user.getEmail();
                String data = user.getData();
                statement.setString(1, name);
                statement.setString(2, login);
                statement.setString(3, password);
                statement.setString(4, sex);
                statement.setString(5, email);
                statement.setString(6, data);
                statement.executeUpdate();
                connectionBuilder.releaseConnection(connection);
            }
        } catch (SQLException exception) {
            throw new DaoException("Error in try insert user", exception);
        }
        return true;//fix me
    }

    @Override
    public User authenticate(String login, String password) throws DaoException {
        int id = 0;
        String name = null;
        String email = null;
        String sex = null;
        String data = null;
        try {
            ConnectionBuilder connectionBuilder = ConnectionBuilder.getInstance();
            Connection connection = connectionBuilder.getFreeConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                sex = resultSet.getString("sex");
                email = resultSet.getString("email");
                data = resultSet.getString("data");
            }
            connectionBuilder.releaseConnection(connection);
        } catch (SQLException exception) {
            throw new DaoException("Error in try input", exception);
        }
        return new User(id, name, sex, data, email);
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update() {
        return null;
    }
}


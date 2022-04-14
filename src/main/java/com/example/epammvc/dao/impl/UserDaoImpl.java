package com.example.epammvc.dao.impl;

import com.example.epammvc.dao.BaseDao;
import com.example.epammvc.dao.UserDao;
import com.example.epammvc.entity.User;
import org.intellij.lang.annotations.Language;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final String SELECT_LOGIN_AND_PASSWORD = "Select * FROM info_user WHERE login = ? and password=?";
    private static final String INSERT_USER= "INSERT INTO info_user(name,login,password,sex,email,data) VALUES (?,?,?,?,?,?);";
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User user) throws SQLException {
        PreparedStatement statement = ConnectSQL(INSERT_USER);
        String name = user.getName();
        String login = user.getLogin();
        String password = user.getPassword();
        String sex = user.getSex();
        String email = user.getEmail();
        String data = user.getData();
        statement.setString(1,name);
        statement.setString(2,login);
        statement.setString(3,password);
        statement.setString(4,sex);
        statement.setString(5,email);
        statement.setString(6,data);
        statement.executeUpdate();
        return true;//fix me
    }

    @Override
    public User authenticate(String login, String password) throws SQLException {
        PreparedStatement statement = ConnectSQL(SELECT_LOGIN_AND_PASSWORD);
        statement.setString(1,login);
        statement.setString(2,password);
        ResultSet resultSet = statement.executeQuery();
        int id = 0;
        String name = null;
        String email = null;
        String sex = null;
        String data = null;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            sex = resultSet.getString("sex");
            email = resultSet.getString("email");
            data = resultSet.getString("data");
        }
        return new User(id, name, sex, data, email);
    }

    private PreparedStatement ConnectSQL(String sqlCommand) {
        PreparedStatement statement = null;
        Properties properties = loadProperties();
        String databaseUrl = (String) properties.get("db.url");
        String databaseUser = (String) properties.get("user");
        String databasePassword = (String) properties.get("password");
        try {
            Connection connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
            statement = connection.prepareStatement(sqlCommand);
        } catch (SQLException exception) {
            //logger
        }
        return statement;
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("D:\\Java\\Epam\\EpamMvc\\src\\main\\resources\\database.properties"));
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        } catch (IOException | ClassNotFoundException exception) {
            //logger
        }
        return properties;
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


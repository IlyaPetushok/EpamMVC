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
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User user) throws SQLException {
        Statement statement = ConnectSQL();
        String name = user.getName();
        String login = user.getLogin();
        String password = user.getPassword();
        String sex = user.getSex();
        String email = user.getEmail();
        String data = user.getData();
        @Language("SQL")
        String sqlCommand = "INSERT INTO info_user(name,login,password,sex,email,data) VALUES (" + "'" + name + "'" + ",'" + login + "'"
                + ",'" + password + "'" + ",'" + sex + "'" + ",'" + email + "'" + ",'" + data + "'" + ");";
        statement.executeUpdate(sqlCommand);
        return true;//fix me
    }

    @Override
    public User authenticate(String login, String password) throws SQLException {
        Statement statement = ConnectSQL();
        @Language("SQL")
        String SqlCommand = "Select * FROM info_user WHERE login='" + login + "'" + "and password=" + "'" + password + "';";
        ResultSet resultSet = statement.executeQuery(SqlCommand);
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

    private Statement ConnectSQL() {
        Statement statement = null;
        Properties properties = loadProperties();
        String databaseUrl = (String) properties.get("db.url");
        String databaseUser = (String) properties.get("user");
        String databasePassword = (String) properties.get("password");
        try {
            Connection connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
            statement = connection.createStatement();
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


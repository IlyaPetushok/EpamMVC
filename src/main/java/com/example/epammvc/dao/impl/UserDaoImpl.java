package com.example.epammvc.dao.impl;

import com.example.epammvc.dao.BaseDao;
import com.example.epammvc.dao.UserDao;
import com.example.epammvc.entity.User;
import com.example.epammvc.exception.DaoException;
import com.example.epammvc.pool.ConnectionBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final String SELECT_LOGIN_AND_PASSWORD = "Select * FROM users WHERE login = ? and password=?";
    private static final String INSERT_USER = "INSERT INTO users(name,password,login,sex,photo,email,date) VALUES (?,?,?,?,?,?,?);";
    private static final String CHECK_LOGIN_OR_EMAIL = "Select * FROM users WHERE login = ? or email=?";
    private static final String SELECT_USERS = "Select  id,name,sex,date,email,photo FROM users";
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
            ResultSet resultSet = statement1.executeQuery();
            if (!resultSet.next()) {
                PreparedStatement statement = connection.prepareStatement(INSERT_USER);
                String name = user.getName();
                String login = user.getLogin();
                String password = user.getPassword();
                String sex = user.getSex();
                String email = user.getEmail();
                String data = user.getData();
                String photo = user.getPhoto();
                statement.setString(1, name);
                statement.setString(3, login);
                statement.setString(2, password);
                statement.setString(4, sex);
                statement.setString(6, email);
                statement.setString(7, data);
                statement.setString(5, photo);
                statement.executeUpdate();
                connectionBuilder.releaseConnection(connection);
            } else {
                return false;
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
        String photo = null;
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
                data = resultSet.getString("date");
                photo = resultSet.getString("photo");
            }
            connectionBuilder.releaseConnection(connection);
        } catch (SQLException exception) {
            throw new DaoException("Error in try input", exception);
        }
        return new User(id, name, sex, data, email, photo);
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        List<User> list_users = new ArrayList<>();
        int id = 0;
        String email = null;
        User user = null;
        String name = null;
        String sex = null;
        String data = null;
        String photo = null;
        try {
            ConnectionBuilder connectionBuilder = ConnectionBuilder.getInstance();
            Connection connection = connectionBuilder.getFreeConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_USERS);
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                sex = resultSet.getString("sex");
                data = resultSet.getString("date");
                email = resultSet.getString("email");
                photo = resultSet.getString("photo");
                user = new User(id, name, sex, data, email, photo);
                list_users.add(user);
            }
            connectionBuilder.releaseConnection(connection);
        } catch (SQLException exception) {
//            throw new DaoException("Error in try input", exception);
        }
        return list_users;
    }

    @Override
    public boolean addFriend(int idUser, int idFriend) throws DaoException {
        String idMessage = String.valueOf(idUser) + String.valueOf(idFriend);
        try {
            ConnectionBuilder connectionBuilder = ConnectionBuilder.getInstance();
            Connection connection = connectionBuilder.getFreeConnection();

            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO message(idmessage,message) Values(" + "'" + idMessage + "'" + "," + "'" + null + "'" + ")");

            statement.executeUpdate("INSERT INTO users_has_myfriends(users_id,id_friend,message_idmessage) VALUES(" + "'" + idUser + "'," + "'" + idFriend + "'," + "'" + idMessage + "'" + ");");
            statement.executeUpdate("INSERT INTO users_has_myfriends(users_id,id_friend,message_idmessage) VALUES(" + "'" + idFriend + "'," + "'" + idUser + "'," + "'" + idMessage + "'" + ");");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<User> findFriend(int idUser) throws DaoException {
        int id = 0;
        String name = null;
        String photo = null;
        List<User> Friend = new ArrayList<>();
        List<Integer> idFriend = new ArrayList<>();
        try {
            ConnectionBuilder connectionBuilder = ConnectionBuilder.getInstance();
            Connection connection = connectionBuilder.getFreeConnection();
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_friend FROM users_has_myfriends WHERE users_id=" + "'" + idUser + "';");
            while (resultSet.next()) {
                idFriend.add(resultSet.getInt("id_friend"));
            }
            if (idFriend.size() != 0) {
                for (int i = 0; i < idFriend.size(); i++) {
                    resultSet = statement.executeQuery("Select  id,name,photo FROM users WHERE id=+" + "'" + idFriend.get(i) + "'");
                    while (resultSet.next()) {
                        id = resultSet.getInt("id");
                        name = resultSet.getString("name");
                        photo = resultSet.getString("photo");
                        Friend.add(new User(id, name, photo));
                    }
                }
            } else {
                return Friend;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Friend;
    }

    @Override
    public boolean sendMessage(int idUser, int idFriend, String message) throws DaoException {
        try {
            String messageSql=null;
            String idMessage = String.valueOf(idUser) + String.valueOf(idFriend);
            ConnectionBuilder connectionBuilder = ConnectionBuilder.getInstance();
            Connection connection = connectionBuilder.getFreeConnection();
            Statement statement = null;

            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("Select message FROM message WHERE idmessage=" + "'"+idMessage + "'");
            while (resultSet.next()){
                messageSql=resultSet.getString("message");
                if(messageSql.equals("null")){
                    messageSql="";
                }
            }
            //INSERT INTO message(message) VALUES (?) WHERE idmessage="+"'"+idMessage+"'"+";
            messageSql+=message;
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("UPDATE message SET message=(?) WHERE idmessage="+"'"+idMessage+"'"+";");
            preparedStatement.setString(1, messageSql);
            preparedStatement.executeUpdate();
            connectionBuilder.releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User update() {
        return null;
    }
}


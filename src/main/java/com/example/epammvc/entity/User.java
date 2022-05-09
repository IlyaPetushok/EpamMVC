package com.example.epammvc.entity;

public class User extends AbstractEntity{
    private int id;
    private String name;
    private String password;
    private String login;
    private String sex;
    private String data;
    private String email;
    //
    private String photo;

    public User(int id, String name, String sex, String data, String email) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.data = data;
        this.email = email;
    }

    public User(int id, String name, String sex, String data, String email, String photo) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.data = data;
        this.email = email;
        this.photo = photo;
    }

    public User(String name, String login, String password, String sex, String email, String data) {
        this.name = name;
        this.password = password;
        this.login = login;
        this.sex = sex;
        this.data = data;
        this.email = email;
    }

    public User(String name, String login, String password, String sex, String email, String data,String photo) {
        this.name = name;
        this.password = password;
        this.login = login;
        this.sex = sex;
        this.data = data;
        this.email = email;
        this.photo=photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

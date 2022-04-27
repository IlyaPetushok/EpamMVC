package com.example.epammvc.controller;

public class Router {
    private String page;
    private Type type=Type.FORWARD;
    enum Type{
        FORWARD,REDIRECT
    }

    public Router() {
    }

    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRedirect() {
        this.type = Type.REDIRECT;
    }
}

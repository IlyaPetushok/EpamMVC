package com.example.epammvc.command;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request) throws SQLException;
}

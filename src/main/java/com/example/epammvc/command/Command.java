package com.example.epammvc.command;

import com.example.epammvc.exception.CommandException;
import com.example.epammvc.exception.DaoException;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
}

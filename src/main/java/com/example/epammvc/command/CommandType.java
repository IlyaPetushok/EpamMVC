package com.example.epammvc.command;

import com.example.epammvc.command.impl.AuthorizationCommand;
import com.example.epammvc.command.impl.DefaultCommand;
import com.example.epammvc.command.impl.LogoutCommand;
import com.example.epammvc.command.impl.RegisterCommand;

public enum CommandType {
    ADD_USER(new RegisterCommand()),
    INPUT_USER(new AuthorizationCommand()),
    DEFAULT(new DefaultCommand()),
    LOGOUT(new LogoutCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command getCommand(String commandStr) {
        //valid
        CommandType commandType = CommandType.valueOf(commandStr.toUpperCase());
        return commandType.command;
    }
}

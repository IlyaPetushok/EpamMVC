package com.example.epammvc.command;

import com.example.epammvc.command.impl.*;

public enum CommandType {
    ADD_USER(new RegisterCommand()),
    INPUT_USER(new AuthorizationCommand()),
    DEFAULT(new DefaultCommand()),
    LOGOUT(new LogoutCommand()),
    MYFRIEND(new FindFriendCommand()),
    SEARCH_FRIEND(new FindUsersCommand()),
    ADD_FRIEND(new AddFriendCommand()),
    SEND_MESSAGE(new SendMessageCommand());

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

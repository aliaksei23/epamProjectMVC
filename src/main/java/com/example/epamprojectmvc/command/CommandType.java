package com.example.epamprojectmvc.command;

import com.example.epamprojectmvc.command.impl.AddUserCommand;
import com.example.epamprojectmvc.command.impl.DefaultCommand;
import com.example.epamprojectmvc.command.impl.LoginCommand;
import com.example.epamprojectmvc.command.impl.LogoutCommand;

import java.util.Locale;

public enum CommandType {

    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String command) {
        CommandType current = CommandType.valueOf(command.toUpperCase(Locale.ROOT));
        return current.command;
    }


}

package com.example.epamprojectmvc.command.impl;

import com.example.epamprojectmvc.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}

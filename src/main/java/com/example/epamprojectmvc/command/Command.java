package com.example.epamprojectmvc.command;

import com.example.epamprojectmvc.exception.CommandException;
import com.example.epamprojectmvc.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {

    String execute(HttpServletRequest request) throws CommandException;

}

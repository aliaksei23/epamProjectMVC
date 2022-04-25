package com.example.epamprojectmvc.command.impl;

import com.example.epamprojectmvc.command.Command;
import com.example.epamprojectmvc.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String rg = request.getParameter("reg");
        request.getSession().setAttribute("reg_here", rg);
        return "pages/registration.jsp";
    }
}

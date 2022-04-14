package com.example.epamprojectmvc.command.impl;

import com.example.epamprojectmvc.command.Command;
import com.example.epamprojectmvc.service.UserService;
import com.example.epamprojectmvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");

        UserService userService = UserServiceImpl.getInstance();
        String page;
        if (userService.authenticate(login, password)) {
            request.setAttribute("user", login);
            page = "pages/main.jsp";
        } else {
            request.setAttribute("login_msg", "incorrect login or pass");
            page = "index.jsp";
        }
        return page;
    }
}

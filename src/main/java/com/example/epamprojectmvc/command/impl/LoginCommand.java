package com.example.epamprojectmvc.command.impl;

import com.example.epamprojectmvc.command.Command;
import com.example.epamprojectmvc.entity.User;
import com.example.epamprojectmvc.exception.CommandException;
import com.example.epamprojectmvc.exception.ServiceException;
import com.example.epamprojectmvc.service.UserService;
import com.example.epamprojectmvc.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");

        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String page;
        try {
            Optional<User> userOptional = userService.authenticate(login, password);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                request.setAttribute("user", login);
                session.setAttribute("user_name", login);
                page = "pages/main.jsp";
            } else {
                request.setAttribute("login_msg", "incorrect login or pass");
                page = "index.jsp";
            }
        } catch (Exception e) {
            throw new CommandException(e);
        }
        return page;
    }
}

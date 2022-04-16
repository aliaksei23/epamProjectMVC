package com.example.epamprojectmvc.controller;

import java.io.*;

import com.example.epamprojectmvc.command.Command;
import com.example.epamprojectmvc.command.CommandType;
import com.example.epamprojectmvc.exception.CommandException;
import com.example.epamprojectmvc.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {


    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        Command command = CommandType.define(commandStr);
        String page;
        try {
            page = command.execute(request);
            request.getRequestDispatcher(page).forward(request, response);
//            response.sendRedirect(page);
        } catch (CommandException e) {
//            response.sendError(500);//1
//            e.printStackTrace();
            throw new ServletException(e.getMessage());//2
//            request.setAttribute("error_msg", e);
//            request.getRequestDispatcher("pages/error/error_500.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}
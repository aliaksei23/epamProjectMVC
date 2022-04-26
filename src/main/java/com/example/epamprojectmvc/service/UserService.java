package com.example.epamprojectmvc.service;

import com.example.epamprojectmvc.entity.User;
import com.example.epamprojectmvc.exception.ServiceException;

import java.util.Optional;

public interface UserService {

//    boolean authenticate(String login, String password) throws ServiceException;

    Optional<User> authenticate(String login, String password) throws ServiceException;
}

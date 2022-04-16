package com.example.epamprojectmvc.service;

import com.example.epamprojectmvc.exception.ServiceException;

public interface UserService {

    boolean authenticate(String login, String password) throws ServiceException;

}

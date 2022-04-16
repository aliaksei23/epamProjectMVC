package com.example.epamprojectmvc.dao;

import com.example.epamprojectmvc.exception.DaoException;

public interface UserDao {

    boolean authenticate (String login, String password) throws DaoException;

}

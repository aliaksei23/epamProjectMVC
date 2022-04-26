package com.example.epamprojectmvc.dao;

import com.example.epamprojectmvc.entity.User;
import com.example.epamprojectmvc.exception.DaoException;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDao {

//    boolean authenticate (String login, String password) throws DaoException;

    Optional<User> authenticate (String login, String password) throws DaoException, SQLException;
}

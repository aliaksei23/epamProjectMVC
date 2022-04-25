package com.example.epamprojectmvc.service.impl;

import com.example.epamprojectmvc.dao.UserDao;
import com.example.epamprojectmvc.dao.impl.UserDaoImpl;
import com.example.epamprojectmvc.entity.User;
import com.example.epamprojectmvc.exception.DaoException;
import com.example.epamprojectmvc.exception.ServiceException;
import com.example.epamprojectmvc.service.UserService;
import com.example.epamprojectmvc.validator.impl.UserValidatorImpl;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

//    @Override
//    public boolean authenticate(String login, String password) throws ServiceException {
//        // todo validate login, pass + md5
//        UserValidatorImpl validator = new UserValidatorImpl();
//        UserDaoImpl userDao = UserDaoImpl.getInstance();
////        if (validator.isLoginValid(login)) {
////            UserDaoImpl userDao = UserDaoImpl.getInstance();
////        }
//        boolean match = false;
//        try {
//            match = userDao.authenticate(login, password);
//        } catch (DaoException e) {
//            throw new ServiceException(e);
////            e.printStackTrace();
//        }
//        return match; //todo
//    }

    @Override
    public Optional<User> authenticate(String login, String password) throws ServiceException {
        // todo validate login, pass + md5
        Optional<User> userOptional = Optional.empty();
        UserValidatorImpl validator = new UserValidatorImpl();
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            if (validator.isLoginValid(login) && validator.isPasswordValid(password)) {
                Optional<String> userPasswordOptional = userDao.authenticate(login, password);
            }
        }
        UserDaoImpl userDao = UserDaoImpl.getInstance();
//        if (validator.isLoginValid(login)) {
//            UserDaoImpl userDao = UserDaoImpl.getInstance();
//        }
        boolean match = false;
        try {
            match = userDao.authenticate(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
//            e.printStackTrace();
        }
        return match; //todo
    }
}

package com.example.epamprojectmvc.service.impl;

import com.example.epamprojectmvc.dao.UserDao;
import com.example.epamprojectmvc.dao.impl.UserDaoImpl;
import com.example.epamprojectmvc.exception.DaoException;
import com.example.epamprojectmvc.exception.ServiceException;
import com.example.epamprojectmvc.service.UserService;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        // todo validate login, pass + md5
        UserDaoImpl userDao = UserDaoImpl.getInstance();
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

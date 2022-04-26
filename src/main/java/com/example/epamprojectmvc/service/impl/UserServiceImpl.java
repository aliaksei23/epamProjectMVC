package com.example.epamprojectmvc.service.impl;

import com.example.epamprojectmvc.dao.UserDao;
import com.example.epamprojectmvc.dao.impl.UserDaoImpl;
import com.example.epamprojectmvc.entity.User;
import com.example.epamprojectmvc.exception.DaoException;
import com.example.epamprojectmvc.exception.ServiceException;
import com.example.epamprojectmvc.service.UserService;
import com.example.epamprojectmvc.util.PasswordEncryptor;
import com.example.epamprojectmvc.validator.impl.UserValidatorImpl;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> authenticate(String login, String password) throws ServiceException {

        Optional<User> userOptional = Optional.empty();
        UserValidatorImpl validator = new UserValidatorImpl();
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            if (validator.isLoginValid(login) && validator.isPasswordValid(password)) {
                Optional<User> user = userDao.authenticate(login, password);
                if (user.isPresent()) {
                    String userPassword = user.get().getPassword();
                    Optional<String> encryptedPassword = PasswordEncryptor.encrypt(password);
                    if (encryptedPassword.isPresent() && userPassword.equals(encryptedPassword.get())) {
                        userOptional = user;
                    }
                }
            }
            return userOptional;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}

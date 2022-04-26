package com.example.epamprojectmvc.validator.impl;

import com.example.epamprojectmvc.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserValidatorImpl implements UserValidator<String> {
    private static final Logger LOGGER = LogManager.getLogger();
    //    private static final String LOGIN_REGEX = "^[\\w.]{3,20}$";
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9._-]{3,}$";
    //    private static final String PASSWORD_REGEX = "^(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\d)\\p{Alnum}{1,20}$";
    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9_.-]*$";


    @Override
    public boolean isPasswordValid(String password) {
        boolean res = isStringCorrect(password, PASSWORD_REGEX);
        LOGGER.log(Level.INFO, " password validation correct {}", res);
        return res;
    }

    @Override
    public boolean isLoginValid(String login) {
        boolean res = isStringCorrect(login, LOGIN_REGEX);
        LOGGER.log(Level.INFO, " login validation correct {}", res);
        return res;
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean isStringCorrect = false;
        if (line != null) {
            isStringCorrect = line.matches(regex);
        }
        return isStringCorrect;
    }
}

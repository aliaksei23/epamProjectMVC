package com.example.epamprojectmvc.validator.impl;

import com.example.epamprojectmvc.validator.UserValidator;

public class UserValidatorImpl implements UserValidator<String> {
    private static final String LOGIN_REGEX = "^[\\w.]{3,20}$";
    private static final String PASSWORD_REGEX = "^(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\d)\\p{Alnum}{8,20}$";


    @Override
    public boolean isPasswordValid(String password) {
        return isStringCorrect(password, PASSWORD_REGEX);
    }

    @Override
    public boolean isLoginValid(String login) {
        return isStringCorrect(login, LOGIN_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean isStringCorrect = false;
        if (line != null) {
            isStringCorrect = line.matches(regex);
        }
        return isStringCorrect;
    }
}

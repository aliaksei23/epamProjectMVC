package com.example.epamprojectmvc.validator;

public interface UserValidator<T> {
    boolean isPasswordValid(T t);

    boolean isLoginValid(T t);
}

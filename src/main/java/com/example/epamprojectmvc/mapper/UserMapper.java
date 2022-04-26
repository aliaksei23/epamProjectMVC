package com.example.epamprojectmvc.mapper;

import com.example.epamprojectmvc.entity.AbstractEntity;
import com.example.epamprojectmvc.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface UserMapper<E extends AbstractEntity> {
    Optional<E> map(ResultSet resultSet) throws DaoException, SQLException;
}

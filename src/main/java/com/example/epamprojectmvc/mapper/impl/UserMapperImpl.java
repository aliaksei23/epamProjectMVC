package com.example.epamprojectmvc.mapper.impl;

import com.example.epamprojectmvc.entity.AbstractEntity;
import com.example.epamprojectmvc.entity.User;
import com.example.epamprojectmvc.entity.UserRole;
import com.example.epamprojectmvc.exception.DaoException;
import com.example.epamprojectmvc.mapper.ColumnName;
import com.example.epamprojectmvc.mapper.UserMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserMapperImpl implements UserMapper<User> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<User> map(ResultSet resultSet) throws DaoException, SQLException {
        User user = new User();
        Optional<User> optionalUser = Optional.empty();
//        if (!resultSet.isBeforeFirst()) {
//            LOGGER.log(Level.INFO, "resultSet empty");
//        } else {
            try {
                LOGGER.log(Level.INFO, "resultSet is not empty");
            user.setId(Long.parseLong(resultSet.getString(ColumnName.USER_ID)));
            user.setUserRole(UserRole.valueOf((resultSet.getString(ColumnName.USER_ROLE_ID))));//todo work?
            user.setName(resultSet.getString(ColumnName.USER_NAME));
            user.setSurname(resultSet.getString(ColumnName.USER_SURNAME));
            user.setSurname(resultSet.getString(ColumnName.USER_SURNAME));
            user.setEmail(resultSet.getString(ColumnName.USER_EMAIL));
            user.setPhoneNumber(resultSet.getString(ColumnName.USER_PHONE_NUMBER));
            user.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
                user.setPassword(resultSet.getString(ColumnName.USER_PASSWORD));
                optionalUser = Optional.of(user);
            } catch (SQLException e) {
                LOGGER.info("User mappind failed" + e, Level.ERROR);
                throw new DaoException("Error in user mapping" + e);
            }
//        }
        return optionalUser;
    }
}

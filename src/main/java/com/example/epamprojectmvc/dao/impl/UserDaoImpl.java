package com.example.epamprojectmvc.dao.impl;

import com.example.epamprojectmvc.dao.BaseDao;
import com.example.epamprojectmvc.dao.UserDao;
import com.example.epamprojectmvc.entity.User;
import com.example.epamprojectmvc.pool.ConnectionPool;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class UserDaoImpl implements UserDao, BaseDao<User> {

    private static final String SELECT_PASSWORD_LOGIN = "SELECT password FROM user WHERE login = ? ";
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PASSWORD_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            @Language("SQL")
            String passFromDb;
            if (resultSet.next()) {
                passFromDb = resultSet.getString(1);
                match = password.equals(passFromDb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return match;
    }

    @Override
    public boolean insert(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        throw new UnsupportedOperationException("delete operation unsupported");
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }
}

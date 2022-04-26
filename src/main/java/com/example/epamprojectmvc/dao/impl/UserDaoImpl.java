package com.example.epamprojectmvc.dao.impl;

import com.example.epamprojectmvc.dao.BaseDao;
import com.example.epamprojectmvc.dao.UserDao;
import com.example.epamprojectmvc.entity.User;
import com.example.epamprojectmvc.exception.DaoException;
import com.example.epamprojectmvc.mapper.ColumnName;
import com.example.epamprojectmvc.mapper.UserMapper;
import com.example.epamprojectmvc.mapper.impl.UserMapperImpl;
import com.example.epamprojectmvc.pool.ConnectionPool;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.util.List;
import java.util.Optional;


public class UserDaoImpl implements UserDao, BaseDao<User> {
    private static final String SELECT_PASSWORD_LOGIN = "SELECT * FROM user WHERE login = ?";
    private static final String ADD_NEW_CUSTOMER = "INSERT INTO user(role_id, name, surname, email, phone_number," +
            " login, password) " + "VALUE (?, ?, ?, ?, ?, ?, ?)";

    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> authenticate(String login, String password) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PASSWORD_LOGIN)) {
            statement.setString(1, login);
            ;

            ResultSet resultSet = statement.executeQuery();
            @Language("SQL")
            String passFromDb;
            if (resultSet.next()) {
                UserMapperImpl userMapper = new UserMapperImpl();
                passFromDb = resultSet.getString(ColumnName.USER_PASSWORD);
                boolean match = password.equals(passFromDb);
                if (match) {
                    userOptional = userMapper.map(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userOptional;
    }

    @Override
    public boolean insert(User user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_NEW_CUSTOMER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, String.valueOf(user.getUserRole().getRole_id()));
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhoneNumber());
            statement.setLong(7, Long.parseLong(user.getLogin()));
            statement.setLong(8, Long.parseLong(user.getPassword()));
            boolean isAdded = statement.executeUpdate() > 0;
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
            return isAdded;
        } catch (SQLException e) {
            throw new DaoException("Error while adding user: " + user, e);
        }
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

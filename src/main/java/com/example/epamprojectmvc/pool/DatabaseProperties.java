package com.example.epamprojectmvc.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.StringJoiner;

public class DatabaseProperties {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String FILE_NAME = "database.properties";
    private static final String DATABASE_DRIVER = "db.driver";
    private static final String DATABASE_URL = "db.url";
    private static final String DATABASE_USERNAME = "user";
    private static final String DATABASE_PASSWORD = "password";

    private final String driverName;
    private final String url;
    private final String username;
    private final String password;

    public DatabaseProperties() {
        Properties properties = new Properties();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Error while reading properties file: {}", FILE_NAME, e);
            throw new RuntimeException("Error while reading properties file: " + FILE_NAME, e);
        }
        driverName = properties.getProperty(DATABASE_DRIVER);
        url = properties.getProperty(DATABASE_URL);
        username = properties.getProperty(DATABASE_USERNAME);
        password = properties.getProperty(DATABASE_PASSWORD);
    }

    public String getDriverName() {
        return driverName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DatabaseProperties.class.getSimpleName() + "[", "]")
                .add("driverName='" + driverName + "'")
                .add("url='" + url + "'")
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .toString();
    }
}

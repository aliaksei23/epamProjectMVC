package com.example.epamprojectmvc.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int CONNECTION_POOL_CAPACITY = 8;
    private static final ReentrantLock lock = new ReentrantLock(true);
    private static final AtomicBoolean create = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private BlockingDeque<ProxyConnection> free = new LinkedBlockingDeque<>(CONNECTION_POOL_CAPACITY);
    private BlockingDeque<ProxyConnection> used = new LinkedBlockingDeque<>(CONNECTION_POOL_CAPACITY);


//    static {
//        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private ConnectionPool() {
        DatabaseProperties databaseProperties = new DatabaseProperties();
        try {
            String driverName = databaseProperties.getDriverName();
            String url = databaseProperties.getUrl();
            String username = databaseProperties.getUsername();
            String password = databaseProperties.getPassword();
            Class.forName(driverName);
            for (int i = 0; i < CONNECTION_POOL_CAPACITY; i++) {
                Connection connection;
                try {
                    connection = DriverManager.getConnection(url, username, password);
                    free.add(new ProxyConnection(connection));
                } catch (SQLException e) {
                    throw new ExceptionInInitializerError(e);
                }
            }
//        String url = "jdbc:mysql://localhost:3306/HotelBooking";
//        Properties prop = new Properties();
//        prop.put("user", "root");
//        prop.put("password", "root");
//        prop.put("autoReconnect", "true");
//        prop.put("characterEncoding", "UTF-8");
//        prop.put("useUnicode", "true");
//        prop.put("useSSL", "true");
//        prop.put("useJDBCCompliantTimezoneShift", "true");
//        prop.put("useLegacyDatetimeCode", "false");
//        prop.put("serverTimezone", "UTC");
//        prop.put("serverSslCert", "classpath:server.crt");
//
//        for (int i = 0; i < CONNECTION_POOL_CAPACITY; i++) {
//            Connection connection;
//            try {
//                connection = DriverManager.getConnection(url, prop);
//            } catch (SQLException e) {
//                throw new ExceptionInInitializerError(e);
//            }
//            free.add(connection);
//        }
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.FATAL, "Error with database: {}", databaseProperties, e);
            throw new RuntimeException("Error with database: " + databaseProperties, e);
        }
    }

    public static ConnectionPool getInstance() {
        if (!create.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    create.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = free.take();
            used.put(connection);
        } catch (InterruptedException e) {
            LOGGER.log(Level.WARN, "Thread was interrupted", e);
//            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection && used.remove(connection)) {
            try {
                free.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            LOGGER.log(Level.ERROR, "Connection is invalid: {}", connection);
        }
//        try {
//            used.remove((ProxyConnection) connection);
//            free.put((ProxyConnection) connection);
//        } catch (InterruptedException e) {
//            LOGGER.log(Level.ERROR, "Connection is invalid: {}", connection);
////            Thread.currentThread().interrupt();
//        }
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Driver was not deregistered", e);
            }
        });
    }

    public void destroyPool() {
        for (int i = 0; i < CONNECTION_POOL_CAPACITY; i++) {
            try {
                free.take().reallyClose();
            } catch (InterruptedException e) {
                LOGGER.log(Level.WARN, "Thread was interrupted", e);
            }
        }
    }
}

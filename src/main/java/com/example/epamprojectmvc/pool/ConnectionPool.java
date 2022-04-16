package com.example.epamprojectmvc.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final int CAPACITY = 8;
    private static ConnectionPool instance;
    private BlockingDeque<Connection> free = new LinkedBlockingDeque<>(CAPACITY);
    private BlockingDeque<Connection> used = new LinkedBlockingDeque<>(CAPACITY);
    private static final ReentrantLock lock = new ReentrantLock(true);
    private static final AtomicBoolean create = new AtomicBoolean(false);

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionPool() {
        String url = "jdbc:mysql://localhost:3306/HotelBooking";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "root");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        prop.put("useSSL", "true");
        prop.put("useJDBCCompliantTimezoneShift", "true");
        prop.put("useLegacyDatetimeCode", "false");
        prop.put("serverTimezone", "UTC");
        prop.put("serverSslCert", "classpath:server.crt");

        for (int i = 0; i < CAPACITY; i++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(url, prop);
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e);
            }
            free.add(connection);
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
        Connection connection = null;
        try {
            connection = free.take();
            used.put(connection);
        } catch (InterruptedException e) {
            // log
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            used.remove(connection);
            free.put(connection);
        } catch (InterruptedException e) {
            // log
            Thread.currentThread().interrupt();
        }
    }
//deregisterDriver
    public void destroyPool(){
        for (int i = 0; i < CAPACITY; i++) {
            try {
                free.take().close();
            } catch (SQLException | InterruptedException e) {
//                e.printStackTrace(); // todo log
            }
        }
    }

}

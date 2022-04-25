package com.example.epammvc.pool;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionBuilder {
    //deregisterDriver
    private static final ReentrantLock lock = new ReentrantLock(true);
    private static final Condition condition = lock.newCondition();
    private static final CountDownLatch initializingLatch = new CountDownLatch(1);
    private static final AtomicBoolean isInstanceInitialized = new AtomicBoolean(false);
    private static final int CAPACITY = 10;
    private static ConnectionBuilder instance;
    private BlockingDeque<Connection> freeConnection = new LinkedBlockingDeque<>(CAPACITY);

    {
        try {
            Properties properties = loadProperties();
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        } catch (ClassNotFoundException exception) {
            //logger
            throw new ExceptionInInitializerError(exception);
        }
    }

    private ConnectionBuilder() {
        Connection connection = null;
        for (int i = 0; i < CAPACITY; i++) {
            try {
                connection = DriverManager.getConnection((String) loadProperties().get("db.url"), loadProperties());
            } catch (SQLException exception) {
                //loger
            }
            freeConnection.add(connection);
        }
    }

    public Connection getFreeConnection() {
        Connection connection = null;
        try {
            lock.lock();
            while (freeConnection.size() == 0) {
                condition.await();
            }
            connection = freeConnection.poll();
        } catch (InterruptedException exception) {
            //logger
        } finally {
            lock.unlock();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        lock.lock();
        try {
            freeConnection.put(connection);
            condition.signalAll();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void destroyPool() {
        try {
            freeConnection.take().close();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static ConnectionBuilder getInstance() {
        if (instance == null) {
            while (isInstanceInitialized.compareAndSet(false, true)) {
                instance = new ConnectionBuilder();
                initializingLatch.countDown();
            }
            try {
                initializingLatch.await();
            } catch (InterruptedException exception) {
                //LOGGER
            }
        }
        return instance;
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("D:\\Java\\Epam\\EpamMvc\\src\\main\\resources\\database.properties"));
        } catch (IOException exception) {
            //logger
        }
        return properties;
    }
}

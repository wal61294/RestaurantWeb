package com.epam.java_training.huzarevich.restaurant.connection_pool;

import com.epam.java_training.huzarevich.restaurant.exceptions.PoolException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public final class DBConnectionPool {

    private static final ResourceBundle resource = ResourceBundle.getBundle("properties.database");
    private static final String url = resource.getString("db.url");
    private static final String user = resource.getString("db.user");
    private static final String password = resource.getString("db.password");
    private static DBConnectionPool instance;
    private static final int size = Integer.parseInt(resource.getString("db.poolsize"));
    private static final Lock lock = new ReentrantLock();

    private BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(10, true);

    public  static DBConnectionPool getInstance() throws SQLException, PoolException {
        lock.lock();
        try {
            if (instance == null) {
                instance = new DBConnectionPool();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }
    

    public DBConnectionPool() throws PoolException {
        try {
            Class.forName(resource.getString("db.driver")).newInstance();
        } catch (Exception e) {
            PoolException pool = new PoolException(e.getMessage());
            pool.setPropertyMessage("error.pool.exception");
            throw pool;
        }
        for (int i = 0; i < size; i++) {
            try {
                pool.add(DriverManager.getConnection(url, user, password));
            } catch (SQLException e) {
                PoolException pool = new PoolException(e.getMessage());
                pool.setPropertyMessage("error.pool.exception");
                throw pool;
            }
        }
    }

    public Connection getConnection() throws PoolException {
        Connection conn = null;
        try {
            conn = pool.take();
        } catch (InterruptedException e) {
            PoolException pool = new PoolException(e.getMessage());
            pool.setPropertyMessage("error.pool.exception");
            throw pool;
        }
        return conn;
    }

    public void closeConnection(Connection conn) throws PoolException {
        if (conn != null) {
            try {
                pool.put(conn);
            } catch (InterruptedException e) {
                PoolException pool = new PoolException(e.getMessage());
                pool.setPropertyMessage("pool.exception");
                throw pool;
            }
        }
    }
}

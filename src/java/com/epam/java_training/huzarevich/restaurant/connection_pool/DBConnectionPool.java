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


 /**
 * Class implements db connection pool .
 * <p>
 * Provides connections to users, quantity of which is limited.
 * </p>
 * 
 * @author huz
 */
public final class DBConnectionPool {

    private static final ResourceBundle resource = ResourceBundle.getBundle("properties.database");
    private static final String url = resource.getString("db.url");
    private static final String user = resource.getString("db.user");
    private static final String password = resource.getString("db.password");
    private static DBConnectionPool instance;
    private static final int size = Integer.parseInt(resource.getString("db.poolsize"));
    private static final Lock lock = new ReentrantLock();

    private BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(10, true);

    /**
     * Realization of Singleton pattern,because there can be only one pool in the system.
     * 
     * @return instance
     * 
     * @throws PoolException
     */
    public static DBConnectionPool getInstance() throws PoolException {
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
    /**
     * Constructor in which JDBC driver gets set up.
     * 
     * @throws PoolException  If there is no JDBC driver or there are problems with the adding. 
     */
    public DBConnectionPool() throws PoolException {
        try {
            Class.forName(resource.getString("db.driver")).newInstance();
        } catch (Exception e) {
            PoolException pool = new PoolException(e.getMessage());
            pool.setPropertyMessage("PoolException");
            throw pool;
        }
        for (int i = 0; i < size; i++) {
            try {
                pool.add(DriverManager.getConnection(url, user, password));
            } catch (SQLException e) {
                PoolException pool = new PoolException(e.getMessage());
                pool.setPropertyMessage("PoolException");
                throw pool;
            }
        }
    }
    /**
     * Returns {@link Connection} object if any is available in the queue.
     * 
     * @return connection
     * 
     * @throws PoolException  If there is a problem with taking a {@link Connection} object from the queue.
     */
    public Connection getConnection() throws PoolException {
        Connection connection = null;
        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            PoolException pool = new PoolException(e.getMessage());
            pool.setPropertyMessage("PoolException");
            throw pool;
        }
        return connection;
    }
    /**
     * Returns {@link Connection} object in the queue, making it available for use.
     * 
     * @param connection  the returning {@link Connection} object
     * 
     * @throws PoolException  If there is a problem with putting a {@link Connection} object to the queue.
     */
    public void closeConnection(Connection connection) throws PoolException {
        if (connection != null) {
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                PoolException pool = new PoolException(e.getMessage());
                pool.setPropertyMessage("PoolException");
                throw pool;
            }
        }
    }
}

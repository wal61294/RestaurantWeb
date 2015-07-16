package com.epam.java_training.huzarevich.restaurant.dao;

import com.epam.java_training.huzarevich.restaurant.connection_pool.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.java_training.huzarevich.restaurant.entity.Entity;
import com.epam.java_training.huzarevich.restaurant.entity.User;
import com.epam.java_training.huzarevich.restaurant.exceptions.PoolException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;


public class UserDAO implements IDAO {

    private Logger logger = Logger.getLogger(this.getClass());
    private static ResourceBundle resource = ResourceBundle.getBundle("properties.database_queries");

    @Override
    public List<Entity> getAll() {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        String sql = resource.getString("user.getAll");
        PreparedStatement stm;
        List<Entity> list = null;
        try {
            stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            list = new ArrayList<Entity>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));

                list.add(user);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        try {
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        return list;
    }

    @Override
    public Integer create(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        User user = (User) instance;
        int i = -1;
        String query = resource.getString("user.create");
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, user.getId());
            preparedStmt.setString(2, user.getLogin());
            preparedStmt.setString(3, user.getPassword());

            i = preparedStmt.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        try {
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }

        return i;

    }

    @Override
    public Entity read(int key) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        String sql = resource.getString("user.read");
        PreparedStatement stm;
        User user = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            user = new User();
            user.setId(rs.getInt("user_id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        return user;

    }

    @Override
    public void update(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        User user = (User) instance;
        int x = -1;
        String query = resource.getString("user.update");
        try {
            PreparedStatement stmt = connection
                    .prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getPassword());

            stmt.setInt(4, user.getId());

            x = stmt.executeUpdate();
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }

    }

    @Override
    public void delete(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        User user = (User) instance;
        String query = resource.getString("user.delete");
        try {
            PreparedStatement stmt = connection
                    .prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.execute();
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }

    }

    @Override
    public Entity find(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        ResultSet rs = null;
        PreparedStatement statement = null;
        User user = (User) instance;
        User u = null;
        String query = resource.getString("user.find");
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());

            rs = statement.executeQuery();

            if (rs.first()) {
                u = new User();
                u.setId(rs.getInt("user_id"));
                u.setLogin(rs.getString("login"));
                u.setPassword(rs.getString("password"));
                u.setActivated(true);
                DBConnectionPool.getInstance().closeConnection(connection);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }

        return u;
    }

}

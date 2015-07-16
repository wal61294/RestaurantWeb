package com.epam.java_training.huzarevich.restaurant.dao;

import com.epam.java_training.huzarevich.restaurant.connection_pool.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.java_training.huzarevich.restaurant.entity.Administrator;
import com.epam.java_training.huzarevich.restaurant.entity.Entity;
import com.epam.java_training.huzarevich.restaurant.entity.User;
import com.epam.java_training.huzarevich.restaurant.exceptions.PoolException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class AdministratorDAO implements IDAO {

    private Logger logger = Logger.getLogger(this.getClass());
    private static ResourceBundle resource = ResourceBundle.getBundle("properties.database_queries");

    @Override
    public Entity read(int key) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        String sql = resource.getString("admin.read");
        PreparedStatement stm;
        Administrator administrator = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            administrator = new Administrator();
            administrator.setId(rs.getInt("admin_id"));
            administrator.setUserId(rs.getInt("user_user_id"));
            administrator.setName(rs.getString("admin_name"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        try {
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        return administrator;

    }

    @Override
    public List<Entity> getAll() {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();

        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        String sql = resource.getString("admin.getAll");
        PreparedStatement stm;
        List<Entity> list = null;
        try {
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                Administrator administrator = new Administrator();
                administrator.setId(rs.getInt("admin_id"));
                administrator.setUserId(rs.getInt("user_user_id"));
                administrator.setName(rs.getString("admin_name"));
                list.add(administrator);
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

        Administrator administrator = (Administrator) instance;
        int i = -1;
        String query = resource.getString("admin.create");
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, administrator.getId());
            preparedStmt.setInt(2, administrator.getUserId());
            preparedStmt.setString(3, administrator.getName());
            i = preparedStmt.executeUpdate();
            connection.close();
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
    public void update(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        String query = resource.getString("admin.update");
        Administrator administrator = (Administrator) instance;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, administrator.getId());
            stmt.setInt(2, administrator.getUserId());
            stmt.setString(3, administrator.getName());
            stmt.setInt(4, administrator.getId());
            stmt.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        try {
            DBConnectionPool.getInstance().closeConnection(connection);
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
            logger.error(ex.getMessage());;
        }
        String query = resource.getString("admin.delete");
        Administrator administrator = (Administrator) instance;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, administrator.getId());
            stmt.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        try {
            DBConnectionPool.getInstance().closeConnection(connection);
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
        String query = resource.getString("admin.find");
        Administrator admin = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());
            rs = statement.executeQuery();
            if (rs.first()) {
                admin = new Administrator();
                admin.setId(rs.getInt("admin_id"));
                admin.setName(rs.getString("admin_name"));
                admin.setUserId(rs.getInt("user_user_id"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        try {
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }

        return admin;
    }

}

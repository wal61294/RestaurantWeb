package com.epam.java_training.huzarevich.restaurant.dao;

import com.epam.java_training.huzarevich.restaurant.connection_pool.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.java_training.huzarevich.restaurant.entity.Entity;
import com.epam.java_training.huzarevich.restaurant.entity.OrderStatus;
import com.epam.java_training.huzarevich.restaurant.exceptions.PoolException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class OrderTypeDAO implements IDAO {

    private static ResourceBundle resource = ResourceBundle.getBundle("properties.database_queries");
    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public Integer create(Entity instance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Entity read(int key) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        String sql = resource.getString("type.read");
        PreparedStatement stm;
        OrderStatus status = null;
        try {
            stm = connection.prepareStatement(sql);

            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            status = new OrderStatus();
            status.setId(rs.getInt("id_order_status"));
            status.setStatus(rs.getString("status"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        try {
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        return status;

    }

    @Override
    public void update(Entity instance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Entity instance) {
        String sql = resource.getString("type.delete");
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
            OrderStatus status = (OrderStatus) instance;
            PreparedStatement stmt = connection
                    .prepareStatement(sql);
            stmt.setInt(1, status.getId());
            stmt.execute();
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public List<Entity> getAll() {
        throw new UnsupportedOperationException();

    }

    @Override
    public Entity find(Entity instance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

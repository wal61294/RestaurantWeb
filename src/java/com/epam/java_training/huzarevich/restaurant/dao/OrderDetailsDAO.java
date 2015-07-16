package com.epam.java_training.huzarevich.restaurant.dao;

import com.epam.java_training.huzarevich.restaurant.connection_pool.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.java_training.huzarevich.restaurant.entity.Entity;
import com.epam.java_training.huzarevich.restaurant.entity.OrderDetails;
import com.epam.java_training.huzarevich.restaurant.exceptions.PoolException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;


public class OrderDetailsDAO implements IDAO {
      private static ResourceBundle resource = ResourceBundle.getBundle("properties.database_queries");
      private Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
    @Override
     public Integer create(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        OrderDetails details = (OrderDetails) instance;
        int i=-1;
        String query = resource.getString("details.create");
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, details.getId());
            preparedStmt.setInt(2, details.getFoodId());
            preparedStmt.setInt(3, details.getOrderId());
             i= preparedStmt.executeUpdate();
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage());
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
        String sql = resource.getString("details.read");
        PreparedStatement stm;
        OrderDetails details = null;
        try {
            stm = connection.prepareStatement(sql);

            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            details = new OrderDetails();
            details.setId(rs.getInt("idorder_details"));
            details.setFoodId(rs.getInt("food_food_id"));
            details.setOrderId(rs.getInt("order_order_id"));

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        try {
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        return details;
    }

    @Override
    public void update(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        String query=resource.getString("details.update");
        OrderDetails details = (OrderDetails) instance;
        try {
            PreparedStatement stmt = connection
                    .prepareStatement(query);
            stmt.setInt(1, details.getId());
            stmt.setInt(2, details.getFoodId());
            stmt.setInt(3, details.getOrderId());
            stmt.setInt(4, details.getId());
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
            logger.error(ex.getMessage());
        }
        String query=resource.getString("details.delete");
        OrderDetails details = (OrderDetails) instance;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, details.getId());
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
    public List<Entity> getAll() {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        String sql = resource.getString("details.getAll");
        PreparedStatement stm;
        List<Entity> list = null;
        try {
            stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            list = new ArrayList<Entity>();
            while (rs.next()) {
                OrderDetails details = new OrderDetails();
                details.setId(rs.getInt("idorder_details"));
                details.setFoodId(rs.getInt("food_food_id"));
                details.setOrderId(rs.getInt("order_order_id"));
                list.add(details);
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
    public Entity find(Entity instance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   

}

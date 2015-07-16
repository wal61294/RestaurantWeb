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
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderTypeDAO implements IDAO {
     private static ResourceBundle resource = ResourceBundle.getBundle("properties.database_queries");
    @Override
    public Integer create(Entity instance) {
          throw new UnsupportedOperationException();
    }

    @Override
    public Entity read(int key) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(OrderTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(OrderTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            e.printStackTrace();
        }
        return status;

    }

    @Override
    public void update(Entity instance) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Entity instance) {
        try {
            Connection connection;
            
            connection = DBConnectionPool.getInstance().getConnection();
            
            OrderStatus status = (OrderStatus) instance;
            
            PreparedStatement stmt = connection
                    .prepareStatement("delete from restaurantDB.order_status WHERE id_order_status = ?");
            stmt.setInt(1, status.getId());
            stmt.execute();
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(OrderTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(OrderTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
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

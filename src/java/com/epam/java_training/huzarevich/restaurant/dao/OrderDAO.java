package com.epam.java_training.huzarevich.restaurant.dao;

import com.epam.java_training.huzarevich.restaurant.connection_pool.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.java_training.huzarevich.restaurant.entity.Entity;
import com.epam.java_training.huzarevich.restaurant.entity.Order;
import com.epam.java_training.huzarevich.restaurant.exceptions.PoolException;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO implements IDAO {
    private static ResourceBundle resource = ResourceBundle.getBundle("properties.database_queries");
    @Override
    public Entity read(int key) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT * FROM restaurantDB.order WHERE order_id = ?;";
        PreparedStatement stm;
        Order order = null;
        try {
            stm = connection.prepareStatement(sql);

            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            order = new Order();
            order.setClientId(rs.getInt("client_id_client"));
            order.setCost(rs.getInt("cost"));
            order.setOrderStatus(rs.getInt("order_status_id_order_status"));
            order.setId(rs.getInt("order_id"));
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
        } catch (PoolException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return order;

    }

    @Override
    public List<Entity> getAll() {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT * FROM restaurantDB.order;";
        PreparedStatement stm;
        List<Entity> list = null;
        try {
            stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            list = new ArrayList<Entity>();
            while (rs.next()) {
                Order order = new Order();
                order.setClientId(rs.getInt("client_id_client"));
                order.setCost(rs.getInt("cost"));
                order.setOrderStatus(rs.getInt("order_status_id_order_status"));
                order.setId(rs.getInt("order_id"));
                list.add(order);
            }
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PoolException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Integer create(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Order order = (Order) instance;
        int i = -1;
        String query = " insert into restaurantDB.order (order_id, cost, client_id_client,"
                + " order_status_id_order_status)"
                + " values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, order.getId());
            preparedStmt.setInt(2, order.getCost());
            preparedStmt.setInt(3, order.getClientId());
            preparedStmt.setInt(4, order.getOrderStatus());
            i = preparedStmt.executeUpdate();
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PoolException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public void update(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Order order = (Order) instance;
        try {
            PreparedStatement stmt = connection
                    .prepareStatement("update restaurantdb.order set"
                            + " cost = ?,  client_id_client = ?,"
                            + "  order_status_id_order_status = ? WHERE order_id = ?");

            stmt.setInt(1, order.getCost());
            stmt.setInt(2, order.getClientId());
            stmt.setInt(3, order.getOrderStatus());
            stmt.setInt(4, order.getId());
            stmt.execute();
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PoolException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Order order = (Order) instance;
        try {
            PreparedStatement stmt = connection
                    .prepareStatement("delete from restaurantDB.order WHERE order_id = ?");
            stmt.setInt(1, order.getId());
            stmt.execute();
             DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PoolException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Entity find(Entity instance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param clientId
     * @return
     */
    public Entity findLastOrder(int clientId) {
        List<Order> orders = new ArrayList<>();
        for (Entity i : getAll()) {
            Order order = (Order) i;
            orders.add(order);
        }
        LinkedList<Order> neededOrders = new LinkedList<>();
        for (Order o : orders) {
            if (o.getClientId() == clientId) {
                neededOrders.add(o);
            }
        }
        return neededOrders.getLast();
    }

    public List<Entity> getClientOrders(int clientId) {
        List<Entity> orders = new ArrayList<>();

        for (Entity i : getAll()) {
            Order order = (Order) i;
            if (order.getClientId() == clientId) {
                orders.add(order);
            }
        }
        return orders;
    }

}

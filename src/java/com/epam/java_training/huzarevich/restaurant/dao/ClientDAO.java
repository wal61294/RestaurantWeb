package com.epam.java_training.huzarevich.restaurant.dao;

import com.epam.java_training.huzarevich.restaurant.connection_pool.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.java_training.huzarevich.restaurant.entity.Client;
import com.epam.java_training.huzarevich.restaurant.entity.Entity;
import com.epam.java_training.huzarevich.restaurant.entity.User;
import com.epam.java_training.huzarevich.restaurant.exceptions.PoolException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDAO implements IDAO {
    private static ResourceBundle resource = ResourceBundle.getBundle("properties.database_queries");
    @Override
    public Entity read(int key) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = resource.getString("client.read");
        PreparedStatement stm;
        Client client = null;
        try {
            stm = connection.prepareStatement(sql);

            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            client = new Client();
            client.setId(rs.getInt("id_client"));
            client.setUserId(rs.getInt("user_user_id"));
            client.setName(rs.getString("client_name"));
            client.setMoney(rs.getInt("money"));
            try {
                DBConnectionPool.getInstance().closeConnection(connection);
            } catch (PoolException ex) {
                Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;

    }

    public List<Entity> getAll() {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = resource.getString("client.getAll");
        List<Entity> list = null;
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            list = new ArrayList<Entity>();
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id_client"));
                client.setUserId(rs.getInt("user_user_id"));
                client.setName(rs.getString("client_name"));
                client.setMoney(rs.getInt("money"));
                list.add(client);
            }
             DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PoolException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
     public Integer create(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Client client = (Client) instance;
        int i=-1;
        String query = resource.getString("client.create");
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, client.getId());
            preparedStmt.setInt(2, client.getUserId());
            preparedStmt.setString(3, client.getName());
            preparedStmt.setInt(4, client.getMoney());
             i= preparedStmt.executeUpdate();
             DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {

            e.printStackTrace();
        } catch (PoolException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public void update(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Client client = (Client) instance;
        String query=resource.getString("client.update");
        try {
            
            PreparedStatement stmt = connection
                    .prepareStatement(query);
            
            stmt.setInt(1, client.getUserId());
            stmt.setString(2, client.getName());
            stmt.setInt(3, client.getMoney());
            stmt.setInt(4, client.getId());
            stmt.execute();
             DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PoolException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Client client = (Client) instance;
        String query=resource.getString("client.delete");
        try {
            PreparedStatement stmt = connection
                    .prepareStatement(query);
            stmt.setInt(1, client.getId());
            stmt.execute();
             DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PoolException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Entity find(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    

        ResultSet rs = null;
        PreparedStatement statement = null;
        User user = (User) instance;
        Client client=null;
        String query=resource.getString("client.find");
        try {

            statement = connection
                    .prepareStatement(query);
            statement.setInt(1, user.getId());
           

            rs = statement.executeQuery();

            if (rs.first()) {
                client=new Client();
                // Retrieve information from the result set.
                client.setId(rs.getInt("id_client"));
                client.setUserId(rs.getInt("user_user_id"));
                client.setName(rs.getString("client_name"));
                client.setMoney(rs.getInt("money"));
 
            }
             DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
        } catch (PoolException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return client;
    }

    

}

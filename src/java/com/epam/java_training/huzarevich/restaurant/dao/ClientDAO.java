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
import org.apache.log4j.Logger;

public class ClientDAO implements IDAO {

    private static ResourceBundle resource = ResourceBundle.getBundle("properties.database_queries");
    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public Entity read(int key) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
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

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        try {
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        return client;

    }

    public List<Entity> getAll() {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
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

        } catch (SQLException e) {
            logger.error(e.getMessage());;
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
        Client client = (Client) instance;
        int i = -1;
        String query = resource.getString("client.create");
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, client.getId());
            preparedStmt.setInt(2, client.getUserId());
            preparedStmt.setString(3, client.getName());
            preparedStmt.setInt(4, client.getMoney());
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
    public void update(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }
        Client client = (Client) instance;
        String query = resource.getString("client.update");
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, client.getUserId());
            stmt.setString(2, client.getName());
            stmt.setInt(3, client.getMoney());
            stmt.setInt(4, client.getId());
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
        Client client = (Client) instance;
        String query = resource.getString("client.delete");
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, client.getId());
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
        Client client = null;
        String query = resource.getString("client.find");
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());
            rs = statement.executeQuery();
            if (rs.first()) {
                client = new Client();
                client.setId(rs.getInt("id_client"));
                client.setUserId(rs.getInt("user_user_id"));
                client.setName(rs.getString("client_name"));
                client.setMoney(rs.getInt("money"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        try {
            DBConnectionPool.getInstance().closeConnection(connection);
        } catch (PoolException ex) {
            logger.error(ex.getMessage());
        }

        return client;
    }

}

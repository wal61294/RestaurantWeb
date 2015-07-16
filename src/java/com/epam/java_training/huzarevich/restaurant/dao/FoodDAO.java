package com.epam.java_training.huzarevich.restaurant.dao;

import com.epam.java_training.huzarevich.restaurant.connection_pool.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.java_training.huzarevich.restaurant.entity.Food;
import com.epam.java_training.huzarevich.restaurant.entity.Entity;
import com.epam.java_training.huzarevich.restaurant.exceptions.PoolException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FoodDAO implements IDAO {
     private static ResourceBundle resource = ResourceBundle.getBundle("properties.database_queries");
    @Override
     public Integer create(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Food food = (Food) instance;
        int i=-1;
        String query = resource.getString("food.create");
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setInt(1, food.getFoodId());
            preparedStmt.setString(2, food.getFoodName());
            preparedStmt.setInt(3, food.getPrice());
             i= preparedStmt.executeUpdate();

             DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {

            e.printStackTrace();
        } catch (PoolException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;

    }

    @Override
    public Entity read(int key) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = resource.getString("food.read");
        Food food = null;
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            food = new Food();
            food.setFoodId(rs.getInt("food_id"));
            food.setFoodName(rs.getString("food_name"));
            food.setPrice(rs.getInt("cost"));
            try {
                DBConnectionPool.getInstance().closeConnection(connection);
            } catch (PoolException ex) {
                Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return food;

    }

    @Override
    public void update(Entity instance) {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Food food = (Food) instance;
        String query=resource.getString("food.update");
        try {
            PreparedStatement stmt = connection
                    .prepareStatement(query);
           
            stmt.setString(1, food.getFoodName());
            stmt.setInt(2, food.getPrice());
            stmt.setInt(3, food.getFoodId());
            stmt.execute();
            stmt.close();
             DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PoolException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Entity instance) {
       throw new UnsupportedOperationException();
    }

    @Override
    public List<Entity> getAll() {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PoolException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = resource.getString("food.getAll");;
        PreparedStatement stm;
        List<Entity> list = null;
        try {
            stm = connection.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            list = new ArrayList<Entity>();
            while (rs.next()) {
                Food food = new Food();
                food.setFoodId(rs.getInt("food_id"));
                food.setFoodName(rs.getString("food_name"));
                food.setPrice(rs.getInt("cost"));
                list.add(food);
            }
             DBConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {

            e.printStackTrace();
        } catch (PoolException ex) {
            Logger.getLogger(FoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Entity find(Entity instance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

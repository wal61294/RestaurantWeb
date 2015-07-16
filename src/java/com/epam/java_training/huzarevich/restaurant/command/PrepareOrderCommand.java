/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.java_training.huzarevich.restaurant.command;

import com.epam.java_training.huzarevich.restaurant.dao.DAOFactory;
import com.epam.java_training.huzarevich.restaurant.dao.DAOs;
import com.epam.java_training.huzarevich.restaurant.entity.Client;
import com.epam.java_training.huzarevich.restaurant.entity.Food;
import com.epam.java_training.huzarevich.restaurant.entity.Entity;
import com.epam.java_training.huzarevich.restaurant.entity.Order;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *    Prepares info for creating new order
 * </p>
 *
 * @author huz
 */
public class PrepareOrderCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        List<Food> foods = new ArrayList<>();
        for (Entity instance : DAOFactory.getDAO(DAOs.FOOD).getAll()) {
            Food food = (Food) instance;
            foods.add(food);
        }
        Client client = (Client) DAOFactory.getDAO(DAOs.CLIENT).find((Entity) request.getSession().getAttribute("user"));
        Order order = new Order();
        order.setClientId(client.getId());
        request.getSession().setAttribute("clientId", client.getId());
        DAOFactory.getDAO(DAOs.ORDER).create(order);
        request.setAttribute("foods", foods);
    }

}

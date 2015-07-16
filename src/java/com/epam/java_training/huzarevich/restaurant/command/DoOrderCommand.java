/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.java_training.huzarevich.restaurant.command;

import com.epam.java_training.huzarevich.restaurant.dao.DAOFactory;
import com.epam.java_training.huzarevich.restaurant.dao.DAOs;
import com.epam.java_training.huzarevich.restaurant.dao.FoodDAO;
import com.epam.java_training.huzarevich.restaurant.dao.IDAO;
import com.epam.java_training.huzarevich.restaurant.dao.OrderDAO;
import com.epam.java_training.huzarevich.restaurant.dao.OrderDetailsDAO;
import com.epam.java_training.huzarevich.restaurant.dao.OrderTypeDAO;
import com.epam.java_training.huzarevich.restaurant.entity.Food;
import com.epam.java_training.huzarevich.restaurant.entity.Order;
import com.epam.java_training.huzarevich.restaurant.entity.OrderDetails;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huz
 */
public class DoOrderCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String[] chosenFood = request.getParameterValues("chooseFood");
        List<Integer> foodIds = new ArrayList<>();
              for(String string: chosenFood){
                  int i=Integer.parseInt(string);
                  foodIds.add(i); 
              }
        
        int foolPrice = 0;
        OrderDAO orderDao = (OrderDAO) DAOFactory.getDAO(DAOs.ORDER);

        int clientId = (int) request.getSession().getAttribute("clientId");
        Order order = (Order) orderDao.findLastOrder(clientId);

        int orderId = order.getId();

        for (int j : foodIds) {
            Food food = (Food) DAOFactory.getDAO(DAOs.FOOD).read(j);
            foolPrice += food.getPrice();
        }
      IDAO dao=  DAOFactory.getDAO(DAOs.ORDER_DETAILS);
        for (int k : foodIds) {
            OrderDetails details = new OrderDetails();
            details.setFoodId(k);
            details.setOrderId(orderId);
            dao.create(details);
        }
        order.setCost(foolPrice);
        orderDao.update(order);
        CommandFactory.getInstance().getCommand(Commands.VIEW_CLIENT_ORDERS).execute(request, response);
    }

}

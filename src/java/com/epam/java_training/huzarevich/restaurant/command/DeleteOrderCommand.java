/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.java_training.huzarevich.restaurant.command;

import com.epam.java_training.huzarevich.restaurant.dao.DAOFactory;
import com.epam.java_training.huzarevich.restaurant.dao.DAOs;
import com.epam.java_training.huzarevich.restaurant.entity.Entity;
import com.epam.java_training.huzarevich.restaurant.entity.Order;
import com.epam.java_training.huzarevich.restaurant.entity.OrderDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huz
 */
public class DeleteOrderCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = new Order();
        order.setId(orderId);
        for (Entity i : DAOFactory.getDAO(DAOs.ORDER_DETAILS).getAll()) {

            OrderDetails details = (OrderDetails) i;
            if (details.getOrderId() == orderId) {
                DAOFactory.getDAO(DAOs.ORDER_DETAILS).delete(details);
            }
        }
        DAOFactory.getDAO(DAOs.ORDER).delete(order);
        CommandFactory.getInstance().getCommand(Commands.VIEW_CLIENT_ORDERS).execute(request, response);
    }

}

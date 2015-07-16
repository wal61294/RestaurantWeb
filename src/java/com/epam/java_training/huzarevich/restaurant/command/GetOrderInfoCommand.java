package com.epam.java_training.huzarevich.restaurant.command;

import com.epam.java_training.huzarevich.restaurant.dao.DAOFactory;
import com.epam.java_training.huzarevich.restaurant.dao.DAOs;
import com.epam.java_training.huzarevich.restaurant.entity.Food;
import com.epam.java_training.huzarevich.restaurant.entity.Entity;
import com.epam.java_training.huzarevich.restaurant.entity.OrderDetails;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 **<p>
 * Gets order inform to client
 * </p>
 *
 * @author huz
 */
public class GetOrderInfoCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        List<OrderDetails> thisOrderDetails = new ArrayList<>();
        for (Entity i : DAOFactory.getDAO(DAOs.ORDER_DETAILS).getAll()) {
            OrderDetails details = (OrderDetails) i;
            if (details.getOrderId() == orderId) {
                thisOrderDetails.add(details);
            }
        }
        List<Food> foods = new ArrayList<>();
        for (OrderDetails od : thisOrderDetails) {
            Food food = (Food) DAOFactory.getDAO(DAOs.FOOD).read(od.getFoodId());
            foods.add(food);
        }
        request.setAttribute("foods", foods);

    }

}

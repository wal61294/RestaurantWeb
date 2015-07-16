package com.epam.java_training.huzarevich.restaurant.command;

import com.epam.java_training.huzarevich.restaurant.dao.DAOFactory;
import com.epam.java_training.huzarevich.restaurant.dao.DAOs;
import com.epam.java_training.huzarevich.restaurant.dao.OrderDAO;
import com.epam.java_training.huzarevich.restaurant.entity.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * This command allows Client to cancel his order from create-order page
 * </p>
 *
 * @author huz
 */
public class CancelOrderCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        OrderDAO orderDao = (OrderDAO) DAOFactory.getDAO(DAOs.ORDER);
        int clientId = (int) request.getSession().getAttribute("clientId");
        Order order = (Order) orderDao.findLastOrder(clientId);
        orderDao.delete(order);
        CommandFactory.getInstance().getCommand(Commands.VIEW_CLIENT_ORDERS).execute(request, response);
    }

}

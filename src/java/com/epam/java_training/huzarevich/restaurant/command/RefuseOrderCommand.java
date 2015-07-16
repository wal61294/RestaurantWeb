
package com.epam.java_training.huzarevich.restaurant.command;

import com.epam.java_training.huzarevich.restaurant.dao.DAOFactory;
import com.epam.java_training.huzarevich.restaurant.dao.DAOs;
import com.epam.java_training.huzarevich.restaurant.entity.Entity;
import com.epam.java_training.huzarevich.restaurant.entity.Order;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *This command gives Administrator abitity to refuse orders made by Client and sets lists with orders to jsp page
 * </p>
 * @author huz
 */
public class RefuseOrderCommand extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
          int orderId=Integer.parseInt(request.getParameter("orderId"));
        Order order= (Order) DAOFactory.getDAO(DAOs.ORDER).read(orderId);
        order.setOrderStatus(3);
        DAOFactory.getDAO(DAOs.ORDER).update(order);
         List <Order> orders=new ArrayList<>();
             for(Entity i:DAOFactory.getDAO(DAOs.ORDER).getAll()){
                 Order or =(Order)i;
                 orders.add(or);
             }
             List <Order> notWatched=new ArrayList<>();
             List <Order> refused=new ArrayList<>();
             List <Order> accepted=new ArrayList<>();
             for(Order o:orders){
               if (o.getOrderStatus()==1){
                   notWatched.add(o);
               }else if(o.getOrderStatus()==2){
                   accepted.add(o);
               }else if(o.getOrderStatus()== 3){
                   refused.add(o);
               }
             }
             request.setAttribute("aOrders", accepted);
             request.setAttribute("rOrders",refused);
             request.setAttribute("uOrders", notWatched);
    }
    }
   

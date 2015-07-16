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
import com.epam.java_training.huzarevich.restaurant.entity.OrderStatus;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huz
 */
public class PrepareOrderOrganisationCommand extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        List <Order> orders=new ArrayList<>();
             for(Entity i:DAOFactory.getDAO(DAOs.ORDER).getAll()){
                 Order order =(Order)i;
                 orders.add(order);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.java_training.huzarevich.restaurant.command;

import com.epam.java_training.huzarevich.restaurant.dao.DAOFactory;
import com.epam.java_training.huzarevich.restaurant.dao.DAOs;
import com.epam.java_training.huzarevich.restaurant.dao.IDAO;
import com.epam.java_training.huzarevich.restaurant.entity.Food;
import com.epam.java_training.huzarevich.restaurant.entity.Entity;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huz
 */
public class UpdateFoodCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
       
        int foodId =(int) request.getSession().getAttribute("actionId");
        IDAO dao = DAOFactory.getDAO(DAOs.FOOD);
        Food food = (Food) dao.read(foodId);
        food.setFoodName(request.getParameter("Name"));
        int cost = Integer.parseInt(request.getParameter("cost"));
        
        food.setPrice(cost);
        dao.update(food);
        List<Food> foods = new ArrayList<>();
        for (Entity instance : dao.getAll()) {
            Food f = (Food) instance;
            foods.add(f);
        }
        request.setAttribute("foods", foods);
    }

}

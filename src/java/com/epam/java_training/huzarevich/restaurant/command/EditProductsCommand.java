/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.java_training.huzarevich.restaurant.command;

import com.epam.java_training.huzarevich.restaurant.dao.DAOFactory;
import com.epam.java_training.huzarevich.restaurant.dao.DAOs;
import com.epam.java_training.huzarevich.restaurant.entity.Food;
import com.epam.java_training.huzarevich.restaurant.entity.Entity;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huz
 */
class EditProductsCommand extends Command {

    public EditProductsCommand() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        List<Food>foods=new ArrayList<>();
                for(Entity instance :DAOFactory.getDAO(DAOs.FOOD).getAll()){
                    Food food=(Food)instance;
                    foods.add(food);
                }
                
                request.setAttribute("foods", foods);
    }

}

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
 * <p>
 * This command allows Administrator to add food to db and sets food list to jsp
 * </p>
 */
public class AddFoodCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("Name");
        int cost = Integer.parseInt(request.getParameter("cost"));
        Food food = new Food();
        food.setFoodName(name);
        food.setPrice(cost);
        DAOFactory.getDAO(DAOs.FOOD).create(food);
        List<Food> foods = new ArrayList<>();
        for (Entity instance : DAOFactory.getDAO(DAOs.FOOD).getAll()) {
            Food f = (Food) instance;
            foods.add(f);
        }

        request.setAttribute("foods", foods);
    }

}

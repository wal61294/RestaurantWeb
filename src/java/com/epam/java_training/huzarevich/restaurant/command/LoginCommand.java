/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.java_training.huzarevich.restaurant.command;

import com.epam.java_training.huzarevich.restaurant.dao.DAOFactory;
import com.epam.java_training.huzarevich.restaurant.dao.DAOs;
import com.epam.java_training.huzarevich.restaurant.dao.UserDAO;
import com.epam.java_training.huzarevich.restaurant.entity.Client;
import com.epam.java_training.huzarevich.restaurant.entity.Food;
import com.epam.java_training.huzarevich.restaurant.entity.Entity;
import com.epam.java_training.huzarevich.restaurant.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *Login command
 * *<p>
 *     command find user in dband if user exists sets notActivated attribute false
 * </p>
 * @author huz
 */
public class LoginCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();

        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));

        UserDAO dao = (UserDAO) DAOFactory.getDAO(DAOs.USER);

        User dbUser = (User) dao.find(user);

        if (dbUser != null) {
            HttpSession session = request.getSession(true);
            if (DAOFactory.getDAO(DAOs.CLIENT).find(dbUser) != null) {
                session.setAttribute("typeOfUser", DAOs.CLIENT);
                List<Food> foods = new ArrayList<>();
                for (Entity instance : DAOFactory.getDAO(DAOs.FOOD).getAll()) {
                    Food food = (Food) instance;
                    foods.add(food);
                }
               Client client= (Client)DAOFactory.getDAO(DAOs.CLIENT).find(dbUser);
                request.setAttribute("money", client.getMoney());
                request.setAttribute("foods", foods);
            } else {
                session.setAttribute("typeOfUser", DAOs.ADMIN);
                List<Client> clients = new ArrayList<>();
                for (Entity instance : DAOFactory.getDAO(DAOs.CLIENT).getAll()) {
                    Client client = (Client) instance;
                    clients.add(client);
                }
                
                request.setAttribute("clients", clients);
            }
            if (dbUser.isActivated()) {

                session.setAttribute("user", dbUser);
            } else {
                request.setAttribute("notActivated", true);
            }
        } else {
            request.setAttribute("notExists", true);
        }
        
    }

}

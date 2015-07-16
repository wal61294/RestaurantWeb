/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.java_training.huzarevich.restaurant.command;

import com.epam.java_training.huzarevich.restaurant.dao.DAOFactory;
import com.epam.java_training.huzarevich.restaurant.dao.DAOs;
import com.epam.java_training.huzarevich.restaurant.dao.IDAO;
import com.epam.java_training.huzarevich.restaurant.entity.Client;
import com.epam.java_training.huzarevich.restaurant.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 **<p>
 * Creates new Client profile
 * </p>
 *
 * @author huz
 */
public class CreateClientCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Client client = new Client();
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("creatingUser");

        User dbUser = (User) DAOFactory.getDAO(DAOs.USER).find(user);
        IDAO adminDAO = DAOFactory.getDAO(DAOs.CLIENT);
        client.setName(request.getParameter("name"));
        int money = Integer.parseInt(request.getParameter("money"));
        client.setMoney(money);
        client.setUserId(dbUser.getId());
        int result = adminDAO.create(client);
        if (result == -1) {
            request.setAttribute("clientNotCreated", true);

        }
    }
}

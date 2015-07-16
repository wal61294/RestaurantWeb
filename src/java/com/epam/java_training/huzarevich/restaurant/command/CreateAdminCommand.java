/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.java_training.huzarevich.restaurant.command;

import com.epam.java_training.huzarevich.restaurant.dao.DAOFactory;
import com.epam.java_training.huzarevich.restaurant.dao.DAOs;
import com.epam.java_training.huzarevich.restaurant.dao.IDAO;
import com.epam.java_training.huzarevich.restaurant.entity.Administrator;
import com.epam.java_training.huzarevich.restaurant.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huz
 */
public class CreateAdminCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Administrator admin = new Administrator();
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("creatingUser");
        User dbUser = (User) DAOFactory.getDAO(DAOs.USER).find(user);
        IDAO adminDAO = DAOFactory.getDAO(DAOs.ADMIN);
        admin.setName(request.getParameter("name"));
        admin.setUserId(dbUser.getId());
        int result = adminDAO.create(admin);
        if (result == -1) {
            request.setAttribute("adminNotCreated", true);

        }
    }

}

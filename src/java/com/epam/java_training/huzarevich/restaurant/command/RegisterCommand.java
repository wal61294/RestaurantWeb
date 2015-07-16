/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.java_training.huzarevich.restaurant.command;

import com.epam.java_training.huzarevich.restaurant.dao.DAOFactory;
import com.epam.java_training.huzarevich.restaurant.dao.DAOs;
import com.epam.java_training.huzarevich.restaurant.dao.IDAO;
import com.epam.java_training.huzarevich.restaurant.dao.UserDAO;
import com.epam.java_training.huzarevich.restaurant.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huz
 */
public class RegisterCommand extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
                
                User user = new User();
		
		user.setLogin(request.getParameter("login"));
		user.setPassword(request.getParameter("password"));
		String choice = request.getParameter("rdoType");
		HttpSession session=request.getSession(true);
                session.setAttribute("choice", choice);
	
		IDAO userDAO = DAOFactory.getDAO(DAOs.USER);
		
		int result = userDAO.create(user);
		session.setAttribute("creatingUser", user);
		if (result == -1) {
			
			request.setAttribute("userNotCreated", true);
		
		
    }
              
    }
    
}

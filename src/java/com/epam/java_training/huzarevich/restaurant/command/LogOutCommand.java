/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.java_training.huzarevich.restaurant.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 **<p>
 * Logout command for admin&client sets false to isActicated attribute
 * </p>
 * @author huz
 */
public class LogOutCommand extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");
    }
    
}

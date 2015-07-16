/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.java_training.huzarevich.restaurant.controller;

import com.epam.java_training.huzarevich.restaurant.command.Command;
import com.epam.java_training.huzarevich.restaurant.command.CommandFactory;
import com.epam.java_training.huzarevich.restaurant.command.Commands;
import com.epam.java_training.huzarevich.restaurant.dao.DAOs;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huz
 */
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void destroy() {
        // delete all resources

        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userPath = request.getServletPath();
        String userAction = request.getParameter("action");
        String url = "/pages/";

        CommandFactory commands = CommandFactory.getInstance();
        Command command = null;

        if (userPath.equals("/login")) {
            url += "login.jsp";
        } else if (userPath.equals("/registration")) {
            url += "register.jsp";
        } else if (userPath.equals("/logout")) {
            command = commands.getCommand(Commands.LOGOUT);
            command.execute(request, response);
            url += "login.jsp";
        } else if (userPath.equals("/create-order")) {
            command = commands.getCommand(Commands.PREPARE_ORDER);
            command.execute(request, response);
            url += "create-order.jsp";
        } else if (userPath.equals("/view-client-orders")) {
            command = commands.getCommand(Commands.VIEW_CLIENT_ORDERS);
            command.execute(request, response);
            url += "ViewOrders.jsp";
        } else if (userPath.equals("/view-orders")) {
            command = commands.getCommand(Commands.PREPARE_ORDER_ORGANISATION);
            command.execute(request, response);
            url += "OrganiseOrders.jsp";
        } else if (userPath.equals("/view-products")) {
            command = commands.getCommand(Commands.EDIT_PRODUCTS);
            command.execute(request, response);
            url += "editProducts.jsp";
        } else if (userAction.equals("addFood")) {
            url += "create-food.jsp";
        } else if (userAction.equals("update")) {
            int foodId = Integer.parseInt(request.getParameter("foodId"));
            request.getSession().setAttribute("actionId", foodId);
            url += "update-food.jsp";
        } else if (userAction.equals("accept")) {
            command = commands.getCommand(Commands.ACCEPT_ORDER);
            command.execute(request, response);
            url += "OrganiseOrders.jsp";
        } else if (userAction.equals("refuse")) {
            command = commands.getCommand(Commands.REFUSE_ORDER);
            command.execute(request, response);
            url += "OrganiseOrders.jsp";
        } else if (userAction.equals("seeFull")) {
            command = commands.getCommand(Commands.FULL_INFO);
            command.execute(request, response);
            url += "OrderInfo.jsp";
        } else if (userAction.equals("deleteOrder")) {
            command = commands.getCommand(Commands.DELETE_ORDER);
            command.execute(request, response);
            url += "ViewOrders.jsp";
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        String url = "/pages/";

        HttpSession session = request.getSession();
        CommandFactory commands = CommandFactory.getInstance();
        Command command = null;

        if (userPath.equals("/check-login")) {

            command = commands.getCommand(Commands.LOGIN);
            command.execute(request, response);

            if (session.getAttribute("user") == null) {
                url += "login.jsp";
            } else {
                if (session.getAttribute("typeOfUser").equals(DAOs.ADMIN)) {
                    url += "admin.jsp";

                } else {
                    url += "client.jsp";
                }

            }
        } else if (userPath.equals("/choice-registration")) {
            if (!request.getParameter("password").equals(request.getParameter("passwordConfirm"))) {
                request.setAttribute("passwordNotMatch", true);
                url += "register.jsp";
            } else {
                request.removeAttribute("passwordNotMatch");
                command = commands.getCommand(Commands.REGISTER);
                command.execute(request, response);

                if (request.getAttribute("userNotCreated") != null) {
                    url += "register.jsp";
                } else {
                    request.removeAttribute("userNotCreated");

                    if (session.getAttribute("choice").equals("Admin")) {
                        url += "registration-admin.jsp";
                        session.setAttribute("register_type", DAOs.ADMIN);
                    } else {
                        url += "registration-client.jsp";
                        session.setAttribute("register_type", DAOs.CLIENT);
                    }

                }
            }
        } else if (userPath.equals("/check-registration")) {
            if (session.getAttribute("register_type").equals(DAOs.ADMIN)) {
                command = commands.getCommand(Commands.CREATE_ADMIN);
                command.execute(request, response);
                if (request.getAttribute("adminNotCreated") != null) {
                    url += "register.jsp";
                } else {
                    request.removeAttribute("adminNotCreated");
                    url += "registration-success.jsp";
                }
            } else if (session.getAttribute("register_type").equals(DAOs.CLIENT)) {
                command = commands.getCommand(Commands.CREATE_CLIENT);
                command.execute(request, response);
                if (request.getAttribute("clientNotCreated") != null) {
                    url += "register.jsp";
                } else {
                    request.removeAttribute("clientNotCreated");
                    url += "registration-success.jsp";
                }
            }
        } else if (userPath.equals("/add-food")) {
            command = commands.getCommand(Commands.ADD_FOOD);
            command.execute(request, response);
            url += "editProducts.jsp";

        } else if (userPath.equals("/update-food")) {
            command = commands.getCommand(Commands.UPDATE_FOOD);
            command.execute(request, response);
            url += "editProducts.jsp";
        } else if (userPath.equals("/do-order")) {

            command = commands.getCommand(Commands.DO_ORDER);
            command.execute(request, response);
            url += "ViewOrders.jsp";

        } else if (userPath.equals("/cancel-order")) {

            command = commands.getCommand(Commands.CANCEL_ORDER);
            command.execute(request, response);
            url += "ViewOrders.jsp";

        }

        request.getRequestDispatcher(url).forward(request, response);

    }

}

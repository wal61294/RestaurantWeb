
package com.epam.java_training.huzarevich.restaurant.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Abstract command class
 * </p>
 * @author huz
 */
public abstract class Command {
    public abstract void execute(HttpServletRequest request,
            HttpServletResponse response);

}

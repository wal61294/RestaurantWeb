/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.java_training.huzarevich.restaurant.exceptions;

/**
 *
 * @author huz
 */
public class PoolException extends Exception{
    private Exception hidden;
    private String propertyMessage;

    public PoolException(String error) {
        super(error);
    }

    public PoolException(String error, Exception exception) {
        super(error);
        hidden = exception;
    }

    public Exception getHiddenException() {
        return this.hidden;
    }

    public String getPropertyMessage() {
        return propertyMessage;
    }

    public void setPropertyMessage(String propertyMessage) {
        this.propertyMessage = propertyMessage;
    }	
    
}

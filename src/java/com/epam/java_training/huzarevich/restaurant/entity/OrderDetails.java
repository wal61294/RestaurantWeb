package com.epam.java_training.huzarevich.restaurant.entity;

public class OrderDetails extends Entity {

    private int id;
    private int orderId;
    private int foodId;

    public OrderDetails() {
        // TODO Auto-generated constructor stub
    }

    public int getFoodId() {
        return foodId;
    }

    public int getId() {
        return id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}

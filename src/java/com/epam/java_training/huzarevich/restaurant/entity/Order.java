package com.epam.java_training.huzarevich.restaurant.entity;

public class Order extends Entity {

    private int id;
    private int statusId = 1;
    private int clientId;
    private int cost;

    public Order() {

    }

    public int getClientId() {
        return clientId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderStatus(int orderStatus) {
        this.statusId = orderStatus;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getOrderStatus() {
        return statusId;
    }

    public int getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }
}

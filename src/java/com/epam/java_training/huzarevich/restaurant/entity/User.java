package com.epam.java_training.huzarevich.restaurant.entity;

public class User extends Entity {

    private String login;
    private String password;
    private int id;
    private boolean isActivated = false;

    public User() {

    }

    public void setLogin(String string) {
        login = string;

    }

    public void setPassword(String string) {
        password = string;

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

}

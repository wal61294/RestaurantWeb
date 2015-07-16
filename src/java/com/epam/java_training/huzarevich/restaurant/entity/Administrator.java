package com.epam.java_training.huzarevich.restaurant.entity;

public class Administrator extends Entity {

    private int id;
    private int userId;
    private String name;

    public Administrator() {

    }

    public void setId(int int1) {
        id = int1;

    }

    public void setName(String string) {
        name = string;

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}

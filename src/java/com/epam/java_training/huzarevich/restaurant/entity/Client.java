package com.epam.java_training.huzarevich.restaurant.entity;

public class Client extends Entity {

	private int id;
	private int money;
	private int userId;
	private String name;
    public Client() {
		// TODO Auto-generated constructor stub
	}
    public Client(int id, int money, int userId,String name) {
		this.id=id;
		this.money=money;
		this.userId=userId;
		this.name=name;
	}
	public void setId(int int1) {
		id = int1;
	}

	public void setName(String string) {
		name = string;
	}

	public void setMoney(int int2) {
		money = int2;
	}

	public int getId() {
		return id;
	}

	public int getMoney() {
		return money;
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

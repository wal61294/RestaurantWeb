package com.epam.java_training.huzarevich.restaurant.entity;

public class Food extends Entity{
	private int foodId;
	private String foodName;
	private int price;

	public Food() {
		
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getFoodName() {
		return foodName;
	}

	public int getFoodId() {
		return foodId;
	}

}

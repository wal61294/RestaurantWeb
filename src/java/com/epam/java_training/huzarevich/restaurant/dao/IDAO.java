package com.epam.java_training.huzarevich.restaurant.dao;


import java.util.List;

import com.epam.java_training.huzarevich.restaurant.entity.Entity;

public interface IDAO {
	public   Integer create(Entity instance);
	public Entity read(int key);
	public void update(Entity instance);
	public void delete(Entity instance);
	public List<Entity>getAll();
        public Entity find(Entity instance);
}

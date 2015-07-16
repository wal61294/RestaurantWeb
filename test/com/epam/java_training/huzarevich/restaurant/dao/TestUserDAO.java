package com.epam.java_training.huzarevich.restaurant.dao;

import com.epam.java_training.huzarevich.restaurant.entity.User;
import com.epam.java_training.huzarevich.restaurant.exceptions.PoolException;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;



public class TestUserDAO {

	private static UserDAO userDao;
	
	@BeforeClass
	public static void init() throws PoolException {
		userDao = new UserDAO();
	}
	
	@Test
	public void testFind() throws  PoolException {
            User user=new User();
           user.setActivated(true);
           user.setLogin("al22222");
           user.setPassword("aaa");
           assertEquals(null, userDao.find(user));
	}
	
	
}

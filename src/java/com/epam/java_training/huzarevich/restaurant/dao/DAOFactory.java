package com.epam.java_training.huzarevich.restaurant.dao;

public abstract class DAOFactory {
	public static IDAO getDAO( DAOs daos) {
		IDAO dao = null;
		switch (daos) {
		case ADMIN:
			dao = new AdministratorDAO();
			break;
		case CLIENT:
			dao = new ClientDAO();
			break;
		case FOOD:
			dao = new FoodDAO();
			break;
		case ORDER:
			dao = new OrderDAO();
			break;
		case ORDER_DETAILS:
			dao = new OrderDetailsDAO();
			break;
		case ORDER_TYPE:
			dao = new OrderTypeDAO();
			break;
		case USER:
			dao = new UserDAO();
			break;
		default:
			break;

		}
		return dao;
	}

}

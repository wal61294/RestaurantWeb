
package com.epam.java_training.huzarevich.restaurant.command;

/**
 *<p>
 * Command factory
 * </p>
 * Realization of Singleton
 * @author huz
 */
public class CommandFactory {

    private static CommandFactory instance;

    private CommandFactory() {

    }

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }

        return instance;
    }

    public Command getCommand(Commands type) {
        Command command = null;
        switch (type) {
            case LOGIN:
                command = new LoginCommand();
                break;
            case LOGOUT:
                command = new LogOutCommand();
                break;
            case REGISTER:
                command = new RegisterCommand();
                break;
            case CREATE_ADMIN:
                command = new CreateAdminCommand();
                break;
            case CREATE_CLIENT:
                command = new CreateClientCommand();
                break;
            case EDIT_PRODUCTS:
                command = new EditProductsCommand();
                break;
            case UPDATE_FOOD:
                command = new UpdateFoodCommand();
                break;
            case ADD_FOOD:
                command = new AddFoodCommand();
                break;
            case PREPARE_ORDER:
                command = new PrepareOrderCommand();
                break;
            case DO_ORDER:
                command = new DoOrderCommand();
                break;
            case CANCEL_ORDER:
                command = new CancelOrderCommand();
                break;
            case PREPARE_ORDER_ORGANISATION:
                command = new PrepareOrderOrganisationCommand();
                break;
            case ACCEPT_ORDER:
                command= new AcceptOrderCommand();
                break;
            case REFUSE_ORDER:
                command= new RefuseOrderCommand();
                break;
            case VIEW_CLIENT_ORDERS:
                command=new ViewClientOrdersCommand();
                break;
            case DELETE_ORDER:
                command=new DeleteOrderCommand();
                break;
            case FULL_INFO:
                command= new GetOrderInfoCommand();
                break;
        }
        return command;
    }
}

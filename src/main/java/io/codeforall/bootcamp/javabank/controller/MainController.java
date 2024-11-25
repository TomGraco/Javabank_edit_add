package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.view.MainView;
import io.codeforall.bootcamp.javabank.view.Messages;
import io.codeforall.bootcamp.javabank.view.UserOptions;

import java.util.Map;

/**
 * The {@link MainView} controller
 */
public class MainController extends AbstractController {

    private Map<Integer, Controller> controllerMap;

    /**
     * Sets the controller map, responsible for the mapping between the menu options and the respective controller
     *
     * @param controllerMap the controller map to set
     */
    public void setControllerMap(Map<Integer, Controller> controllerMap) {
        this.controllerMap = controllerMap;
    }

    /**
     * Gets the customer name
     *
     * @return the customer name
     */
    public String getCustomerName() {
        Customer customer = authService.getAccessingCustomer();
        return customer.getFirstName() + " " + customer.getLastName();
    }

    /**
     * Invokes the next controller based on menu selection
     *
     * @param option the option chosen
     */
    public void onMenuSelection(int option) {

        if (option == UserOptions.QUIT.getOption()) {
            return;
        }

        if (!controllerMap.containsKey(option)) {
            throw new IllegalStateException(Messages.SYSTEM_ERROR);
        }

        controllerMap.get(option).init();
        init();
    }
}

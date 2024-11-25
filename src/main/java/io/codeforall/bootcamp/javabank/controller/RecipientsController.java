package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.persistence.model.Recipient;
import io.codeforall.bootcamp.javabank.services.CustomerService;
import io.codeforall.bootcamp.javabank.view.RecipientsView;

import java.util.List;

/**
 * The {@link RecipientsView} controller
 */
public class RecipientsController extends AbstractController {

    private CustomerService customerService;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Gets a list of the customer recipients
     *
     * @return the recipient list
     * @see CustomerService#listRecipients(Integer)
     */
    public List<Recipient> getRecipients() {
        Customer customer = authService.getAccessingCustomer();
        return customerService.listRecipients(customer.getId());
    }
}

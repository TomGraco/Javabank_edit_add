package io.codeforall.bootcamp.javabank.services;


import io.codeforall.bootcamp.javabank.model.Customer;

import java.util.Optional;

/**
 * An {@link AuthService} implementation
 */
public class AuthServiceImpl implements AuthService {

    private CustomerService customerService;
    private Integer accessingCustomerId;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * @see AuthService#authenticate(Integer)
     */
    @Override
    public boolean authenticate(Integer id) {

        Optional<Customer> customer = Optional.ofNullable(customerService.get(id));

        customer.ifPresent(customer1 -> accessingCustomerId = customer1.getId());

        return customer.isPresent();

//        if (customer == null) {
//            return false;
//        }
//
//        accessingCustomerId = customer.getId();
//        return true;
    }

    /**
     * @see AuthService#getAccessingCustomer()
     */
    @Override
    public Customer getAccessingCustomer() {
        return customerService.get(accessingCustomerId);
    }
}

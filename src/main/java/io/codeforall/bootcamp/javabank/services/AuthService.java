package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.model.Customer;

/**
 * Common interface for authentication services, provides methods
 * for customer authentication and authorization
 */
public interface AuthService {

    /**
     * Authenticates the accessing customer
     *
     * @param id the customer id
     * @return {@code true} if authentication was successful
     */
    boolean authenticate(Integer id);

    /**
     * Gets the accessing customer
     *
     * @return the customer
     */
    Customer getAccessingCustomer();

}

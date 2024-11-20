package io.codeforall.bootcamp.javabank.persistence.daos;

import io.codeforall.bootcamp.javabank.model.Customer;

import java.util.List;

public interface CustomerDao extends Dao<Customer> {

    /**
     * Gets a list of customer ids
     *
     * @return the list of customer ids
     */
    List<Integer> getCustomerIds();
}

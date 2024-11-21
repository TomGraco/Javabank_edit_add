package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.model.AbstractModel;
import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.Recipient;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.TransactionManager;
import io.codeforall.bootcamp.javabank.persistence.dao.CustomerDao;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An {@link CustomerService} implementation
 */
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;
    private TransactionManager tx;

    /**
     * Sets the customer data access object
     *
     * @param customerDao the account DAO to set
     */
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * Sets the transaction manager
     *
     * @param tx the transaction manager to set
     */
    public void setTransactionManager(TransactionManager tx) {
        this.tx = tx;
    }

    /**
     * @see CustomerService#get(Integer)
     */
    @Override
    public Customer get(Integer id) {

        try {

            tx.beginRead();
            return customerDao.findById(id);

        } finally {
            tx.commit();
        }
    }

    /**
     * @see CustomerService#getBalance(Integer)
     */
    @Override
    public double getBalance(Integer id) {

        try {

            tx.beginRead();

            Customer customer = Optional.ofNullable(customerDao.findById(id))
                    .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

            return customer.getAccounts().stream()
                    .mapToDouble(Account::getBalance)
                    .sum();

        } finally {
            tx.commit();
        }
    }

    /**
     * @see CustomerService#listCustomerAccountIds(Integer)
     */
    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        try {

            tx.beginRead();

            Customer customer = Optional.ofNullable(customerDao.findById(id))
                    .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

            return customer.getAccounts().stream()
                    .map(AbstractModel::getId)
                    .collect(Collectors.toSet());

        } finally {
            tx.commit();
        }
    }

    /**
     * @see CustomerService#listRecipients(Integer)
     */
    @Override
    public List<Recipient> listRecipients(Integer id) {

        try {

            tx.beginRead();

            Customer customer = Optional.ofNullable(customerDao.findById(id))
                    .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

            return new ArrayList<>(customer.getRecipients());

        } finally {
            tx.commit();
        }
    }
}

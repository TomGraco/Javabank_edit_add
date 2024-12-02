package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.errors.ErrorMessage;
import io.codeforall.bootcamp.javabank.persistence.model.Recipient;
import io.codeforall.bootcamp.javabank.persistence.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.persistence.dao.AccountDao;
import io.codeforall.bootcamp.javabank.persistence.dao.CustomerDao;
import io.codeforall.bootcamp.javabank.persistence.dao.RecipientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * An {@link CustomerService} implementation
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;
    private RecipientDao recipientDao;
    private AccountDao accountDao;

    /**
     * Sets the customer data access object
     *
     * @param customerDao the account DAO to set
     */
    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * Sets the recipient data access object
     *
     * @param recipientDao the recipient DAO to set
     */
    @Autowired
    public void setRecipientDao(RecipientDao recipientDao) {
        this.recipientDao = recipientDao;
    }

    /**
     * Sets the account data access object
     *
     * @param accountDao the account DAO to set
     */
    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * @see CustomerService#get(Integer)
     */
    public Customer get(Integer id) {
        return customerDao.findById(id);
    }

    /**
     * @see CustomerService#getBalance(Integer)
     */
    @Override
    public double getBalance(Integer id) {

        Customer customer = Optional.ofNullable(customerDao.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

        return customer.getAccounts().stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }

    /**
     * @see CustomerService#delete(Integer)
     */
    @Transactional
    @Override
    public void delete(Integer id) {
        customerDao.delete(id);
    }

    /**
     * @see CustomerService#list()
     */
    @Override
    public List<Customer> list() {
        return customerDao.findAll();
    }

    /**
     * @see CustomerService#listRecipients(Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public List<Recipient> listRecipients(Integer id) {

        // check then act logic requires transaction,
        // event if read only
        Customer customer = Optional.ofNullable(customerDao.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

        return new ArrayList<>(customer.getRecipients());
    }

    /**
     * @see CustomerService#removeRecipient(Integer, Integer)
     */
    @Transactional
    @Override
    public void removeRecipient(Integer id, Integer recipientId) {

        Customer customer = Optional.ofNullable(customerDao.findById(id))
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.CUSTOMER_NOT_FOUND));

        Recipient recipient = Optional.ofNullable(recipientDao.findById(recipientId))
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.RECIPIENT_NOT_FOUND));

        if (!recipient.getCustomer().getId().equals(id)) {
            throw new IllegalArgumentException(ErrorMessage.CUSTOMER_RECIPIENT_NOT_FOUND);
        }

        customer.removeRecipient(recipient);
        customerDao.saveOrUpdate(customer);
    }
}

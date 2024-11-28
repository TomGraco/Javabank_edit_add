package io.codeforall.bootcamp.javabank.services.mock;

import io.codeforall.bootcamp.javabank.services.CustomerService;
import io.codeforall.bootcamp.javabank.persistence.model.AbstractModel;
import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.persistence.model.Recipient;
import io.codeforall.bootcamp.javabank.persistence.model.account.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A mock {@link CustomerService} implementation
 */
public class MockCustomerService extends AbstractMockService<Customer> implements CustomerService {

    /**
     * @see CustomerService#get(Integer)
     */
    @Override
    public Customer get(Integer id) {
        return modelMap.get(id);
    }

    /**
     * @see CustomerService#getBalance(Integer)
     */
    @Override
    public double getBalance(Integer customerId) {

        List<Account> accounts = modelMap.get(customerId).getAccounts();

        return accounts.stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }

    /**
     * @see CustomerService#list()
     */
    @Override
    public List<Customer> list() {
        return new ArrayList<>(modelMap.values());
    }

    /**
     * @see CustomerService#listCustomerAccountIds(Integer)
     */
    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        List<Account> accounts = modelMap.get(id).getAccounts();

        return accounts.stream()
                .map(AbstractModel::getId)
                .collect(Collectors.toSet());
    }

    /**
     * @see CustomerService#listRecipients(Integer)
     */
    @Override
    public List<Recipient> listRecipients(Integer id) {
        return modelMap.get(id).getRecipients();
    }
}

package io.codeforall.bootcamp.javabank.controller.transaction;

import io.codeforall.bootcamp.javabank.services.AccountService;
import io.codeforall.bootcamp.javabank.services.CustomerService;
import io.codeforall.bootcamp.javabank.controller.AbstractController;

import java.util.Set;

/**
 * A generic account transaction controller to be used as a base for concrete transaction controller implementations
 * @see AbstractController
 * @see AccountTransactionController
 */
public abstract class AbstractAccountTransactionController extends AbstractController implements AccountTransactionController {

    protected AccountService accountService;
    private CustomerService customerService;

    /**
     * Sets the account service
     *
     * @param accountService the account service to set
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * @see AccountTransactionController#getAccountIds()
     */
    public Set<Integer> getAccountIds() {
        return customerService.listCustomerAccountIds(authService.getAccessingCustomer().getId());
    }

}

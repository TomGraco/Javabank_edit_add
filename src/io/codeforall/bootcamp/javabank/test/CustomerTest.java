package io.codeforall.bootcamp.javabank.test;

import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.domain.account.AccountType;
import io.codeforall.bootcamp.javabank.domain.account.SavingsAccount;
import io.codeforall.bootcamp.javabank.managers.AccountManager;

public class CustomerTest {

    public boolean test() {

        AccountManager accountManager = new AccountManager();
        Customer customer = new Customer(1, "Rui");
        customer.setAccountManager(accountManager);

        // customer should not contain any accounts
        if (customer.getAccountIds().size() != 0) {
            return false;
        }

        // should be able to open accounts
        int ac = customer.openAccount(AccountType.CHECKING);
        int as = customer.openAccount(AccountType.SAVINGS);

        if (customer.getAccountIds().size() != 2) {
            return false;
        }

        // customer should be able to get the balance of each individual account
        accountManager.deposit(ac, 100);
        accountManager.deposit(as, SavingsAccount.MIN_BALANCE + 100);
        if (customer.getBalance(ac) != 100 || customer.getBalance(as) != SavingsAccount.MIN_BALANCE + 100) {
            return false;
        }

        // customer should be able to get the combined balance of all its accounts
        if (customer.getBalance() != 200 + SavingsAccount.MIN_BALANCE) {
            return false;
        }

        return true;
    }

}

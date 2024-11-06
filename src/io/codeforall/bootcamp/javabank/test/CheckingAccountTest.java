package io.codeforall.bootcamp.javabank.test;

import io.codeforall.bootcamp.javabank.domain.account.Account;
import io.codeforall.bootcamp.javabank.domain.account.CheckingAccount;

public class CheckingAccountTest {

    public boolean test() {

        Account account = new CheckingAccount(1);

        // account should start with zero money
        if (account.getBalance() != 0) {
            return false;
        }

        // we should be able to deposit money in account
        account.credit(80);
        if (account.getBalance() != 80) {
            return false;
        }

        // we should be able to take money from account
        account.debit(40);
        if (account.getBalance() != 40) {
            return false;
        }

        return true;
    }

}

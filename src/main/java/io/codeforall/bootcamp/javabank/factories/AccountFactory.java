package io.codeforall.bootcamp.javabank.factories;

import io.codeforall.bootcamp.javabank.persistence.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.model.account.AccountType;
import io.codeforall.bootcamp.javabank.persistence.model.account.CheckingAccount;
import io.codeforall.bootcamp.javabank.persistence.model.account.SavingsAccount;

/**
 * A factory for creating accounts of different types
 */
public class AccountFactory {

    /**
     * Creates a new {@link Account}
     *
     * @param accountType the account type
     * @return the new account
     */
    public Account createAccount(AccountType accountType) {

        Account newAccount;
        switch (accountType) {
            case CHECKING:
                newAccount = new CheckingAccount();
                break;
            case SAVINGS:
                newAccount = new SavingsAccount();
                break;
            default:
                newAccount = null;

        }

        return newAccount;
    }
}

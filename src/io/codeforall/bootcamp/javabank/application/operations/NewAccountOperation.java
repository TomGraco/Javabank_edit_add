package io.codeforall.bootcamp.javabank.application.operations;

import io.codeforall.bootcamp.javabank.domain.account.AccountType;
import io.codeforall.bootcamp.javabank.application.BankApplication;
import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.application.UserOptions;

/**
 * A bank operation to create new accounts
 * @see AbstractBankOperation
 * @see UserOptions#OPEN_ACCOUNT
 */
public class NewAccountOperation extends AbstractBankOperation {

    /**
     * Creates a new {@code NewAccountOperation}
     *
     * @see AbstractBankOperation#AbstractBankOperation(BankApplication)
     */
    public NewAccountOperation(BankApplication bankApplication) {
        super(bankApplication);
    }

    /**
     * Creates a new bank account
     *
     * @see AbstractBankOperation#execute()
     */
    @Override
    public void execute() {

        int accountId = customer.openAccount(AccountType.CHECKING);

        System.out.println("\n" + Messages.CREATED_ACCOUNT + customer.getName() + " : " + accountId);
    }
}

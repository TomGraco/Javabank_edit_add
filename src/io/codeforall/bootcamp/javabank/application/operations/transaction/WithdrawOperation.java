package io.codeforall.bootcamp.javabank.application.operations.transaction;

import io.codeforall.bootcamp.javabank.application.BankApplication;
import io.codeforall.bootcamp.javabank.application.UserOptions;

/**
 * An account transaction used to withdraw an amount
 * @see AbstractAccountTransactionOperation
 * @see UserOptions#WITHDRAW
 */
public class WithdrawOperation extends AbstractAccountTransactionOperation {

    /**
     * Initializes a new {@code WithdrawOperation}
     *
     * @see AbstractAccountTransactionOperation
     */
    public WithdrawOperation(BankApplication bankApplication) {
        super(bankApplication);
    }

    /**
     * Withdraw an amount from an account
     *
     * @see AbstractAccountTransactionOperation#execute()
     */
    @Override
    public void execute() {

        super.execute();

        if (!hasAccounts()) {
            return;
        }

        Integer accountId = scanAccount();
        Double amount = scanAmount();

        if (customer.getAccountIds().contains(accountId)) {
            accountManager.withdraw(accountId, amount);
        }
    }
}

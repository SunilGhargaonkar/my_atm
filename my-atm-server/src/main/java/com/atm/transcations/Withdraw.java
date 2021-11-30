package com.atm.transcations;

import com.atm.account.AccountBalance;

import java.io.IOException;

public class Withdraw {
    private final AccountBalance accountBalance;

    public Withdraw(final long accountNumber) {
        accountBalance = new AccountBalance(accountNumber);
    }

    void withdrawAmount(final long amount) throws IOException {
        final long balanceAmount = accountBalance.getCustomerAccountBalance();

        if (isAllowedToWithDraw(amount, balanceAmount)) {
            final long finalAmount = balanceAmount - amount;

            accountBalance.updateBalanceAmount(finalAmount);

            if (finalAmount == accountBalance.getCustomerAccountBalance()) {
                System.out.println("Your balance is updated");
            } else {
                System.out.println("Unable to update balance");
            }
        } else
            System.out.println("Sorry, You don't have enough balance to withdraw");
    }

    private boolean isAllowedToWithDraw(final long amount, final long balanceAmount) {
        return balanceAmount - amount > 0;
    }

}

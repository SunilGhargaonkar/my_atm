package com.atm.transcations;

import com.atm.account.AccountBalance;

import java.io.IOException;

public class Deposit {
    private final AccountBalance accountBalance;

    public Deposit(final long accountNumber) {
        this.accountBalance = new AccountBalance(accountNumber);
    }

    void depositMoney(final long depositAmount) throws IOException {
        final long balanceAmount = accountBalance.getCustomerAccountBalance();
        final long finalAmount = depositAmount + balanceAmount;

        accountBalance.updateBalanceAmount(finalAmount);

        if (finalAmount == accountBalance.getCustomerAccountBalance()) {
            System.out.println("Your money has been deposited");
        } else {
            System.out.println("Unable to deposit the money");
        }

    }
}

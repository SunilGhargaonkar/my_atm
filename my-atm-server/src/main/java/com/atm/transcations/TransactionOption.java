package com.atm.transcations;

import com.atm.account.AccountBalance;

import java.io.IOException;

public record TransactionOption(long accountNumber, int choice) {// Record Type - Java 15 feature

    public void performOperation(int amount) throws IOException {
        switch (choice) { //Enhanced switch expression - Java 12-14 feature
            case 1 -> {
                final AccountBalance accountBalance = new AccountBalance(accountNumber);
                System.out.println(accountBalance.formatAccountBalance());
            }
            case 2 -> {
                final Withdraw withdraw = new Withdraw(accountNumber);
                withdraw.withdrawAmount(amount);
            }
            case 3 -> {
                final Deposit deposit = new Deposit(accountNumber);
                deposit.depositMoney(amount);
            }
            default -> System.out.println("Sorry, invalid choice! Please verify your account again");

        }
    }
}

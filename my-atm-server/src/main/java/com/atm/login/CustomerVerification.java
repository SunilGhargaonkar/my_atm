package com.atm.login;

import com.atm.account.AccountHolder;
import file.operations.Account;

import java.util.List;

public class CustomerVerification {
    private boolean validCustomer = false;
    private AccountHolder accountHolder;

    public CustomerVerification() {
        this(new AccountHolder());
    }

    CustomerVerification(final AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    public boolean isValidCustomer(final long accountNumber, final long accountPin) {
        final List<Account> account = getAccountDetails(accountNumber);

        if (!account.isEmpty()) {
            account.forEach(
                    a -> {
                        if (a.getAccountPin() == accountPin) {
                            validCustomer = true;
                            System.out.println("Welcome to My ATM!");
                        } else
                            System.out.println("Invalid Pin");
                    }
            );
        } else
            System.out.println("Unable to find your account.");

        return validCustomer;
    }

    private List<Account> getAccountDetails(long accountNumber) {

        return accountHolder.getAccountDetailsFromAccountNumber(accountNumber);
    }
}

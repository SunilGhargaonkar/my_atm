package com.atm.account;

import file.operations.Account;
import file.operations.ReadCsvFile;

import java.util.List;

public class AccountHolder {
    private final List<Account> accountData;

    public AccountHolder() {
        this(new ReadCsvFile().read());
    }

    //Used for unit testing
    public AccountHolder(final List<Account> accountData) {
        this.accountData = accountData;
    }

    public List<Account> getAccountDetailsFromAccountNumber(final long accountNumber) {

        return accountData
                .stream()
                .filter(a -> a.getAccountNumber() == accountNumber)
                .toList(); //toList - Java 16 feature
    }
}

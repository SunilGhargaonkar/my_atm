package com.atm.account;

import file.operations.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountHolderTest {
    final long accountNumber = 1111;
    final long pin = 1234;
    final long accountBalance = 1000;

    @Test
    @DisplayName("Given account number exist in DB Then account details should be returned")
    void accountDetailsShouldBeReturnedWhenAccountNumberMatchesInDB() {
        final Account account = setUpAccount();

        final List<Account> accountsDetails = List.of(account); //.of() Immutable Collection Methods - Java 9 feature

        final AccountHolder accountHolder = new AccountHolder(accountsDetails);
        final List<Account> accountResult = accountHolder.getAccountDetailsFromAccountNumber(accountNumber);

        assertTrue(accountResult.stream().allMatch(a -> a.getAccountBalance() == accountBalance), "Expected account balance is not matching");
        assertTrue(accountResult.containsAll(accountsDetails), "Result doesn't contain all the elements as expected");
    }

    @Test
    @DisplayName("Given account number doesn't exist in DB Then account details should be empty")
    void accountDetailsShouldBeEmptyWhenAccountNumberDoesNtMatchesInDB() {
        final AccountHolder accountHolder = new AccountHolder(new ArrayList<>());
        final List<Account> accountResult = accountHolder.getAccountDetailsFromAccountNumber(accountNumber);

        assertTrue(accountResult.isEmpty(), "Account results should be empty");
    }

    private Account setUpAccount() {
        final Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountPin(pin);
        account.setAccountBalance(accountBalance);

        return account;
    }

}
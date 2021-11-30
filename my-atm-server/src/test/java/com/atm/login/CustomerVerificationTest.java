package com.atm.login;

import com.atm.account.AccountHolder;
import file.operations.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerVerificationTest {
    final long accountNumber = 1111;
    final long pin = 1234;
    final long accountBalance = 1000;

    @Test
    @DisplayName("Given Account number exist in DB login is successful")
    void whenAccountNumberAndPinMatchesThenCustomerShouldBeVerifiedSuccessfully() {
        final Account account = setUpAccount();
        final List<Account> accountsDetails = List.of(account);//.of() Immutable Collection Methods - Java 9 feature
        final AccountHolder accountHolder = new AccountHolder(accountsDetails);


        final CustomerVerification customerVerification = new CustomerVerification(accountHolder);

        assertTrue(customerVerification.isValidCustomer(accountNumber, pin));
    }

    @Test
    @DisplayName("Given Account number exist in DB but pin doesn't match Then login is not allowed")
    void whenAccountNumberExitsInDBButPinDoesntMatchThenCustomerShouldNotVerified() {
        final Account account = setUpAccount();
        final List<Account> accountsDetails = List.of(account);
        final AccountHolder accountHolder = new AccountHolder(accountsDetails);


        final CustomerVerification customerVerification = new CustomerVerification(accountHolder);

        assertFalse(customerVerification.isValidCustomer(accountNumber, pin + 1));
    }

    @Test
    @DisplayName("Given Account number doesn't exist in DB Then login is not allowed")
    void whenAccountNumberDoesntExitsInDBThenCustomerShouldNotVerified() {
        final Account account = setUpAccount();
        final List<Account> accountsDetails = List.of(account);
        final AccountHolder accountHolder = new AccountHolder(accountsDetails);


        final CustomerVerification customerVerification = new CustomerVerification(accountHolder);

        assertFalse(customerVerification.isValidCustomer(accountNumber + 1, pin));
    }
    @Test
    @DisplayName("Given DB doesn't have record Then login is not allowed")
    void whenAccountNumberDoesNotProvidedThenCustomerShouldNotVerified() {
        final CustomerVerification customerVerification = new CustomerVerification();

        assertFalse(customerVerification.isValidCustomer(accountNumber + 1, pin));
    }

    private Account setUpAccount() {
        final Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountPin(pin);
        account.setAccountBalance(accountBalance);

        return account;
    }
}
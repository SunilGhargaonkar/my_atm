package com.atm.account;

import file.operations.Account;
import file.operations.WriteToCsvFile;
import file.operations.WriteToFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public record AccountBalance(long accountNumber) {// Record Type - Java 15 feature

    public String formatAccountBalance() throws FileNotFoundException {
        return formatAccountBalance(getCustomerAccountBalance());
    }

    private String formatAccountBalance(final long balance) {
        final DecimalFormat decimalFormat = new DecimalFormat("'£' ###,##0.00");

        return decimalFormat.format(balance);
    }

    public long getCustomerAccountBalance() {
        final AccountHolder accountHolder = new AccountHolder();

        final List<Account> customerAccountDetails = accountHolder.getAccountDetailsFromAccountNumber(accountNumber);

        return customerAccountDetails.stream()
                .map(Account::getAccountBalance)
                .mapToLong(Long::longValue)
                .sum();
    }

    public void updateBalanceAmount(final long finalAmount) throws IOException {
        final WriteToFile writeToFile = new WriteToCsvFile(accountNumber);
        writeToFile.write(finalAmount);
    }
}

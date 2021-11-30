package com.atm.transcations;

public enum TransactionType {
    WITHDRAW("Withdraw amount"),
    DEPOSIT("Deposit Amount"),
    BALANCE_CHECK("Check Available Balance Account"),
    EXIT("Exit");

    private final String option;

    TransactionType(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}

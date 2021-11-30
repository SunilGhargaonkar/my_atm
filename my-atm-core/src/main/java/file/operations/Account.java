package file.operations;

import com.opencsv.bean.CsvBindByName;


public final class Account {

    @CsvBindByName(column = "accountNumber")
    private long accountNumber;

    @CsvBindByName(column = "accountPin")
    private long accountPin;

    @CsvBindByName(column = "accountBalance")
    private long accountBalance;

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getAccountPin() {
        return accountPin;
    }

    public void setAccountPin(long accountPin) {
        this.accountPin = accountPin;
    }

    public long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(long accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountPin=" + accountPin +
                ", accountBalance=" + accountBalance +
                '}';
    }
}

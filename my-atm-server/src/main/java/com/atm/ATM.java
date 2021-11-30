package com.atm;

import com.atm.account.AccountBalance;
import com.atm.login.CustomerVerification;
import com.atm.transcations.TransactionOption;
import com.atm.transcations.TransactionType;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {
        final CustomerVerification customerVerification = new CustomerVerification();
        final Scanner accountDetails = new Scanner(System.in);
        boolean loginSuccessful = false;
        long accountNumber = 0;

        try {
            System.out.print("Enter the account number: ");
            accountNumber = accountDetails.nextLong();

            System.out.print("Enter the Pin: ");
            final long accountPin = accountDetails.nextLong();

            loginSuccessful = customerVerification.isValidCustomer(accountNumber, accountPin);
        } catch (InputMismatchException e) {
            System.out.println("Please enter valid details");
            e.printStackTrace();
        }

        try {
            int amount;
            int choice;

            if (loginSuccessful) {
                do {
                    final String choiceOptions = String.format("""
                                                                
                                    Please select the option to continue the transaction
                                    Type 1 For - %s
                                    Type 2 For - %s
                                    Type 3 For - %s
                                    Type 4 For - %s
                                    """,
                            TransactionType.BALANCE_CHECK.getOption(),
                            TransactionType.WITHDRAW.getOption(),
                            TransactionType.DEPOSIT.getOption(),
                            TransactionType.EXIT.getOption()
                    ); // Text Block - Java 13-15 feature

                    System.out.println(choiceOptions);

                    choice = accountDetails.nextInt();

                    final TransactionOption transactionOption = new TransactionOption(accountNumber, choice);

                    if (choice == 2 || choice == 3) {
                        System.out.println("Please enter the amount to continue the transaction");
                        amount = accountDetails.nextInt();
                        transactionOption.performOperation(amount);
                    } else if (choice == 1) {
                        final AccountBalance accountBalance = new AccountBalance(accountNumber);
                        System.out.println("Your account balance is: " + accountBalance.formatAccountBalance());
                    } else if (choice == 4) {
                        break;
                    } else {
                        System.out.println("Sorry, invalid choice");
                    }
                }
                while (choice > 0 && choice < 5);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

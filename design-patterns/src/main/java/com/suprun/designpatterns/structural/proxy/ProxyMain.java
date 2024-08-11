package com.suprun.designpatterns.structural.proxy;

/**
 * @author Yurii_Suprun
 */
public class ProxyMain {
    public static void main(String... args) {
        // Using the secure proxy to interact with the bank account
        BankAccount bankAccount = new SecureBankAccountProxy(1000.0);

        bankAccount.deposit(500.0); // Valid deposit
        bankAccount.deposit(-200.0); // Invalid deposit

        double balance = bankAccount.getBalance(); // Perform balance inquiry

        System.out.println("Current balance: " + balance);
    }
}


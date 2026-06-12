package com.suprun.designpatterns.structural.proxy;

/**
 * Demo class for the Proxy design pattern.
 * Shows how a proxy can control access to an object and add additional functionality.
 *
 * @author Yurii_Suprun
 */
public class ProxyMain {

    public static void main(String... args) {
        // Create a secure proxy that wraps a bank account
        BankAccount bankAccount = new SecureBankAccountProxy(1000.0);

        System.out.println("=== Attempting Valid Deposit ===");
        bankAccount.deposit(500.0);

        System.out.println("\n=== Attempting Invalid Deposit ===");
        bankAccount.deposit(-200.0);

        System.out.println("\n=== Checking Balance ===");
        double balance = bankAccount.getBalance();

        System.out.println("Current balance: " + balance);
    }
}


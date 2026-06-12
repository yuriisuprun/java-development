package com.suprun.designpatterns.structural.proxy;

/**
 * BankAccountImpl is the real subject in the Proxy pattern.
 * It implements actual bank account operations.
 *
 * @author Yurii_Suprun
 */
public class BankAccountImpl implements BankAccount {

    private double balance;

    /**
     * Constructs a bank account with an initial balance.
     *
     * @param initialBalance the initial balance
     */
    public BankAccountImpl(double initialBalance) {
        this.balance = initialBalance;
        System.out.println("Created a bank account with initial balance: " + initialBalance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " into the account. New balance: " + balance);
    }

    @Override
    public double getBalance() {
        return balance;
    }
}


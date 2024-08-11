package com.suprun.designpatterns.structural.proxy;

/**
 * @author Yurii_Suprun
 */
// Real Subject
public class BankAccountImpl implements BankAccount {
    private double balance;

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


package com.suprun.designpatterns.structural.proxy;

/**
 * BankAccount interface defines the contract for bank account operations.
 * This interface is used in the Proxy pattern to define the subject interface.
 *
 * @author Yurii_Suprun
 */
public interface BankAccount {

    /**
     * Deposits the specified amount to the account.
     *
     * @param amount the amount to deposit
     */
    void deposit(double amount);

    /**
     * Gets the current account balance.
     *
     * @return the current balance
     */
    double getBalance();
}

package com.suprun.designpatterns.structural.proxy;

/**
 * @author Yurii_Suprun
 */
// Subject interface
public interface BankAccount {
    void deposit(double amount);
    double getBalance();
}

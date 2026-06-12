package com.suprun.designpatterns.behavioral.strategy;

/**
 * PaymentStrategy interface defines the contract for different payment strategies.
 * This interface is part of the Strategy design pattern.
 *
 * @author Yurii_Suprun
 */
public interface PaymentStrategy {

    /**
     * Processes payment for the given amount.
     *
     * @param amount the amount to be paid
     */
    void pay(int amount);
}

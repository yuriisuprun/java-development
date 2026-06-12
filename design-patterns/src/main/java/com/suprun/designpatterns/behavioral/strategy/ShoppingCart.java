package com.suprun.designpatterns.behavioral.strategy;

import java.util.Objects;

/**
 * ShoppingCart is a context class that uses different payment strategies.
 * This class demonstrates the Strategy design pattern by allowing runtime
 * selection of the payment strategy.
 *
 * @author Yurii_Suprun
 */
public class ShoppingCart {

    private PaymentStrategy paymentStrategy;

    /**
     * Sets the payment strategy to be used for checkout.
     *
     * @param paymentStrategy the payment strategy to use
     * @throws NullPointerException if paymentStrategy is null
     */
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = Objects.requireNonNull(paymentStrategy, "Payment strategy cannot be null");
    }

    /**
     * Processes the checkout with the currently set payment strategy.
     *
     * @param amount the amount to checkout
     * @throws IllegalStateException if no payment strategy has been set
     */
    public void checkout(int amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy must be set before checkout");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        paymentStrategy.pay(amount);
    }
}
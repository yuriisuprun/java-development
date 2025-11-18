package com.suprun.designpatterns.behavioral.strategy;

public class ShoppingCart {

    private PaymentStrategy paymentStrategy;

    // Choosing strategy at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}
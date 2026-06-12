package com.suprun.designpatterns.behavioral.strategy;

/**
 * Demo class for the Strategy design pattern.
 * Shows how to use different payment strategies at runtime.
 *
 * @author Yurii_Suprun
 */
public class Main {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Using Credit Card strategy
        cart.setPaymentStrategy(new CreditCardPayment("1111-2222-3333-4444"));
        cart.checkout(100);

        // Switching to PayPal strategy at runtime
        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(50);
    }
}

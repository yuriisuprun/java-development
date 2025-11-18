package com.suprun.designpatterns.behavioral.strategy;

public class Main {

    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentStrategy(new CreditCardPayment("1111-2222-3333-4444"));
        cart.checkout(100);

        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(50);
    }
}

package com.suprun.designpatterns.behavioral.strategy;

import java.util.Objects;

/**
 * PayPalPayment is a concrete strategy for paying with PayPal.
 * Implements the PaymentStrategy interface.
 *
 * @author Yurii_Suprun
 */
public class PayPalPayment implements PaymentStrategy {

    private final String email;

    /**
     * Constructs a PayPal payment strategy.
     *
     * @param email the PayPal email address
     * @throws NullPointerException if email is null
     */
    public PayPalPayment(String email) {
        this.email = Objects.requireNonNull(email, "Email cannot be null");
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using PayPal: " + maskEmail(email));
    }

    /**
     * Masks the email for security purposes.
     *
     * @param email the full email address
     * @return the masked email address
     */
    private String maskEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex <= 1) {
            return "***@***";
        }
        String localPart = email.substring(0, 1) + "*".repeat(Math.max(0, atIndex - 2)) + email.substring(atIndex - 1);
        String domain = email.substring(atIndex);
        return localPart + domain;
    }
}

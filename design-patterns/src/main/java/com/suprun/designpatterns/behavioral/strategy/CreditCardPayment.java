package com.suprun.designpatterns.behavioral.strategy;

import java.util.Objects;

/**
 * CreditCardPayment is a concrete strategy for paying with a credit card.
 * Implements the PaymentStrategy interface.
 *
 * @author Yurii_Suprun
 */
public class CreditCardPayment implements PaymentStrategy {

    private final String cardNumber;

    /**
     * Constructs a credit card payment strategy.
     *
     * @param cardNumber the credit card number
     * @throws NullPointerException if cardNumber is null
     */
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = Objects.requireNonNull(cardNumber, "Card number cannot be null");
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid with Credit Card: " + maskCardNumber(cardNumber));
    }

    /**
     * Masks the card number for security purposes.
     *
     * @param cardNumber the full card number
     * @return the masked card number
     */
    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() < 4) {
            return "****";
        }
        return "*".repeat(cardNumber.length() - 4) + cardNumber.substring(cardNumber.length() - 4);
    }
}

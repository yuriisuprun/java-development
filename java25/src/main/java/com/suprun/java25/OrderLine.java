package com.suprun.java25;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Demonstrates JEP 513: Flexible Constructor Bodies.
 * A non-canonical constructor validates and parses input before calling this(...).
 */
public record OrderLine(String itemName, int quantity, BigDecimal unitPrice) {
    public OrderLine {
        Objects.requireNonNull(itemName, "itemName must not be null");
        Objects.requireNonNull(unitPrice, "unitPrice must not be null");

        if (itemName.isBlank()) {
            throw new IllegalArgumentException("itemName must not be blank");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }

        if (unitPrice.signum() < 0) {
            throw new IllegalArgumentException("unitPrice must not be negative");
        }
    }

    public OrderLine(String itemName, String quantityText, BigDecimal unitPrice) {
        int parsedQuantity = Integer.parseInt(quantityText);
        if (parsedQuantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }

        this(itemName, parsedQuantity, unitPrice);
    }

    public BigDecimal lineTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}

package com.suprun.java21;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Yurii_Suprun
 */
public record DrinkItem(String name, BigDecimal price) implements Item {
    public DrinkItem {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(price, "price must not be null");

        if (name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }

        if (price.signum() < 0) {
            throw new IllegalArgumentException("price must not be negative");
        }
    }
}

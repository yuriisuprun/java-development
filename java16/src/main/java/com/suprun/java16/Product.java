package com.suprun.java16;

import java.math.BigDecimal;
import java.util.Objects;

public record Product(String name, BigDecimal price) {
    public Product {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name must not be blank");
        }

        Objects.requireNonNull(price, "price must not be null");

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product price must not be negative");
        }
    }
}

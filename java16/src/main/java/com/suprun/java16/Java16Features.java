package com.suprun.java16;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

public final class Java16Features {
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    private Java16Features() {
    }

    public static String describe(Object value) {
        if (value instanceof Product product) {
            return "%s costs %s".formatted(product.name(), product.price());
        }

        if (value instanceof String text) {
            return "Text with %d characters".formatted(text.length());
        }

        if (value instanceof Integer number) {
            return "Integer value %d".formatted(number);
        }

        return "Unsupported value: %s".formatted(value);
    }

    public static Product applyDiscount(Product product, BigDecimal discountPercent) {
        Objects.requireNonNull(product, "product must not be null");
        Objects.requireNonNull(discountPercent, "discountPercent must not be null");

        if (discountPercent.compareTo(BigDecimal.ZERO) < 0 || discountPercent.compareTo(ONE_HUNDRED) > 0) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100");
        }

        BigDecimal discountMultiplier = BigDecimal.ONE.subtract(discountPercent.divide(ONE_HUNDRED));
        BigDecimal discountedPrice = product.price()
                .multiply(discountMultiplier)
                .setScale(2, RoundingMode.HALF_UP);

        return new Product(product.name(), discountedPrice);
    }

    public static List<String> productNames(List<Product> products) {
        Objects.requireNonNull(products, "products must not be null");

        return products.stream()
                .map(Product::name)
                .toList();
    }
}

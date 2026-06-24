package com.suprun.java21;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.SequencedCollection;

public final class Java21Features {
    private static final Locale CURRENCY_LOCALE = Locale.US;

    private Java21Features() {
    }

    public static String describeProduct(Product product) {
        Objects.requireNonNull(product, "product must not be null");

        return "Product %d: %s costs %s".formatted(
                product.id(),
                product.name(),
                formatPrice(product.price())
        );
    }

    public static String describeItem(Item item) {
        Objects.requireNonNull(item, "item must not be null");

        return switch (item) {
            case FoodItem(String name, BigDecimal price) -> "Food item %s costs %s".formatted(name, formatPrice(price));
            case DrinkItem(String name, BigDecimal price) -> "Drink item %s costs %s".formatted(name, formatPrice(price));
        };
    }

    public static int numberOfBedrooms(Building building) {
        Objects.requireNonNull(building, "building must not be null");

        if (building instanceof House(String ignoredAddress, int numberOfBedrooms, boolean ignoredHasBasement)) {
            return numberOfBedrooms;
        }

        throw new IllegalArgumentException("Unsupported building type: " + building.getClass().getName());
    }

    public static <T> T newest(SequencedCollection<T> values) {
        Objects.requireNonNull(values, "values must not be null");

        if (values.isEmpty()) {
            throw new IllegalArgumentException("values must not be empty");
        }

        return values.getLast();
    }

    private static String formatPrice(BigDecimal price) {
        return NumberFormat.getCurrencyInstance(CURRENCY_LOCALE).format(price);
    }
}

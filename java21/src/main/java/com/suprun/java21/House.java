package com.suprun.java21;

import java.util.Objects;

/**
 * @author Yurii_Suprun
 */
public record House(String address, int numberOfBedrooms, boolean hasBasement) implements Building {
    public House {
        Objects.requireNonNull(address, "address must not be null");

        if (address.isBlank()) {
            throw new IllegalArgumentException("address must not be blank");
        }

        if (numberOfBedrooms < 0) {
            throw new IllegalArgumentException("numberOfBedrooms must not be negative");
        }
    }
}

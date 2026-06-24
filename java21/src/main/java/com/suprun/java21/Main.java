package com.suprun.java21;

import java.math.BigDecimal;

/**
 * @author ${USER}
 */
public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Product product = new Product(1L, "Laptop", BigDecimal.valueOf(1_000));
        Item apple = new FoodItem("Apple", BigDecimal.valueOf(0.99));
        Item water = new DrinkItem("Water", BigDecimal.valueOf(1.30));
        Building house = new House("123 World 5y", 3, true);

        System.out.println(Java21Features.describeProduct(product));
        System.out.println(Java21Features.describeItem(apple));
        System.out.println(Java21Features.describeItem(water));
        System.out.println("Bedrooms: " + Java21Features.numberOfBedrooms(house));
    }
}

package com.suprun;

import java.util.List;

import static java.lang.StringTemplate.STR;
import static java.util.FormatProcessor.FMT;

/**
 * @author ${USER}
 */
public class Main {
    public static void main(String[] args) {

        var product = new Product(1L, "Laptop", 1000.00);

        // String templates
//        String str1 = STR."Product with id \ {product.productId()} is \ {product.name()} and has prise $\ {product.price()}";
//        String str2 = FMT."Product with id \ {product.productId()} is \ {product.name()} and has prise $%.2f\ {product.price()}";

        // Record patterns
        var apple = new FoodItem("Apple", 0.99);
        var water = new DrinkItem("Water", 1.30);
        System.out.println(apple);
        System.out.println(water);

        // Unnamed patterns
        var house = new House("123 World 5y", 3, true);
//        printNumberOfBedrooms(house);

//        List<String> items = List.of("Apple", "Banana", "Cherry");
//        // Unnamed variable
//        for (var _ : items) {
//            System.out.println("Processing an item...");
//        }
    }

//    public static void printItem(Item item) {
//        if (item instanceof FoodItem(var name, var price)) {
//            System.out.println("Food Item: " + name + " costs $" + price);
//        } else if (item instanceof DrinkItem(var name, var price)) {
//            System.out.println("Drink Item: " + name + " costs $" + price);
//        }
//    }

//        public static void printNumberOfBedrooms(Building building) {
//        if (building instanceof House(_, var numberOfBedrooms, _)) {
//            System.out.println("Number of bedrooms: " + numberOfBedrooms);
//        }
//    }
}
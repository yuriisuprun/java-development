package com.suprun.designpatterns.structural.decorator;

/**
 * Demo class for the Decorator design pattern.
 * Shows how to add features to a coffee object dynamically.
 *
 * @author Yurii_Suprun
 */
public class Main {

    public static void main(String[] args) {
        // Create a basic coffee
        Coffee coffee = new BasicCoffee();
        System.out.println("Basic Coffee:");
        System.out.println("Description: " + coffee.getDescription());
        System.out.println("Cost: $" + coffee.getCost());

        System.out.println();

        // Decorate with milk
        coffee = new MilkDecorator(coffee);
        System.out.println("Coffee with Milk:");
        System.out.println("Description: " + coffee.getDescription());
        System.out.println("Cost: $" + coffee.getCost());

        System.out.println();

        // Decorate with sugar
        coffee = new SugarDecorator(coffee);
        System.out.println("Coffee with Milk and Sugar:");
        System.out.println("Description: " + coffee.getDescription());
        System.out.println("Cost: $" + coffee.getCost());
    }
}


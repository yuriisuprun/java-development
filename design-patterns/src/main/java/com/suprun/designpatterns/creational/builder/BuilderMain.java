package com.suprun.designpatterns.creational.builder;

/**
 * Demo class for the Builder design pattern.
 * Shows how to use the CarBuilder to create Car instances with various configurations.
 *
 * @author Yurii_Suprun
 */
public class BuilderMain {

    public static void main(String[] args) {
        // Create a car with air conditioning and sunroof
        Car car1 = new Car.CarBuilder("V8", 4)
                .setAirConditioning(true)
                .setSunroof(true)
                .build();
        System.out.println("Car 1: " + car1);

        // Create a car with only air conditioning
        Car car2 = new Car.CarBuilder("V6", 4)
                .setAirConditioning(true)
                .build();
        System.out.println("Car 2: " + car2);

        // Create a basic car with no optional features
        Car car3 = new Car.CarBuilder("I4", 4)
                .build();
        System.out.println("Car 3: " + car3);
    }
}


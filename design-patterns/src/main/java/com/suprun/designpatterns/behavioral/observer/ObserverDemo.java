package com.suprun.designpatterns.behavioral.observer;

/**
 * Demo class for the Observer design pattern.
 * Shows how observers are notified when the observed object's state changes.
 *
 * @author Yurii_Suprun
 */
public class ObserverDemo {

    public static void main(String[] args) {
        // Create a weather station (subject)
        WeatherStation station = new WeatherStation();

        // Create observers
        Observer phoneDisplay = new PhoneDisplay();
        Observer windowDisplay = new WindowDisplay();

        // Register observers
        System.out.println("=== Registering Observers ===");
        station.registerObserver(phoneDisplay);
        station.registerObserver(windowDisplay);

        // Update temperature - both observers are notified
        System.out.println("\n=== Temperature Update: 25.5°C ===");
        station.setTemperature(25.5f);

        System.out.println("\n=== Temperature Update: 31.2°C ===");
        station.setTemperature(31.2f);

        // Remove an observer
        System.out.println("\n=== Removing Window Display ===");
        station.removeObserver(windowDisplay);

        // Update temperature - only phone display is notified
        System.out.println("\n=== Temperature Update: 28.0°C ===");
        station.setTemperature(28.0f);
    }
}


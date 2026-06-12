package com.suprun.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * WeatherStation is a concrete Subject that manages observers and notifies them
 * when the temperature changes. This class demonstrates the Observer design pattern.
 *
 * @author Yurii_Suprun
 */
public class WeatherStation implements Subject {

    private final List<Observer> observers = new ArrayList<>();
    private float temperature;

    @Override
    public void registerObserver(Observer observer) {
        Objects.requireNonNull(observer, "Observer cannot be null");
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        Objects.requireNonNull(observer, "Observer cannot be null");
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    /**
     * Sets the temperature and notifies all registered observers.
     *
     * @param temperature the new temperature value
     */
    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    /**
     * Gets the current temperature.
     *
     * @return the current temperature
     */
    public float getTemperature() {
        return temperature;
    }
}


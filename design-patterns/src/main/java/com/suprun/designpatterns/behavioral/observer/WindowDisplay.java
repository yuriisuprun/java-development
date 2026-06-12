package com.suprun.designpatterns.behavioral.observer;

/**
 * WindowDisplay is an observer that displays temperature updates on a window display.
 * Implements the Observer interface to receive updates from a Subject.
 *
 * @author Yurii_Suprun
 */
public class WindowDisplay implements Observer {

    @Override
    public void update(float temperature) {
        System.out.println("Window Display: Temperature updated to " + temperature + "°C");
    }
}


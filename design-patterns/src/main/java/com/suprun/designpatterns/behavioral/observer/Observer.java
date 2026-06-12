package com.suprun.designpatterns.behavioral.observer;

/**
 * Observer interface for the Observer design pattern.
 * Defines the contract for objects that want to be notified of state changes.
 *
 * @author Yurii_Suprun
 */
public interface Observer {

    /**
     * Called when the observed object's state changes.
     *
     * @param temperature the new temperature value
     */
    void update(float temperature);
}

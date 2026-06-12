package com.suprun.designpatterns.behavioral.observer;

/**
 * Subject interface for the Observer design pattern.
 * Defines the contract for objects that manage observers and notify them of state changes.
 *
 * @author Yurii_Suprun
 */
public interface Subject {

    /**
     * Registers an observer to be notified of state changes.
     *
     * @param observer the observer to register
     */
    void registerObserver(Observer observer);

    /**
     * Removes an observer from the notification list.
     *
     * @param observer the observer to remove
     */
    void removeObserver(Observer observer);

    /**
     * Notifies all registered observers of the state change.
     */
    void notifyObservers();
}

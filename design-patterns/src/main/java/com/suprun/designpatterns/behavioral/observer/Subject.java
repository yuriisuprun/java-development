package com.suprun.designpatterns.behavioral.observer;

interface Subject {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

package com.suprun.designpatterns.behavioral.observer;

public class PhoneDisplay implements Observer {

    @Override
    public void update(float temperature) {
        System.out.println("Phone Display: Temperature updated to " + temperature + "°C");
    }
}

class WindowDisplay implements Observer {

    @Override
    public void update(float temperature) {
        System.out.println("Window Display: Temperature updated to " + temperature + "°C");
    }
}

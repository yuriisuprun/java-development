package com.suprun.designpatterns.behavioral.observer;

public class ObserverDemo {

    public static void main(String[] args) {

        WeatherStation station = new WeatherStation();

        Observer phone = new PhoneDisplay();
        Observer window = new WindowDisplay();

        station.registerObserver(phone);
        station.registerObserver(window);

        station.setTemperature(25.5f);
        station.setTemperature(31.2f);

        station.removeObserver(window);

        station.setTemperature(28.0f);
    }
}


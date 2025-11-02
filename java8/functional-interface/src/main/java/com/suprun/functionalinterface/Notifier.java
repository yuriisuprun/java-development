package com.suprun.functionalinterface;

@FunctionalInterface
public interface Notifier {
    void send(String message, String recipient);
}
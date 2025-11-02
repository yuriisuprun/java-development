package com.suprun.functionalinterface;

public class NotificationService {

    public static void sendEmail(String message, String email) {
        System.out.println("Sending email to " + email + " with message: " + message);
    }

    public void sendSMS(String message, String phoneNumber) {
        System.out.println("Sending SMS to " + phoneNumber + " with message: " + message);
    }
}

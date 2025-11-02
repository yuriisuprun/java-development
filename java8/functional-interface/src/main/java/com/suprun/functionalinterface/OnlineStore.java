package com.suprun.functionalinterface;

public class OnlineStore {

    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        // Lambda expression: custom notification
        Notifier pushNotifier = (msg, user) ->
                System.out.println("Push Notification to " + user + ": " + msg);

        // Method reference: static method
        Notifier emailNotifier = NotificationService::sendEmail;

        // Method reference: instance method
        Notifier smsNotifier = service::sendSMS;

        processOrder("Order #123 shipped!", "alice@example.com", emailNotifier);
        processOrder("Order #456 delivered!", "+15551234", smsNotifier);
        processOrder("Order #789 confirmed!", "Bob", pushNotifier);
    }

    static void processOrder(String message, String recipient, Notifier notifier) {
        System.out.println("Processing order update...");
        notifier.send(message, recipient);
        System.out.println("---------------------------------\n");
    }
}

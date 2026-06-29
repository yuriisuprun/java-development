package com.suprun.java25;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        OrderLine orderLine = new OrderLine("Keyboard", "2", BigDecimal.valueOf(49.99));
        System.out.println("Line total: " + orderLine.lineTotal());

        String greeting = Java25Features.runWithUser("Alice", () ->
                System.out.println("Processing request for " + Java25Features.currentUserGreeting()));
        System.out.println(greeting);

        String derivedKey = Java25Features.deriveKeyHex(
                "initial-key-material".getBytes(StandardCharsets.UTF_8),
                "salt-value".getBytes(StandardCharsets.UTF_8),
                "context-info".getBytes(StandardCharsets.UTF_8),
                32
        );
        System.out.println("Derived key: " + derivedKey);
    }
}

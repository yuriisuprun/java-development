package com.suprun.java25;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class Java25FeaturesTest {
    @Test
    void createsOrderLineWithFlexibleConstructorBody() {
        OrderLine orderLine = new OrderLine("Mouse", "3", BigDecimal.valueOf(19.99));

        assertThat(orderLine.quantity()).isEqualTo(3);
        assertThat(orderLine.lineTotal()).isEqualByComparingTo("59.97");
    }

    @Test
    void rejectsInvalidQuantityBeforeCanonicalConstructor() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new OrderLine("Mouse", "0", BigDecimal.valueOf(19.99)))
                .withMessage("quantity must be positive");
    }

    @Test
    void bindsScopedValueForCurrentThread() {
        AtomicReference<String> observedUser = new AtomicReference<>();

        String greeting = Java25Features.runWithUser("Bob", () ->
                observedUser.set(Java25Features.currentUserGreeting()));

        assertThat(observedUser.get()).isEqualTo("Hello, Bob");
        assertThat(greeting).isEqualTo("Hello, Bob");
    }

    @Test
    void derivesDeterministicKeyMaterialWithKdfApi() {
        byte[] inputKeyMaterial = "initial-key-material".getBytes(StandardCharsets.UTF_8);
        byte[] salt = "salt-value".getBytes(StandardCharsets.UTF_8);
        byte[] info = "context-info".getBytes(StandardCharsets.UTF_8);

        String first = Java25Features.deriveKeyHex(inputKeyMaterial, salt, info, 32);
        String second = Java25Features.deriveKeyHex(inputKeyMaterial, salt, info, 32);

        assertThat(first).hasSize(64);
        assertThat(first).isEqualTo(second);
    }

    @Test
    void rejectsInvalidKdfArguments() {
        byte[] inputKeyMaterial = "initial-key-material".getBytes(StandardCharsets.UTF_8);
        byte[] salt = "salt-value".getBytes(StandardCharsets.UTF_8);
        byte[] info = "context-info".getBytes(StandardCharsets.UTF_8);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Java25Features.deriveKeyHex(inputKeyMaterial, salt, info, 0))
                .withMessage("length must be positive");
    }
}

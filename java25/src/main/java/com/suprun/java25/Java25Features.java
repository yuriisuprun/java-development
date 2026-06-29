package com.suprun.java25;

import javax.crypto.KDF;
import javax.crypto.spec.HKDFParameterSpec;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HexFormat;
import java.util.Objects;

/**
 * Demonstrates finalized Java 25 language and library features.
 */
public final class Java25Features {
    private static final ScopedValue<String> REQUEST_USER = ScopedValue.newInstance();

    private Java25Features() {
    }

    /**
     * JEP 506: Scoped Values — bind immutable request context for the current thread.
     */
    public static String currentUserGreeting() {
        return "Hello, " + REQUEST_USER.get();
    }

    public static String runWithUser(String username, Runnable action) {
        Objects.requireNonNull(username, "username must not be null");
        Objects.requireNonNull(action, "action must not be null");

        return ScopedValue.where(REQUEST_USER, username).call(() -> {
            action.run();
            return currentUserGreeting();
        });
    }

    /**
     * JEP 510: Key Derivation Function API — derive key material with HKDF-SHA256.
     */
    public static String deriveKeyHex(byte[] inputKeyMaterial, byte[] salt, byte[] info, int length) {
        Objects.requireNonNull(inputKeyMaterial, "inputKeyMaterial must not be null");
        Objects.requireNonNull(salt, "salt must not be null");
        Objects.requireNonNull(info, "info must not be null");

        if (length <= 0) {
            throw new IllegalArgumentException("length must be positive");
        }

        try {
            KDF hkdf = KDF.getInstance("HKDF-SHA256");
            AlgorithmParameterSpec params = HKDFParameterSpec.ofExtract()
                    .addIKM(inputKeyMaterial)
                    .addSalt(salt)
                    .thenExpand(info, length);

            return HexFormat.of().formatHex(hkdf.deriveData(params));
        } catch (Exception exception) {
            throw new IllegalStateException("Failed to derive key material", exception);
        }
    }
}

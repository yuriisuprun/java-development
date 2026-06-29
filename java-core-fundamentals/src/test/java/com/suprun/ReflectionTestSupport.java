package com.suprun;

/**
 * Wraps reflection calls for tests without relying on Lombok or checked-exception propagation.
 */
final class ReflectionTestSupport {
    @FunctionalInterface
    interface ThrowingRunnable {
        void run() throws ReflectiveOperationException;
    }

    @FunctionalInterface
    interface ThrowingSupplier<T> {
        T get() throws ReflectiveOperationException;
    }

    private ReflectionTestSupport() {
    }

    static void run(ThrowingRunnable action) {
        try {
            action.run();
        } catch (ReflectiveOperationException exception) {
            throw new AssertionError(exception);
        }
    }

    static <T> T get(ThrowingSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (ReflectiveOperationException exception) {
            throw new AssertionError(exception);
        }
    }
}

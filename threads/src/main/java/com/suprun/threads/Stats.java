package com.suprun.threads;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Thread-safe counter backed by {@link AtomicLong}.
 *
 * @author Yurii_Suprun
 */
public final class Stats {

    private final AtomicLong counter = new AtomicLong();

    /**
     * Increments the counter atomically.
     */
    public void increment() {
        counter.incrementAndGet();
    }

    /**
     * @return the current counter value
     */
    public long getCounter() {
        return counter.get();
    }
}

package com.suprun.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Demonstrates concurrent counter increments with raw threads and an executor.
 *
 * @author Yurii_Suprun
 */
public final class CounterDemo {

    static final int INCREMENTS_PER_THREAD = 10_000;
    static final int THREAD_COUNT = 2;

    private CounterDemo() {
    }

    /**
     * Increments {@code stats} from multiple raw {@link Thread} instances.
     *
     * @param stats shared counter
     * @return final counter value after all threads complete
     * @throws InterruptedException if a thread join is interrupted
     */
    public static long runWithRawThreads(Stats stats) throws InterruptedException {
        Runnable incrementTask = () -> {
            for (int i = 0; i < INCREMENTS_PER_THREAD; i++) {
                stats.increment();
            }
        };

        Thread[] threads = IntStream.range(0, THREAD_COUNT)
                .mapToObj(i -> new Thread(incrementTask, "increment-thread-" + i))
                .toArray(Thread[]::new);

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        return stats.getCounter();
    }

    /**
     * Increments {@code stats} using a fixed-size {@link ExecutorService}.
     *
     * @param stats shared counter
     * @return final counter value after all tasks complete
     * @throws InterruptedException if awaiting termination is interrupted
     */
    public static long runWithExecutor(Stats stats) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        try {
            for (int i = 0; i < THREAD_COUNT; i++) {
                executor.execute(() -> {
                    for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                        stats.increment();
                    }
                });
            }
        } finally {
            executor.shutdown();
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        }
        return stats.getCounter();
    }
}

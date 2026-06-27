package com.suprun.threads;

/**
 * Entry point for thread concurrency demonstrations.
 *
 * @author Yurii_Suprun
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {
        long expected = (long) CounterDemo.THREAD_COUNT * CounterDemo.INCREMENTS_PER_THREAD;

        long rawThreadsResult = CounterDemo.runWithRawThreads(new Stats());
        System.out.println("Raw threads counter: " + rawThreadsResult + " (expected " + expected + ")");

        long executorResult = CounterDemo.runWithExecutor(new Stats());
        System.out.println("Executor counter: " + executorResult + " (expected " + expected + ")");
    }
}

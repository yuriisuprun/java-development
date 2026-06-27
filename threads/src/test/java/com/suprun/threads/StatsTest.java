package com.suprun.threads;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatsTest {

    @Test
    void incrementsAtomicallyFromMultipleThreads() throws InterruptedException {
        Stats stats = new Stats();
        long result = CounterDemo.runWithRawThreads(stats);

        assertThat(result).isEqualTo((long) CounterDemo.THREAD_COUNT * CounterDemo.INCREMENTS_PER_THREAD);
    }

    @Test
    void incrementsFromExecutorTasks() throws InterruptedException {
        Stats stats = new Stats();
        long result = CounterDemo.runWithExecutor(stats);

        assertThat(result).isEqualTo((long) CounterDemo.THREAD_COUNT * CounterDemo.INCREMENTS_PER_THREAD);
    }
}

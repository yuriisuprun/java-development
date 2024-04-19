package com.suprun;

import com.suprun.threads.Stats;
import lombok.SneakyThrows;

import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;

/**
 * @author Yurii_Suprun
 */
public class Main {
    @SneakyThrows
    public static void main(String[] args) {

        Stats stats = new Stats();

        var executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {
            for (int i = 0; i < 10_000; i++) {
                stats.increment();
            }
        });
        executor.shutdown();



//        Runnable incrementLogic = () -> {
//            for (int i = 0; i < 10_000; i++) {
//                stats.increment();
//            }
//        };
        Runnable incrementLogic = () -> {
            synchronized (stats) {
                for (int i = 0; i < 10_000; i++) {
                    stats.increment();
                }
            }
        };
//        Thread thread1 = new Thread(incrementLogic);
//        Thread thread2 = new Thread(incrementLogic);
//        thread1.start();
//        thread2.start();
//        thread1.join();
//        thread2.join();
        System.out.println(stats.getCounter());


//        System.out.println("From thread" + Thread.currentThread().getName());
////        TimeUnit.SECONDS.sleep(1);
//
//        var thread = new Thread(() -> {
//            System.out.println("From thread" + Thread.currentThread().getName());
//            System.out.println("12345");
//        });
//        thread.setName("firstThread");
//        thread.start();
    }
}
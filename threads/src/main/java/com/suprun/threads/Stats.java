package com.suprun.threads;

/**
 * @author Yurii_Suprun
 */
public class Stats {

    private long counter = 0L;

    public void increment(){
        counter++;
    }

    public long getCounter(){
        return counter;
    }
}

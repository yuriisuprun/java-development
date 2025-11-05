package com.suprun.designpatterns.creational.singleton;

/**
 * @author Yurii_Suprun
 */
public class Singleton {

    private static Singleton instance = null;
    private Singleton(){

        if (instance != null) {
            throw new IllegalStateException("Singleton instance already exists. Use getInstance() method.");
        }
    }

    public static Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}

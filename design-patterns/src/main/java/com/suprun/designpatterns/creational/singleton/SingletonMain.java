package com.suprun.designpatterns.creational.singleton;

/**
 * @author Yurii_Suprun
 */
public class SingletonMain {
    public static void main(String[] args) {

        Singleton instance = Singleton.getInstance();
        System.out.println(instance.toString());

        System.out.println("Hello Singleton!");
    }
}
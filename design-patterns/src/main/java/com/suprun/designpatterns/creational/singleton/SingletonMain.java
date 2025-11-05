package com.suprun.designpatterns.creational.singleton;

/**
 * @author Yurii_Suprun
 */
public class SingletonMain {
    public static void main(String[] args) {

        // testing classic Singleton
        Singleton instance = Singleton.getInstance();
        System.out.println(instance.toString());

        // testing enum Singleton
        EnumSingleton enumInstance = EnumSingleton.INSTANCE;
        EnumSingleton enumInstance2 = EnumSingleton.INSTANCE;
        System.out.println(enumInstance);
        System.out.println(enumInstance2);
    }
}
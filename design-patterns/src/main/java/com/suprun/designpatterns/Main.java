package com.suprun.designpatterns;

import com.suprun.designpatterns.creational.Singleton;

/**
 * @author Yurii_Suprun
 */
public class Main {
    public static void main(String[] args) {

        Singleton instance = Singleton.getInstance();
        System.out.println(instance.toString());

        System.out.println("Hello design patterns!");
    }
}
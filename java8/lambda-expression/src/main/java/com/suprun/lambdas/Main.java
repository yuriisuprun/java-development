package com.suprun.lambdas;

import com.suprun.lambdas.functionalinterface.MyFunctionalInterface;

public class Main {
    public static void main(String[] args) {
        // Traditional way - direct lambda/method reference
        MyFunctionalInterface<String, Integer> mfi1 = Integer::parseInt;

        // Using composition with andThen
        MyFunctionalInterface<String, Integer> mfi2 = mfi1.andThen(x -> x * x);

        // Multi-line lambda expression
        MyFunctionalInterface<String, Integer> mfi3 = str -> {
            int i = Integer.parseInt(str);
            return i * i;
        };

        // Using the factory method for explicit creation
        MyFunctionalInterface<String, Integer> mfi4 = MyFunctionalInterface.of(Integer::parseInt);

        // Using identity function in composition
        MyFunctionalInterface<Integer, Integer> identity = MyFunctionalInterface.identity();
        MyFunctionalInterface<Integer, Integer> addTen = identity.andThen(x -> x + 10);

        System.out.println("Traditional method reference: " + mfi1.apply("8"));           // 8
        System.out.println("With andThen composition: " + mfi2.apply("8"));              // 64
        System.out.println("Multi-line lambda: " + mfi3.apply("8"));                      // 64
        System.out.println("Factory method: " + mfi4.apply("8"));                         // 8
        System.out.println("Identity with composition: " + addTen.apply(8));              // 18
    }
}


package com.suprun.lambdas;

import com.suprun.lambdas.functionalinterface.MyFunctionalInterface;

/**
 * @author Yurii_Suprun
 */
public class Main {
    public static void main(String[] args) {
        MyFunctionalInterface<String, Integer> mfi1 = Integer::parseInt;

        MyFunctionalInterface<String, Integer> mfi2 = mfi1.andThen(x -> x * x);

        MyFunctionalInterface<String, Integer> mfi3 = str -> {
            int i = Integer.parseInt(str);
            return i * i;
        };

        System.out.println(mfi1.apply("8"));
        System.out.println(mfi2.apply("8"));
        System.out.println(mfi3.apply("8"));
    }
}

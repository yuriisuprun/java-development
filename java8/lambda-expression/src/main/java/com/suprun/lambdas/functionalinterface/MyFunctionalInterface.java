package com.suprun.lambdas.functionalinterface;

/**
 * @author Yurii_Suprun
 */
public interface MyFunctionalInterface<T, R> {

    R apply(T t);

    default <U> MyFunctionalInterface<T, U> andThen(MyFunctionalInterface<R, U> after) {
        return x -> after.apply(apply(x));
    }
}

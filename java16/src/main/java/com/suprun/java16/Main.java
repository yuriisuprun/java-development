package com.suprun.java16;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var products = List.of(
                new Product("Laptop", BigDecimal.valueOf(1_000.00)),
                new Product("Mouse", BigDecimal.valueOf(25.50))
        );

        products.stream()
                .map(product -> Java16Features.applyDiscount(product, BigDecimal.TEN))
                .map(Java16Features::describe)
                .forEach(System.out::println);

        System.out.println(Java16Features.productNames(products));
    }
}

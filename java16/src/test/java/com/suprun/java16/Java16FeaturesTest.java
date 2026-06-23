package com.suprun.java16;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class Java16FeaturesTest {
    @Test
    void describeProductWithPatternMatchingForInstanceof() {
        var product = new Product("Keyboard", BigDecimal.valueOf(89.99));

        assertThat(Java16Features.describe(product))
                .isEqualTo("Keyboard costs 89.99");
    }

    @Test
    void appliesDiscountToProductRecord() {
        var product = new Product("Monitor", BigDecimal.valueOf(250.00));

        Product discounted = Java16Features.applyDiscount(product, BigDecimal.valueOf(15));

        assertThat(discounted).isEqualTo(new Product("Monitor", new BigDecimal("212.50")));
    }

    @Test
    void rejectsDiscountOutsideValidRange() {
        var product = new Product("Monitor", BigDecimal.valueOf(250.00));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Java16Features.applyDiscount(product, BigDecimal.valueOf(101)))
                .withMessage("Discount percent must be between 0 and 100");
    }

    @Test
    void returnsUnmodifiableProductNamesWithStreamToList() {
        var products = List.of(
                new Product("Laptop", BigDecimal.valueOf(1_000.00)),
                new Product("Mouse", BigDecimal.valueOf(25.50))
        );

        List<String> names = Java16Features.productNames(products);

        assertThat(names).containsExactly("Laptop", "Mouse");
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> names.add("Keyboard"));
    }
}

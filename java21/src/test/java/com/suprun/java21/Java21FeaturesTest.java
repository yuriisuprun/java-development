package com.suprun.java21;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class Java21FeaturesTest {
    @Test
    void describesProduct() {
        Product product = new Product(1L, "Laptop", BigDecimal.valueOf(1_000));

        assertThat(Java21Features.describeProduct(product))
                .isEqualTo("Product 1: Laptop costs $1,000.00");
    }

    @Test
    void describesItemsWithRecordPatternsAndPatternSwitch() {
        assertThat(Java21Features.describeItem(new FoodItem("Apple", BigDecimal.valueOf(0.99))))
                .isEqualTo("Food item Apple costs $0.99");

        assertThat(Java21Features.describeItem(new DrinkItem("Water", BigDecimal.valueOf(1.30))))
                .isEqualTo("Drink item Water costs $1.30");
    }

    @Test
    void extractsHouseBedroomsWithRecordPattern() {
        Building house = new House("123 World 5y", 3, true);

        assertThat(Java21Features.numberOfBedrooms(house)).isEqualTo(3);
    }

    @Test
    void returnsNewestSequencedCollectionElement() {
        assertThat(Java21Features.newest(List.of("first", "second", "third"))).isEqualTo("third");
        assertThat(Java21Features.newest(new LinkedHashSet<>(List.of("first", "second")))).isEqualTo("second");
    }

    @Test
    void rejectsInvalidValues() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Product(0, "Laptop", BigDecimal.ONE))
                .withMessage("id must be positive");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new House("123 World 5y", -1, false))
                .withMessage("numberOfBedrooms must not be negative");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Java21Features.newest(List.of()))
                .withMessage("values must not be empty");
    }
}

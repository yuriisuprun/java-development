package com.suprun.improved_switch;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NumberUtilsTest {

    @Test
    void returnsProductOfTwoLargestPositiveNumbers() {
        assertThat(NumberUtils.maxProductOfTwoNumbers(new int[]{3, 5, 1, 7}))
                .isEqualTo(35);
    }

    @Test
    void returnsProductOfTwoSmallestNegativeNumbersWhenTheyProduceMaximum() {
        assertThat(NumberUtils.maxProductOfTwoNumbers(new int[]{-10, -20, 3, 4}))
                .isEqualTo(200);
    }

    @Test
    void doesNotChangeInputArrayOrder() {
        int[] numbers = {3, 5, 1, 7};

        NumberUtils.maxProductOfTwoNumbers(numbers);

        assertThat(numbers).containsExactly(3, 5, 1, 7);
    }

    @Test
    void rejectsArraysWithFewerThanTwoNumbers() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> NumberUtils.maxProductOfTwoNumbers(new int[]{1}))
                .withMessage("At least two numbers are required");
    }
}

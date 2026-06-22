package com.suprun.improved_switch;

public final class NumberUtils {

    private NumberUtils() {
    }

    public static int maxProductOfTwoNumbers(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            throw new IllegalArgumentException("At least two numbers are required");
        }

        int largest = Math.max(numbers[0], numbers[1]);
        int secondLargest = Math.min(numbers[0], numbers[1]);
        int smallest = secondLargest;
        int secondSmallest = largest;

        for (int i = 2; i < numbers.length; i++) {
            int number = numbers[i];

            if (number > largest) {
                secondLargest = largest;
                largest = number;
            } else if (number > secondLargest) {
                secondLargest = number;
            }

            if (number < smallest) {
                secondSmallest = smallest;
                smallest = number;
            } else if (number < secondSmallest) {
                secondSmallest = number;
            }
        }

        return Math.max(largest * secondLargest, smallest * secondSmallest);
    }
}

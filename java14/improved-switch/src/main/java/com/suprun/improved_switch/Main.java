package com.suprun.improved_switch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * @author Yurii_Suprun
 */
enum Season {WINTER, SPRING, SUMMER, FALL}

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        int stage = random.nextInt(10);
        String season = switch (stage) {
            case 1 -> "Winter";
            case 2 -> "Spring";
            case 3 -> "Summer";
            case 4 -> "Autumn";
            default -> {
                System.out.println("Provided season is invalid");
                yield "Invalid stage";
            }
        };
        System.out.println(season);

        // switch usage with enums
        Season seasonEnum = Season.SPRING;
        String weather = switch (seasonEnum) {
            case WINTER -> "Snowy";
            case SPRING -> "Warn";
            case SUMMER -> "Sunny";
            case FALL -> "Rainy";
            default -> {
                System.out.println("Provided season is invalid");
                yield "Invalid stage";
            }
        };
        System.out.println(weather);
    }

    public int countVowels(String s) {
        String normalizedString = s.toLowerCase().trim();
        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
        int counter = 0;
        for (char c : vowels) {
            if (normalizedString.contains(Character.toString(c))) {
                counter++;
            }
        }
        return counter;
    }

    public int countVowels2(String s) {
        String normalizedString = s.toLowerCase().trim();
        String vowels = "aeiouy";
        int counter = 0;
        for (char c : normalizedString.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                counter++;
            }
        }
        return counter;
    }

    public int countVowels3StreamApi(String s) {
        String normalizedString = s.toLowerCase().trim();
        String vowels = "aeiouy";
        return (int) normalizedString.chars()
                .filter(c -> vowels.indexOf(c) != -1)
                .count();
    }

    public int maxProductOfTwoNumbers(int[] numbers) {
        if (numbers.length < 2) {
            return Integer.MIN_VALUE;
        }
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - 1; j++) {
                if (numbers[j] < numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        return numbers[0] * numbers[1];
    }

    public int maxProductOfTwoNumbers2(int[] numbers) {
        if (numbers.length < 2) {
            return Integer.MIN_VALUE;
        }
        // Sort in descending order
        Arrays.sort(numbers);
        // Get the two largest numbers
        return numbers[numbers.length - 1] * numbers[numbers.length - 2];
    }
}

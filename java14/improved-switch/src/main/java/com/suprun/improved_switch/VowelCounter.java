package com.suprun.improved_switch;

import java.util.Objects;

public final class VowelCounter {

    private static final String STANDARD_VOWELS = "aeiou";
    private static final String VOWELS_WITH_Y = STANDARD_VOWELS + "y";

    private VowelCounter() {
    }

    public static int countUniqueStandardVowels(String value) {
        String normalizedValue = normalize(value);
        int counter = 0;

        for (int i = 0; i < STANDARD_VOWELS.length(); i++) {
            if (normalizedValue.indexOf(STANDARD_VOWELS.charAt(i)) >= 0) {
                counter++;
            }
        }

        return counter;
    }

    public static int countVowelsIncludingY(String value) {
        String normalizedValue = normalize(value);
        int counter = 0;

        for (int i = 0; i < normalizedValue.length(); i++) {
            if (VOWELS_WITH_Y.indexOf(normalizedValue.charAt(i)) >= 0) {
                counter++;
            }
        }

        return counter;
    }

    public static int countVowelsIncludingYWithStreams(String value) {
        String normalizedValue = normalize(value);
        return (int) normalizedValue.chars()
                .filter(character -> VOWELS_WITH_Y.indexOf(character) >= 0)
                .count();
    }

    private static String normalize(String value) {
        return Objects.requireNonNull(value, "value must not be null")
                .toLowerCase()
                .trim();
    }
}

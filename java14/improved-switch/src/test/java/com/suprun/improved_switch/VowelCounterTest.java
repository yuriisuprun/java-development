package com.suprun.improved_switch;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VowelCounterTest {

    @Test
    void countsUniqueStandardVowels() {
        assertThat(VowelCounter.countUniqueStandardVowels("  Education  "))
                .isEqualTo(5);
    }

    @Test
    void countsEveryVowelIncludingY() {
        assertThat(VowelCounter.countVowelsIncludingY("Rhythm and blues"))
                .isEqualTo(4);
    }

    @Test
    void streamImplementationMatchesLoopImplementation() {
        String value = "Java switch expressions";

        assertThat(VowelCounter.countVowelsIncludingYWithStreams(value))
                .isEqualTo(VowelCounter.countVowelsIncludingY(value));
    }
}

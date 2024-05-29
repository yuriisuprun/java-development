package com.suprun.coding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test class for {@link Coding}
 *
 * @author Yurii_Suprun
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CodingTest {

    private Coding coding;

    @BeforeEach
    void setUp() {
        coding = new Coding();
    }

    @Test
    @Order(1)
    void testBubbleSortAlgorithm() {
        int maxSubstringLength1 = coding.lengthOfLongestSubstring("abcabcbb");
        int maxSubstringLength2 = coding.lengthOfLongestSubstring("bbbbb");
        int maxSubstringLength3 = coding.lengthOfLongestSubstring("pwwkew");

        assertEquals(3, maxSubstringLength1);
        assertEquals(1, maxSubstringLength2);
        assertEquals(3, maxSubstringLength3);
    }
}
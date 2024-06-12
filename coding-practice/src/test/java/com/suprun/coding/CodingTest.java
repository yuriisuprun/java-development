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
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void testLengthOfLongestSubstring() {
        int maxSubstringLength1 = coding.lengthOfLongestSubstring("abcabcbb");
        int maxSubstringLength2 = coding.lengthOfLongestSubstring("bbbbb");
        int maxSubstringLength3 = coding.lengthOfLongestSubstring("pwwkew");

        assertEquals(3, maxSubstringLength1);
        assertEquals(1, maxSubstringLength2);
        assertEquals(3, maxSubstringLength3);
    }

    @Test
    @Order(2)
    void testToSumAlgorithm() {
        int[] targetIndexes1 = coding.toSum(new int[]{2, 4, 5, 6}, 8);
        int[] targetIndexes2 = coding.toSum(new int[]{11, 7, 2, 15}, 9);
        int[] targetIndexes3 = coding.toSum(new int[]{3, 3}, 6);

        assertEquals(Arrays.toString(new int[]{0, 3}), Arrays.toString(targetIndexes1));
        assertEquals(Arrays.toString(new int[]{1, 2}), Arrays.toString(targetIndexes2));
        assertEquals(Arrays.toString(new int[]{0, 1}), Arrays.toString(targetIndexes3));
    }

    @Test
    @Order(3)
    void testGetDuplicate() {
        int result = coding.getDuplicate(Arrays.asList(2, 4, 5, 4, 6));

        assertEquals(4, result);
    }

    @Test
    @Order(4)
    void testGetDuplicateStreamApi() {
        int result = coding.getDuplicateUsingStreamApi(Arrays.asList(2, 4, 5, 4, 6));

        assertEquals(4, result);
    }

    @Test
    @Order(5)
    void testDuplicates(){
        String expected = "abcda";
        String actual = coding.removeDuplicates("abccccda");
        assertEquals(expected, actual);
    }

    @Test
    @Order(6)
    void testDuplicatesNullThrowsException(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            coding.removeDuplicates(null);
        });
        assertEquals("Input string must not be null or empty.", exception.getMessage());
    }

    @Test
    @Order(7)
    void testDuplicate(){
        String expected = "abcda";
        String actual = coding.removeDuplicates("abcda");
        assertEquals(expected, actual);
    }

    @Test
    @Order(8)
    void testDuplicate2(){
        String expected = "abcda";
        String actual = coding.removeDuplicates("abcddda");
        assertEquals(expected, actual);
    }

    @Test
    @Order(9)
    void testEmptyStringThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            coding.removeDuplicates("");
        });
        assertEquals("Input string must not be null or empty.", exception.getMessage());
    }

    @Test
    @Order(10)
    void testSingleCharacterString() {
        assertEquals("a", coding.removeDuplicates("a"));
    }

    @Test
    @Order(11)
    void testStringWithNoDuplicates() {
        assertEquals("abcde", coding.removeDuplicates("abcde"));
    }

    @Test
    @Order(12)
    void testStringWithAllSameCharacters() {
        assertEquals("a", coding.removeDuplicates("aaaaa"));
    }

    @Test
    @Order(13)
    void testStringWithConsecutiveDuplicates() {
        assertEquals("abcda", coding.removeDuplicates("abccccda"));
    }

    @Test
    @Order(14)
    void testStringWithNonConsecutiveDuplicates() {
        assertEquals("abcbacd", coding.removeDuplicates("abccbacd"));
    }

    @Test
    @Order(15)
    void testStringWithSpecialCharacters() {
        assertEquals("a!b@c#", coding.removeDuplicates("a!!b@@c##"));
    }

    @Test
    @Order(16)
    void testStringWithWhitespace() {
        // Edge case: string with whitespace
        assertEquals("a b c", coding.removeDuplicates("a  b  c"));
    }

    @Test
    @Order(17)
    void testStringWithNumbers() {
        assertEquals("12345", coding.removeDuplicates("1122334455"));
    }

//    @Test
//    @Order(18)
//    void testStringWithMixedCase() {
//        // Regular case: string with mixed case characters
//        assertEquals("AaBbCc", Main.removeDuplicates("AaAaBbBbCcCc"));
//    }

    @Test
    @Order(19)
    void testLongString() {
        // Edge case: very long string
        String input = "a".repeat(1000) + "b".repeat(1000) + "c".repeat(1000);
        String expected = "abc";
        assertEquals(expected, coding.removeDuplicates(input));
    }
}
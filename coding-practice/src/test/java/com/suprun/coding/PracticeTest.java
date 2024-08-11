package com.suprun.coding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Yurii_Suprun
 */
public class PracticeTest {

    private Practice practice;

    @BeforeEach
    void setUp() {
        practice = new Practice();
    }

    @Test
    void reverseString_ShouldReturnCorrectString_WhenValidStringProvided() {
        String actual = practice.reverseString("Propagation");
        assertEquals("noitagaporP", actual);
    }

    @Test
    void reverseString_ShouldReturnCorrectString_WhenEmptyStringProvided() {
        String actual = practice.reverseString("");
        assertEquals("", actual);
    }

    @Test
    void reverseString_ShouldReturnCorrectString_WhenOneSymbolStringProvided() {
        String actual = practice.reverseString("P");
        assertEquals("P", actual);
    }

    @Test
    void reverseString_ShouldReturnCorrectString_WhenPalindromeStringProvided() {
        String actual = practice.reverseString("rotator");
        assertEquals("rotator", actual);
    }

    @Test
    void reverseString_ShouldReturnCorrectString_WhenStringWithSpaceProvided() {
        String actual = practice.reverseString("Propa gation");
        assertEquals("noitag aporP", actual);
    }

    @Test
    void reverseString_ShouldReturnCorrectString_WhenSpecialSymbolStringProvided() {
        String actual = practice.reverseString("#@");
        assertEquals("@#", actual);
    }

    @Test
    void reverseStringWithConcatenation() {
        String actual = practice.reverseStringWithConcatenation("Propagation");
        assertEquals("noitagaporP", actual);
    }

    @Test
    void reverseStringWithStringBuilder() {
        String actual = practice.reverseStringWithStringBuilder("Propagation");
        assertEquals("noitagaporP", actual);
    }

    @Test
    void filterEvenNumbers() {
        List<Integer> actual = practice.filterEvenNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(Arrays.asList(2, 4, 6), actual);
    }

    @Test
    void maxNumber() {
        int actual = practice.maxNumber(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(6, actual);
    }

    @Test
    void testLengthOfLongestSubstring() {
        int maxSubstringLength1 = practice.lengthOfLongestSubstring("abcabcbb");
        int maxSubstringLength2 = practice.lengthOfLongestSubstring("bbbbb");
        int maxSubstringLength3 = practice.lengthOfLongestSubstring("pwwkew");

        assertEquals(3, maxSubstringLength1);
        assertEquals(1, maxSubstringLength2);
        assertEquals(3, maxSubstringLength3);
    }

    @Test
    void testToSumAlgorithm() {
        int[] targetIndexes1 = practice.toSum(new int[]{2, 4, 5, 6}, 8);
        int[] targetIndexes2 = practice.toSum(new int[]{11, 7, 2, 15}, 9);
        int[] targetIndexes3 = practice.toSum(new int[]{3, 3}, 6);

        assertEquals(Arrays.toString(new int[]{0, 3}), Arrays.toString(targetIndexes1));
        assertEquals(Arrays.toString(new int[]{1, 2}), Arrays.toString(targetIndexes2));
        assertEquals(Arrays.toString(new int[]{0, 1}), Arrays.toString(targetIndexes3));
    }

    @Test
    void getDuplicate_ShouldReturnCorrectDuplicateNumber_WhenListWithDuplicatesProvided() {
        int actual = practice.getDuplicate(Arrays.asList(1, 2, 2, 3, 4, 5, 6));
        assertEquals(2, actual);
    }

    @Test
    void getDuplicateUsingStreamApi_ShouldReturnCorrectDuplicateNumber_WhenListWithDuplicatesProvided() {
        int actual = practice.getDuplicateUsingStreamApi(Arrays.asList(1, 2, 2, 3, 4, 5, 6));
        assertEquals(2, actual);
    }

    @Test
    void getDuplicateWithArray_ShouldReturnCorrectDuplicateNumber_WhenListWithDuplicatesProvided() {
        int actual = practice.getDuplicateWithArray(Arrays.asList(1, 2, 2, 3, 4, 5, 6));
        assertEquals(2, actual);
    }

    @Test
    void getDuplicate() {
        int actual = practice.getDuplicate(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(0, actual);
    }

    @Test
    void removeDuplicates() {
        String actual = practice.removeDuplicates("pppolymorphism");
        assertEquals("polymorphism", actual);
    }

    @Test
    void isPalindrome() {
        boolean actual = practice.isPalindrome("kayak");
        assertTrue(actual);
    }

    @Test
    void isPalindrome_false() {
        boolean actual = practice.isPalindrome("abstraction");
        assertFalse(actual);
    }


    @Test
    void getSortedHashTags() {
        List<String> tweets = List.of("This JEP is #typescript for scientific #python",
                "and it makes #java operations consistently #python.",
                "The default #java operations are #python or strictfp,",
                "#Java both of which guarantee the same results from the #java calculations on every platform.");

        List<String> hashTags = practice.getSortedHashTags(tweets);
        assertEquals(List.of("#java", "#python", "#typescript"), hashTags);
    }

    @Test
    void bubbleSortArray() {
        int[] result = practice.bubbleSortArray(new int[]{5, 3, 1, 0, 2, 4});
        assertEquals(Arrays.toString(new int[]{0, 1, 2, 3, 4, 5}), Arrays.toString(result));
    }

    @Test
    void bubbleSortArray_emptyArray() {
        int[] result = practice.bubbleSortArray(new int[]{});
        assertEquals(Arrays.toString(new int[]{}), Arrays.toString(result));
    }

    @Test
    void bubbleSortArray_minMaxIntegers() {
        assertArrayEquals(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE},
                practice.bubbleSortArray(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0}));
    }

    @Test
    void bubbleSortArray_duplicateValues() {
        assertArrayEquals(new int[]{1, 2, 2, 4, 4}, practice.bubbleSortArray(new int[]{4, 2, 4, 1, 2}));
    }

    @Test
    void bubbleSortArray_negativeAndPositiveIntegers() {
        assertArrayEquals(new int[]{-2, -1, 0, 3, 5}, practice.bubbleSortArray(new int[]{-1, 3, -2, 5, 0}));
    }

    @Test
    void bubbleSortArray_singleElementArray() {
        assertArrayEquals(new int[]{42}, practice.bubbleSortArray(new int[]{42}));
    }
}

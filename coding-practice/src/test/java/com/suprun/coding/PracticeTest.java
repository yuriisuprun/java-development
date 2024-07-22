package com.suprun.coding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Yurii_Suprun
 */
public class PracticeTest {

    private Practice practice;

    @BeforeEach
    public void setUp(){
        practice = new Practice();
    }

    @Test
    public void reverseString_ShouldReturnCorrectString_WhenValidStringProvided(){
        String actual = practice.reverseString("Propagation");
        assertEquals("noitagaporP", actual);
    }

    @Test
    public void reverseString_ShouldReturnCorrectString_WhenEmptyStringProvided(){
        String actual = practice.reverseString("");
        assertEquals("", actual);
    }

    @Test
    public void reverseString_ShouldReturnCorrectString_WhenOneSymbolStringProvided(){
        String actual = practice.reverseString("P");
        assertEquals("P", actual);
    }

    @Test
    public void reverseString_ShouldReturnCorrectString_WhenPalindromeStringProvided(){
        String actual = practice.reverseString("rotator");
        assertEquals("rotator", actual);
    }

    @Test
    public void reverseString_ShouldReturnCorrectString_WhenStringWithSpaceProvided(){
        String actual = practice.reverseString("Propa gation");
        assertEquals("noitag aporP", actual);
    }

    @Test
    public void reverseString_ShouldReturnCorrectString_WhenSpecialSymbolStringProvided(){
        String actual = practice.reverseString("#@");
        assertEquals("@#", actual);
    }

    @Test
    public void reverseStringWithConcatenation(){
        String actual = practice.reverseStringWithConcatenation("Propagation");
        assertEquals("noitagaporP", actual);
    }

    @Test
    public void reverseStringWithStringBuilder(){
        String actual = practice.reverseStringWithStringBuilder("Propagation");
        assertEquals("noitagaporP", actual);
    }

    @Test
    public void filterEvenNumbers(){
        List<Integer> actual = practice.filterEvenNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(Arrays.asList(2, 4, 6), actual);
    }

    @Test
    public void maxNumber(){
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
    public void getDuplicate_ShouldReturnCorrectDuplicateNumber_WhenListWithDuplicatesProvided(){
        int actual = practice.getDuplicate(Arrays.asList(1, 2, 2, 3, 4, 5, 6));
        assertEquals(2, actual);
    }

    @Test
    public void getDuplicateUsingStreamApi_ShouldReturnCorrectDuplicateNumber_WhenListWithDuplicatesProvided(){
        int actual = practice.getDuplicateUsingStreamApi(Arrays.asList(1, 2, 2, 3, 4, 5, 6));
        assertEquals(2, actual);
    }

    @Test
    public void getDuplicateWithArray_ShouldReturnCorrectDuplicateNumber_WhenListWithDuplicatesProvided(){
        int actual = practice.getDuplicateWithArray(Arrays.asList(1, 2, 2, 3, 4, 5, 6));
        assertEquals(2, actual);
    }

    @Test
    public void getDuplicate(){
        int actual = practice.getDuplicate(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(0, actual);
    }

    @Test
    public void removeDuplicates(){
        String actual = practice.removeDuplicates("pppolymorphism");
        assertEquals("polymorphism", actual);
    }

    @Test
    public void isPalindrome(){
        boolean actual = practice.isPalindrome("kayak");
        assertTrue(actual);
    }

    @Test
    public void isPalindrome_false(){
        boolean actual = practice.isPalindrome("abstraction");
        assertFalse(actual);
    }
}

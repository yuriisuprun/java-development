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
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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

    @Test
    @Order(20)
    void testBubbleSortAlgorithm() {
        int[] originalArray = {3, 60, 35, 2, 45, 320, 5};
        int[] expectedArray = {2, 3, 5, 35, 45, 60, 320};
        int[] actualArray = coding.bubbleSort(originalArray);

        assertEquals(Arrays.toString(expectedArray), Arrays.toString(actualArray));
    }

    @Test
    @Order(21)
    void testReverseArray() {
        int[] numbers = {25, 5, 4, 67, 85, 104};
        int[] expectedArray = {104, 85, 67, 4, 5, 25};
        int[] actualArray = coding.reverseArray(numbers);

        assertEquals(Arrays.toString(expectedArray), Arrays.toString(actualArray));
    }

    @Test
    @Order(22)
    void testFindMinInArray() {
        int[] numbers = {25, 5, 4, 67, 85, 104};
        Integer Integer = 4;
        int actualMin = coding.findMinInArray(numbers);

        assertEquals(Integer, actualMin);
    }

    @Test
    @Order(23)
    void testFindMinInArrayGenericComparable() {
        Integer[] numbers = {25, 5, 4, 67, 85, 104};
        Integer Integer = 4;
        int actualMin = coding.findMinInArrayGenericComparable(numbers);

        assertEquals(Integer, actualMin);
    }

    @Test
    @Order(24)
    void testFindMinInArrayGenericComparator() {
        Integer[] numbers = {25, 5, 4, 67, 85, 104};
        Integer Integer = 4;
        int actualMin = coding.findMinInArrayGenericComparator(numbers, (o1, o2) -> o1.compareTo(o2));

        assertEquals(Integer, actualMin);
    }

    @Test
    @Order(25)
    void testIsTextPalindrome_true() {
        String text = "A man, a plan, a canal, Panama!";
        boolean result = coding.isTextPalindrome(text);

        assertTrue(result);
    }

    @Test
    @Order(26)
    void testIsTextPalindrome_false() {
        String text = "Banana";
        boolean result = coding.isTextPalindrome(text);

        assertFalse(result);
    }

    @Test
    @Order(27)
    void testReversePositiveInteger() {
        int originalInt = 123;
        int result = coding.reverseInteger(originalInt);

        assertEquals(321, result);
    }

    @Test
    @Order(28)
    void testReverseNegativeInteger() {
        int originalInt = -123;
        int result = coding.reverseInteger(originalInt);

        assertEquals(-321, result);
    }

    @Test
    @Order(29)
    void testReverseInteger() {
        int originalInt = 120;
        int result = coding.reverseInteger(originalInt);

        assertEquals(21, result);
    }

    @Test
    @Order(30)
    void testReversePositiveIntegerWithoutString() {
        int originalInt = 2147483647;
        int result = coding.reverseIntegerWithoutString(originalInt);

        assertEquals(0, result);
    }

    @Test
    @Order(31)
    void testReverseNegativeIntegerWithoutString() {
        int originalInt = -2147483648;
        int result = coding.reverseIntegerWithoutString(originalInt);

        assertEquals(0, result);
    }

    @Test
    @Order(32)
    void testReverseIntegerWithoutString() {
        int originalInt = 120;
        int result = coding.reverseIntegerWithoutString(originalInt);

        assertEquals(21, result);
    }

    @Test
    @Order(33)
    void testFindMissedNumber() {
        int[] integers = {4, 5, 6, 7, 8, 10, 11};
        int result = coding.findMissedNumber(integers);

        assertEquals(9, result);
    }

    @Test
    @Order(34)
    void testFindMissedNumberBySum() {
        int[] integers = {4, 5, 6, 8, 9, 10, 11};
        int result = coding.findMissedNumberBySum(integers);

        assertEquals(7, result);
    }

    @Test
    @Order(35)
    @DisplayName("insertionSort method returns correct sorted array")
    void testSortArray() {
        int[] integers = {5, 2, 4, 6, 1};
        int[] expected = {1, 2, 4, 5, 6};
        int[] actual = coding.insertionSort(integers);

        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Test
    @Order(36)
    @DisplayName("areAnagrams returns correct value")
    void testAreAnagrams_true() {
        boolean actual1 = coding.areAnagrams("HEART", "EARTH");
        boolean actual2 = coding.areAnagrams("LISTEN", "SILENT");

        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    @Order(37)
    @DisplayName("areAnagrams returns correct value")
    void testAreAnagrams_false() {
        boolean actual1 = coding.areAnagrams("HEART", "EARTH2");
        boolean actual2 = coding.areAnagrams("LISTEN", "SILENT2");

        assertFalse(actual1);
        assertFalse(actual2);
    }

    @Test
    @Order(38)
    @DisplayName("areAnagrams returns correct value")
    void testAreAnagramsOopApproach_true() {
        boolean actual1 = coding.areAnagramsOopApproach("HEART", "EARTH");
        boolean actual2 = coding.areAnagramsOopApproach("LISTEN", "SILENT");

        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    @Order(39)
    @DisplayName("areAnagrams returns correct value")
    void testAreAnagramsOopApproach_false() {
        boolean actual1 = coding.areAnagramsOopApproach("HEART", "EARTH2");
        boolean actual2 = coding.areAnagramsOopApproach("LISTEN", "SILENT2");

        assertFalse(actual1);
        assertFalse(actual2);
    }

    @Test
    @Order(40)
    @DisplayName("filterUniqueHashtags returns correct value")
    void testfilterUniqueHashtags() {
        Set<String> hashSet = new HashSet<>();
        Collections.addAll(hashSet, "#applications", "#strict", "#mainly", "#floating-point");
        Set<String> actual = coding.filterUniqueHashtags();

        assertEquals(hashSet, actual);
    }

    @Test
    @Order(41)
    void reverseString_ShouldReturnCorrectString_WhenValidStringProvided() {
        String actual = coding.reverseString("Propagation");
        assertEquals("noitagaporP", actual);
    }

    @Test
    @Order(42)
    void reverseString_ShouldReturnCorrectString_WhenEmptyStringProvided() {
        String actual = coding.reverseString("");
        assertEquals("", actual);
    }

    @Test
    @Order(43)
    void reverseString_ShouldReturnCorrectString_WhenOneSymbolStringProvided() {
        String actual = coding.reverseString("P");
        assertEquals("P", actual);
    }

    @Test
    @Order(44)
    void reverseString_ShouldReturnCorrectString_WhenPalindromeStringProvided() {
        String actual = coding.reverseString("rotator");
        assertEquals("rotator", actual);
    }

    @Test
    @Order(45)
    void reverseString_ShouldReturnCorrectString_WhenStringWithSpaceProvided() {
        String actual = coding.reverseString("Propa gation");
        assertEquals("noitag aporP", actual);
    }

    @Test
    @Order(46)
    void reverseString_ShouldReturnCorrectString_WhenSpecialSymbolStringProvided() {
        String actual = coding.reverseString("#@");
        assertEquals("@#", actual);
    }

    @Test
    @Order(47)
    void reverseStringWithConcatenation() {
        String actual = coding.reverseStringWithConcatenation("Propagation");
        assertEquals("noitagaporP", actual);
    }

    @Test
    @Order(48)
    void reverseStringWithStringBuilder() {
        String actual = coding.reverseStringWithStringBuilder("Propagation");
        assertEquals("noitagaporP", actual);
    }

    @Test
    @Order(49)
    void filterEvenNumbers() {
        List<Integer> actual = coding.filterEvenNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(Arrays.asList(2, 4, 6), actual);
    }

    @Test
    @Order(50)
    void maxNumber() {
        int actual = coding.maxNumber(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(6, actual);
    }

    @Test
    @Order(51)
    void getDuplicate_ShouldReturnCorrectDuplicateNumber_WhenListWithDuplicatesProvided() {
        int actual = coding.getDuplicate(Arrays.asList(1, 2, 2, 3, 4, 5, 6));
        assertEquals(2, actual);
    }

    @Test
    @Order(52)
    void getDuplicateUsingStreamApi_ShouldReturnCorrectDuplicateNumber_WhenListWithDuplicatesProvided() {
        int actual = coding.getDuplicateUsingStreamApi(Arrays.asList(1, 2, 2, 3, 4, 5, 6));
        assertEquals(2, actual);
    }

    @Test
    @Order(53)
    void getDuplicateWithArray_ShouldReturnCorrectDuplicateNumber_WhenListWithDuplicatesProvided() {
        int actual = coding.getDuplicateWithArray(Arrays.asList(1, 2, 2, 3, 4, 5, 6));
        assertEquals(2, actual);
    }

    @Test
    @Order(54)
    void getDuplicate() {
        int actual = coding.getDuplicate(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(0, actual);
    }

    @Test
    @Order(55)
    void removeDuplicates() {
        String actual = coding.removeDuplicates("pppolymorphism");
        assertEquals("polymorphism", actual);
    }

    @Test
    @Order(56)
    void isPalindrome() {
        boolean actual = coding.isPalindrome("kayak");
        assertTrue(actual);
    }

    @Test
    @Order(57)
    void isPalindrome_false() {
        boolean actual = coding.isPalindrome("abstraction");
        assertFalse(actual);
    }


    @Test
    @Order(58)
    void getSortedHashTags() {
        List<String> tweets = List.of("This JEP is #typescript for scientific #python",
                "and it makes #java operations consistently #python.",
                "The default #java operations are #python or strictfp,",
                "#Java both of which guarantee the same results from the #java calculations on every platform.");

        List<String> hashTags = coding.getSortedHashTags(tweets);
        assertEquals(List.of("#java", "#python", "#typescript"), hashTags);
    }

    @Test
    @Order(59)
    void bubbleSortArray() {
        int[] result = coding.bubbleSortArray(new int[]{5, 3, 1, 0, 2, 4});
        assertEquals(Arrays.toString(new int[]{0, 1, 2, 3, 4, 5}), Arrays.toString(result));
    }

    @Test
    @Order(60)
    void bubbleSortArray_emptyArray() {
        int[] result = coding.bubbleSortArray(new int[]{});
        assertEquals(Arrays.toString(new int[]{}), Arrays.toString(result));
    }

    @Test
    @Order(61)
    void bubbleSortArray_minMaxIntegers() {
        assertArrayEquals(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE},
                coding.bubbleSortArray(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0}));
    }

    @Test
    @Order(62)
    void bubbleSortArray_duplicateValues() {
        assertArrayEquals(new int[]{1, 2, 2, 4, 4}, coding.bubbleSortArray(new int[]{4, 2, 4, 1, 2}));
    }

    @Test
    @Order(63)
    void bubbleSortArray_negativeAndPositiveIntegers() {
        assertArrayEquals(new int[]{-2, -1, 0, 3, 5}, coding.bubbleSortArray(new int[]{-1, 3, -2, 5, 0}));
    }

    @Test
    @Order(64)
    void bubbleSortArray_singleElementArray() {
        assertArrayEquals(new int[]{42}, coding.bubbleSortArray(new int[]{42}));
    }
}
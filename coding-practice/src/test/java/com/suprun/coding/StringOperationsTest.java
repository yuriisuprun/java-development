package com.suprun.coding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link StringOperations}
 *
 * @author Yurii_Suprun
 */
@DisplayName("String Operations Tests")
public class StringOperationsTest {

    private StringOperations stringOps;

    @BeforeEach
    void setUp() {
        stringOps = new StringOperations();
    }

    @Test
    @DisplayName("Should reverse string using char array")
    void testReverseString() {
        assertEquals("olleh", stringOps.reverseString("hello"));
        assertEquals("a", stringOps.reverseString("a"));
        assertEquals("", stringOps.reverseString(""));
    }

    @Test
    @DisplayName("Should reverse string using concatenation")
    void testReverseStringWithConcatenation() {
        assertEquals("olleh", stringOps.reverseStringWithConcatenation("hello"));
        assertEquals("a", stringOps.reverseStringWithConcatenation("a"));
    }

    @Test
    @DisplayName("Should reverse string using StringBuilder")
    void testReverseStringWithStringBuilder() {
        assertEquals("olleh", stringOps.reverseStringWithStringBuilder("hello"));
        assertEquals("a", stringOps.reverseStringWithStringBuilder("a"));
    }

    @ParameterizedTest
    @CsvSource({
            "racecar, true",
            "hello, false",
            "a, true",
            "ab, false",
            "aba, true"
    })
    @DisplayName("Should check if string is palindrome")
    void testIsPalindrome(String input, boolean expected) {
        assertEquals(expected, stringOps.isPalindrome(input));
    }

    @Test
    @DisplayName("Should check palindrome case-insensitive using StringBuilder")
    void testIsPalindromeStringBuilder() {
        assertTrue(stringOps.isPalindromeStringBuilder("Racecar"));
        assertFalse(stringOps.isPalindromeStringBuilder("Hello"));
    }

    @Test
    @DisplayName("Should check palindrome ignoring spaces and punctuation")
    void testIsTextPalindrome() {
        assertTrue(stringOps.isTextPalindrome("A man a plan a canal Panama"));
        assertFalse(stringOps.isTextPalindrome("race a car")); // raceacar is not a palindrome
        assertFalse(stringOps.isTextPalindrome("hello world"));
    }

    @Test
    @DisplayName("Should remove consecutive duplicate characters")
    void testRemoveDuplicates() {
        assertEquals("abcda", stringOps.removeDuplicates("abccccda"));
        assertEquals("abc", stringOps.removeDuplicates("abc"));
        assertEquals("a", stringOps.removeDuplicates("aaa"));
    }

    @Test
    @DisplayName("Should throw exception for null or empty string in removeDuplicates")
    void testRemoveDuplicatesThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> stringOps.removeDuplicates(null));
        assertThrows(IllegalArgumentException.class, () -> stringOps.removeDuplicates(""));
    }

    @ParameterizedTest
    @CsvSource({
            "listen, silent, true",
            "heart, earth, true",
            "hello, world, false",
            "aab, aba, true"
    })
    @DisplayName("Should check if strings are anagrams")
    void testAreAnagrams(String s1, String s2, boolean expected) {
        assertEquals(expected, stringOps.areAnagrams(s1, s2));
    }

    @Test
    @DisplayName("Should check anagrams with OOP approach")
    void testAreAnagramsOopApproach() {
        assertTrue(stringOps.areAnagramsOopApproach("listen", "silent"));
        assertFalse(stringOps.areAnagramsOopApproach("hello", "world"));
    }

    @Test
    @DisplayName("Should toggle string case")
    void testToggleString() {
        assertEquals("HELLO", stringOps.toggleString("hello"));
        assertEquals("hello", stringOps.toggleString("HELLO"));
        assertEquals("HeLLo WoRLD", stringOps.toggleString("hEllO wOrld"));
        assertEquals("", stringOps.toggleString(""));
    }

    @Test
    @DisplayName("Should remove character at specified index")
    void testRemoveCharAt() {
        assertEquals("hllo", stringOps.removeCharAt("hello", 1)); // removes 'e'
        assertEquals("helo", stringOps.removeCharAt("hello", 2)); // removes 'l'
        assertEquals("ello", stringOps.removeCharAt("hello", 0)); // removes 'h'
    }

    @Test
    @DisplayName("Should throw exception for invalid index in removeCharAt")
    void testRemoveCharAtThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> stringOps.removeCharAt("hello", -1));
        assertThrows(IllegalArgumentException.class, () -> stringOps.removeCharAt("hello", 5));
    }

    @Test
    @DisplayName("Should find longest substring without repeating characters")
    void testLengthOfLongestSubstring() {
        assertEquals(3, stringOps.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, stringOps.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, stringOps.lengthOfLongestSubstring("pwwkew"));
        assertEquals(0, stringOps.lengthOfLongestSubstring(""));
    }

    @Test
    @DisplayName("Should count unique strings in text")
    void testCountUniqueStrings() {
        assertEquals(3, StringOperations.countUniqueStrings("Hello world! Hello Java, hello world."));
        assertEquals(0, StringOperations.countUniqueStrings(""));
        assertEquals(0, StringOperations.countUniqueStrings(null));
    }

    @Test
    @DisplayName("Should sort strings by length")
    void testSortStringsByLength() {
        List<String> input = List.of("cat", "white", "my", "flower", "home");
        List<String> result = StringOperations.sortStringsByLength(input);
        assertEquals(List.of("my", "cat", "home", "white", "flower"), result);
    }

    @Test
    @DisplayName("Should join strings with comma separator")
    void testJoinStrings() {
        assertEquals("a, b, c, d, e", StringOperations.joinStrings(List.of("a", "b", "c", "d", "e")));
        assertEquals("hello", StringOperations.joinStrings(List.of("hello")));
    }

    @Test
    @DisplayName("Should find longest string in list")
    void testGetLongestString() {
        assertEquals("background", StringOperations.getLongestString(List.of("the", "your", "background", "picture", "way")));
    }

    @Test
    @DisplayName("Should throw exception for empty list in getLongestString")
    void testGetLongestStringThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> StringOperations.getLongestString(List.of()));
    }

    @Test
    @DisplayName("Should check if strings are anagrams with spaces")
    void testIsAnagram() {
        assertTrue(stringOps.isAnagram(new String[]{"listen", "silent"}));
        assertTrue(stringOps.isAnagram(new String[]{"the eyes", "they see"}));
        assertFalse(stringOps.isAnagram(new String[]{"hello", "world"}));
    }

    @Test
    @DisplayName("Should throw exception for invalid input in isAnagram")
    void testIsAnagramThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> stringOps.isAnagram(new String[]{"hello"}));
        assertThrows(IllegalArgumentException.class, () -> stringOps.isAnagram(null));
    }

    @Test
    @DisplayName("Should filter unique hashtags")
    void testFilterUniqueHashtags() {
        Set<String> hashtags = stringOps.filterUniqueHashtags();
        assertTrue(hashtags.contains("#mainly"));
        assertTrue(hashtags.contains("#applications"));
        assertTrue(hashtags.contains("#strict"));
        assertTrue(hashtags.contains("#floating-point"));
    }

    @Test
    @DisplayName("Should get sorted hashtags from tweets")
    void testGetSortedHashTags() {
        List<String> tweets = List.of(
                "I love #java and #coding #java #java",
                "#kotlin is great #java"
        );
        List<String> result = stringOps.getSortedHashTags(tweets);
        assertEquals("#java", result.get(0)); // Most frequent
        assertTrue(result.contains("#kotlin"));
        assertTrue(result.contains("#coding"));
    }
}

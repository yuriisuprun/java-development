package com.suprun.streamapi;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * @author Yurii_Suprun
 */
@AllArgsConstructor
public class StreamApi {

    /**
     * Returns a {@link List} of strings with sorted strings by frequency
     *
     *  @param textWithHashtags a text with hashtags
     * @return list of strings
     */
    public List<String> getSortedHashCodes(String textWithHashtags) {
        return Arrays.stream(textWithHashtags.split(" "))
                .filter(word -> word.startsWith("#"))
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey).toList();
    }

    /**
     * Returns a length of the longest string in provided list
     *
     * @param list a list of strings
     * @return a string length
     */
    public int getLongestStringLength(List<String> list) {
        OptionalInt response = list.stream()
                .mapToInt(String::length)
                .max();
        if (response.isPresent()) {
            return response.getAsInt();
        } else {
            return 0;
        }
    }


    /**
     * Returns a sorted {@link List} of strings without first letters
     *
     * @param list a list of strings
     * @return a list of strings
     */
    public List<String> deleteFirstLetterFromStrings(List<String> list) {
        return list.stream()
                .map(string -> string.substring(1))
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    /**
     * Returns a letters sum of string which length is more than @param wordLengthToCount
     *
     * @param list a list of strings
     * @param wordLengthToCount a list of strings
     * @return a string length
     */
    public int countLettersForStringsLongerThan(List<String> list, int wordLengthToCount) {
        return list.stream()
                .map(String::length)
                .filter(length -> length > wordLengthToCount)
                .reduce(0, Integer::sum);

//        return originalList.stream()
//                .mapToInt(String::length)
//                .filter(length -> length > wordLengthToCount)
//                .sum();
    }
}
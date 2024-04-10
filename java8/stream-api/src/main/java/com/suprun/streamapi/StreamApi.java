package com.suprun.streamapi;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * @author Yurii_Suprun
 */
@AllArgsConstructor
public class StreamApi {

    /**
     * Returns List of strings with sorted strings by frequency
     *
     *  @param textWithHashtags a text with hashtags
     * @return list of Strings
     */
    public List<String> getSortedHashCodes(String textWithHashtags) {
        return Arrays.stream(textWithHashtags.split(" "))
                .filter(word -> word.startsWith("#"))
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey).toList();
    }

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

    public List<String> deleteFirstLetterFromStrings(List<String> list) {
        return list.stream()
                .map(string -> string.substring(1))
                .toList();
    }

    public int countLettersForStringsLongerThan(List<String> originalList, int wordLengthToCount) {
        return originalList.stream()
                .map(String::length)
                .filter(length -> length > wordLengthToCount)
                .reduce(0, Integer::sum);

//        return originalList.stream()
//                .mapToInt(String::length)
//                .filter(length -> length > wordLengthToCount)
//                .sum();
    }
}
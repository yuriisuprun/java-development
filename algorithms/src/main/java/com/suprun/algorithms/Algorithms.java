package com.suprun.algorithms;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Yurii_Suprun
 */
@AllArgsConstructor
public class Algorithms {

    /**
     * Returns a {@link List} of strings with sorted strings by frequency
     *
     * @param textWithHashtags a text with hashtags
     * @return list of strings
     */
    public List<String> getSortedHashCodes(String textWithHashtags) {
        return Arrays.stream(textWithHashtags.split(" "))
                .filter(word -> word.startsWith("#"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey).toList();
    }

    public int[] bubbleSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++){
                if(array[j + 1] < array[j]){
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}
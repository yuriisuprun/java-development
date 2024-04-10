package com.suprun.algorithms;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
     * Returns a sorted array using bubble sort algorithm
     *
     * @param array to sort
     * @return a sorted array
     */
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

    public int[] reverseArray(int[] numbers) {
        int temp;
        for (int i = 0; i < numbers.length/2; i++) {
            temp = numbers[i];
            numbers[i] = numbers[numbers.length - i - 1];
            numbers[numbers.length - i - 1] = temp;
        }
        return numbers;
    }

    public void reverseArray(Integer[] numbers) {
        List<Integer> numbersList = new ArrayList<>(Arrays.asList(numbers));
        Collections.reverse(numbersList);
    }
}
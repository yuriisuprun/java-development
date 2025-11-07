package com.suprun.coding;

import java.util.Arrays;
import java.util.*;
import java.util.stream.IntStream;

public class ArrayTasks {

    public static void main(String... args) {
        Integer[] numbers1 = {1, 2, 3, 4, 5};

        int[] numbers2 = new int[6];
        numbers2[0] = 6;
        numbers2[1] = 7;
        numbers2[2] = 8;
        numbers2[3] = -3;
        numbers2[4] = 110;
        numbers2[5] = 11;

        String[] names = {"Alice", "Bob", "Charlie"};

        Integer[] numbersReversed = reverseArray(numbers1);
        int[] numbersReversed2 = reverseIntArrayWithStream(numbers2);
        int[] findMaximumAndMinimumElement = findMaximumAndMinimumElement(numbers2);
        String[] namesReversed = reverseArray(names);
        System.out.println(Arrays.toString(numbersReversed));
        System.out.println(Arrays.toString(namesReversed));
        System.out.println(Arrays.toString(numbersReversed2));
        System.out.println(Arrays.toString(findMaximumAndMinimumElement));
        System.out.println("=====================");
        System.out.println(Arrays.toString(ArrayTasks.bubbleSortArray(numbers2)));
        System.out.println("=====================");
        System.out.println("=====================");
        int[] numbersReversed3 = ArrayTasks.reverseArray(numbers2);
        System.out.println(Arrays.toString(numbersReversed3));
        System.out.println("=====================");
    }

    private static <T> T[] reverseArray(T[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            T temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        return arr;
    }

    private static int[] reverseIntArrayWithStream(int[] array) {
        return IntStream.rangeClosed(1, array.length)
                .map(i -> array[array.length - i])
                .toArray();
    }

    private static int[] findMaximumAndMinimumElement(int[] array) {
        int[] minMaxArray = new int[2];
        int min = array[0];
        int max = array[0];
        for (int i : array) {
            if (i < min) {
                min = i;
            }
            if (i > max) {
                max = i;
            }
        }
        minMaxArray[0] = min;
        minMaxArray[1] = max;
        return minMaxArray;
    }

    private static int[] bubbleSortArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    private static int[] reverseArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }
}

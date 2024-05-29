package com.suprun.coding;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Yurii_Suprun
 */
@AllArgsConstructor
public class Coding {

    public int lengthOfLongestSubstring(String str) {
        if (str == null || str.isEmpty()){
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int leftPosition = 0;

        for (int rightPosition = 0; rightPosition < str.length(); rightPosition++) {
            char currentChar = str.charAt(rightPosition);

            if (map.containsKey(currentChar) && map.get(currentChar) >= leftPosition) {
                leftPosition = map.get(currentChar) + 1;
            }
            map.put(currentChar, rightPosition);
            maxLength = Math.max(maxLength, rightPosition - leftPosition + 1);
        }
        return maxLength;
    }
}
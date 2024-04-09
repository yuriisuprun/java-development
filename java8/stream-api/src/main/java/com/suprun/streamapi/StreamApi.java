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
     * Returns List of strings with the max value of balance
     *
     * @return account with max balance wrapped with optional
     */
//    public Optional<Account> findRichestPerson() {
//        return accounts.stream()
//                .max(Comparator.comparing(Account::getBalance));
//    }
    public List<String> getSortedHashCodes(String textWithHashTags) {
        return Arrays.stream(textWithHashTags.split(" "))
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
}
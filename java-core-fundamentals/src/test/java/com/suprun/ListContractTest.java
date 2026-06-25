package com.suprun;

import com.suprun.list_implementations.ArrayList;
import com.suprun.list_implementations.LinkedList;
import com.suprun.list_implementations.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ListContractTest {

    @ParameterizedTest
    @MethodSource("lists")
    void containsSupportsNullElements(List<String> list) {
        list.add("first");
        list.add(null);
        list.add("last");

        assertThat(list.contains(null)).isTrue();
        assertThat(list.contains("missing")).isFalse();
    }

    @ParameterizedTest
    @MethodSource("lists")
    void addByIndexPreservesOrder(List<Integer> list) {
        list.add(1);
        list.add(3);

        list.add(1, 2);

        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("lists")
    void removeReturnsElementAndCompactsList(List<String> list) {
        list.add("a");
        list.add("b");
        list.add("c");

        String removed = list.remove(1);

        assertThat(removed).isEqualTo("b");
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.get(1)).isEqualTo("c");
        assertThat(list.contains("b")).isFalse();
    }

    static Stream<List<?>> lists() {
        return Stream.of(new ArrayList<>(), new LinkedList<>());
    }
}

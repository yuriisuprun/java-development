package com.suprun.otherfeaturesofJava8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Type Inference and Var-Args Tests")
class TypeInferenceVarArgsTest {

    private TypeInferenceVarArgs typeInferenceVarArgs;

    @BeforeEach
    void setUp() {
        typeInferenceVarArgs = new TypeInferenceVarArgs();
    }

    @Test
    @DisplayName("Var-args integers")
    void testVarArgsIntegers() {
        int[] result = typeInferenceVarArgs.varArgsIntegers(1, 2, 3, 4, 5);
        assertEquals(5, result.length);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    @DisplayName("Var-args integers empty")
    void testVarArgsIntegersEmpty() {
        int[] result = typeInferenceVarArgs.varArgsIntegers();
        assertEquals(0, result.length);
    }

    @Test
    @DisplayName("Var-args generic types")
    void testVarArgsGeneric() {
        List<String> result = typeInferenceVarArgs.varArgsGeneric("apple", "banana", "cherry");
        assertEquals(3, result.size());
        assertEquals("apple", result.get(0));
        assertEquals("banana", result.get(1));
    }

    @Test
    @DisplayName("Var-args generic with single element")
    void testVarArgsGenericSingle() {
        List<Integer> result = typeInferenceVarArgs.varArgsGeneric(42);
        assertEquals(1, result.size());
        assertEquals(42, result.get(0));
    }

    @Test
    @DisplayName("Var-args generic with no elements")
    void testVarArgsGenericEmpty() {
        List<String> result = typeInferenceVarArgs.varArgsGeneric();
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Var-args objects")
    void testVarArgsObjects() {
        String result = typeInferenceVarArgs.varArgsObjects("hello", 42, 3.14, true);
        assertEquals("hello 42 3.14 true", result);
    }

    @Test
    @DisplayName("Var-args objects single")
    void testVarArgsObjectsSingle() {
        String result = typeInferenceVarArgs.varArgsObjects("single");
        assertEquals("single", result);
    }

    @Test
    @DisplayName("Sum var-args")
    void testSumVarArgs() {
        int sum = typeInferenceVarArgs.sumVarArgs(1, 2, 3, 4, 5);
        assertEquals(15, sum);
    }

    @Test
    @DisplayName("Sum var-args empty")
    void testSumVarArgsEmpty() {
        int sum = typeInferenceVarArgs.sumVarArgs();
        assertEquals(0, sum);
    }

    @Test
    @DisplayName("Sum var-args single")
    void testSumVarArgsSingle() {
        int sum = typeInferenceVarArgs.sumVarArgs(42);
        assertEquals(42, sum);
    }

    @Test
    @DisplayName("Product var-args")
    void testProductVarArgs() {
        int product = typeInferenceVarArgs.productVarArgs(2, 3, 4);
        assertEquals(24, product);
    }

    @Test
    @DisplayName("Product var-args single")
    void testProductVarArgsSingle() {
        int product = typeInferenceVarArgs.productVarArgs(42);
        assertEquals(42, product);
    }

    @Test
    @DisplayName("Product var-args empty throws exception")
    void testProductVarArgsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> typeInferenceVarArgs.productVarArgs());
    }

    @Test
    @DisplayName("Max var-args")
    void testMaxVarArgs() {
        int max = typeInferenceVarArgs.maxVarArgs(3, 1, 4, 1, 5, 9, 2, 6);
        assertEquals(9, max);
    }

    @Test
    @DisplayName("Max var-args single")
    void testMaxVarArgsSingle() {
        int max = typeInferenceVarArgs.maxVarArgs(42);
        assertEquals(42, max);
    }

    @Test
    @DisplayName("Max var-args empty throws exception")
    void testMaxVarArgsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> typeInferenceVarArgs.maxVarArgs());
    }

    @Test
    @DisplayName("Min var-args")
    void testMinVarArgs() {
        int min = typeInferenceVarArgs.minVarArgs(3, 1, 4, 1, 5, 9, 2, 6);
        assertEquals(1, min);
    }

    @Test
    @DisplayName("Min var-args single")
    void testMinVarArgsSingle() {
        int min = typeInferenceVarArgs.minVarArgs(42);
        assertEquals(42, min);
    }

    @Test
    @DisplayName("Min var-args empty throws exception")
    void testMinVarArgsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> typeInferenceVarArgs.minVarArgs());
    }

    @Test
    @DisplayName("Join strings")
    void testJoinStrings() {
        String result = typeInferenceVarArgs.joinStrings("-", "one", "two", "three");
        assertEquals("one-two-three", result);
    }

    @Test
    @DisplayName("Join strings with comma")
    void testJoinStringsComma() {
        String result = typeInferenceVarArgs.joinStrings(", ", "apple", "banana", "cherry");
        assertEquals("apple, banana, cherry", result);
    }

    @Test
    @DisplayName("Create list from var-args")
    void testCreateList() {
        List<String> list = typeInferenceVarArgs.createList("a", "b", "c");
        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
    }

    @Test
    @DisplayName("Count matching with type inference")
    void testCountMatching() {
        long count = typeInferenceVarArgs.countMatching(
                s -> s.length() > 2,
                "a", "ab", "abc", "abcd"
        );
        assertEquals(2, count); // "abc", "abcd"
    }

    @Test
    @DisplayName("Is sorted with type inference")
    void testIsSorted() {
        assertTrue(typeInferenceVarArgs.isSorted(1, 2, 3, 4, 5));
        assertFalse(typeInferenceVarArgs.isSorted(1, 3, 2, 4, 5));
    }

    @Test
    @DisplayName("Is sorted empty")
    void testIsSortedEmpty() {
        assertTrue(typeInferenceVarArgs.isSorted());
    }

    @Test
    @DisplayName("Flatten arrays")
    void testFlattenArrays() {
        int[] result = typeInferenceVarArgs.flattenArrays(
                new int[]{1, 2},
                new int[]{3, 4},
                new int[]{5, 6}
        );
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, result);
    }

    @Test
    @DisplayName("Flatten single array")
    void testFlattenArraysSingle() {
        int[] result = typeInferenceVarArgs.flattenArrays(new int[]{1, 2, 3});
        assertArrayEquals(new int[]{1, 2, 3}, result);
    }

    @Test
    @DisplayName("Create pair with type inference")
    void testCreatePair() {
        TypeInferenceVarArgs.Pair<String, String> pair = typeInferenceVarArgs.createPair("answer", "world");
        assertEquals("answer", pair.getFirst());
        assertEquals("world", pair.getSecond());
    }

    @Test
    @DisplayName("Create pair with integers")
    void testCreatePairIntegers() {
        TypeInferenceVarArgs.Pair<Integer, Integer> pair = typeInferenceVarArgs.createPair(10, 42);
        assertEquals(10, pair.getFirst());
        assertEquals(42, pair.getSecond());
    }

    @Test
    @DisplayName("Pair toString")
    void testPairToString() {
        TypeInferenceVarArgs.Pair<String, String> pair = typeInferenceVarArgs.createPair("hello", "world");
        assertEquals("Pair{hello, world}", pair.toString());
    }
}

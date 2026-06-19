package com.suprun.list_implementations;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * {@link ArrayList} is an implementation of {@link List} interface based on dynamic arrays.
 * This resizable data structure automatically grows when needed and is a simplified version
 * of {@link java.util.ArrayList}.
 *
 * Features:
 * - O(1) access time to elements by index
 * - O(n) insertion and deletion time (amortized O(1) for append)
 * - Automatic capacity expansion when needed
 *
 * @param <T> the type of elements in this list
 * @author Yurii_Suprun
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elements;
    private int size;

    /**
     * Creates an ArrayList with a specific initial capacity.
     *
     * @param initCapacity the initial capacity of the internal array
     * @throws IllegalArgumentException if the specified initial capacity is not positive
     */
    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        elements = new Object[initCapacity];
    }

    /**
     * Creates an ArrayList with a default capacity of 5 elements.
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates and returns a new ArrayList containing the provided elements.
     *
     * @param <T> the type of elements
     * @param elements the elements to add to the list
     * @return a new ArrayList containing all provided elements
     */
    public static <T> List<T> of(T... elements) {
        List<T> list = new ArrayList<>();
        Stream.of(elements)
                .forEach(list::add);
        return list;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element the element to add
     */
    @Override
    public void add(T element) {
        resizeIfNeeded();
        elements[size] = element;
        size++;
    }

    /**
     * Resizes the internal array if the current capacity is exceeded.
     * The new capacity is double the current capacity.
     */
    private void resizeIfNeeded() {
        if (size == elements.length) {
            Object[] newArray = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    /**
     * Adds an element at a specific index, shifting elements if necessary.
     *
     * @param index the index where the element should be inserted
     * @param element the element to add
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size + 1);
        resizeIfNeeded();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Retrieves an element by index.
     *
     * @param index the index of the element
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    /**
     * Returns the first element of the list in O(1) time.
     *
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return get(0);
    }

    /**
     * Returns the last element of the list in O(1) time.
     *
     * @return the last element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return get(size - 1);
    }

    /**
     * Replaces the element at a specific index.
     *
     * @param index the index of the element to replace
     * @param element the new element value
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void set(int index, T element) {
        Objects.checkIndex(index, size);
        elements[index] = element;
    }

    /**
     * Removes and returns the element at a specific index.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T element = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return element;
    }

    /**
     * Checks if the list contains a specific element.
     * Handles null values correctly.
     *
     * @param element the element to search for
     * @return {@code true} if the element exists, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the list is empty.
     *
     * @return {@code true} if the list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all elements from the list and resets to default capacity.
     */
    @Override
    public void clear() {
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }
}

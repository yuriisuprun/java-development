package com.suprun.list_implementations;

/**
 * List interface provides a generic contract for implementing list data structures.
 * This interface supports basic operations such as adding, removing, and accessing elements.
 *
 * @param <T> the type of elements in this list
 * @author Yurii_Suprun
 */
public interface List<T> {
    /**
     * Adds an element to the end of the list.
     *
     * @param element the element to add
     */
    void add(T element);

    /**
     * Adds an element at a specific index in the list.
     *
     * @param index the position to insert the element
     * @param element the element to add
     */
    void add(int index, T element);

    /**
     * Replaces the element at a specific index.
     *
     * @param index the position of the element to replace
     * @param element the new element value
     */
    void set(int index, T element);

    /**
     * Retrieves the element at a specific index.
     *
     * @param index the position of the element
     * @return the element at the specified index
     */
    T get(int index);

    /**
     * Retrieves the first element in the list.
     *
     * @return the first element
     */
    T getFirst();

    /**
     * Retrieves the last element in the list.
     *
     * @return the last element
     */
    T getLast();

    /**
     * Removes and returns the element at a specific index.
     *
     * @param index the position of the element to remove
     * @return the removed element
     */
    T remove(int index);

    /**
     * Checks if the list contains a specific element.
     *
     * @param element the element to search for
     * @return {@code true} if the element exists, {@code false} otherwise
     */
    boolean contains(T element);

    /**
     * Checks if the list is empty.
     *
     * @return {@code true} if the list is empty, {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the list.
     *
     * @return the size of the list
     */
    int size();

    /**
     * Removes all elements from the list.
     */
    void clear();
}

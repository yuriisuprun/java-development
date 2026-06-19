package com.suprun.list_implementations;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link LinkedList} is a list implementation based on singly linked generic nodes.
 * A node is implemented as a static inner class {@link Node}.
 *
 * Features:
 * - O(1) insertion and deletion at the beginning or end
 * - O(n) access time to elements by index
 * - Dynamic memory allocation
 *
 * @param <T> the type of elements in this list
 * @author Yurii_Suprun
 */
public class LinkedList<T> implements List<T> {
    /**
     * Node represents a single element in the linked list.
     *
     * @param <T> the type of element
     */
    static class Node<T> {
        T element;
        Node<T> next;

        /**
         * Creates a node with the specified element.
         *
         * @param element the element to store
         */
        public Node(T element) {
            this.element = element;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    /**
     * Creates a LinkedList with the provided elements.
     *
     * @param <T> the type of elements
     * @param elements the elements to add to the list
     * @return a new LinkedList containing all provided elements
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> list = new LinkedList<>();
        for (T element : elements) {
            list.add(element);
        }
        return list;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element the element to add
     */
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (first == null) {
            first = this.last = newNode;
        } else {
            this.last.next = newNode;
            last = newNode;
        }
        size++;
    }

    /**
     * Adds an element at a specific index.
     *
     * @param index the index where the element should be inserted
     * @param element the element to add
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size + 1);
        Node<T> newNode = new Node<>(element);
        if (first == null) {
            first = last = newNode;
        } else if (index == 0) {
            newNode.next = first;
            first = newNode;
        } else if (index == size) {
            last.next = newNode;
            last = newNode;
        } else {
            Node<T> previous = getNodeByIndex(index - 1);
            newNode.next = previous.next;
            previous.next = newNode;
        }
        size++;
    }

    /**
     * Retrieves the node at a specific index.
     *
     * @param index the index of the node
     * @return the node at the specified index
     */
    private Node<T> getNodeByIndex(int index) {
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
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
        Node<T> node = getNodeByIndex(index);
        node.element = element;
    }

    /**
     * Retrieves an element by index.
     *
     * @param index the index of the element
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return getNodeByIndex(index).element;
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
     * Removes and returns the element at a specific index.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedElement;
        if (index == 0) {
            removedElement = first.element;
            first = first.next;
            if (first == null) {
                last = null;
            }
        } else {
            Node<T> prev = getNodeByIndex(index - 1);
            removedElement = prev.next.element;
            prev.next = prev.next.next;
            if (index == size - 1) {
                last = prev;
            }
        }
        size--;
        return removedElement;
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
        Node<T> current = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (current.element == null) {
                    return true;
                }
                current = current.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (current.element != null && current.element.equals(element)) {
                    return true;
                }
                current = current.next;
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
        return first == null;
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
     * Removes all elements from the list.
     */
    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }
}

package main.java.ru.aston.myArrayList;

import java.util.Comparator;

/**
 * MyList is an interface that defines the basic actions of list based on dynamic array.
 *
 * @param <T> - type of stored elements
 */
public interface MyList<T> {

    /**
     * Adds element to the end of the list.
     *
     * @param element the element to be added
     */
    void add(T element);

    /**
     * Inserts element into the list at the given index.
     *
     * @param index   the index at which the specified element is to be inserted
     * @param element - element to be inserted
     */
    void add(int index, T element);

    /**
     * Replaces the element at the given index in the list with the other element.
     *
     * @param index - the index of the element to be replaced
     * @param element - the element to be stored at the given index
     * @return the previous element from the list at given position
     */
    T set(int index, T element);

    /**
     * Returns the element at the given position in the list.
     *
     * @param index - the index of the element to return
     * @return the element at the given index
     */
    T get(int index);

    /**
     * Removes the element at the given index in the list.
     *
     * @param index - the index of the element to be removed
     * @return the element that was removed
     */
    T remove(int index);

    /**
     * Removes all elements from the list.
     */
    void clear();

    /**
     * Sorts the list into ascending order using the natural ordering.
     */
    void sort();

    /**
     * Sorts the list according custom comparator.
     *
     * @param comparator - the comparator to determine the order of elements
     */
    void sort(Comparator<T> comparator);

    /**
     * Returns the number of elements in the list.
     *
     * @return - the number of elements
     */
    int size();
}
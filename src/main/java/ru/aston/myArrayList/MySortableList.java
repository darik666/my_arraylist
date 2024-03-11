package main.java.ru.aston.myArrayList;

import java.util.Comparator;

/**
 * MySortableList is an extension of MyList interface. Provides additional sorting methods.
 *
 * @param <T> - type of stored elements
 */
public interface MySortableList<T> extends MyList<T> {

    /**
     * Sorts the list in ascending order using the quicksort algorithm.
     */
    void quicksort();

    /**
     * Sorts the list according custom comparator using the quicksort algorithm.
     *
     * @param comparator - the comparator to determine the order of elements
     */
    void quicksort(Comparator<T> comparator);
}
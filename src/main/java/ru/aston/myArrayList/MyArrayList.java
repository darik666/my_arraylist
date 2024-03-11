package main.java.ru.aston.myArrayList;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MyArrayList is an implementation of a dynamic array list. Supports basic operations
 * such as adding, getting, setting, removing, sorting, and additional quicksort.
 *
 * @param <T> - type of stored elements
 */
public class MyArrayList<T> implements MySortableList<T> {

    /**
     * Default initial capacity for array.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Storage for elements.
     */
    private Object[] array;

    /**
     * Current size of the list.
     */
    private int size = 0;

    /**
     * Constructs empty list with default initial capacity.
     */
    public MyArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructs empty list with specified initial capacity.
     *
     * @param initialCapacity - initial capacity of the list
     * @throws IllegalArgumentException - in case of given negative initial capacity
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.array = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal initial capacity number: " + initialCapacity);
        }
    }

    /**
     * Adds element to the end of the list.
     *
     * @param element - element to be added
     */
    public void add(T element) {
        checkCapacity();
        array[size++] = element;
    }

    /**
     * Inserts element at the given index.
     *
     * @param index  - index at which the specified element is to be inserted
     * @param element - element to be inserted
     * @throws IndexOutOfBoundsException - in case of the index is out of range
     */
    public void add(int index, T element) {
        checkIndex(index);
        checkCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Replaces the element at the given position with other element.
     *
     * @param index  - index of the element to be replaced
     * @param element - element to be stored at the given position
     * @return the element previously at the given position
     * @throws IndexOutOfBoundsException - in case of the index is out of range
     */
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = (T) array[index];
        array[index] = element;
        return oldValue;
    }

    /**
     * Returns the element from the given index in the list.
     *
     * @param index - the index of the element to return
     * @return the element at the given position in the list
     * @throws IndexOutOfBoundsException - in case of the index is out of range
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    /**
     * Removes the element from the given index in the list.
     *
     * @param index - the index of the element to be removed
     * @return the element that was removed
     * @throws IndexOutOfBoundsException - in case of the index is out of range
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        T deleted = (T) array[index];
        checkIndex(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return deleted;
    }

    /**
     * Removes all elements from the list, leaving it empty.
     */
    public void clear() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Sorts the list into ascending order using the natural ordering.
     */
    public void sort() {
        Arrays.sort(array, 0, size);
    }

    /**
     * Sorts the list according to the custom comparator.
     *
     * @param comparator the comparator to determine the order of the list
     */
    @SuppressWarnings("unchecked")
    public void sort(Comparator<T> comparator) {
        Arrays.sort((T[]) array, 0, size, comparator);
    }

    /**
     * Checks if the given index is out of list range
     *
     * @param index - given index to check
     */
    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of lists size: " + size);
        }
    }

    /**
     * Checks if list is full and grows its size
     */
    private void checkCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 2);
        }
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements
     */
    public int size() {
        return size;
    }

    /**
     * Sorts the list using the quicksort algorithm.
     */
    @SuppressWarnings("unchecked")
    public void quicksort() {
        quicksort((T[]) array, 0, size - 1);
    }

    /**
     * Sorts the list using the quicksort algorithm with a custom comparator.
     *
     * @param comparator - the comparator to determine the order of elements
     */
    @SuppressWarnings("unchecked")
    public void quicksort(Comparator<T> comparator) {
        quicksort((T[]) array, 0, size - 1, comparator);
    }

    /**
     * Sorts the array using the quicksort algorithm.
     *
     * @param arr - array to be sorted.
     * @param from - starting index of the subarray to be sorted.
     * @param to - ending index of the subarray to be sorted.
     */
    private void quicksort(T[] arr, int from, int to) {
        if (from < to) {
            int divideIndex = partition(arr, from, to);
            quicksort(arr, from, divideIndex - 1);
            quicksort(arr, divideIndex, to);
        }
    }

    /**
     * Sorts the array using the quicksort algorithm with a comparator.
     *
     * @param arr - array to be sorted.
     * @param from - starting index of the subarray to be sorted.
     * @param to - ending index of the subarray to be sorted.
     * @param comparator - comparator to determine the order of the elements.
     */
    private void quicksort(T[] arr, int from, int to, Comparator<T> comparator) {
        if (from < to) {
            int divideIndex = partition(arr, from, to, comparator);
            quicksort(arr, from, divideIndex - 1, comparator);
            quicksort(arr, divideIndex, to, comparator);
        }
    }

    /**
     * Partitions the array for quicksort.
     *
     * @param arr   The array to be partitioned.
     * @param from  The starting index of the subarray to be partitioned.
     * @param to    The ending index of the subarray to be partitioned.
     * @return The index of the pivot element after partitioning.
     */
    @SuppressWarnings("unchecked")
    private int partition(T[] arr, int from, int to) {
        int rightIndex = to;
        int leftIndex = from;

        T pivot = arr[(from + to) / 2];
        while (leftIndex <= rightIndex) {
            while (((Comparable<T>) arr[leftIndex]).compareTo(pivot) < 0) {
                leftIndex++;
            }

            while (((Comparable<T>) arr[rightIndex]).compareTo(pivot) > 0) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                swap(arr, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    /**
     * Partitions the array for quicksort with a comparator.
     *
     * @param arr - array to be partitioned.
     * @param from - starting index of the subarray to be partitioned.
     * @param to - ending index of the subarray to be partitioned.
     * @param comparator - comparator to determine the order of the elements.
     * @return The index of the pivot element after partition.
     */
    private int partition(T[] arr, int from, int to, Comparator<T> comparator) {
        int rightIndex = to;
        int leftIndex = from;

        T pivot = arr[(from + to) / 2];
        while (leftIndex <= rightIndex) {
            while (comparator.compare(arr[leftIndex], pivot) < 0) {
                leftIndex++;
            }

            while (comparator.compare(arr[rightIndex], pivot) > 0) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                swap(arr, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    /**
     * Swaps two elements in the array.
     *
     * @param array - array in which elements are to be swapped.
     * @param index1 - index of the first element.
     * @param index2 - index of the second element.
     */
    private void swap(T[] array, int index1, int index2) {
        T tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }
}
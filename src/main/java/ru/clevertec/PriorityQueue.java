package ru.clevertec;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue<T> implements MyPriorityQueue<T> {
    private T[] heap;
    private int size;
    private Comparator<? super T> comparator;

    private static final int DEFAULT_CAPACITY = 8;

    public PriorityQueue() {
        this(DEFAULT_CAPACITY, null);
    }

    public PriorityQueue(Comparator<? super T> comparator) {
        this(DEFAULT_CAPACITY, comparator);
    }

    @SuppressWarnings("unchecked")
    private PriorityQueue(int capacity, Comparator<? super T> comparator) {
        this.heap = (T[]) new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(T element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        ensureCapacity();
        heap[size] = element;
        siftUp(size);
        size++;
    }

    @Override
    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException("Priority queue is empty");
        }
        return heap[0];
    }

    @Override
    public T poll() {
        if (size == 0) {
            return null;
        }
        return removeAt(0);
    }

    @Override
    public boolean remove(T element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(element)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    private T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        T removedValue = heap[index];
        size--;
        if (index < size) {
            heap[index] = heap[size];
            siftDown(index);
            if (heap[index] == removedValue) {
                siftUp(index);
            }
        }
        heap[size] = null;
        return removedValue;
    }

    private void ensureCapacity() {
        if (size >= heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    private void siftUp(int index) {
        T element = heap[index];
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            T parent = heap[parentIndex];
            if (compare(element, parent) >= 0) {
                break;
            }
            heap[index] = parent;
            index = parentIndex;
        }
        heap[index] = element;
    }

    private void siftDown(int index) {
        T element = heap[index];
        int half = size / 2;
        while (index < half) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            int smallestChild = leftChild;

            if (rightChild < size
                    && compare(heap[rightChild], heap[leftChild]) < 0) {
                smallestChild = rightChild;
            }

            if (compare(heap[smallestChild], element) >= 0) {
                break;
            }

            heap[index] = heap[smallestChild];
            index = smallestChild;
        }
        heap[index] = element;
    }

    @SuppressWarnings("unchecked")
    private int compare(T a, T b) {
        return comparator.compare(a, b);
    }
}

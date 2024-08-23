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
            throw new NoSuchElementException("Priority queue is empty");
        }
        T result = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return result;
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

            if (rightChild < size && compare(heap[rightChild], heap[leftChild]) < 0) {
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
        if (comparator != null) {
            return comparator.compare(a, b);
        } else if (a instanceof Comparable) {
            return ((Comparable<? super T>) a).compareTo(b);
        } else {
            throw new IllegalArgumentException("Elements must be comparable or a comparator must be provided");
        }
    }
}

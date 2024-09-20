package ru.clevertec;

public interface MyPriorityQueue<T> {

    void add(T element);

    T peek();

    T poll();

    boolean remove(T element);

    boolean isEmpty();

    int size();
}

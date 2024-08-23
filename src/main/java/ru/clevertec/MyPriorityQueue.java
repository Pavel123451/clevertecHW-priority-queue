package ru.clevertec;

public interface MyPriorityQueue<T> {

    void add(T element);

    T peek();

    T poll();
}

package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements deque.Deque<T>, Iterable<T> {
    private static final int INIT_CAPACITY = 10;
    private static final double INCREASE_FACTOR = 1.3;
    private static final int DECREASE_FACTOR = 4;


    private class ADequeIterator implements Iterator<T> {
        private int index;

        public ADequeIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            return get(index++);
        }
    }
    private T[] items;
    private int head;
    private int tail;
    private int size;
    private int total;

    public ArrayDeque() {
        total = INIT_CAPACITY;
        items = (T[]) new Object[total];
        head = 0;
        tail = head + 1;
        size = 0;
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i + 1] = items[(head + 1) % total];
            head = (head + 1) % total;
        }
        total = capacity;
        items = newArray;
        head = 0;
        tail = size + 1;
    }

    @Override
    public void addFirst(T item) {
        if (head == tail) {
            resize((int) Math.round(total * INCREASE_FACTOR));
        }
        items[head] = item;
        head = (head - 1 + total) % total;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (head == tail) {
            resize((int) Math.round(total * INCREASE_FACTOR));
        }
        items[tail] = item;
        tail = (tail + 1) % total;
        size++;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {

    }

    @Override
    public T removeFirst() {

        if (size == 0) {
            return null;
        }
        if (size < Math.round(total / DECREASE_FACTOR)) {
            resize((int) Math.round(total / DECREASE_FACTOR) + 2);
        }
        T tmp = items[(head + 1) % total];
        items[(head + 1) % total] = null;
        head = (head + 1) % total;
        size--;
        return tmp;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size < Math.round(total / DECREASE_FACTOR)) {
            resize((int) Math.round(total / DECREASE_FACTOR));
        }
        T tmp = items[(tail - 1 + total) % total];
        items[(tail - 1 + total) % total] = null;
        tail = (tail - 1 + total) % total;
        size--;
        return tmp;
    }

    @Override
    public T get(int index) {
        return items[(head + 1 + index) % total];
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        if (((Deque<?>) o).size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(((Deque<?>) o).get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new ADequeIterator();
    }
}

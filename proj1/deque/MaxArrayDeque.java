package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> implements Deque<T>, Iterable<T>{
    private static final int INIT_CAPACITY = 100;
    private static final double RESIZE_FACTOR = 1.5;

    private class MADequeIterator implements Iterator<T> {
        private int index;

        public MADequeIterator() {
            this.index = head;
        }

        @Override
        public boolean hasNext() {
            return (index + 1) % total != tail;
        }

        @Override
        public T next() {
            T tmp = items[(++index) % total];
            return tmp;
        }
    }
    private T[] items;
    private int head;
    private int tail;
    private int size;
    private int total;
    private Comparator<T> comparator;

    public MaxArrayDeque() {
        this(null);
    }

    public MaxArrayDeque(Comparator<T> c) {
        total = INIT_CAPACITY;
        items = (T[]) new Object[total];
        head = 0;
        tail = head + 1;
        size = 0;
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T max = get(0);
        for (int i = 0; i < size; i++) {
            T cur = get(i);
            if (c.compare(max, cur) < 0) {
                max = cur;
            }
        }
        return max;
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < total; i++) {
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
            resize((int) Math.round(total * RESIZE_FACTOR));
        }
        items[head] = item;
        head = (head - 1 + total) % total;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (head == tail) {
            resize((int) Math.round(total * RESIZE_FACTOR));
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
        if (! (o instanceof Deque)) {
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
        return new MADequeIterator();
    }
}

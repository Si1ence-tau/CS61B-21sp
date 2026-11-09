package deque;

public class ArrayDeque<T> implements Deque<T> {
    private static final int INIT_CAPACITY = 100;
    private static final double RESIZE_FACTOR = 1.5;

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
        for (int i = 0; i < total; i++) {
            newArray[i] = items[(head + 1) % total];
            head = (head + 1) % total;
        }
        total = capacity;
        items = newArray;
        head = 0;
        tail = size - 1;
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
    public boolean isEmpty() {
        return size == 0;
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
        T tmp = items[(head + 1) % total];
        items[(head + 1) % total] = null;
        head = (head + 1) % total;
        return tmp;
    }

    @Override
    public T removeLast() {
        T tmp = items[(tail - 1 + total) % total];
        items[(tail - 1 + total) % total] = null;
        tail = (tail - 1 + total) % total;
        return tmp;
    }

    @Override
    public T get(int index) {
        return items[(head + 1 + index) % total];
    }
}

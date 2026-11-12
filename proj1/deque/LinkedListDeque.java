package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class LLDequeIterator implements Iterator<T> {
        Node index;

        public LLDequeIterator() {
            index = sentinel;
        }

        @Override
        public boolean hasNext() {
            return index.next != sentinel;
        }

        @Override
        public T next() {
            index = index.next;
            T tmp = index.item;
            return tmp;
        }
    }
    private class Node {
        private T item;
        private Node next;
        private Node previous;

        private Node() {
            item = null;
            next = this;
            previous = this;
        }
    }
    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node();
    }

    public T getRecursive(int index) {
        return helperGetRecursive(index, sentinel.next);
    }

    private T helperGetRecursive(int index, Node start) {
        if (index == 0) {
            return start.item;
        }
        return helperGetRecursive(--index, start.next);
    }

    @Override
    public void addFirst(T item) {
        Node newMember = new Node();
        newMember.item = item;
        newMember.next = sentinel.next;
        newMember.previous = sentinel;
        sentinel.next = newMember;
        newMember.next.previous = newMember;
        size++;
    }
    @Override
    public void addLast(T item) {
        Node newMember = new Node();
        newMember.item = item;
        newMember.next = sentinel;
        newMember.previous = sentinel.previous;
        sentinel.previous.next = newMember;
        sentinel.previous = newMember;
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
        Node removed = sentinel.next;
        removed.next.previous = removed.previous;
        removed.previous.next = removed.next;
        if (size > 0) {
            size--;
        }
        return removed.item;
    }

    @Override
    public T removeLast() {
        Node removed = sentinel.previous;
        removed.next.previous = removed.previous;
        removed.previous.next = removed.next;
        if (size > 0) {
            size--;
        }
        return removed.item;
    }

    @Override
    public T get(int index) {
        Node tmp = sentinel;
        for(int i = 0; i < index + 1; i++) {
            if (tmp != null) {
                tmp = tmp.next;
            } else {
                return null;
            }
        }
        return tmp.item;
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
        return new LLDequeIterator();
    }
}

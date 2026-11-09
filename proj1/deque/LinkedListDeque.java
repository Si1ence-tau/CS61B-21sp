package deque;

public class LinkedListDeque<T> implements Deque<T> {
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
    public boolean isEmpty() {
        return (size == 0);
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
}

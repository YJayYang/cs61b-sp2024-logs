package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jay
 */
public class LinkedListDeque61B<T> implements Deque61B<T> {


    private class Node {

        private T items;
        private Node next;
        private Node previous;

        public Node(Node prev, T x, Node n) {
            items = x;
            next = n;
            previous = prev;
        }
        T getHelper(int m) {
            if (m == 0) {
                return items;
            } else {
                return next.getHelper(m - 1);
            }
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        size = 0;
    }
    @Override
    public void addFirst(T x) {

        Node newNode = new Node(sentinel, x, sentinel.previous);
        if (sentinel.previous == sentinel) {

            sentinel.previous = newNode;
            sentinel.next = newNode;
            size += 1;
            return;

        }
        sentinel.next = newNode;
        sentinel.next.previous = newNode;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(sentinel.previous, x, sentinel);
        if  (sentinel.next == sentinel) {
            addFirst(x);
            return;
        }

        sentinel.previous.next = newNode;
        sentinel.previous = newNode;
        size += 1;

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node current = sentinel.next;
        while (current != sentinel) {
            returnList.add(current.items);
            current = current.next;
        }
        return (List<T>) returnList;
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
    public T removeFirst() {
        Node p = sentinel.next;
        if (p == sentinel) {
            return null;
        } else if (p.next == sentinel){
            sentinel.next = sentinel;
            sentinel.previous = sentinel;
            size = 0;
            return p.items;
        }
        sentinel.next = p.next;
        p.next.previous = sentinel;
        size -= 1;
        return p.items;

    }

    @Override
    public T removeLast() {
        Node p = sentinel.previous;
        if (p == sentinel) {
            return null;
        } else if (p.previous == sentinel){
            removeFirst();
            return p.items;
        }
        sentinel.previous = p.previous;
        p.previous.next = sentinel;
        size -= 1;
        return p.items;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative");
        }
        Node currentNode = sentinel.next;
        int currentIndex = 0;
        while (currentNode != sentinel && currentIndex < index) {
            currentNode = currentNode.next;
            currentIndex++;
        }

        // Check if we reached the end of the list without finding the index
        if (currentNode == sentinel || currentIndex != index) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        return currentNode.items;

    }

    @Override
    public T getRecursive(int index) {return sentinel.next.getHelper(index);}

    @Override
    public Iterator<T> iterator() {
        return new LinkSetIterator();
    }

    private class LinkSetIterator implements Iterator<T> {
        private Node p;

        public LinkSetIterator() {
            Node p = sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return p != sentinel;
        }
        @Override
        public T next() {
            T returnItem = p.items;
            p = p.next;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof LinkedListDeque61B<?>)) {
            return false;
        }

        LinkedListDeque61B<?> other = (LinkedListDeque61B<?>) o;

        if (this.size() != other.size()) {
            return false;
        }

        return this.toString().equals(other.toString());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
}

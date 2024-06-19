import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private class Node {
        private T item;
        private Node Previous;
        private Node next;

        public Node(Node Prev, T x, Node n) {
            item = x;
            Previous = Prev;
            next = n;
        }

    }

    private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.Previous = sentinel;
        size = 0;
    }


    @Override
    public void addFirst(T x) {
        Node newNode = new Node(sentinel, x, sentinel.next);
        if (sentinel.next == sentinel) {
            sentinel.next = newNode;
            sentinel.Previous = newNode;
            return;
        }
        sentinel.next = newNode;
        size += 1;

    }

    @Override
    public void addLast(T x) {
        if (sentinel.next == sentinel) {
            addFirst(x);
            return;
        }
        Node p = new Node(sentinel.Previous, x, sentinel);
        p.Previous.next = p;
        sentinel.Previous = p;
        size += 1;

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node currentNode = sentinel.next;

        while (currentNode != sentinel) {
            returnList.add(currentNode.item);
            currentNode = currentNode.next;
        }




        return (List<T>) returnList;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}

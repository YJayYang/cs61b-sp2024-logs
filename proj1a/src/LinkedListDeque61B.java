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
          T getHelper(int m) {
            if (m == 0) {
                return item;
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
        sentinel.Previous = sentinel;
        size = 0;
    }


    @Override
    public void addFirst(T x) {
        Node newNode = new Node(sentinel, x, sentinel.next);
        if (sentinel.next == sentinel) {
            sentinel.next = newNode;
            sentinel.Previous = newNode;
            size += 1;
            return;
        }
        sentinel.next = newNode;
        newNode.next.Previous = newNode;
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
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        Node p = sentinel.next;
        if (p == null) {
            return null;
        }else if (p.next == sentinel){
            sentinel.next = sentinel;
            sentinel.Previous = sentinel;
            size -= 1;
            return p.item;
        }

        sentinel.next = p.next;
        sentinel.next.Previous = sentinel;
        size -= 1;
        return p.item;
    }

    @Override
    public T removeLast() {
        Node p = sentinel.Previous;
        if (p == null) {
            return null;
        }else if (sentinel.next == sentinel){
            removeFirst();
            return p.item;
        }
        sentinel.Previous = p.Previous;
        p.Previous.next = sentinel;
        size -= 1;
        return p.item;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
        throw new IndexOutOfBoundsException("Index cannot be negative");
    }

    Node currentNode = sentinel.next;
    int currentIndex = 0;

    // Traverse the list to the specified index
    while (currentNode != sentinel && currentIndex < index) {
        currentNode = currentNode.next;
        currentIndex++;
    }

    // Check if we reached the end of the list without finding the index
    if (currentNode == sentinel || currentIndex != index) {
        throw new IndexOutOfBoundsException("Index out of bounds");
    }

    return currentNode.item;
    }



    @Override
    public T getRecursive(int index) {
        return sentinel.next.getHelper(index);
    }
}
